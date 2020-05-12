package co.com.santander.accesorecursos.soap.service.impl;

import co.com.santander.accesorecursos.soap.clients.DocumentosCliente;
import co.com.santander.accesorecursos.soap.resources.documentos.ConsultarDocumentosResponse;
import co.com.santander.accesorecursos.soap.service.DocumentosService;
import co.com.santander.chatbot.domain.payload.enviarextracto.ConsultarDocumentoPayload;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class DocumentosServiceImplTest {
    private DocumentosService documentosService;
    @Mock
    private DocumentosCliente documentosCliente;
    @Mock
    private ModelMapper modelMapper;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.documentosService = new DocumentosServiceImpl(documentosCliente, modelMapper);
    }

    @Test
    public void consultarDocumentosError() {
        Mockito.when(documentosCliente.consultarDocumentos(Mockito.any())).thenReturn(new ArrayList<>());
        List<ConsultarDocumentosResponse> result = this.documentosService.consultarDocumentos(ConsultarDocumentoPayload.builder().build());
        Assert.assertNotNull(result);
    }

}