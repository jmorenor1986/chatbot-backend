package co.com.santander.chatbot.backend.web.common.utilities;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StringUtilitiesTest {


    @Test
    public void testCurrencyFormatSimple(){
        Long number = 1000000000L;
        String format = "#,###.###";

        String result = StringUtilities.formatoMoneda(format, number);
        Assert.assertNotNull(result);
        Assert.assertEquals("1.000.000.000", result);
    }

    @Test
    public void testCurrencyFormatSimboloMoneda(){
        Long number = 1000000000L;
        String format = "$ #,###.###";

        String result = StringUtilities.formatoMoneda(format, number);
        Assert.assertNotNull(result);
        Assert.assertEquals("$ 1.000.000.000", result);
    }



}