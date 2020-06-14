package co.com.santander.chatbot.domain.payload.service.extracto;

import co.com.santander.chatbot.domain.validators.InvalidNumVerificador;
import co.com.santander.chatbot.domain.validators.LengthValues;
import co.com.santander.chatbot.domain.validators.MandatoryConstraint;
import co.com.santander.chatbot.domain.validators.OnlyNumbers;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor @NoArgsConstructor
public class EnvioExtractoPayload {
    @ApiModelProperty(
            notes = "Número de telefono del cliente",
            name = "Telefono",
            dataType = "String",
            required = true,
            allowableValues = "Debe contener 10 a 12 caracteres"
    )
    @OnlyNumbers(message = "telefono")
    @MandatoryConstraint(message = "telefono")
    @LengthValues(min = 10, max = 12, message = "telefono")
    private String telefono;
    @ApiModelProperty(
            notes = "Número de credito en el cual solo se exponen los ultimos 5 digitos",
            name = "Numero credito ofuscado",
            dataType = "String",
            required = true
    )
    @MandatoryConstraint(message = "Número de credito ofuscado")
    private String numeroCreditoOfuscado;
    @ApiModelProperty(
            notes = "Número verificador obtenido del servicio (Obtener creditos)",
            name = "Número verficador",
            dataType = "String",
            required = true
    )
    @InvalidNumVerificador(message = "Número verificador")
    @MandatoryConstraint(message = "Número Verificador")
    private String numeroVerificador;
    @ApiModelProperty(
            notes = "Idetificador unico del documento",
            name = "Id Documento",
            dataType = "Integer"
    )
    @MandatoryConstraint(message = "Identificador único del documento", zeroIsValid = false)
    private Integer idExtracto;
    @ApiModelProperty(
            notes = "Mes del Extracto a solicitar",
            name = "mes",
            dataType = "Integer"
    )
    @MandatoryConstraint(message = "Mes Extracto", zeroIsValid = false)
    private Integer mes;
    @ApiModelProperty(
            notes = "Año del cual se desea enviar el extracto",
            name = "vigencia",
            dataType = "Integer"
    )
    @MandatoryConstraint(message = "Vigencia", zeroIsValid = false)
    private Integer vigencia;

    private String infoUno;

    private String  infoDos;

    private String infoTres;
}
