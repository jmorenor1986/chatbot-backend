package co.com.santander.chatbot.domain.payload.accesodatos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponsePayload {
    private Boolean resultado;
    private Integer idRespuesta;
    private Object descripcionRespuesta;
    private Long minutos;

}
