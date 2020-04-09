package co.com.santander.chatbot.accesodatos.controller;

import co.com.santander.chatbot.accesodatos.entity.UsuarioApp;
import co.com.santander.chatbot.accesodatos.service.UsuarioAppService;
import co.com.santander.chatbot.domain.payload.accesodatos.UsuarioAppPayload;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

@SpringBootTest
public class UsuarioAppControllerTest {

    private UsuarioAppController usuarioAppController;
    @Mock
    private UsuarioAppService usuarioAppService;
    private ModelMapper modelMapper;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.modelMapper = new ModelMapper();
        this.usuarioAppController = new UsuarioAppController(usuarioAppService, modelMapper);
    }

    @Test
    public void testCreateUser() {
        UsuarioAppPayload usuarioAppPayloadInput = UsuarioAppPayload.builder()
                .usuario("jsierra")
                .contra("1234")
                .build();
        UsuarioApp usuarioApp = UsuarioApp.builder()
                .usuario("jsierra")
                .contra("1234")
                .id(Long.valueOf(1))
                .build();
        Mockito.when(usuarioAppService.createUser(ArgumentMatchers.any())).thenReturn(usuarioApp);
        ResponseEntity<UsuarioAppPayload> rta = usuarioAppController.createUser(usuarioAppPayloadInput);
        Assert.assertNotNull(rta);
        Assert.assertEquals(HttpStatus.OK, rta.getStatusCode());
    }

    @Test
    public void testValidateUserSUCCESS() {
        UsuarioAppPayload usuarioAppPayloadInput = UsuarioAppPayload.builder()
                .usuario("jsierra")
                .contra("1234")
                .build();
        Mockito.when(usuarioAppService.validateLoginUser("jsierra", "1234")).thenReturn(Optional.of(Boolean.TRUE));
        ResponseEntity<Boolean> rta = usuarioAppController.validateUser(usuarioAppPayloadInput);
        Assert.assertNotNull(rta);
        Assert.assertEquals(HttpStatus.OK, rta.getStatusCode());

    }

    @Test
    public void testValidateUserUNAUTHORIZED() {
        UsuarioAppPayload usuarioAppPayloadInput = UsuarioAppPayload.builder()
                .usuario("jsierra")
                .contra("1234")
                .build();
        Mockito.when(usuarioAppService.validateLoginUser("jsierra", "1234")).thenReturn(Optional.of(Boolean.FALSE));
        ResponseEntity<Boolean> rta = usuarioAppController.validateUser(usuarioAppPayloadInput);
        Assert.assertNotNull(rta);
        Assert.assertEquals(HttpStatus.UNAUTHORIZED, rta.getStatusCode());

    }


}