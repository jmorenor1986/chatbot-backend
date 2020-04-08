package co.com.santander.chatbot.backend.web.controller.payload;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioPayload {
    private Long telefono;
    private String colaIdentificacion;
}
