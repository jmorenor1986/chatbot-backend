package co.com.santander.chatbot.domain.payload.enviarextracto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VigenciaExtracto {

    private String id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String idDocumentos;
    private String anio;
    private String mes;

}
