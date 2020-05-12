package co.com.santander.accesorecursos.soap.controller;

import co.com.santander.accesorecursos.soap.resources.documentos.ConsultarDocumentosResponse;
import co.com.santander.accesorecursos.soap.service.DocumentosService;
import co.com.santander.chatbot.domain.payload.enviarextracto.ConsultarDocumentoPayload;
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
        ResponseEntity<List<ConsultarDocumentosResponse>> result = documentosController.consultaDocumentos(ConsultarDocumentoPayload.builder().build());
        Assert.assertNotNull(result);
        Assert.assertEquals(200, result.getStatusCodeValue());
    }
}
