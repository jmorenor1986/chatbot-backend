package co.com.santander.chatbot.domain.validators.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.OK)
public class ExtractoDataErrorException extends RuntimeException {

    public ExtractoDataErrorException(String message) {
        super(message);
    }

}
