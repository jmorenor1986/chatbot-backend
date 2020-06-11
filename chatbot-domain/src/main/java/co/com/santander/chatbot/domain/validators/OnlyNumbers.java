package co.com.santander.chatbot.domain.validators;

import co.com.santander.chatbot.domain.validators.impl.OnlyNumbersValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = OnlyNumbersValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OnlyNumbers {

    String message() default "Valor no permitido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
