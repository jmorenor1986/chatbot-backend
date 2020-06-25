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
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ClienteRepositoryTest_IT {

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    public void testConsultaUsuarioSuccess() {
        List<Cliente> listaCliente = clienteRepository.findAll();
        List<Cliente> result = clienteRepository.consultarXCedulaYTelefono("3005632010", "%9066");
        Assert.assertNotNull(result);
    }

    @Test
    public void testConsultarUsuarioNotExist() {
        List<Cliente> result = clienteRepository.consultarXCedulaYTelefono("3005632010", "5270");
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

    @Test
    public void testFindByTelefonoAndNumerCreditoSUCCESS(){
        String telefono = "3005632010";
        String numCredito = "6000000456";
        List<Cliente> creditos = clienteRepository.findByTelefonoAndNumerCredito(telefono, numCredito);
        Assert.assertNotNull(creditos);
        Assert.assertTrue( creditos.size() > 0 );
    }

    @Test
    public void testFindByTelefonoAndNumerCreditoFAILED(){
        String telefono = "3005632015";
        String numCredito = "60000004567";
        List<Cliente> creditos = clienteRepository.findByTelefonoAndNumerCredito(telefono, numCredito);
        Assert.assertNotNull(creditos);
        Assert.assertTrue( creditos.size() == 0 );
    }
    @Test
    public void testFindByCedulaAndNumerCredito(){
        String numCredito = "6000000457";
        String cedula = "9098";
        List<Cliente> creditos = clienteRepository.findByCedulaEndingWithAndNumerCredito(cedula, numCredito);
        Assert.assertNotNull(creditos);
        Assert.assertTrue( creditos.size() > 0 );
    }

    @Test
    public void testFindByCedulaAndNumerCreditoEMPTY(){
        String numCredito = "600000045722222";
        String cedula = "9098";
        List<Cliente> creditos = clienteRepository.findByCedulaEndingWithAndNumerCredito(cedula, numCredito);
        Assert.assertNotNull(creditos);
        Assert.assertTrue( creditos.size() == 0 );
    }

    @Test
    public void testFindByCedulaAndNumerCreditoSUCCESS(){
        String numCredito = "6000000457";
        String cedula = "56789098";
        Optional<Cliente> cliente = clienteRepository.findByCedulaAndNumerCredito(cedula, numCredito);
        Assert.assertNotNull(cliente);
        Assert.assertTrue(cliente.isPresent());
    }

    @Test
    public void testFindByCedulaAndNumerCreditEMPTY(){
        String numCredito = "6000000457";
        String cedula = "56789098444444";
        Optional<Cliente> cliente = clienteRepository.findByCedulaAndNumerCredito(cedula, numCredito);
        Assert.assertNotNull(cliente);
        Assert.assertFalse(cliente.isPresent());
    }
}
