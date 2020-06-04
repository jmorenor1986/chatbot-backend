package co.com.santander.chatbot.domain.payload.accesodatos;

import co.com.santander.chatbot.domain.validators.AllowedValues;
import co.com.santander.chatbot.domain.validators.MandatoryConstraint;
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
    private Long telefono;
    private Date horaEnviadoTeminos;
    private Date horaOperacion;
    @AllowedValues(message = "operacion", longValues = { 1L , 2L })
    @MandatoryConstraint(message = "operacion", zeroIsValid = false)
    private Long operacion;

}
