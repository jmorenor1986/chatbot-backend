package co.com.santander.chatbot.domain.validators.impl;

import co.com.santander.chatbot.domain.common.utilities.SecurityUtilities;
import co.com.santander.chatbot.domain.validators.InvalidNumVerificador;
import co.com.santander.chatbot.domain.validators.exceptions.InvalidNumVerificadorException;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Log
@Component
public class InvalidNumVerificadorValidator implements ConstraintValidator<InvalidNumVerificador, String> {

    private String message;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            SecurityUtilities.desencriptar(value);
            return true;
        } catch (Exception e) {
            log.severe(e.getMessage());
        }
        throw new InvalidNumVerificadorException(message);
    }

    @Override
    public void initialize(InvalidNumVerificador invalidNumVerificador) {
        message = invalidNumVerificador.message();
    }
}
