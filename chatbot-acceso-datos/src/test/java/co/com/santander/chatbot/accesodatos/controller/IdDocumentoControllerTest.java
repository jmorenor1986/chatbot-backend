package co.com.santander.chatbot.accesodatos.controller;

import co.com.santander.chatbot.accesodatos.entity.IdDocumento;
import co.com.santander.chatbot.accesodatos.service.IdDocumentoService;
import co.com.santander.chatbot.domain.payload.accesodatos.documento.IdDocumentoPayload;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

@SpringBootTest
public class IdDocumentoControllerTest {

    private IdDocumentoController idDocumentoController;
    @Mock
    private IdDocumentoService idDocumentoService;
    private ModelMapper mapper;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mapper = new ModelMapper();
        idDocumentoController = new IdDocumentoController(idDocumentoService, mapper);
    }

    @Test
    public void testSaveSUCCESS(){
        IdDocumentoPayload request = IdDocumentoPayload.builder()
                .fechaFin("01/05/2020")
                .fechaIni("01/01/2019")
                .idDocumentos("fkjfkldsjflkfdsj")
                .anio("2020")
                .mes("05")
                .producto("VEHICULO")
                .build();
        IdDocumento responseMockito = IdDocumento.builder()
                .id(1L)
                .fechaFin("01/05/2020")
                .fechaIni("01/01/2019")
                .idDocumentos("fkjfkldsjflkfdsj")
                .anio("2020")
                .mes("05")
                .producto("VEHICULO")
                .build();
        Mockito.doReturn(responseMockito).when(idDocumentoService).save(Mockito.any());
        ResponseEntity<IdDocumentoPayload> response = idDocumentoController.save(request);

        Assert.assertNotNull(response);
    }
    @Test
    public void testGetDocumentById(){
        IdDocumento responseMockito = IdDocumento.builder()
                .id(1L)
                .fechaFin("01/05/2020")
                .fechaIni("01/01/2019")
                .idDocumentos("fkjfkldsjflkfdsj")
                .anio("2020")
                .mes("05")
                .producto("VEHICULO")
                .build();
        Mockito.doReturn(Optional.of(responseMockito)).when(idDocumentoService).findById(1L);
        ResponseEntity response = idDocumentoController.getDocumentById(1L);
        Assert.assertNotNull(response);

    }

}