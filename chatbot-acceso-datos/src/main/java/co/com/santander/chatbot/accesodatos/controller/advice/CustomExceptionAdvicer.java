package co.com.santander.chatbot.accesodatos.controller.advice;

import co.com.santander.chatbot.domain.payload.accesodatos.ResponsePayload;
import co.com.santander.chatbot.domain.validators.exceptions.MandatoryFieldException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@SuppressWarnings({"unchecked", "rawtypes"})
@ControllerAdvice
public class CustomExceptionAdvicer extends ResponseEntityExceptionHandler {
    @ExceptionHandler(MandatoryFieldException.class)
    public final ResponseEntity<?> handlerMandatoryFieldException(MandatoryFieldException ex, WebRequest request) {
        return new ResponseEntity<>(ResponsePayload.builder()
                .descripcionRespuesta(ex.getMessage())
                .idRespuesta(1)
                .resultadoValidacion(Boolean.FALSE)
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<?> handlerGenericException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(ResponsePayload.builder()
                .descripcionRespuesta(ex.getMessage())
                .idRespuesta(1)
                .resultadoValidacion(Boolean.FALSE)
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
