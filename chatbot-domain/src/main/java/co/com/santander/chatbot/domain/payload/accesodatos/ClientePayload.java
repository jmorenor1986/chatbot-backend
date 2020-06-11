package co.com.santander.chatbot.domain.payload.accesodatos;

import co.com.santander.chatbot.domain.validators.LengthValues;
import co.com.santander.chatbot.domain.validators.MandatoryConstraint;
import co.com.santander.chatbot.domain.validators.OnlyNumbers;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor @AllArgsConstructor
public class ClientePayload {
    @OnlyNumbers(message = "telefono")
    @MandatoryConstraint(message = "telefono")
    @LengthValues(min = 10, max = 12, message = "telefono")
    private String telefono;
    @MandatoryConstraint(message = "cedula")
    @LengthValues(min = 4, max = 4, message = "cedula")
    private String cedula;
}
