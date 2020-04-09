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
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@SpringBootTest
public class UsuarioAppServiceImplTest {

    private UsuarioAppServiceImpl usuarioAppServiceImpl;
    @Mock
    private UsuarioAppRepository usuarioAppRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.usuarioAppServiceImpl = new UsuarioAppServiceImpl(usuarioAppRepository,passwordEncoder);
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

    @Test
    public void validateLoginUserSuccess(){
        Optional<UsuarioApp> usuarioResp = Optional.of(UsuarioApp.builder()
                .usuario("jnsierra")
                .contra("$2a$10$wTzQOUmHqTduKyShciUmiuguuG8N9Miz9TZ1b9sjd8qO1VirZR4yi")
                .id(Long.valueOf(1))
                .build());
        Mockito.when(usuarioAppRepository.findByUsuario("jnsierra")).thenReturn(usuarioResp);
        Mockito.when(passwordEncoder.matches("1234", "$2a$10$wTzQOUmHqTduKyShciUmiuguuG8N9Miz9TZ1b9sjd8qO1VirZR4yi")).thenReturn(true);
        Optional<Boolean> rta = usuarioAppServiceImpl.validateLoginUser("jnsierra", "1234");
        Assert.assertNotNull(rta);
        Assert.assertEquals(rta.get(), Boolean.TRUE);
    }

    @Test
    public void validateLoginUserFailed(){
        Optional<UsuarioApp> usuarioResp = Optional.of(UsuarioApp.builder()
                .usuario("jnsierra")
                .contra("$2a$10$wTzQOUmHqTduKyShciUmiuguuG8N9Miz9TZ1b9sjd8qO1VirZR4yi")
                .id(Long.valueOf(1))
                .build());
        Mockito.when(usuarioAppRepository.findByUsuario("jnsierra")).thenReturn(usuarioResp);
        Mockito.when(passwordEncoder.matches("1234", "123")).thenReturn(false);
        Optional<Boolean> rta = usuarioAppServiceImpl.validateLoginUser("jnsierra", "1234");
        Assert.assertNotNull(rta);
        Assert.assertEquals(rta.get(), Boolean.FALSE);
    }
}