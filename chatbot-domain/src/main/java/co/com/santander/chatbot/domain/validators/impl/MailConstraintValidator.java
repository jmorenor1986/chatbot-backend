package co.com.santander.chatbot.domain.validators.impl;

import co.com.santander.chatbot.domain.validators.MailConstraint;
import co.com.santander.chatbot.domain.validators.exceptions.MailConstraintException;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class MailConstraintValidator implements ConstraintValidator<MailConstraint, String> {

    private String message;
    private String mail;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        mail = value;
        Boolean valida = validateRegularExpresion();
        if(Boolean.TRUE.equals(valida)){
            return true;
        }
        throw new MailConstraintException(message);
    }

    private Boolean validateRegularExpresion(){
        // Patron para validar el email
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        Matcher mather = pattern.matcher(mail);
        return mather.find();
    }

    @Override
    public void initialize(MailConstraint mailConstraint) {
        message = mailConstraint.message();
    }
}
