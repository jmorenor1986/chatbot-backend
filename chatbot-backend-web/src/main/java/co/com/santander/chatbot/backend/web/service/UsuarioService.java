package co.com.santander.chatbot.backend.web.service;

import co.com.santander.chatbot.backend.web.exceptions.CustomAuthenticationException;
import co.com.santander.chatbot.domain.dto.security.TokenDto;

import java.util.Optional;

public interface UsuarioService {

    Optional<TokenDto> generaToken(String usuario, String contra) throws CustomAuthenticationException;
}
