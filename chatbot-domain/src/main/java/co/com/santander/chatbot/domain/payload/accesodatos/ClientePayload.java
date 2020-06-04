package co.com.santander.chatbot.domain.payload.accesodatos;

import co.com.santander.chatbot.domain.validators.AllowedValues;
import co.com.santander.chatbot.domain.validators.LengthValues;
import co.com.santander.chatbot.domain.validators.MandatoryConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor @AllArgsConstructor
public class ClientePayload {
    @MandatoryConstraint(message = "telefono")
    @LengthValues(min = 10, max = 10, message = "telefono")
    private String telefono;
    @MandatoryConstraint(message = "cedula")
    private String cedula;
}
