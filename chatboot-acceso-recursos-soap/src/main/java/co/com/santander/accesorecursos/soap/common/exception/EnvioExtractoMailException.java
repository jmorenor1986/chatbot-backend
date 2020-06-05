package co.com.santander.accesorecursos.soap.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.OK)
public class EnvioExtractoMailException extends RuntimeException {

    public EnvioExtractoMailException(String message) {
        super(message);
    }
}
