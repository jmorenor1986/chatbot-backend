package co.com.santander.chatbot.domain.validators.impl;

import co.com.santander.chatbot.domain.validators.exceptions.AllowedValuesException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AllowedValuesValidatorTest {

    private AllowedValuesValidator allowedValuesValidator;

    @Before
    public void setUp(){
        allowedValuesValidator = new AllowedValuesValidator();
    }

    @Test
    public void testIsValidStringSUCCESS(){
        String valores[] = {"1","2"};
        allowedValuesValidator.setStringValues(valores);
        Boolean response = allowedValuesValidator.isValid("1", null);
        Assert.assertTrue(response);
    }

    @Test(expected = AllowedValuesException.class)
    public void testIsValidStringFAILED(){
        String valores[] = {"1","2"};
        allowedValuesValidator.setStringValues(valores);
        Boolean response = allowedValuesValidator.isValid("3", null);
        Assert.assertTrue(response);
    }

    @Test
    public void testIsValidLongSUCCESS(){
        long valores[] = {1L,2L};
        allowedValuesValidator.setLongValues(valores);
        Boolean response = allowedValuesValidator.isValid(1L, null);
        Assert.assertTrue(response);
    }

    @Test(expected = AllowedValuesException.class)
    public void testIsValidLongFAILED(){
        long valores[] = {1L,2L};
        allowedValuesValidator.setLongValues(valores);
        Boolean response = allowedValuesValidator.isValid(3L, null);
        Assert.assertTrue(response);
    }



}