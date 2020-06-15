package co.com.santander.chatbot.domain.payload.accesodatos;

import co.com.santander.chatbot.domain.validators.AllowedValues;
import co.com.santander.chatbot.domain.validators.LengthValues;
import co.com.santander.chatbot.domain.validators.MandatoryConstraint;
import co.com.santander.chatbot.domain.validators.OnlyNumbers;
import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(
            notes = "Número de telefono del cliente",
            name = "Telefono",
            dataType = "String",
            required = true,
            allowableValues = "{Debe contener 10 a 12 caracteres} {Solo acepta datos númericos}"
    )
    @OnlyNumbers(message = "telefono")
    @LengthValues(min = 10, max = 12, message = "telefono")
    @MandatoryConstraint(message = "operacion")
    private String telefono;
    @ApiModelProperty(
            notes = "Hora de aceptación de terminos",
            name = "Hora enviado terminos",
            dataType = "Date",
            required = true,
            allowableValues = "{Fecha valida}"
    )
    private Date horaEnviadoTeminos;
    @ApiModelProperty(
            notes = "Hora en la cual se almaceno el registro en la base de datos del banco",
            name = "Hora Operacion",
            dataType = "Date",
            allowableValues = "{Fecha valida}"
    )
    private Date horaOperacion;
    @ApiModelProperty(
            notes = "Indica el tipo de operación que esta registrando (1,2)",
            name = "Tipo Operacion",
            dataType = "Long",
            allowableValues = "{1: Aceptación}. {2: No aceptación}"
    )
    @AllowedValues(message = "operacion", longValues = { 1L , 2L })
    @MandatoryConstraint(message = "operacion", zeroIsValid = false)
    private Long operacion;

}
