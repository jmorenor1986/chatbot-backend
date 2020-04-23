package co.com.santander.chatbot.backend.web.common.aspect.validate;

import co.com.santander.chatbot.acceso.recursos.clients.core.InfoWhatsAppWSClient;
import co.com.santander.chatbot.acceso.recursos.clients.core.ParametrosServiceClient;
import co.com.santander.chatbot.backend.web.common.utilities.SecurityUtilities;
import co.com.santander.chatbot.domain.enums.ServiciosEnum;
import co.com.santander.chatbot.domain.payload.accesodatos.InfoWhatsAppWSPayload;
import co.com.santander.chatbot.domain.payload.accesodatos.ResponsePayload;
import co.com.santander.chatbot.domain.payload.accesodatos.ValidarProcesoPayload;
import co.com.santander.chatbot.domain.payload.service.certificados.CertificadoPayload;
import co.com.santander.chatbot.domain.validators.exceptions.ValidateStateCertificateException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.security.GeneralSecurityException;
import java.util.Date;

@Aspect
@Component
public class ValidarEstadoAspect {

    private final InfoWhatsAppWSClient infoWhatsAppWSClient;
    private final ParametrosServiceClient parametrosServiceClient;

    @Autowired
    public ValidarEstadoAspect(InfoWhatsAppWSClient infoWhatsAppWSClient, ParametrosServiceClient parametrosServiceClient) {
        this.infoWhatsAppWSClient = infoWhatsAppWSClient;
        this.parametrosServiceClient = parametrosServiceClient;
    }

    @Around("@annotation( co.com.santander.chatbot.backend.web.common.aspect.validate.ValidateState)")
    public Object validarEstado(ProceedingJoinPoint joinPoint) throws Throwable {
        if (validateExistingProcesss(joinPoint.getArgs()))
            return joinPoint.proceed();
        throw new ValidateStateCertificateException("Ya existe una solicitud en curso, por favor espere un momento.");
    }

    private Boolean validateExistingProcesss(Object[] args) {
        CertificadoPayload datos = (CertificadoPayload) args[1];
        ResponseEntity<InfoWhatsAppWSPayload> result = null;
        ServiciosEnum serviciosEnum = (ServiciosEnum) args[2];
        try {
            result = infoWhatsAppWSClient.validateExistingProcess((String) args[0], SecurityUtilities.desencriptar(datos.getNumeroCredito()), datos.getIdentificacion(), datos.getNumeroPeticion());
        } catch (GeneralSecurityException e) {
            throw new ValidateStateCertificateException("Error con los datos ingresados");
        }
        if (result.getStatusCodeValue() == 200)
            if (result.getBody().getEstado() == 0)
                return validateProcessWithParams(result.getBody().getFechaEnvio(), args[0].toString(), serviciosEnum.getMessage());
            else
                throw new ValidateStateCertificateException("Ya existe una solicitud en curso");
        throw new ValidateStateCertificateException("Error al consultar los datos");


    }

    private Boolean validateProcessWithParams(Date fechaEnvio, String token, String servicio) {
        ResponseEntity<ResponsePayload> resultProcessWithParams = parametrosServiceClient.consultarProcesoParametros(token,ValidarProcesoPayload.builder()
                .fechaUltimaSolicitud(fechaEnvio)
                .servicio(servicio)
                .canal("WhatsApp")
                .build());
        if (resultProcessWithParams.getStatusCodeValue() == 200)
            if (resultProcessWithParams.getBody().getResultadoValidacion() == Boolean.TRUE)
                return Boolean.TRUE;
            else
                throw new ValidateStateCertificateException(resultProcessWithParams.getBody().getDescripcionRespuesta().toString());
        throw new ValidateStateCertificateException("Error al consultar los datos");

    }


}
