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

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class InfoWhatsAppWSRepositoryTest_IT {
    @Autowired
    private InfoWhatsAppWSRepository infoWhatsAppWSRepository;

    @Before
    public void setUp(){

    }
    @Test
    public void testSaveSUCCESS(){
        InfoWhatsAppWS insert = InfoWhatsAppWS.builder()
                .numCreditoBanco("12345678")
                .numeroIdentificacion("1234567")
                .numPeticionServicio(Long.valueOf(1))
                .FechaEnvio(new Date())
                .estado(Long.valueOf(1))
                .build();
        InfoWhatsAppWS result = infoWhatsAppWSRepository.save(insert);
        Assert.assertNotNull(result);
        Assert.assertNotNull(result.getId());
    }

}