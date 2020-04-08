package co.com.santander.chatbot.backend.web.service.impl;

import co.com.santander.chatbot.backend.web.service.TokenService;
import co.com.santander.chatbot.backend.web.service.UsuarioService;
import co.com.santander.chatbot.domain.dto.security.TokenDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final TokenService tokenService;

    @Autowired
    public UsuarioServiceImpl(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public Optional<TokenDto> generaToken() {
        // Busco los roles del usuario
        String roleStr = "ROLE_ADMIN";
        //Genero la lista de roles del usuario
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(roleStr);

        //Genero el token
        String jwt = tokenService.generateToken("jnsierrac@gmail.com", grantedAuthorities);
        return Optional.of(TokenDto.builder()
                .time(TokenServiceImpl.seconds)
                .token(jwt)
                .build());
    }
}
