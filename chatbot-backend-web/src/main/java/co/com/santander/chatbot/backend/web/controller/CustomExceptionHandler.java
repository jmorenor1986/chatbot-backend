package co.com.santander.chatbot.backend.web.controller;

import co.com.santander.chatbot.domain.payload.accesodatos.ResponsePayload;
import co.com.santander.chatbot.domain.validators.exceptions.MandatoryFieldException;
import co.com.santander.chatbot.domain.validators.exceptions.ValidateStateCertificateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@SuppressWarnings({"unchecked", "rawtypes"})
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MandatoryFieldException.class)
    public final ResponseEntity<Object> handlerMandatoryFieldException(MandatoryFieldException ex, WebRequest request) {
        return new ResponseEntity<>(ResponsePayload.builder()
                .descripcionRespuesta(ex.getMessage())
                .idRespuesta(1)
                .resultadoValidacion(Boolean.FALSE)
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ValidateStateCertificateException.class)
    public final ResponseEntity<Object> validateStateCertificateException(ValidateStateCertificateException ex, WebRequest request) {
        return new ResponseEntity<>(ResponsePayload.builder()
                .resultadoValidacion(Boolean.FALSE)
                .idRespuesta(1)
                .minutos(ex.getMinutos())
                .descripcionRespuesta(ex.getMessage())
                .build(), HttpStatus.OK);
    }


}
