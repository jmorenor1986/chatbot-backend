package co.com.santander.chatbot.backend.web.service.impl;

import co.com.santander.chatbot.acceso.recursos.clients.core.ClienteClient;
import co.com.santander.chatbot.acceso.recursos.clients.core.InfoWhatsAppWSClient;
import co.com.santander.chatbot.acceso.recursos.clients.core.ParametrosServiceClient;
import co.com.santander.chatbot.backend.web.common.utilities.DateUtilities;
import co.com.santander.chatbot.backend.web.common.utilities.SecurityUtilities;
import co.com.santander.chatbot.backend.web.common.utilities.StringUtilities;
import co.com.santander.chatbot.backend.web.service.ValidarProcesoService;
import co.com.santander.chatbot.domain.enums.ServiciosEnum;
import co.com.santander.chatbot.domain.payload.accesodatos.InfoWhatsAppWSPayload;
import co.com.santander.chatbot.domain.payload.accesodatos.ResponsePayload;
import co.com.santander.chatbot.domain.payload.accesodatos.ValidarProcesoPayload;
import co.com.santander.chatbot.domain.payload.accesodatos.cliente.ClienteViewPayload;
import co.com.santander.chatbot.domain.payload.service.certificados.CertificadoPayload;
import co.com.santander.chatbot.domain.validators.exceptions.ValidateStateCertificateException;
import co.com.santander.chatbot.domain.validators.exceptions.ValidateStatusAfterProcess;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.GeneralSecurityException;
import java.util.Calendar;
import java.util.Date;

@Log @Getter @Setter
@Service
public class ValidarProcesoServiceImpl implements ValidarProcesoService {

    public static final String ERROR_CONSUMO_SERVICE = "Error al consultar los datos";
    public static final String ERROR_CIPHER = "Número de credito invalido";
    public static final String ERROR_SOLICITUD_CURSO = "Ya existe una solicitud pendiente en curso";
    private String token;
    private String credito;
    private String identificacion;
    private ServiciosEnum serviciosEnum;
    private ClienteViewPayload cliente;
    private Long numPeticionServicio;

    private Long minutosRestantes;

    private final InfoWhatsAppWSClient infoWhatsAppWSClient;
    private final ParametrosServiceClient parametrosServiceClient;
    private final ClienteClient clienteClient;

    @Autowired
    public ValidarProcesoServiceImpl(InfoWhatsAppWSClient infoWhatsAppWSClient, ParametrosServiceClient parametrosServiceClient, ClienteClient clienteClient) {
        this.infoWhatsAppWSClient = infoWhatsAppWSClient;
        this.parametrosServiceClient = parametrosServiceClient;
        this.clienteClient = clienteClient;
    }

    @Override
    public Boolean validateExistingProcesss(Object[] args) {
        getParams(args);
        if(findClient()) {
            ResponseEntity<InfoWhatsAppWSPayload>  result = infoWhatsAppWSClient.validateExistingProcess(token, getCredito(), getIdentificacion(), getNumPeticionServicio());
            if (result.getStatusCodeValue() == 200)
                if (result.getBody().getEstado() == 0) {
                    return validateProcessWithParams(getToken(), result.getBody().getFechaEnvio(), serviciosEnum.getMessage());
                } else {
                    return Boolean.TRUE;
                }
            if (result.getStatusCodeValue() == 204)
                return Boolean.TRUE;
            throw new ValidateStateCertificateException(ERROR_CONSUMO_SERVICE, 0L);
        }
        return Boolean.FALSE;
    }

    private Boolean findClient(){
        ResponseEntity<ClienteViewPayload> response =  clienteClient.getClientByCedulaAndNumCredito(getToken(), getIdentificacion(), getCredito());
        if(HttpStatus.OK.equals(response.getStatusCode())){
            setCliente(response.getBody());
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    private Boolean validateProcessWithParams(String token, Date fechaEnvio, String servicio) {
        ValidarProcesoPayload valida = ValidarProcesoPayload.builder()
                .fechaUltimaSolicitud(fechaEnvio)
                .servicio(servicio)
                .canal("WhatsApp")
                .build();
        ResponseEntity<ResponsePayload> resultProcessWithParams = parametrosServiceClient.consultarProcesoParametros(token, valida);
        if (resultProcessWithParams.getStatusCodeValue() == 200)
            if (resultProcessWithParams.getBody().getResultadoValidacion() == Boolean.TRUE) {
                return Boolean.TRUE;
            } else {
                Long minutos = DateUtilities.generateDifferenceDates(fechaEnvio, new Date());
                throw new ValidateStatusAfterProcess(resultProcessWithParams.getBody().getDescripcionRespuesta().toString(), StringUtilities.ofuscarCorreo(cliente.getEmail(), 5) , StringUtilities.ofuscarCredito(getCredito()), cliente.getConvenio(), minutos);
            }
        throw new ValidateStateCertificateException(ERROR_CONSUMO_SERVICE, 0L);
    }

    private void getParams(Object[] args){
        setToken((String) args[0]);
        setServiciosEnum((ServiciosEnum) args[1]);
        setNumPeticionServicio((Long) args[4]);
        if (args[2] instanceof CertificadoPayload) {
            CertificadoPayload data = (CertificadoPayload) args[2];
            setIdentificacion(data.getIdentificacion());
            setCredito(data.getNumeroCredito());
        }
        try {
            setCredito(SecurityUtilities.desencriptar(getCredito()));
        } catch (Exception e) {
            log.severe(e.getMessage());
        }
    }
}
