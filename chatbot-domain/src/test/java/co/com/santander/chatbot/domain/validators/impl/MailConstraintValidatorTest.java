package co.com.santander.chatbot.domain.validators.impl;

import co.com.santander.chatbot.domain.validators.exceptions.MailConstraintException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MailConstraintValidatorTest {

    private MailConstraintValidator mailConstraintValidator;

    @Before
    public void setUp(){
        mailConstraintValidator = new MailConstraintValidator();
    }

    @Test
    public void testIsValidSUCCESS(){
        Boolean response = mailConstraintValidator.isValid("correo@gmail.com", null);
        Assert.assertTrue(response);
    }

    @Test(expected = MailConstraintException.class)
    public void testIsValidFAILED(){
        mailConstraintValidator.isValid("correo@gmail", null);
    }

}