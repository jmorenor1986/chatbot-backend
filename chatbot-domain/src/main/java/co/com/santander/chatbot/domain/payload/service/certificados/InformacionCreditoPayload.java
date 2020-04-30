package co.com.santander.chatbot.domain.payload.service.certificados;

import co.com.santander.chatbot.domain.validators.MandatoryConstraint;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InformacionCreditoPayload {
    @MandatoryConstraint(message = "Número de télefono errado", min = 10, max = 12)
    private String telefono;
    @MandatoryConstraint(message = "Número de crédito errado")
    private String numeroCreditoOfuscado;
    @MandatoryConstraint(message = "Número de verificador  errado")
    private String numeroVerificador;
    @MandatoryConstraint(message = "tipoOperacion errado", min = 1, max = 1)
    private String tipoOperacionUsuario;
    private String infoUno;
    private String infoDos;
    private String infoTres;
}
