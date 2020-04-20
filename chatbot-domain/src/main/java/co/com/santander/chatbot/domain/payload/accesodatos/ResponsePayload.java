package co.com.santander.chatbot.domain.payload.accesodatos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponsePayload {
    private Boolean resultadoValidacion;
    private Integer idRespuesta;
    private Object descripcionRespuesta;

}
