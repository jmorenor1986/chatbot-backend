package co.com.santander.chatbot.accesodatos.repository;

import co.com.santander.chatbot.accesodatos.entity.InfoWhatsAppWS;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class InfoWhatsAppWSRepositoryTest_IT {
    @Autowired
    private InfoWhatsAppWSRepository infoWhatsAppWSRepository;

    @Before
    public void setUp() {
    }

    @Test
    public void testSaveSUCCESS() {
        InfoWhatsAppWS insert = InfoWhatsAppWS.builder()
                .numCreditoBanco("12345678")
                .numeroIdentificacion("1234567")
                .numPeticionServicio(Long.valueOf(1))
                .fechaEnvio(new Date())
                .estado(Long.valueOf(1))
                .build();
        InfoWhatsAppWS result = infoWhatsAppWSRepository.save(insert);
        Assert.assertNotNull(result);
        Assert.assertNotNull(result.getId());
    }

    @Test
    public void testFindByNumCreditoBancoAndNumeroIdentificacionAndNumPeticionServicioSUCCESS() {
        List<InfoWhatsAppWS> result = infoWhatsAppWSRepository.findByNumCreditoBancoAndNumeroIdentificacionAndNumPeticionServicio("12345678", "1234", Long.valueOf("3"));
        Assert.assertNotNull(result);
        Assert.assertTrue(result.size() > 0);
    }

    @Test
    public void testFindByNumCreditoBancoAndNumeroIdentificacionAndNumPeticionEstadoFAILED() {
        List<InfoWhatsAppWS> result = infoWhatsAppWSRepository.findByNumCreditoBancoAndNumeroIdentificacionAndNumPeticionServicio("aaqde", "1234", Long.valueOf("3"));
        Assert.assertNotNull(result);
        Assert.assertTrue(result.size() == 0);
    }


}