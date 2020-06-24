package co.com.santander.chatbot.domain.payload.service.obtenercreditos;

import co.com.santander.chatbot.domain.validators.AllowedValues;
import co.com.santander.chatbot.domain.validators.LengthValues;
import co.com.santander.chatbot.domain.validators.MandatoryConstraint;
import co.com.santander.chatbot.domain.validators.OnlyNumbers;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreditosUsuarioPayload {
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
            notes = "Indica el tipo de operacion que puede realizar el usuario (0,1,2,3,4,5,6)",
            name = "Tipo Operacion Usuario",
            dataType = "String",
            required = true,
            allowableValues = "{0:Todos los servicios}{1: Información de crédito},{2: Enviar extracto}, {3: Enviar certificacion saldo}, {4: Enviar certificación Paz y Salvo}, {5: Enviar autorización débito}, {6: Enviar declaracion de renta}, {7: Link PSE}"
    )
    @MandatoryConstraint(message = "tipo operacion")
    @AllowedValues(message = "tipo operacion", longValues = {0L,1L,2L,3L,4L,5L,6L,7L})
    private Long tipoOperacion;
    private String infoUno;
    private String infoDos;
    private String infoTres;
}