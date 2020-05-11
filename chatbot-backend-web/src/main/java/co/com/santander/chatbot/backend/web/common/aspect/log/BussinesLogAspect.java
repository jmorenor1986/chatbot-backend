package co.com.santander.chatbot.backend.web.common.aspect.log;

import co.com.santander.chatbot.acceso.recursos.clients.core.LogCliente;
import co.com.santander.chatbot.backend.web.common.utilities.SecurityUtilities;
import co.com.santander.chatbot.domain.common.utilities.GenericLogPayload;
import co.com.santander.chatbot.domain.enums.ServiciosEnum;
import co.com.santander.chatbot.domain.payload.accesodatos.ResponsePayload;
import co.com.santander.chatbot.domain.payload.service.certificados.InformacionCreditoPayload;
import co.com.santander.chatbot.domain.payload.service.certificados.GenericCertificatePayload;
import co.com.santander.chatbot.domain.payload.service.certificados.InformacionCreditoResponsePayload;
import co.com.santander.chatbot.domain.payload.service.obtenercreditos.CreditosUsuarioPayload;
import co.com.santander.chatbot.domain.validators.exceptions.ValidateStateCertificateException;
import co.com.santander.chatbot.domain.validators.exceptions.ValidateStatusAfterProcess;
import com.google.gson.Gson;
import lombok.extern.java.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Log
@Aspect
@Order(0)
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
        Object response = null;
        String strResponse = "";
        try {
            response = joinPoint.proceed();
            strResponse = new Gson().toJson(response);
        }catch (ValidateStateCertificateException  e){
            strResponse = generateResponseException(e);
            generateLog(strResponse);
            throw new ValidateStateCertificateException(e.getMessage(), 0L);
        }catch (ValidateStatusAfterProcess e){
            strResponse = generateResponseExceptionAfter(e);
            generateLog(strResponse);
            throw new ValidateStatusAfterProcess(e.getMessage(),e.getEmail(), e.getNumeroCredito(), e.getConvenio(), e.getMinutos(), e.getIdRespuesta());
        }
        generateLog(strResponse);
        return response;
    }

    public Long getTipoOperacion(ServiciosEnum servicioEnum, Object[] args ){
        if(ServiciosEnum.SERVICIO_OBTENER_CREDITOS.equals(servicioEnum)){
            CreditosUsuarioPayload credito = (CreditosUsuarioPayload) args[3];
            return credito.getTipoOperacion();
        }else if(ServiciosEnum.SERVICIO_INFORMACION_CREDITO.equals(servicioEnum)){
            InformacionCreditoPayload informacionCreditoPayload = (InformacionCreditoPayload) args[2];
            return  Long.valueOf(informacionCreditoPayload.getTipoOperacionUsuario());
        }
        return null;
    }

    public String generateResponseException(Exception e){
        return new Gson().toJson( ResponsePayload.builder()
                .resultadoValidacion(Boolean.FALSE)
                .idRespuesta(1)
                .descripcionRespuesta(e.getMessage())
                .build() );
    }

    public String generateResponseExceptionAfter(ValidateStatusAfterProcess ex){
        return new Gson().toJson( InformacionCreditoResponsePayload.builder()
                .resultadoEnvio(Boolean.FALSE.toString())
                .emailOfuscado(ex.getEmail())
                .numeroCreditoOfuscado(ex.getNumeroCredito())
                .convenio(ex.getConvenio())
                .infoUnoR(ex.getMinutos().toString())
                .descripcionRespuesta(ex.getMessage())
                .build() );
    }

    public void generateLog(String response){
        genericLog.setTraza(response);
        token = genericLog.getToken();
        genericLog.setToken("");
        logCliente.insertarLog(token, genericLog);
    }

    public void mapperArgs(Object[] args){
        String telefono  = "";
        String identificacion = "";
        String credito = "";
        String request ="";
        if(args[2] instanceof InformacionCreditoPayload){
            InformacionCreditoPayload data = (InformacionCreditoPayload) args[2];
            request = new Gson().toJson(args[2]);
            telefono = data.getTelefono();
            try{
                credito = SecurityUtilities.desencriptar( data.getNumeroVerificador() );
            }catch (Exception e){}
        }else if(args[2] instanceof GenericCertificatePayload){
            GenericCertificatePayload data = (GenericCertificatePayload) args[2];
            request = new Gson().toJson(args[2]);
            telefono = data.getTelefono();
            try{
                credito = SecurityUtilities.desencriptar( data.getNumeroVerificador() );
            }catch (Exception e){}
        }else{
            request = new Gson().toJson(args[3]);
            telefono = (String) args[2];
        }
        genericLog = GenericLogPayload.builder()
                .token((String) args[0])
                .serviciosEnum((ServiciosEnum) args[1])
                .telefono(telefono)
                .identificacion(identificacion)
                .credito(credito)
                .request(request)
                .tipoOperacion(getTipoOperacion((ServiciosEnum) args[1], args))
                .build();
    }
}
