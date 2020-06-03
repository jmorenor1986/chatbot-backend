package co.com.santander.chatbot.backend.web.common.utilities;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class DateUtilitiesTest {
    @Before
    public void setUp(){

    }

    @Test
    public void testStringToDate(){
        Date fecha = DateUtilities.stringToDate("01-01-2020");
        Assert.assertNull(fecha);
    }

}