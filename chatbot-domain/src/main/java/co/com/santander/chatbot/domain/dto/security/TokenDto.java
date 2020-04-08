package co.com.santander.chatbot.domain.dto.security;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TokenDto {

    private String token;
    private Integer time;
    private String mensaje;
}
