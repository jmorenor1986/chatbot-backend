package co.com.santander.accesorecursos.soap.controller;

import co.com.santander.accesorecursos.soap.common.exception.EnvioExtractoMailException;
import co.com.santander.chatbot.domain.payload.enviarextracto.EnvioDocumentoMailResponsePayload;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@SuppressWarnings({"unchecked", "rawtypes"})
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EnvioExtractoMailException.class)
    public final ResponseEntity<Object> handlerEnvioExtractoMailException(EnvioExtractoMailException ex, WebRequest request) {
        return new ResponseEntity<>(EnvioDocumentoMailResponsePayload.builder()
                        .respuesta("Error al consumir el servicio de envio de mail: ".concat(ex.getMessage()))
                        .envioExitoso(Boolean.FALSE)
                        .build(), HttpStatus.OK);
    }

}
