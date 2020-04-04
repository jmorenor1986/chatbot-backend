package co.com.santander.chatbot.backend.web.controller;

import co.com.santander.chatbot.backend.web.service.KeepAliveService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class KeepAliveControllerTest {

    public static final String MESSAGE_ALIVE = "Hi!!, its alive";
    @Mock
    private KeepAliveService keepAliveService;
    private KeepAliveController keepAliveController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        keepAliveController = new KeepAliveController(keepAliveService);
    }

    @Test
    public void testKeepAliveControllerSuccess() {
        Mockito.when(keepAliveService.getAlive()).thenReturn(MESSAGE_ALIVE);
        String result = keepAliveController.getAlive();
        Assert.assertNotNull(result);
    }
}
