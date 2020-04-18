package co.com.santander.chatbot.backend.web.service.impl;


import co.com.santander.chatbot.backend.web.client.UsuarioAppClient;
import co.com.santander.chatbot.backend.web.exceptions.CustomAuthenticationException;
import co.com.santander.chatbot.backend.web.service.TokenService;
import co.com.santander.chatbot.backend.web.service.UsuarioService;
import co.com.santander.chatbot.domain.dto.security.TokenDto;
import co.com.santander.chatbot.domain.payload.accesodatos.UsuarioAppPayload;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final TokenService tokenService;
    private final UsuarioAppClient usuarioAppClient;

    @Autowired
    public UsuarioServiceImpl(TokenService tokenService, UsuarioAppClient usuarioAppClient) {
        this.tokenService = tokenService;
        this.usuarioAppClient = usuarioAppClient;
    }

    @Override
    public Optional<TokenDto> generaToken(String usuario, String contra) throws CustomAuthenticationException {
        if (!validateCredentials(usuario, contra))
            throw new CustomAuthenticationException("Usuario o Contrasenia incorrecta");

        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN");
        //Genero el token
        String jwt = tokenService.generateToken(usuario, grantedAuthorities);
        return Optional.of(TokenDto.builder()
                .time(TokenServiceImpl.seconds)
                .token(jwt)
                .build());
    }

    private Boolean validateCredentials(String usuario, String pass) {
        ResponseEntity<Boolean> rtaService = null;
        try {
            rtaService = usuarioAppClient.validateUser(UsuarioAppPayload.builder()
                    .usuario(usuario)
                    .contra(pass)
                    .build());
        } catch (FeignException e) {
            if (e.status() == 401)
                return Boolean.FALSE;
        }
        if (HttpStatus.OK.equals(rtaService.getStatusCode())) {
            return rtaService.getBody();
        }
        return Boolean.FALSE;
    }

}
