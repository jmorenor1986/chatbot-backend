package co.com.santander.chatbot.backend.web.service.impl;

import co.com.santander.chatbot.acceso.recursos.clients.core.InfoWhatsAppWSClient;
import co.com.santander.chatbot.acceso.recursos.clients.core.ParametrosServiceClient;
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
import java.util.Date;

@Service
public class ValidarProcesoServiceImpl implements ValidarProcesoService {

    public static final String ERROR_CONSUMO_SERVICE = "Error al consultar los datos";
    public static final String ERROR_CIPHER = "Número de credito invalido";
    public static final String ERROR_SOLICITUD_CURSO = "Ya existe una solicitud en curso";
    private final InfoWhatsAppWSClient infoWhatsAppWSClient;
    private final ParametrosServiceClient parametrosServiceClient;

    @Autowired
    public ValidarProcesoServiceImpl(InfoWhatsAppWSClient infoWhatsAppWSClient, ParametrosServiceClient parametrosServiceClient) {
        this.infoWhatsAppWSClient = infoWhatsAppWSClient;
        this.parametrosServiceClient = parametrosServiceClient;
    }

    @Override
    public Boolean validateExistingProcesss(Object[] args) {
        CertificadoPayload datos = (CertificadoPayload) args[2];
        ResponseEntity<InfoWhatsAppWSPayload> result = null;
        ServiciosEnum serviciosEnum = (ServiciosEnum) args[1];
        try {
            result = infoWhatsAppWSClient.validateExistingProcess((String) args[0], SecurityUtilities.desencriptar(datos.getNumeroCredito()), datos.getIdentificacion(), (Long) args[4]);
        } catch (GeneralSecurityException e) {
            throw new ValidateStateCertificateException(ERROR_CIPHER);
        }
        if (result.getStatusCodeValue() == 200)
            if (result.getBody().getEstado() == 1)
                return validateProcessWithParams(result.getBody().getFechaEnvio(), args[0].toString(), serviciosEnum.getMessage());
            else
                throw new ValidateStateCertificateException(ERROR_SOLICITUD_CURSO);

        if (result.getStatusCodeValue() == 204)
            return Boolean.TRUE;
        throw new ValidateStateCertificateException(ERROR_CONSUMO_SERVICE);
    }

    private Boolean validateProcessWithParams(Date fechaEnvio, String token, String servicio) {
        ResponseEntity<ResponsePayload> resultProcessWithParams = parametrosServiceClient.consultarProcesoParametros(token, ValidarProcesoPayload.builder()
                .fechaUltimaSolicitud(fechaEnvio)
                .servicio(servicio)
                .canal("WhatsApp")
                .build());
        if (resultProcessWithParams.getStatusCodeValue() == 200)
            if (resultProcessWithParams.getBody().getResultadoValidacion() == Boolean.TRUE)
                return Boolean.TRUE;
            else
                throw new ValidateStateCertificateException(resultProcessWithParams.getBody().getDescripcionRespuesta().toString());
        throw new ValidateStateCertificateException(ERROR_CONSUMO_SERVICE);

    }
}
