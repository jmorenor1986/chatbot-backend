package co.com.santander.chatbot.domain.payload.service.certificados;

import co.com.santander.chatbot.domain.validators.AllowedValues;
import co.com.santander.chatbot.domain.validators.LengthValues;
import co.com.santander.chatbot.domain.validators.MandatoryConstraint;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InformacionCreditoPayload {
    @MandatoryConstraint(message = "télefono")
    @LengthValues(min = 10, max = 10, message = "telefono")
    private String telefono;
    @MandatoryConstraint(message = "crédito")
    private String numeroCreditoOfuscado;
    @MandatoryConstraint(message = "numero verificador")
    private String numeroVerificador;
    @AllowedValues(message = "tipo operacion", stringValues = {"1", "2"} )
    @MandatoryConstraint(message = "tipo operacion")
    private String tipoOperacionUsuario;
    private String infoUno;
    private String infoDos;
    private String infoTres;
}
