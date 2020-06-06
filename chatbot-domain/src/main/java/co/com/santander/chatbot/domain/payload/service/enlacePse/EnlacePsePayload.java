package co.com.santander.chatbot.domain.payload.service.enlacePse;

import co.com.santander.chatbot.domain.validators.InvalidNumVerificador;
import co.com.santander.chatbot.domain.validators.LengthValues;
import co.com.santander.chatbot.domain.validators.MandatoryConstraint;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EnlacePsePayload {
    @MandatoryConstraint(message = "telefono")
    @LengthValues(min = 10, max = 10, message = "telefono")
    private String telefono;
    @MandatoryConstraint(message = "Número de credito Ofuscado")
    private String numeroCreditoOfuscado;
    @InvalidNumVerificador(message = "Número verificador")
    @MandatoryConstraint(message = "Número Verificador")
    private String numeroVerificador;
    private String infoUno;
    private String infoDos;
    private String infoTres;
}
