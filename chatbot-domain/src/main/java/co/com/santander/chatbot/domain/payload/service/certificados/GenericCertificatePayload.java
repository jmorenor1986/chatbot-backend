package co.com.santander.chatbot.domain.payload.service.certificados;

import co.com.santander.chatbot.domain.validators.LengthValues;
import co.com.santander.chatbot.domain.validators.MandatoryConstraint;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GenericCertificatePayload {
    @MandatoryConstraint(message = "télefono")
    @LengthValues(min = 10, max = 10, message = "telefono")
    private String telefono;
    @MandatoryConstraint(message = "Número de crédito")
    private String numeroCreditoOfuscado;
    @MandatoryConstraint(message = "Número Verificador")
    private String numeroVerificador;
    private String infoUno;
    private String infoDos;
    private String infoTres;
}
