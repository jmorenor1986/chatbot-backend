package co.com.santander.chatbot.accesodatos.controller;

import co.com.santander.chatbot.accesodatos.controller.input.UsuarioInput;
import co.com.santander.chatbot.accesodatos.service.UsuarioService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

@SpringBootTest
public class UsuarioControllerTest {

    private UsuarioController usuarioController;
    @Mock
    private UsuarioService usuarioService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        usuarioController = new UsuarioController(usuarioService);
    }

    @Test
    public void testControllerSuccess() {
        Mockito.when(usuarioService.consultarUsuario(new Long("12345"), "5270")).thenReturn(Optional.of(Boolean.TRUE));
        ResponseEntity<Boolean> result = usuarioController.consultaUsuario(UsuarioInput.builder()
                .colaIdentificacion("5270")
                .telefono(new Long("12345"))
                .build());

        Assert.assertNotNull(result);
        Assert.assertEquals(result.getBody(), Boolean.TRUE);
        Assert.assertEquals(result.getStatusCodeValue(), 200);
    }

    @Test
    public void testControllerNotContent() {
        Mockito.when(usuarioService.consultarUsuario(new Long("12345"), "5270")).thenReturn(Optional.of(Boolean.FALSE));
        ResponseEntity<Boolean> result = usuarioController.consultaUsuario(UsuarioInput.builder()
                .colaIdentificacion("5270")
                .telefono(new Long("12345"))
                .build());

        Assert.assertNotNull(result);
        Assert.assertEquals(result.getBody(), Boolean.FALSE);
        Assert.assertEquals(result.getStatusCodeValue(), 204);
    }

}
