package co.com.santander.chatbot.backend.web.service;

import co.com.santander.chatbot.backend.web.service.impl.KeepAliveServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class KeepAliveServiceTest {

    private KeepAliveService keepAliveService;

    @Before
    public void setUp() {
        keepAliveService = new KeepAliveServiceImpl();
    }

    @Test
    public void testKeepAliveServiceSuccess() {
        String result = keepAliveService.getAlive();
        Assert.assertNotNull(result);
    }
}
