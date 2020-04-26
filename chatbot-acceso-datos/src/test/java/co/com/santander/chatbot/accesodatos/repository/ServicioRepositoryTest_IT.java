package co.com.santander.chatbot.accesodatos.repository;

import co.com.santander.chatbot.accesodatos.entity.Servicio;
import co.com.santander.chatbot.domain.enums.ServiciosEnum;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class ServicioRepositoryTest_IT {
    @Autowired
    private ServicioRepository servicioRepository;

    @Before
    public void setUp(){
    }
    @Test
    public void testFindByNombre(){
        Optional<Servicio> servicio = servicioRepository.findByNombre(ServiciosEnum.SERVICIO_OBTENER_CREDITOS.name());
        Assert.assertNotNull(servicio);
        Assert.assertTrue(servicio.isPresent());
        Assert.assertNotNull(servicio.get());
    }

}