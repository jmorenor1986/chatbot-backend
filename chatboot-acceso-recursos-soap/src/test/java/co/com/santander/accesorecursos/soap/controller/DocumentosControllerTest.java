package co.com.santander.accesorecursos.soap.controller;

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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class DocumentosControllerTest {

    @Mock
    private DocumentosService documentosService;
    private DocumentosController documentosController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        documentosController = new DocumentosController(documentosService);
    }

    @Test
    public void testConsultaDocumentosSuccess() {
        Mockito.when(documentosService.consultarDocumentos(Mockito.any())).thenReturn(new ArrayList<>());
        ResponseEntity<List<ConsultarDocumentosPayloadResponse>> result = documentosController.consultaDocumentos(ConsultarDocumentoPayload.builder().build());
        Assert.assertNotNull(result);
        Assert.assertEquals(200, result.getStatusCodeValue());
    }

    @Test
    public void testEnvioMailDocumentosSucess() {
        Mockito.when(documentosService.enviarMailDocumentoId(Mockito.any())).thenReturn(EnvioDocumentoMailResponsePayload.builder()
                .respuesta("OK")
                .build());
        ResponseEntity<EnvioDocumentoMailResponsePayload> result = documentosController.envioMailDocumento(EnviarMailDocumentoPayload.builder().build());
        Assert.assertNotNull(result);
        Assert.assertEquals(200, result.getStatusCodeValue());
    }
}
