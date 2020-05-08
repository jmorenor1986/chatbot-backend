package co.com.santander.chatbot.domain.payload.accesodatos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor @AllArgsConstructor
public class ParametrosServicioPayload {

    private Long id;
    private Integer numeroIntentos;
    private Integer tiempoIntentos;
    private Integer tiempoPosterior;
}
