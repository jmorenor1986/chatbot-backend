package co.com.santander.chatbot.domain.payload.enviarextracto;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class TokenPayload {
    private String user;
    private String password;
}
