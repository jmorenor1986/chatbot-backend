package co.com.santander.chatbot.domain.validators;

import co.com.santander.chatbot.domain.validators.impl.MandatoryValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MandatoryValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MandatoryConstraint {
    String message() default "Invalid field";
    boolean zeroIsValid() default true;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
