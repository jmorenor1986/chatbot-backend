package co.com.santander.chatbot.domain.common.utilities;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;


@SpringBootTest
public class DateUtilitiesTest {
    @Test
    public void testSumMinutesToDate(){
        Date response = DateUtilities.sumMinutesToDate(new Date(), 100);
        Assert.assertNotNull(response);
    }
}