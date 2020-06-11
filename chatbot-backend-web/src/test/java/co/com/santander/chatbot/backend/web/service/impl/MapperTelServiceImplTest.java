package co.com.santander.chatbot.backend.web.service.impl;

import co.com.santander.chatbot.backend.web.service.MapperTelService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MapperTelServiceImplTest {

    private MapperTelService mapperTelService;

    @Before
    public void setUp(){
        this.mapperTelService = new MapperTelServiceImpl();
    }

    @Test
    public void testMapTelDigitsSUCCESS(){
        String tel = "573229042718";
        String response = mapperTelService.mapTelDigits(tel);
        Assert.assertEquals("3229042718", response);
    }

    @Test
    public void testMapTelDigitsSUCCESS_WITHOUT_CUT(){
        String tel = "3229042718";
        String response = mapperTelService.mapTelDigits(tel);
        Assert.assertEquals("3229042718", response);
    }

}