package co.com.santander.chatbot.backend.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class TokenDto {

    private String token;
    private Integer time;
    private String mensaje;
}
