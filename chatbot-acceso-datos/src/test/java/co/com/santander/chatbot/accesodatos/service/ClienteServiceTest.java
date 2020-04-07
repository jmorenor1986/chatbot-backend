package co.com.santander.chatbot.accesodatos.service;

import co.com.santander.chatbot.accesodatos.entity.Cliente;
import co.com.santander.chatbot.accesodatos.repository.UsuarioRepository;
import co.com.santander.chatbot.accesodatos.service.impl.UsuarioServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;
import java.util.Optional;

@SpringBootTest
public class ClienteServiceTest {

    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        usuarioService = new UsuarioServiceImpl(usuarioRepository);
    }

    @Test
    public void testConsultarUsuarioSuccess() {
        Mockito.when(usuarioRepository.consultarUsuarioXTelefonoId(new BigInteger("0"), "5270")).thenReturn(Cliente.builder().build());
        Optional<Boolean> result = usuarioService.consultarUsuario(new BigInteger("0"), "5270");
        Assert.assertNotNull(result);
        Assert.assertEquals(Boolean.TRUE, result.get());
    }

    @Test
    public void testConsultarUsuarioError() {
        Mockito.when(usuarioRepository.consultarUsuarioXTelefonoId(new BigInteger("0"), "5270")).thenReturn(null);
        Optional<Boolean> result = usuarioService.consultarUsuario(new BigInteger("0"), "5270");
        Assert.assertNotNull(result);
        Assert.assertEquals(Boolean.FALSE, result.get());
    }
}
