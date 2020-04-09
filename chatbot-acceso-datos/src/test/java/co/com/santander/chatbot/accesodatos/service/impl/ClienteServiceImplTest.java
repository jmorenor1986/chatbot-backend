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

import java.math.BigInteger;
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
        Mockito.when(clienteRepository.consultarClienteXTelefonoId(new BigInteger("0"), new BigInteger("5270"))).thenReturn(Cliente.builder().build());
        Optional<Boolean> result = clienteService.consultarCliente(new BigInteger("0"), "5270");
        Assert.assertNotNull(result);
        Assert.assertEquals(Boolean.TRUE, result.get());
    }

    @Test
    public void testConsultarUsuarioError() {
        Mockito.when(clienteRepository.consultarClienteXTelefonoId(new BigInteger("0"), new BigInteger("5270"))).thenReturn(null);
        Optional<Boolean> result = clienteService.consultarCliente(new BigInteger("0"), "5270");
        Assert.assertNotNull(result);
        Assert.assertEquals(Boolean.FALSE, result.get());
    }
}
