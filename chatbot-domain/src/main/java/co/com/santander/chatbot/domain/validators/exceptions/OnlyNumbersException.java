package co.com.santander.chatbot.domain.validators.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.OK)
public class OnlyNumbersException extends RuntimeException {

    public OnlyNumbersException(String message) {
        super(message);
    }
}
