package co.com.santander.chatbot.accesodatos.repository;

import co.com.santander.chatbot.accesodatos.entity.Canal;
import co.com.santander.chatbot.accesodatos.entity.Log;
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

import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class LogClienteRepositoryTest_IT {

    @Autowired
    private LogClienteRepository logClienteRepository;

    @Before
    public void setUp(){
    }

    @Test
    public void testInsertLog(){
        Log logEntity = Log.builder()
                .canal(Canal.builder().id(1L).build())
                .feha(new Date())
                .traza("traza")
                .servicio(Servicio.builder().id(1L).build())
                .telefono("3229032614")
                .nombreCliente("Jesus Nicolas Sierra Chaparro")
                .build();
        Log logResponse =  logClienteRepository.save(logEntity);
        Assert.assertNotNull(logResponse);
    }

}