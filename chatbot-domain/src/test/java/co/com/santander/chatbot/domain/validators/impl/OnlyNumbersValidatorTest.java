package co.com.santander.chatbot.domain.validators.impl;

import co.com.santander.chatbot.domain.validators.exceptions.OnlyNumbersException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OnlyNumbersValidatorTest {

    private OnlyNumbersValidator onlyNumbersValidator;

    @Before
    public void setUp(){
        this.onlyNumbersValidator = new OnlyNumbersValidator();
    }

    @Test
    public void testIsValidSTRING_SUCCESS(){
        boolean response = this.onlyNumbersValidator.isValid("123456789", null);
        Assert.assertTrue(response);
    }

    @Test(expected = OnlyNumbersException.class)
    public void testIsValidSTRING_FAILED(){
        boolean response = this.onlyNumbersValidator.isValid("123456789X", null);
    }

    @Test
    public void testIsValidLONG_SUCCESS(){
        boolean response = this.onlyNumbersValidator.isValid(123456789L, null);
        Assert.assertTrue(response);
    }

    @Test
    public void testIsValidINT_SUCCESS(){
        boolean response = this.onlyNumbersValidator.isValid(1234567, null);
        Assert.assertTrue(response);
    }

}