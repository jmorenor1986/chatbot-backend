package co.com.santander.chatbot.domain.validators.impl;

import co.com.santander.chatbot.domain.validators.exceptions.MandatoryFieldException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MandatoryValidatorTest {

    private MandatoryValidator mandatoryConstraint;

    @Before
    public void setUp(){
        mandatoryConstraint = new MandatoryValidator();
    }
    @Test( expected = MandatoryFieldException.class)
    public void testIsValidISNULL(){
        mandatoryConstraint.isValid(null, null);
    }

    @Test( expected = MandatoryFieldException.class)
    public void testIsValidEMPTY(){
        mandatoryConstraint.isValid("", null);
    }

    @Test
    public void testIsValid_STRING_SUCCESS(){
        boolean valida = mandatoryConstraint.isValid("test", null);
        Assert.assertTrue(valida);
    }

    @Test
    public void testIsValid_LONG_SUCCESS(){
        mandatoryConstraint.setZeroIsValid(true);
        boolean valida = mandatoryConstraint.isValid(0L, null);
        Assert.assertTrue(valida);
    }

    @Test(expected = MandatoryFieldException.class)
    public void testIsValid_LONG_FAILED(){
        mandatoryConstraint.setZeroIsValid(false);
        mandatoryConstraint.setMessage("Valor");
        mandatoryConstraint.isValid(0L, null);
    }

    @Test
    public void testIsValid_INTEGER_SUCCESS(){
        mandatoryConstraint.setZeroIsValid(true);
        boolean valida = mandatoryConstraint.isValid(0, null);
        Assert.assertTrue(valida);
    }

    @Test(expected = MandatoryFieldException.class)
    public void testIsValid_INTEGER_FAILED(){
        mandatoryConstraint.setZeroIsValid(false);
        mandatoryConstraint.setMessage("Valor");
        mandatoryConstraint.isValid(0, null);
    }


}