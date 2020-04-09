package co.com.santander.chatbot.backend.web.service.impl;

import co.com.santander.chatbot.backend.web.client.UsuarioAppClient;
import co.com.santander.chatbot.backend.web.exceptions.CustomAuthenticationException;
import co.com.santander.chatbot.backend.web.service.TokenService;
import co.com.santander.chatbot.domain.dto.security.TokenDto;
import co.com.santander.chatbot.domain.payload.accesodatos.UsuarioAppPayload;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class UsuarioServiceImplTest {

    private UsuarioServiceImpl usuarioService;
    @Mock
    private TokenService tokenService;
    @Mock
    private UsuarioAppClient usuarioAppClient;

    private final String USUARIO = "jnsierra";
    private final String PASSWORD = "123456";
    private final String TOKEN = "1234";

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.usuarioService = new UsuarioServiceImpl(tokenService, usuarioAppClient);
    }

    @Test(expected = CustomAuthenticationException.class)
    public void testGeneraTokenUNAUTHORIZED() throws CustomAuthenticationException {
        Mockito.when(usuarioAppClient.validateUser(UsuarioAppPayload.builder()
                .usuario(USUARIO)
                .contra(PASSWORD)
                .build())).thenReturn(new ResponseEntity<Boolean>(HttpStatus.UNAUTHORIZED));
        Optional<TokenDto> tokenDto = this.usuarioService.generaToken(USUARIO, PASSWORD);
    }

    @Test
    public void testGeneraTokenSUCCESS() throws CustomAuthenticationException {
        UsuarioAppPayload usuarioInput = UsuarioAppPayload.builder()
                .usuario(USUARIO)
                .contra(PASSWORD)
                .build();
        ResponseEntity<Boolean> rtaRest = new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
        Mockito.when(usuarioAppClient.validateUser(usuarioInput)).thenReturn(rtaRest);

        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN");

        Mockito.when(tokenService.generateToken(USUARIO, grantedAuthorities)).thenReturn(TOKEN);
        Optional<TokenDto> tokenDto = this.usuarioService.generaToken(USUARIO, PASSWORD);
        Assert.assertNotNull(tokenDto);
    }


}