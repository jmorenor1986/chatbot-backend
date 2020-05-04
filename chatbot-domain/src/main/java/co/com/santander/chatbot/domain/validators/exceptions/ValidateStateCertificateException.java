package co.com.santander.chatbot.domain.validators.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.ACCEPTED)
public class ValidateStateCertificateException extends RuntimeException {
    @Getter
    private Long minutos;

    public ValidateStateCertificateException(String message, Long minutos) {
        super(message);
        this.minutos = minutos;
    }
}
