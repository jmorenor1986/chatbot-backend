package co.com.santander.chatbot.backend.web.service.impl;

import co.com.santander.chatbot.backend.web.dto.TokenDto;
import co.com.santander.chatbot.backend.web.service.TokenService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class UsuarioServiceImplTest {

    private UsuarioServiceImpl usuarioService;
    @Mock
    private TokenService tokenService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.usuarioService = new UsuarioServiceImpl(tokenService);
    }
    @Test
    public void testGeneraToken(){
        Optional<TokenDto> tokenDto =  this.usuarioService.generaToken();
        Assert.assertNotNull(tokenDto);
    }


}