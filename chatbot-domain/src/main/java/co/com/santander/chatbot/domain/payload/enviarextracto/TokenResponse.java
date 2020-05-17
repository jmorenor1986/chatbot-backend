package co.com.santander.chatbot.domain.payload.enviarextracto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TokenResponse {
    private String respuesta;
    private String mensajeError;
}
