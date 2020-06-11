package co.com.santander.chatbot.domain.validators.impl;

import co.com.santander.chatbot.domain.validators.exceptions.InvalidNumVerificadorException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class InvalidNumVerificadorValidatorTest {

    private InvalidNumVerificadorValidator invalidNumVerificadorValidator;

    @Before
    public void setUp(){
        this.invalidNumVerificadorValidator = new InvalidNumVerificadorValidator();
    }

    @Test
    public void testIsValidSUCCESS(){
        boolean response = invalidNumVerificadorValidator.isValid("1JEnZBkGUjt6YzbxAD805mIa5vmZyn2Rtjlr/pQ0Ll4=", null);
        Assert.assertTrue(response);
    }

    @Test(expected = InvalidNumVerificadorException.class)
    public void testIsValidFAILED(){
        boolean response = invalidNumVerificadorValidator.isValid("12343", null);
        Assert.assertTrue(response);
    }

    @Test(expected = NullPointerException.class)
    public void testInit(){
        invalidNumVerificadorValidator.initialize(null);
    }

}