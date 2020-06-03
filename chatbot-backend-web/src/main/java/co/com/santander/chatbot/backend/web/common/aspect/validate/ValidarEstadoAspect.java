package co.com.santander.chatbot.backend.web.common.aspect.validate;

import co.com.santander.chatbot.backend.web.service.ValidarProcesoService;
import co.com.santander.chatbot.domain.validators.exceptions.ValidateStateCertificateException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(1)
@Component
public class ValidarEstadoAspect {

    private final ValidarProcesoService validarProcesoService;

    @Autowired
    public ValidarEstadoAspect(ValidarProcesoService validarProcesoService) {
        this.validarProcesoService = validarProcesoService;
    }

    @Around("@annotation( co.com.santander.chatbot.backend.web.common.aspect.validate.ValidateState)")
    public Object validarEstado(ProceedingJoinPoint joinPoint) throws Throwable {
        Boolean valida = validarProcesoService.validateExistingProcesss(joinPoint.getArgs());
        if (Boolean.TRUE.equals(valida))
            return joinPoint.proceed();
        throw new ValidateStateCertificateException("Ya existe una solicitud en curso, por favor espere un momento.", 0L);
    }


}
