package co.com.santander.chatbot.accesodatos.repository;

import co.com.santander.chatbot.accesodatos.entity.Cliente;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigInteger;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class ClienteRepositoryTest_IT {

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    public void testConsultaUsuarioSuccess() {
        Cliente result = clienteRepository.consultarClienteXTelefonoId(new BigInteger("3014001617"), "5270");
        Assert.assertNotNull(result);
    }

    @Test
    public void testConsultarUsuarioNotExist() {
        Cliente result = clienteRepository.consultarClienteXTelefonoId(new BigInteger("3104001617"), "5270");
        Assert.assertNull(result);
    }
}
