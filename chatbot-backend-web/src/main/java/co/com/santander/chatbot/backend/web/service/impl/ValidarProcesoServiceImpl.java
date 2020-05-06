package co.com.santander.chatbot.backend.web.service.impl;

import co.com.santander.chatbot.acceso.recursos.clients.core.InfoWhatsAppWSClient;
import co.com.santander.chatbot.acceso.recursos.clients.core.ParametrosServiceClient;
import co.com.santander.chatbot.backend.web.common.utilities.DateUtilities;
import co.com.santander.chatbot.backend.web.common.utilities.SecurityUtilities;
import co.com.santander.chatbot.backend.web.service.ValidarProcesoService;
import co.com.santander.chatbot.domain.enums.ServiciosEnum;
import co.com.santander.chatbot.domain.payload.accesodatos.InfoWhatsAppWSPayload;
import co.com.santander.chatbot.domain.payload.accesodatos.ResponsePayload;
import co.com.santander.chatbot.domain.payload.accesodatos.ValidarProcesoPayload;
import co.com.santander.chatbot.domain.payload.service.certificados.CertificadoPayload;
import co.com.santander.chatbot.domain.validators.exceptions.ValidateStateCertificateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.GeneralSecurityException;
import java.util.Calendar;
import java.util.Date;

@Service
public class ValidarProcesoServiceImpl implements ValidarProcesoService {

    public static final String ERROR_CONSUMO_SERVICE = "Error al consultar los datos";
    public static final String ERROR_CIPHER = "Número de credito invalido";
    public static final String ERROR_SOLICITUD_CURSO = "Ya existe una solicitud pendiente en curso";
    private final InfoWhatsAppWSClient infoWhatsAppWSClient;
    private final ParametrosServiceClient parametrosServiceClient;

    @Autowired
    public ValidarProcesoServiceImpl(InfoWhatsAppWSClient infoWhatsAppWSClient, ParametrosServiceClient parametrosServiceClient) {
        this.infoWhatsAppWSClient = infoWhatsAppWSClient;
        this.parametrosServiceClient = parametrosServiceClient;
    }

    @Override
    public Boolean validateExistingProcesss(Object[] args) {
        String token = (String) args[0];
        ServiciosEnum serviciosEnum = (ServiciosEnum) args[1];
        CertificadoPayload datos = (CertificadoPayload) args[2];
        ResponseEntity<InfoWhatsAppWSPayload> result = null;
        try {
            result = infoWhatsAppWSClient.validateExistingProcess(token, SecurityUtilities.desencriptar(datos.getNumeroCredito()), datos.getIdentificacion(), (Long) args[4]);
        } catch (GeneralSecurityException e) {
            throw new ValidateStateCertificateException(ERROR_CIPHER, 0L);
        }
        if (result.getStatusCodeValue() == 200)
            if (result.getBody().getEstado() == 0) {
                return validateProcessWithParams(result.getBody().getFechaEnvio(), args[0].toString(), serviciosEnum.getMessage());
            } else {
                return Boolean.TRUE;
            }
        if (result.getStatusCodeValue() == 204)
            return Boolean.TRUE;
        throw new ValidateStateCertificateException(ERROR_CONSUMO_SERVICE, 0L);
    }

    private Boolean validateProcessWithParams(Date fechaEnvio, String token, String servicio) {
        ValidarProcesoPayload valida = ValidarProcesoPayload.builder()
                .fechaUltimaSolicitud(fechaEnvio)
                .servicio(servicio)
                .canal("WhatsApp")
                .build();
        ResponseEntity<ResponsePayload> resultProcessWithParams = parametrosServiceClient.consultarProcesoParametros(token, valida );
        if (resultProcessWithParams.getStatusCodeValue() == 200)
            if (resultProcessWithParams.getBody().getResultadoValidacion() == Boolean.TRUE){
                return Boolean.TRUE;
            }else{
                Long minutos = DateUtilities.generateDifferenceDates(fechaEnvio, new Date());
                throw new ValidateStateCertificateException(resultProcessWithParams.getBody().getDescripcionRespuesta().toString(), minutos);
            }
        throw new ValidateStateCertificateException(ERROR_CONSUMO_SERVICE, 0L);
    }
}
