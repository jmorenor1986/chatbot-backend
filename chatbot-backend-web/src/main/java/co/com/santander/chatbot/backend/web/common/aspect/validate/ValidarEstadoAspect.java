package co.com.santander.chatbot.backend.web.common.aspect.validate;

import co.com.santander.chatbot.acceso.recursos.clients.core.InfoWhatsAppWSClient;
import co.com.santander.chatbot.domain.validators.exceptions.ValidateStateCertificateException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ValidarEstadoAspect {

    private final InfoWhatsAppWSClient infoWhatsAppWSClient;

    @Autowired
    public ValidarEstadoAspect(InfoWhatsAppWSClient infoWhatsAppWSClient) {
        this.infoWhatsAppWSClient = infoWhatsAppWSClient;
    }

    @Around("@annotation( co.com.santander.chatbot.backend.web.common.aspect.validate.ValidateState)")
    public Object validarEstado(ProceedingJoinPoint joinPoint) throws Throwable {
        if (validateExistingProcesss(joinPoint.getArgs()))
            throw new ValidateStateCertificateException("Ya existe una solicitud en curso, por favor espere un momento.");
        return joinPoint.proceed();

    }

    private Boolean validateExistingProcesss(Object[] args) {
        DatosTest datos = (DatosTest) args[1];
        ResponseEntity<Boolean> result = infoWhatsAppWSClient.validateExistingProcess((String) args[0], datos.getNumeroCredito(), datos.getIdentificacion(), datos.getNumeroPeticion());
        return result.getBody();
    }
}
