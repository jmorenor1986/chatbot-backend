package co.com.santander.accesorecursos.soap.service.impl;

import co.com.santander.accesorecursos.soap.clients.DocumentosCliente;
import co.com.santander.accesorecursos.soap.service.DocumentosService;
import co.com.santander.chatbot.domain.payload.enviarextracto.ConsultarDocumentoPayload;
import co.com.santander.chatbot.domain.payload.enviarextracto.ConsultarDocumentosPayloadResponse;
import co.com.santander.chatbot.domain.payload.enviarextracto.EnviarMailDocumentoPayload;
import co.com.santander.chatbot.domain.payload.enviarextracto.EnvioDocumentoMailResponsePayload;
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
    public void consultarDocumentos() {
        Mockito.when(documentosCliente.consultarDocumentos(Mockito.any())).thenReturn(new ArrayList<>());
        List<ConsultarDocumentosPayloadResponse> result = this.documentosService.consultarDocumentos(ConsultarDocumentoPayload.builder().build());
        Assert.assertNotNull(result);
    }

    @Test
    public void enviarMailDocumentoId() {
        Mockito.when(documentosCliente.enviarMailDocumentoId(Mockito.any())).thenReturn("Envio correctamente");
        EnvioDocumentoMailResponsePayload result = this.documentosService.enviarMailDocumentoId(EnviarMailDocumentoPayload.builder().build());
        Assert.assertNotNull(result);

    }


}