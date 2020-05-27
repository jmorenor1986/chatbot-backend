package co.com.santander.chatbot.domain.payload.accesodatos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParametrosAppPayload {

    private Long id;
    private String clave;
    private String valor;

}
