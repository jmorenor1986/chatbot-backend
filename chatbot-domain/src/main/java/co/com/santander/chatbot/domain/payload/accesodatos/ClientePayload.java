package co.com.santander.chatbot.domain.payload.accesodatos;

import co.com.santander.chatbot.domain.validators.LengthValues;
import co.com.santander.chatbot.domain.validators.MandatoryConstraint;
import co.com.santander.chatbot.domain.validators.OnlyNumbers;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor @AllArgsConstructor
public class ClientePayload {
    @ApiModelProperty(
            notes = "Número de telefono del cliente",
            name = "Telefono",
            dataType = "String",
            required = true,
            allowableValues = "{Debe contener 10 a 12 caracteres} {Solo acepta datos númericos}"
    )
    @OnlyNumbers(message = "telefono")
    @MandatoryConstraint(message = "telefono")
    @LengthValues(min = 10, max = 12, message = "telefono")
    private String telefono;
    @ApiModelProperty(
            notes = "Últimos cuatro dígitos de cedula del cliente",
            name = "cedula",
            dataType = "String",
            required = true,
            allowableValues = "{4 últimos digitos del documento de identidad del cliente}"
    )
    @MandatoryConstraint(message = "cedula")
    @LengthValues(min = 4, max = 4, message = "cedula")
    private String cedula;

}
