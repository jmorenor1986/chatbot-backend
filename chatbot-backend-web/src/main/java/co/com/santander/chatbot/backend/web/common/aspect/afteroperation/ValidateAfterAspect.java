package co.com.santander.chatbot.backend.web.common.aspect.afteroperation;

import co.com.santander.chatbot.backend.web.service.ValidateProcessAfterService;
import co.com.santander.chatbot.domain.validators.exceptions.ValidateStateCertificateException;
import lombok.extern.java.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Log
@Aspect
@Order(20)
@Component
public class ValidateAfterAspect {

    private final ValidateProcessAfterService validateProcessAfterService;
    @Autowired
    public ValidateAfterAspect(ValidateProcessAfterService validateProcessAfterService) {
        this.validateProcessAfterService = validateProcessAfterService;
    }

    @Around("@annotation(co.com.santander.chatbot.backend.web.common.aspect.afteroperation.ValidateAfter)")
    public Object generateLog(ProceedingJoinPoint joinPoint) throws Throwable {
        Boolean valida = validateProcessAfterService.validateExistingAfterProcess(joinPoint.getArgs());
        if( Boolean.TRUE.equals(valida) ){
            return joinPoint.proceed();
        }
        throw new ValidateStateCertificateException("Validacion errornea.", 0L);
    }
}
