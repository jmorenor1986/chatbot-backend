package co.com.santander.chatbot.backend.web.service.impl;

import co.com.santander.chatbot.backend.web.service.FormatoMonedaService;
import co.com.santander.chatbot.backend.web.service.ParametrosAppService;
import co.com.santander.chatbot.domain.payload.service.enlacePse.ResponseEnlacePsePayload;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class FormatoMonedaServiceImplTest {

    private FormatoMonedaService formatoMonedaService;
    @Mock
    private ParametrosAppService parametrosAppService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.formatoMonedaService = new FormatoMonedaServiceImpl(parametrosAppService);
    }

    @Test
    public void test(){
        Mockito.doReturn(Optional.of("#,###.###")).when(parametrosAppService).getParamByKey(Mockito.any(), Mockito.any());

        ResponseEnlacePsePayload response = ResponseEnlacePsePayload.builder()
                .valorPagar("1000000")
                .valorTotal("1000010")
                .valorMora("10")
                .build();

        ResponseEnlacePsePayload request =  formatoMonedaService.currencyFormat("1", response);
        Assert.assertNotNull(request);
    }

}