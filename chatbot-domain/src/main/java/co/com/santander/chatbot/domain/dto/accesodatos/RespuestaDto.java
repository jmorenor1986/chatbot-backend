package co.com.santander.chatbot.domain.dto.accesodatos;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RespuestaDto {
    private Boolean resultadoConsulta;
    private Integer idRespuesta;
    private String descripcionRespuesta;
    private List<Credito> creditos;
}
