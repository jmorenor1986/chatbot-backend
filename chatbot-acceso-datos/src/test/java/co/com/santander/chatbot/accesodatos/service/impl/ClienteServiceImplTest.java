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
import org.springframework.http.HttpStatus;

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
        Mockito.when(clienteRepository.consultarXCedulaYTelefono("0", "%".concat("5270"))).thenReturn(Cliente.builder().build());
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
                .id(Long.valueOf("1"))
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
}
