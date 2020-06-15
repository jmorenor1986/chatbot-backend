package co.com.santander.chatbot.domain.payload.service.certificados;

import co.com.santander.chatbot.domain.validators.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InformacionCreditoPayload {
    @ApiModelProperty(
            notes = "Número de telefono del cliente",
            name = "Telefono",
            dataType = "String",
            required = true,
            allowableValues = "{Debe contener 10 a 12 caracteres} {Solo acepta datos númericos}"
    )
    @OnlyNumbers(message = "telefono")
    @MandatoryConstraint(message = "télefono")
    @LengthValues(min = 10, max = 12, message = "telefono")
    private String telefono;
    @ApiModelProperty(
            notes = "Número de credito en el cual solo se exponen los ultimos 5 digitos",
            name = "Numero credito ofuscado",
            dataType = "String",
            required = true
    )
    @MandatoryConstraint(message = "crédito")
    private String numeroCreditoOfuscado;
    @ApiModelProperty(
            notes = "Número verificador obtenido del servicio (Obtener creditos)",
            name = "Número verficador",
            dataType = "String",
            required = true,
            allowableValues = "{Número validado por algoritmo de desencripción}"
    )
    @InvalidNumVerificador(message = "numero verificador")
    @MandatoryConstraint(message = "numero verificador")
    private String numeroVerificador;
    @ApiModelProperty(
            notes = "Indica el tipo de operacion que puede realizar el usuario (1,2)",
            name = "Tipo Operacion Usuario",
            dataType = "String",
            required = true,
            allowableValues = "{1},{2}"
    )
    @AllowedValues(message = "tipo operacion", stringValues = {"1", "2"} )
    @MandatoryConstraint(message = "tipo operacion")
    private String tipoOperacionUsuario;
    private String infoUno;
    private String infoDos;
    private String infoTres;
}
