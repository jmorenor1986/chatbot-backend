package co.com.santander.chatbot.backend.web.controller.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioPayload {
    private Long telefono;
    private String colaIdentificacion;
}
