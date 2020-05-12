package co.com.santander.accesorecursos.soap.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
