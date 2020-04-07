package co.com.santander.chatbot.accesodatos.repository;

import co.com.santander.chatbot.accesodatos.entity.Usuario;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class UsuarioRepositoryTest_IT {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    public void testConsultaUsuarioSuccess() {
        Usuario result = usuarioRepository.consultarUsuarioXTelefonoId(new Long("3014001617"), "5270");
        Assert.assertNotNull(result);
    }

    @Test
    public void testConsultarUsuarioNotExist() {
        Usuario result = usuarioRepository.consultarUsuarioXTelefonoId(new Long("3104001617"), "5270");
        Assert.assertNull(result);
    }
}
