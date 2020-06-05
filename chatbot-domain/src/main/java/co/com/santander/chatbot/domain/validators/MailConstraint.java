package co.com.santander.chatbot.domain.validators;

import co.com.santander.chatbot.domain.validators.impl.AllowedValuesValidator;
import co.com.santander.chatbot.domain.validators.impl.MailConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MailConstraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MailConstraint {

    String message() default "Correo invalido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
