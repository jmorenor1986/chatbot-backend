package co.com.santander.chatbot.domain.payload.accesodatos;

import lombok.Data;

@Data
public class ParametrosServicioPayload {

    private Long id;
    private Integer numeroIntentos;
    private Integer tiempoIntentos;
    private Integer tiempoPosterior;
}
