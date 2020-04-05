package co.com.santander.chatbot.backend.web.controller.payload;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RespuestaPayload {
    private Boolean resultadoValidacion;
    private Long idRespuesta;
    private String descripcionRespuesta;
}
