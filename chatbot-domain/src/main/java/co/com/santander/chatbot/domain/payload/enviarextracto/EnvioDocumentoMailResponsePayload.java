package co.com.santander.chatbot.domain.payload.enviarextracto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EnvioDocumentoMailResponsePayload {
    private String respuesta;
    private Boolean envioExitoso;
}
