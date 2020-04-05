package co.com.santander.chatbot.backend.web.controller.payload;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UsuarioPayload {
    private Long telefono;
    private String colaIdentificacion;
}
