package co.com.santander.chatbot.backend.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class CustomAuthenticationException extends Exception {

    public CustomAuthenticationException(String message) {
        super(message);
    }
}
