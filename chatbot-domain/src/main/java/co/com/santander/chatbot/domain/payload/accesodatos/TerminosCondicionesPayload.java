package co.com.santander.chatbot.domain.payload.accesodatos;

import co.com.santander.chatbot.domain.validators.AllowedValues;
import co.com.santander.chatbot.domain.validators.LengthValues;
import co.com.santander.chatbot.domain.validators.MandatoryConstraint;
import co.com.santander.chatbot.domain.validators.OnlyNumbers;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor @NoArgsConstructor
public class TerminosCondicionesPayload {

    private Long id;
    @OnlyNumbers(message = "telefono")
    @LengthValues(min = 10, max = 12, message = "telefono")
    @MandatoryConstraint(message = "operacion")
    private String telefono;
    private Date horaEnviadoTeminos;
    private Date horaOperacion;
    @AllowedValues(message = "operacion", longValues = { 1L , 2L })
    @MandatoryConstraint(message = "operacion", zeroIsValid = false)
    private Long operacion;

}
