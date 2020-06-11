package co.com.santander.chatbot.domain.validators.impl;

import co.com.santander.chatbot.domain.validators.LengthValues;
import co.com.santander.chatbot.domain.validators.exceptions.LengthValuesException;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class LengthValueValidator implements ConstraintValidator<LengthValues, Object> {

    private String message;
    @Setter
    private int min;
    @Setter
    private int max;

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        String valorAValidar = "";
        if(value instanceof Long){
            valorAValidar = ((Long) value).toString();
        }else if(value instanceof Integer){
            valorAValidar = ((Integer) value).toString();
        }else if(value instanceof String){
            valorAValidar = (String) value;
        }
        Boolean valida = validaTamanioString(valorAValidar);
        if(Boolean.TRUE.equals(valida)){
            return true;
        }
        throw new LengthValuesException(message);
    }

    private Boolean validaTamanioString(String valor){
        int tamanio = String.valueOf(valor).length();
        if (tamanio >= min && tamanio <= max){
            return true;
        }
        return false;
    }

    @Override
    public void initialize(LengthValues lengthValues) {
        message = lengthValues.message();
        min = lengthValues.min();
        max = lengthValues.max();
    }
}
