package co.com.santander.chatbot.backend.web.controller;

import co.com.santander.chatbot.backend.web.exceptions.CustomAuthenticationException;
import co.com.santander.chatbot.backend.web.service.UsuarioService;
import co.com.santander.chatbot.domain.dto.security.TokenDto;
import co.com.santander.chatbot.domain.dto.security.UsuarioDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

@SpringBootTest
public class LoginControllerTest {

    private LoginController loginController;
    @Mock
    private UsuarioService usuarioService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.loginController = new LoginController(usuarioService);
    }

    @Test
    public void testLoginSUCCESS() throws CustomAuthenticationException {
        UsuarioDto usuarioDto = UsuarioDto.builder()
                .correo("jnsierrac@gmail.com")
                .contrasena("123")
                .build();
        Mockito.when(usuarioService.generaToken(usuarioDto.getCorreo(), usuarioDto.getContrasena())).thenReturn(Optional.of(TokenDto.builder()
                .token("12345876543gdghbfr5yhbvfrtg")
                .time(3600)
                .build()));
        ResponseEntity<TokenDto> rta = loginController.login(usuarioDto);
        Assert.assertNotNull(rta);
    }

    @Test
    public void testLoginFAILED() throws CustomAuthenticationException {
        UsuarioDto usuarioDto = UsuarioDto.builder()
                .correo("jnsierrac@gmail.com")
                .contrasena("12343")
                .build();
        Mockito.when(usuarioService.generaToken(usuarioDto.getCorreo(), usuarioDto.getContrasena())).thenReturn(Optional.empty());
        ResponseEntity<TokenDto> rta = loginController.login(usuarioDto);
        Assert.assertEquals( HttpStatus.UNAUTHORIZED, rta.getStatusCode());
    }


}