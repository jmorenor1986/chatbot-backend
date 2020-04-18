package co.com.santander.chatbot.domain.payload.accesodatos;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Builder
@Data
public class UsuarioInput {
    @NotNull(message = "Telefono es obligatorio")
    private String telefono;
    @NotNull(message = "Telefono es obligatorio")
    private String cedula;
}
