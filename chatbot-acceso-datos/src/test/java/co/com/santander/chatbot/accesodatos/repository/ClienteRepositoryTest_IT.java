package co.com.santander.chatbot.accesodatos.repository;

import co.com.santander.chatbot.accesodatos.entity.Cliente;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ClienteRepositoryTest_IT {

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    public void testConsultaUsuarioSuccess() {
        List<Cliente> listaCliente = clienteRepository.findAll();
        Cliente result = clienteRepository.consultarXCedulaYTelefono("3005632010", "%9066");
        Assert.assertNotNull(result);
    }

    @Test
    public void testConsultarUsuarioNotExist() {
        Cliente result = clienteRepository.consultarXCedulaYTelefono("3005632010", "5270");
        Assert.assertNull(result);
    }
    @Test
    public void testFindByTelefonoSUCCESS(){
        String telefono = "3005632010";
        List<Cliente> creditos = clienteRepository.findByTelefono(telefono);
        Assert.assertNotNull(creditos);
        Assert.assertTrue( creditos.size() > 0 );
    }

    @Test
    public void testFindByTelefonoFAILED(){
        String telefono = "3005632011";
        List<Cliente> creditos = clienteRepository.findByTelefono(telefono);
        Assert.assertNotNull(creditos);
        Assert.assertTrue( creditos.size() == 0 );
    }
}
