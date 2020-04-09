package co.com.santander.chatbot.accesodatos.repository;

import co.com.santander.chatbot.accesodatos.entity.UsuarioApp;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UsuarioAppRepositoryTest_IT {

    @Autowired
    private UsuarioAppRepository usuarioAppRepository;

    @Before
    public void setUp(){
        UsuarioApp usuarioApp = UsuarioApp.builder()
                .contra("123")
                .usuario("jsierra")
                .build();
        usuarioAppRepository.save(usuarioApp);
    }

    @Test
    public void testFindByUsuarioSUCESS(){
        Optional<UsuarioApp>  usuarioApp =  usuarioAppRepository.findByUsuario("jsierra");
        Assert.assertEquals(Boolean.TRUE, usuarioApp.isPresent());
        Assert.assertNotNull(usuarioApp.get());
        Assert.assertEquals("jsierra", usuarioApp.get().getUsuario());
    }

}