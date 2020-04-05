package co.com.santander.chatbot.backend.web.controller.payload;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RespuestaPayload {
    private Boolean resultadoValidacion;
    private Long idRespuesta;
    private String descripcionRespuesta;
}
