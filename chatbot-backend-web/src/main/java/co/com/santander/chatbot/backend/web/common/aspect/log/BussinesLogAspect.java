package co.com.santander.chatbot.backend.web.common.aspect.log;

import co.com.santander.chatbot.acceso.recursos.clients.core.LogCliente;
import co.com.santander.chatbot.backend.web.common.utilities.SecurityUtilities;
import co.com.santander.chatbot.domain.common.utilities.GenericLogPayload;
import co.com.santander.chatbot.domain.enums.ServiciosEnum;
import co.com.santander.chatbot.domain.payload.service.certificados.CertificadoPayload;
import com.google.gson.Gson;
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

    private GenericLogPayload genericLog;
    private final LogCliente logCliente;
    private String token;

    @Autowired
    public BussinesLogAspect(LogCliente logCliente) {
        this.logCliente = logCliente;
    }

    @Around("@annotation( co.com.santander.chatbot.backend.web.common.aspect.log.BussinessLog)")
    public Object generateLog(ProceedingJoinPoint joinPoint) throws Throwable {
        mapperArgs(joinPoint.getArgs());
        log.info(genericLog.toString());
        Object response = joinPoint.proceed();
        genericLog.setTraza(new Gson().toJson(response));
        token = genericLog.getToken();
        genericLog.setToken("");
        logCliente.insertarLog(token, genericLog);
        return response;
    }

    public void mapperArgs(Object[] args){
        String telefono  = "";
        String identificacion = "";
        String credito = "";
        if(args[2] instanceof CertificadoPayload ){
            CertificadoPayload data = (CertificadoPayload) args[2];
            identificacion = data.getIdentificacion();
            try{
                credito = SecurityUtilities.desencriptar( data.getNumeroCredito());
            }catch (Exception e){}
        }else{
            telefono = (String) args[2];
        }
        genericLog = GenericLogPayload.builder()
                .token((String) args[0])
                .serviciosEnum((ServiciosEnum) args[1])
                .telefono(telefono)
                .identificacion(identificacion)
                .credito(credito)
                .build();
    }
}
