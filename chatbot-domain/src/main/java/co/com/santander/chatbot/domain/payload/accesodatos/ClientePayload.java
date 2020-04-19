package co.com.santander.chatbot.domain.payload.accesodatos;

import co.com.santander.chatbot.domain.validators.MandatoryConstraint;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ClientePayload {
    @MandatoryConstraint(message = "Número de telefono errado", min = 10, max = 10)
    private String telefono;
    @MandatoryConstraint(message = "Número de cedula errado", min = 4, max = 4)
    private String cedula;
}
