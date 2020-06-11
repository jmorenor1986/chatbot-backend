package co.com.santander.chatbot.domain.payload.service.certificados;

import co.com.santander.chatbot.domain.validators.InvalidNumVerificador;
import co.com.santander.chatbot.domain.validators.LengthValues;
import co.com.santander.chatbot.domain.validators.MandatoryConstraint;
import co.com.santander.chatbot.domain.validators.OnlyNumbers;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GenericCertificatePayload {
    @OnlyNumbers(message = "telefono")
    @MandatoryConstraint(message = "télefono")
    @LengthValues(min = 10, max = 12, message = "telefono")
    private String telefono;
    @MandatoryConstraint(message = "Número de crédito")
    private String numeroCreditoOfuscado;
    @InvalidNumVerificador(message = "Número verificador")
    @MandatoryConstraint(message = "Número Verificador")
    private String numeroVerificador;
    private String infoUno;
    private String infoDos;
    private String infoTres;
}
