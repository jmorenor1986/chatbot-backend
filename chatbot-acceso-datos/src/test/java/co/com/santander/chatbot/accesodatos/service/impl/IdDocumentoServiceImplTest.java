package co.com.santander.chatbot.accesodatos.service.impl;

import co.com.santander.chatbot.accesodatos.entity.IdDocumento;
import co.com.santander.chatbot.accesodatos.repository.IdDocumentoRepository;
import co.com.santander.chatbot.accesodatos.service.IdDocumentoService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class IdDocumentoServiceImplTest {

    private IdDocumentoService idDocumentoService;
    @Mock
    private IdDocumentoRepository idDocumentoRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.idDocumentoService = new IdDocumentoServiceImpl(idDocumentoRepository);
    }
    @Test
    public void testSave(){
        IdDocumento responseMockito = IdDocumento.builder()
                .mes("1")
                .anio("2020")
                .fechaIni("01/01/2020")
                .fechaFin("05/05/2020")
                .idDocumentos("12345678iuytrew")
                .insercion(new Date())
                .build();

        Mockito.doReturn(responseMockito).when(idDocumentoRepository).save(Mockito.any());

        IdDocumento response = idDocumentoService.save(IdDocumento.builder()
                .id(1L)
                .mes("1")
                .anio("2020")
                .fechaIni("01/01/2020")
                .fechaFin("05/05/2020")
                .idDocumentos("12345678iuytrew")
                .insercion(new Date())
                .build());
        Assert.assertNotNull(response);
    }

}