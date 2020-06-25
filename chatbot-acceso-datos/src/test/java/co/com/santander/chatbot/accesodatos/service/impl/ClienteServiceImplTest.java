package co.com.santander.chatbot.accesodatos.service.impl;

import co.com.santander.chatbot.accesodatos.entity.Cliente;
import co.com.santander.chatbot.accesodatos.repository.ClienteRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ClienteServiceImplTest {

    private ClienteServiceImpl clienteService;

    @Mock
    private ClienteRepository clienteRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        clienteService = new ClienteServiceImpl(clienteRepository);
    }

    @Test
    public void testConsultarUsuarioSuccess() {
        List<Cliente> response = new ArrayList<>();
        response.add(Cliente.builder().build());
        Mockito.when(clienteRepository.consultarXCedulaYTelefono("0", "%".concat("5270"))).thenReturn(response);
        Optional<Boolean> result = clienteService.consultarCliente("0", "5270");
        Assert.assertNotNull(result);
        Assert.assertEquals(Boolean.TRUE, result.get());
    }

    @Test
    public void testConsultarUsuarioError() {
        Mockito.when(clienteRepository.consultarXCedulaYTelefono("0", "5270")).thenReturn(null);
        Optional<Boolean> result = clienteService.consultarCliente("0", "5270");
        Assert.assertNotNull(result);
        Assert.assertEquals(Boolean.FALSE, result.get());
    }

    @Test
    public void testConsultarLienteByTelefonoSUCCESS(){
        String telefono = "3005632010";
        List<Cliente> result = new ArrayList<>();
        Cliente item = Cliente.builder()
                .nombreCliente("LOPEZ LOPEZ LUIS EMILIO")
                .telefono("3005632010")
                .cedula("56789066")
                .email("elisabeth.becerra@samtel.co")
                .numerCredito("6000000456")
                .banco("BANCO COMERCIAL AVVILLAS")
                .estado("Cerrado")
                .idProducto("9991")
                .idBanco("52")
                .convenio("MARCALI INTERNACIONAL SA")
                .build();
        result.add(item);

        Mockito.when(clienteRepository.findByTelefono(telefono)).thenReturn(result);

        Optional<List<Cliente>> response = clienteService.consultarClienteByTelefono(telefono);

        Assert.assertNotNull(response);
        Assert.assertTrue(response.isPresent());
        Assert.assertTrue( response.get().size() > 0);

    }

    @Test
    public void testConsultarLienteByTelefonoFAILED(){
        String telefono = "3005632011";
        List<Cliente> result = new ArrayList<>();
        Mockito.when(clienteRepository.findByTelefono(telefono)).thenReturn(result);

        Optional<List<Cliente>> response = clienteService.consultarClienteByTelefono(telefono);

        Assert.assertNotNull(response);
        Assert.assertFalse(response.isPresent());
    }

    @Test
    public void testFindByTelefonoAndNumerCreditoSUCCESS(){
        String telefono = "3005632010";
        String numCredito = "123456789";
        List<Cliente> result = new ArrayList<>();
        Cliente item = Cliente.builder()
                .nombreCliente("LOPEZ LOPEZ LUIS EMILIO")
                .telefono("3005632010")
                .numerCredito("123456789")
                .cedula("56789066")
                .email("elisabeth.becerra@samtel.co")
                .numerCredito("6000000456")
                .banco("BANCO COMERCIAL AVVILLAS")
                .estado("Cerrado")
                .idProducto("9991")
                .idBanco("52")
                .convenio("MARCALI INTERNACIONAL SA")
                .build();
        result.add(item);

        Mockito.when( clienteRepository.findByTelefonoAndNumerCredito( telefono, numCredito ) ).thenReturn(result);

        Optional<Cliente> response =  clienteService.consultarClienteByTelefonoAndNumCredito(telefono, numCredito);

        Assert.assertNotNull(response);
        Assert.assertTrue(response.isPresent());
    }

    @Test
    public void testFindByTelefonoAndNumerCreditoFAILED(){
        String telefono = "3005632010";
        String numCredito = "1234567891234";
        List<Cliente> result = new ArrayList<>();

        Mockito.when( clienteRepository.findByTelefonoAndNumerCredito( telefono, numCredito ) ).thenReturn(result);

        Optional<Cliente> response =  clienteService.consultarClienteByTelefonoAndNumCredito(telefono, numCredito);

        Assert.assertNotNull(response);
        Assert.assertFalse(response.isPresent());
    }
    @Test
    public void validaCreditoByCedulaSUCCESS(){
        String credito = "6000000457";
        String cedula = "56789098";

        List<Cliente> result = new ArrayList<>();
        Cliente item = Cliente.builder()
                .nombreCliente("GOMEZ GARCIA LUISA CLRA")
                .telefono("3005632011")
                .cedula("56789098")
                .email("jesus.sierra@samtel.co")
                .numerCredito("6000000457")
                .banco("BANCO COMERCIAL AVVILLAS")
                .estado("Cerrado")
                .idProducto("200")
                .idBanco("52")
                .convenio("SIDA S.A.")
                .build();
        result.add(item);

        Mockito.when(clienteRepository.findByCedulaEndingWithAndNumerCredito(cedula, credito)).thenReturn(result);

        Optional<Boolean> validate = clienteService.validaCreditoByCedula(cedula, credito);
        Assert.assertNotNull(validate);
        Assert.assertTrue(validate.isPresent());
        Assert.assertTrue(validate.get());
    }

    @Test
    public void validaCreditoByCedulaFAILED(){
        String credito = "6000000457";
        String cedula = "56789098";

        List<Cliente> result = new ArrayList<>();

        Mockito.when(clienteRepository.findByCedulaEndingWithAndNumerCredito(cedula, credito)).thenReturn(result);

        Optional<Boolean> validate = clienteService.validaCreditoByCedula(cedula, credito);
        Assert.assertNotNull(validate);
        Assert.assertTrue(validate.isPresent());
        Assert.assertFalse(validate.get());
    }


    @Test
    public void testFindCedulaByCedulaAndCreditoSUCCESS(){
        String credito = "6000000457";
        String cedula = "56789098";

        List<Cliente> result = new ArrayList<>();
        Cliente item = Cliente.builder()
                .nombreCliente("GOMEZ GARCIA LUISA CLRA")
                .telefono("3005632011")
                .cedula("56789098")
                .email("jesus.sierra@samtel.co")
                .numerCredito("6000000457")
                .banco("BANCO COMERCIAL AVVILLAS")
                .estado("Cerrado")
                .idProducto("200")
                .idBanco("52")
                .convenio("SIDA S.A.")
                .build();
        result.add(item);

        Mockito.when(clienteRepository.findByCedulaEndingWithAndNumerCredito(cedula, credito)).thenReturn(result);

        Optional<String> validate = clienteService.findCedulaByCedulaAndCredito(cedula, credito);
        Assert.assertNotNull(validate);
        Assert.assertTrue(validate.isPresent());
        Assert.assertNotNull(validate.get());
    }

    @Test
    public void testFindCedulaByCedulaAndCreditoFAILED(){
        String credito = "6000000457";
        String cedula = "56789098";

        List<Cliente> result = new ArrayList<>();

        Mockito.when(clienteRepository.findByCedulaEndingWithAndNumerCredito(cedula, credito)).thenReturn(result);

        Optional<String> validate = clienteService.findCedulaByCedulaAndCredito(cedula, credito);
        Assert.assertNotNull(validate);
        Assert.assertFalse(validate.isPresent());
    }
}