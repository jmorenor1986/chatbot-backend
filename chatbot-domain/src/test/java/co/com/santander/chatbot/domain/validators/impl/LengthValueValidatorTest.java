package co.com.santander.chatbot.domain.validators.impl;

import co.com.santander.chatbot.domain.validators.exceptions.LengthValuesException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LengthValueValidatorTest {

    private LengthValueValidator lengthValueValidator;
    @Before
    public void setUp(){
        this.lengthValueValidator = new LengthValueValidator();
        this.lengthValueValidator.setMin(10);
        this.lengthValueValidator.setMax(12);
    }
    @Test
    public void testIsValidStringSUCCESS(){
        boolean response = lengthValueValidator.isValid("12345678901", null);
        Assert.assertTrue(response);
    }

    @Test(expected = LengthValuesException.class)
    public void testIsValidStringFAILED_UP(){
        lengthValueValidator.isValid("1234567890123", null);
    }

    @Test(expected = LengthValuesException.class)
    public void testIsValidStringFAILED_DOWN(){
        lengthValueValidator.isValid("123", null);
    }


    @Test
    public void testIsValidLongSUCCESS(){
        boolean response = lengthValueValidator.isValid(12345678901L, null);
        Assert.assertTrue(response);
    }

    @Test(expected = LengthValuesException.class)
    public void testIsValidLongFAILED_UP(){
        lengthValueValidator.isValid(1234567890123L, null);
    }

    @Test(expected = LengthValuesException.class)
    public void testIsValidLongFAILED_DOWN(){
        lengthValueValidator.isValid(123L, null);
    }


    @Test
    public void testIsValidIntegerSUCCESS(){
        boolean response = lengthValueValidator.isValid(1234567890, null);
        Assert.assertTrue(response);
    }

    @Test(expected = LengthValuesException.class)
    public void testIsValidIntegerFAILED_DOWN(){
        lengthValueValidator.isValid(123, null);
    }

}