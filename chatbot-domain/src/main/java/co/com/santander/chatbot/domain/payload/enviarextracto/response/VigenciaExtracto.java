package co.com.santander.chatbot.domain.payload.enviarextracto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VigenciaExtracto {

    private String idDocumento;
    private String anio;
    private String mes;

}
