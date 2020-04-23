package co.com.santander.chatbot.backend.web.common.aspect.log;

import co.com.santander.chatbot.domain.common.utilities.GenericLog;
import co.com.santander.chatbot.domain.enums.ServiciosEnum;
import lombok.extern.java.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Log
@Aspect
@Component
public class BussinesLogAspect {

    private GenericLog genericLog;

    @Autowired
    public BussinesLogAspect() {
    }

    @Around("@annotation( co.com.santander.chatbot.backend.web.common.aspect.log.BussinessLog)")
    public Object generateLog(ProceedingJoinPoint joinPoint) throws Throwable {
        mapperArgs(joinPoint.getArgs());
        log.info(genericLog.toString());
        return joinPoint.proceed();
    }

    public void mapperArgs(Object[] args){
        genericLog = GenericLog.builder()
                .token((String) args[0])
                .serviciosEnum((ServiciosEnum) args[1])
                .telefono((String) args[2])
                .credito(validateNumCreditByService((ServiciosEnum) args[1], args))
                .build();
    }

    public String validateNumCreditByService(ServiciosEnum serviciosEnum, Object[] args){
        if(ServiciosEnum.SERVICIO_ENLACE_PSE.equals(serviciosEnum)){
            return (String) args[3];
        }
        return null;
    }
}
