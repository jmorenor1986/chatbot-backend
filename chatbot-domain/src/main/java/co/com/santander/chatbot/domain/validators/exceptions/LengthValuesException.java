package co.com.santander.chatbot.domain.validators.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.OK)
public class LengthValuesException extends RuntimeException  {

    public LengthValuesException(String message) {
        super(message);
    }
}
