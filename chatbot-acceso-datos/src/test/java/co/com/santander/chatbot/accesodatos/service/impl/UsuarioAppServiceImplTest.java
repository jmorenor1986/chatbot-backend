package co.com.santander.chatbot.accesodatos.service.impl;

import co.com.santander.chatbot.accesodatos.entity.UsuarioApp;
import co.com.santander.chatbot.accesodatos.repository.UsuarioAppRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
public class UsuarioAppServiceImplTest {

    private UsuarioAppServiceImpl usuarioAppServiceImpl;
    @Mock
    private UsuarioAppRepository usuarioAppRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.usuarioAppServiceImpl = new UsuarioAppServiceImpl(usuarioAppRepository, new BCryptPasswordEncoder());
    }

    @Test
    public void testCreateUser() {
        //Preparo el usuario a insertar
        UsuarioApp usuarioInsert = UsuarioApp.builder()
                .usuario("jnsierra")
                .contra("1234567")
                .build();
        UsuarioApp usuarioResp = UsuarioApp.builder()
                .usuario("jnsierra")
                .contra("1234567")
                .id(Long.valueOf(1))
                .build();
        Mockito.when(usuarioAppRepository.save(usuarioInsert)).thenReturn(usuarioResp);
        UsuarioApp usuario = usuarioAppServiceImpl.createUser(usuarioInsert);
        Assert.assertNotNull(usuario);
    }

}