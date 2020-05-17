package co.com.santander.chatbot.domain.payload.enviarextracto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IndicesPayloadResponse {
    private String nombre;
    private String  valor;
}
