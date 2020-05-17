package co.com.santander.chatbot.domain.payload.enviarextracto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EnvioDocumentoPayload {
    private String mailCC;
    private String mailPara;
}
