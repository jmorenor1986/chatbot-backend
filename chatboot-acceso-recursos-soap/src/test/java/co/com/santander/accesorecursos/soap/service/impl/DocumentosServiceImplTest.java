package co.com.santander.accesorecursos.soap.service.impl;

import co.com.santander.accesorecursos.soap.clients.DocumentosClient;
import co.com.santander.accesorecursos.soap.config.properties.ServiceProperties;
import co.com.santander.accesorecursos.soap.service.DocumentosService;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.token.TokenService;

@SpringBootTest
public class DocumentosServiceImplTest {
    private DocumentosService documentosService;
    @Mock
    private ServiceProperties serviceProperties;
    @Mock
    private DocumentosClient documentosClient;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private TokenService tokenService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(serviceProperties.getCliente()).thenReturn("121212");
        Mockito.when(serviceProperties.getProducto()).thenReturn("121212");
        Mockito.when(serviceProperties.getUsuarioRemoto()).thenReturn("121212");
        this.documentosService = new DocumentosServiceImpl(serviceProperties, documentosClient, modelMapper, tokenService);
    }

}