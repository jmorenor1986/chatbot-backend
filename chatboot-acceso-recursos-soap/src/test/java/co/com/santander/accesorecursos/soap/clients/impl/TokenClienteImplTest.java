package co.com.santander.accesorecursos.soap.clients.impl;

import co.com.santander.accesorecursos.soap.clients.TokenCliente;
import co.com.santander.accesorecursos.soap.config.properties.ServiceProperties;
import co.com.santander.accesorecursos.soap.resources.token.ComputecSTSDelegate;
import co.com.santander.accesorecursos.soap.resources.token.ComputecSTSDelegateImpl;
import co.com.santander.accesorecursos.soap.resources.token.ComputecSTSService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TokenClienteImplTest {

    private TokenCliente tokenCliente;
    @Mock
    private ComputecSTSService getServiceToken;
    @Mock
    private ServiceProperties serviceProperties;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        tokenCliente = new TokenClienteImpl(getServiceToken, serviceProperties);
    }

    @Test
    public void testGenerarTokenSuccess() {
        this.serviceProperties.setUser("1121212");
        this.serviceProperties.setPassword("sdsdsd");
        ComputecSTSDelegate computecSTSDelegate = new ComputecSTSDelegateImpl();
        Mockito.when(getServiceToken.getComputecSTSPort()).thenReturn(computecSTSDelegate);
        String result = tokenCliente.generarToken();
        Assert.assertNotNull(result);
    }

}