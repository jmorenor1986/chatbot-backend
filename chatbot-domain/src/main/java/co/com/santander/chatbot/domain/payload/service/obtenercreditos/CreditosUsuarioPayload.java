package co.com.santander.chatbot.domain.payload.service.obtenercreditos;

import co.com.santander.chatbot.domain.validators.AllowedValues;
import co.com.santander.chatbot.domain.validators.MandatoryConstraint;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreditosUsuarioPayload {

    private String telefono;
    @MandatoryConstraint(message = "tipo operacion")
    @AllowedValues(message = "tipo operacion", longValues = {1L,2L})
    private Long tipoOperacion;
    private String infoUno;
    private String infoDos;
    private String infoTres;
}