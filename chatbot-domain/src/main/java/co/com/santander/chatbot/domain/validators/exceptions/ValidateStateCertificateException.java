package co.com.santander.chatbot.domain.validators.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.ACCEPTED)
public class ValidateStateCertificateException extends RuntimeException {
    public ValidateStateCertificateException(String message) {
        super(message);
    }
}