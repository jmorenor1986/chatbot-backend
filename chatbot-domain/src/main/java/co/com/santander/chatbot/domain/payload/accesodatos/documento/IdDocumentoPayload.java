package co.com.santander.chatbot.domain.payload.accesodatos.documento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor @NoArgsConstructor
public class IdDocumentoPayload {

    private Long id;
    private String idDocumentos;
    private String anio;
    private String mes;
}
