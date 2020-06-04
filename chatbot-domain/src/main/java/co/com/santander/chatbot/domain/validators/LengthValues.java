package co.com.santander.chatbot.domain.validators;

import co.com.santander.chatbot.domain.validators.impl.AllowedValuesValidator;
import co.com.santander.chatbot.domain.validators.impl.LengthValueValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = LengthValueValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LengthValues {

    String message() default "Tamanio incorrecto";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int min() default 0;

    int max() default Integer.MAX_VALUE;

}
