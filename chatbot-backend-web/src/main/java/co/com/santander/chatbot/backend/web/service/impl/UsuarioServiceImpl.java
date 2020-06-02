package co.com.santander.chatbot.backend.web.service.impl;



import co.com.santander.chatbot.acceso.recursos.clients.core.UsuarioAppClient;
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
        Boolean validaCredentials = validateCredentials(usuario, contra);
        if (Boolean.FALSE.equals(validaCredentials)){
            throw new CustomAuthenticationException("Usuario o Contrasenia incorrecta");
        }

        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN");
        //Genero el token
        String jwt = tokenService.generateToken(usuario, grantedAuthorities);
        return Optional.of(TokenDto.builder()
                .time(TokenServiceImpl.SECONDS)
                .token(jwt)
                .build());
    }

    private Boolean validateCredentials(String usuario, String pass) {
        Optional<Boolean> response = callServiceValidateUser(UsuarioAppPayload.builder()
                .usuario(usuario)
                .contra(pass)
                .build());
        return response.isPresent() ? response.get() : Boolean.FALSE ;
    }

    private Optional<Boolean> callServiceValidateUser(UsuarioAppPayload usuario){
        try {
            ResponseEntity<Boolean> rtaService = usuarioAppClient.validateUser(usuario);
            if (HttpStatus.OK.equals(rtaService.getStatusCode())) {
                return Optional.of(rtaService.getBody());
            }
        }catch (FeignException e){
            return Optional.empty();
        }
        return Optional.empty();
    }

}
