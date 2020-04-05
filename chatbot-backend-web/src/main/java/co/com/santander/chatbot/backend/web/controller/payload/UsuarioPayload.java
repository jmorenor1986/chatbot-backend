package co.com.santander.chatbot.backend.web.controller.payload;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioPayload {
    private Long telefono;
    private String colaIdentificacion;
}
