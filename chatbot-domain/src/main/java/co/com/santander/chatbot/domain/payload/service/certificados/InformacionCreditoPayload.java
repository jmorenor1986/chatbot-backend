package co.com.santander.chatbot.domain.payload.service.certificados;

import co.com.santander.chatbot.domain.validators.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InformacionCreditoPayload {
    @OnlyNumbers(message = "telefono")
    @MandatoryConstraint(message = "télefono")
    @LengthValues(min = 10, max = 12, message = "telefono")
    private String telefono;
    @MandatoryConstraint(message = "crédito")
    private String numeroCreditoOfuscado;
    @InvalidNumVerificador(message = "numero verificador")
    @MandatoryConstraint(message = "numero verificador")
    private String numeroVerificador;
    @AllowedValues(message = "tipo operacion", stringValues = {"1", "2"} )
    @MandatoryConstraint(message = "tipo operacion")
    private String tipoOperacionUsuario;
    private String infoUno;
    private String infoDos;
    private String infoTres;
}
