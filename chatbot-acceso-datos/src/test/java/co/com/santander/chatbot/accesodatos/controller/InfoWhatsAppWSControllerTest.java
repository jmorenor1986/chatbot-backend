package co.com.santander.chatbot.accesodatos.controller;

import co.com.santander.chatbot.accesodatos.entity.InfoWhatsAppWS;
import co.com.santander.chatbot.accesodatos.service.InfoWhatsAppWSService;
import co.com.santander.chatbot.domain.payload.accesodatos.InfoWhatsAppWSPayload;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.Optional;

@SpringBootTest
public class InfoWhatsAppWSControllerTest {

    private InfoWhatsAppWSController infoWhatsAppWSController;
    private ModelMapper modelMapper;
    @Mock
    private InfoWhatsAppWSService infoWhatsAppWSService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.modelMapper = new ModelMapper();
        infoWhatsAppWSController = new InfoWhatsAppWSController(infoWhatsAppWSService, modelMapper);
    }

    @Test
    public void testSave(){
        InfoWhatsAppWSPayload payload = InfoWhatsAppWSPayload.builder()
                .numCreditoBanco("12345678")
                .numeroIdentificacion("1234567")
                .numPeticionServicio(Long.valueOf(1))
                .estado(Long.valueOf(1))
                .build();

        Optional<InfoWhatsAppWS> responseEntity = Optional.of(InfoWhatsAppWS.builder()
                .id(Long.valueOf(1))
                .numCreditoBanco("12345678")
                .numeroIdentificacion("1234567")
                .numPeticionServicio(Long.valueOf(1))
                .estado(Long.valueOf(1))
                .build());
        InfoWhatsAppWS entity = modelMapper.map(payload, InfoWhatsAppWS.class);
        Mockito.when(infoWhatsAppWSService.saveEntity(Mockito.any())).thenReturn(responseEntity);
        ResponseEntity<InfoWhatsAppWSPayload> response = infoWhatsAppWSController.save(payload);
        Assert.assertNotNull(response);
    }
}