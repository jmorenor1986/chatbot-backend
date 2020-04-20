package co.com.santander.chatbot.domain.payload.service.obtenercreditos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreditosUsuarioPayload {

    private String telefono;
    private Long tipoOperacion;
    private String infoUno;
    private String infoDos;
    private String infoTres;

}
