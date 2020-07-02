package co.com.santander.chatbot.domain.payload.enviarextracto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseExtractosDisponibles {

    private String resultado;
    private String emailOfuscado;
    private String numeroCreditoOfuscado;
    private String numeroVerificador;
    private String idRespuesta;
    private String descripcionRespuesta;
    private List<VigenciaExtracto> vigencias;

}
