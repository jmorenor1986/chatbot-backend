package co.com.santander.chatbot.domain.payload.service.enlacePse;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EnlacePsePayload {

    private String telefono;
    private String numeroCreditoOfuscado;
    private String numeroVerificador;
    private String infoUno;
    private String infoDos;
    private String infoTres;
}
