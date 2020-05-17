package co.com.santander.chatbot.domain.payload.enviarextracto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EnviarMailDocumentoPayload {
    private ConsultarDocumentoPayload consultarDocumentoPayload;
    private EnvioDocumentoPayload envioDocumentoPayload;

}
