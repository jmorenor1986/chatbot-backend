package co.com.santander.chatbot.accesodatos.controller;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;

public class BaseControllerTest {

    @Autowired
    private ClienteController clienteController;

    @Before
    public void setUp() {
        StandaloneMockMvcBuilder standaloneMockMvcBuilder
                = MockMvcBuilders.standaloneSetup(clienteController);
        RestAssuredMockMvc.standaloneSetup(standaloneMockMvcBuilder);
    }
}
