package co.com.santander.chatbot.accesodatos.service.impl;

import co.com.santander.chatbot.accesodatos.entity.InfoWhatsAppWS;
import co.com.santander.chatbot.accesodatos.repository.InfoWhatsAppWSRepository;
import co.com.santander.chatbot.accesodatos.service.InfoWhatsAppWSService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class InfoWhatsAppWSServiceImplTest {

    private InfoWhatsAppWSService infoWhatsAppWSService;
    @Mock
    private InfoWhatsAppWSRepository infoWhatsAppWSRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        infoWhatsAppWSService = new InfoWhatsAppWSServiceImpl(infoWhatsAppWSRepository);
    }
    @Test
    public void testSaveEntitySUCCESS(){
        InfoWhatsAppWS inputEntity = InfoWhatsAppWS.builder()
                .numCreditoBanco("12345678")
                .numeroIdentificacion("1234567")
                .numPeticionServicio(Long.valueOf(1))
                .fechaEnvio(new Date())
                .estado(Long.valueOf(1))
                .build();
        InfoWhatsAppWS outputEntity = InfoWhatsAppWS.builder()
                .id(Long.valueOf(1))
                .numCreditoBanco("12345678")
                .numeroIdentificacion("1234567")
                .numPeticionServicio(Long.valueOf(1))
                .fechaEnvio(new Date())
                .estado(Long.valueOf(1))
                .build();
        Mockito.when(infoWhatsAppWSRepository.save(inputEntity)).thenReturn(outputEntity);
        Optional<InfoWhatsAppWS> respuesta = infoWhatsAppWSService.saveEntity(inputEntity);
        Assert.assertTrue(respuesta.isPresent());
        Assert.assertNotNull(respuesta.get());
    }

    @Test
    public void validateExistingProcessSUCCESS(){
        String numCreditoBanco = "12345678";
        String numeroIdentificacion = "1234";
        Long numPeticionServicio = Long.valueOf("1");
        Long estado = Long.valueOf("0");

        final List<InfoWhatsAppWS> respuestaRepo = new ArrayList<>();
        InfoWhatsAppWS item = InfoWhatsAppWS.builder().build();
        respuestaRepo.add(item);


        Mockito.when(infoWhatsAppWSRepository
                .findByNumCreditoBancoAndNumeroIdentificacionAndNumPeticionServicio(numCreditoBanco, numeroIdentificacion,numPeticionServicio))
                .thenReturn(respuestaRepo);
        List<InfoWhatsAppWS> respuesta = infoWhatsAppWSService.validateExistingProcess(numCreditoBanco, numeroIdentificacion, numPeticionServicio);

        Assert.assertNotNull(respuesta);
    }

    @Test
    public void validateExistingProcessFAILED(){
        String numCreditoBanco = "12345678";
        String numeroIdentificacion = "1234";
        Long numPeticionServicio = Long.valueOf("1");
        Long estado = Long.valueOf("0");

        final List<InfoWhatsAppWS> respuestaRepo = new ArrayList<>();


        Mockito.when(infoWhatsAppWSRepository
                .findByNumCreditoBancoAndNumeroIdentificacionAndNumPeticionServicio(numCreditoBanco, numeroIdentificacion,numPeticionServicio))
                .thenReturn(respuestaRepo);
        List<InfoWhatsAppWS> respuesta = infoWhatsAppWSService.validateExistingProcess(numCreditoBanco, numeroIdentificacion, numPeticionServicio);

        Assert.assertNotNull(respuesta);
    }
}