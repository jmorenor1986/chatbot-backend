package co.com.santander.chatbot.backend.web.controller;

import co.com.santander.chatbot.domain.payload.accesodatos.ResponsePayload;
import co.com.santander.chatbot.domain.payload.service.certificados.InformacionCreditoResponsePayload;
import co.com.santander.chatbot.domain.validators.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@SuppressWarnings({"unchecked", "rawtypes"})
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MailConstraintException.class)
    public final ResponseEntity<Object> handlerMailConstraintException(MailConstraintException ex, WebRequest request) {
        return new ResponseEntity<>(ResponsePayload.builder()
                .descripcionRespuesta("Datos erróneos, los datos no cumplen con el formato (correo invalido) (".concat(ex.getMessage()).concat(")"))
                .idRespuesta(4)
                .resultadoValidacion(Boolean.FALSE)
                .build(), HttpStatus.OK);
    }

    @ExceptionHandler(LengthValuesException.class)
    public final ResponseEntity<Object> lengthValuesException(LengthValuesException ex, WebRequest request) {
        return new ResponseEntity<>(ResponsePayload.builder()
                .descripcionRespuesta("Datos erróneos, los datos no cumplen con el formato (tamanio no permitido) (".concat(ex.getMessage()).concat(")"))
                .idRespuesta(4)
                .resultadoValidacion(Boolean.FALSE)
                .build(), HttpStatus.OK);
    }

    @ExceptionHandler(AllowedValuesException.class)
    public final ResponseEntity<Object> handlerAllowedValuesException(AllowedValuesException ex, WebRequest request) {
        return new ResponseEntity<>(ResponsePayload.builder()
                .descripcionRespuesta("Datos erróneos, los datos no cumplen con el formato (".concat(ex.getMessage()).concat(")"))
                .idRespuesta(4)
                .resultadoValidacion(Boolean.FALSE)
                .build(), HttpStatus.OK);
    }

    @ExceptionHandler(MandatoryFieldException.class)
    public final ResponseEntity<Object> handlerMandatoryFieldException(MandatoryFieldException ex, WebRequest request) {
        return new ResponseEntity<>(ResponsePayload.builder()
                .descripcionRespuesta("Datos incompletos (".concat(ex.getMessage()).concat(")"))
                .idRespuesta(5)
                .resultadoValidacion(Boolean.FALSE)
                .build(), HttpStatus.OK);
    }

    @ExceptionHandler(ValidateStateCertificateException.class)
    public final ResponseEntity<Object> validateStateCertificateException(ValidateStateCertificateException ex, WebRequest request) {
        return new ResponseEntity<>(ResponsePayload.builder()
                .resultadoValidacion(Boolean.FALSE)
                .idRespuesta(7)
                .minutos(ex.getMinutos())
                .descripcionRespuesta(ex.getMessage())
                .build(), HttpStatus.OK);
    }

    @ExceptionHandler(ValidateStatusAfterProcess.class)
    public final ResponseEntity<Object> validateStateAfter(ValidateStatusAfterProcess ex, WebRequest request){
        return new ResponseEntity<>(InformacionCreditoResponsePayload.builder()
                .resultadoEnvio(Boolean.FALSE.toString())
                .emailOfuscado(ex.getEmail())
                .numeroCreditoOfuscado(ex.getNumeroCredito())
                .convenio(ex.getConvenio())
                .infoUnoR(ex.getMinutos().toString())
                .descripcionRespuesta(ex.getMessage())
                .idRespuesta(ex.getIdRespuesta())
                .build(), HttpStatus.OK);
    }


}
