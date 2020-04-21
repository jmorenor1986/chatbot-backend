package co.com.santander.chatbot.backend.web.common.aspect.validate;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ValidarEstadoAspect {
    @Around("@annotation( co.com.santander.chatbot.backend.web.common.aspect.validate.ValidarEstado)")
    public Object validarEstado(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println(joinPoint.getArgs());
        final Object proceed = joinPoint.proceed();
        return proceed;
    }
}
