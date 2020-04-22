package co.com.santander.chatbot.accesodatos.repository;

import co.com.santander.chatbot.accesodatos.entity.ParametrosServicio;
import co.com.santander.chatbot.domain.enums.ServiciosEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class ParametrosServicioRepositoryTest_IT {
    @Autowired
    private ParametrosServicioRepository parametrosServicioRepository;

    @Test
    public void testFindByNameServiceSUCCESS() {
        List<ParametrosServicio> result = parametrosServicioRepository.findByNameService(ServiciosEnum.SERVICIO_PAZ_Y_SALVO.getMessage());
        Assert.assertNotNull(result);
        Assert.assertTrue(result.size() > 0);
    }

}