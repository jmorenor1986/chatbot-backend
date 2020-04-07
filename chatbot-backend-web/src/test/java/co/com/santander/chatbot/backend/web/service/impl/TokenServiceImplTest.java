package co.com.santander.chatbot.backend.web.service.impl;

import co.com.santander.chatbot.backend.web.service.TokenService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TokenServiceImplTest {

    private final static String SECRET = "a6e21884876f43819523c31033fa697e";
    private final static String USER = "jnsierrac@gmail.com";

    private TokenService tokenService;
    private List<GrantedAuthority> grantedAuthorities;

    @Before
    public void setUp(){
        this.tokenService = new TokenServiceImpl(SECRET);
        grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN");
    }

    @Test
    public void testGenerateToken(){
        String token = tokenService.generateToken(USER, grantedAuthorities);
        Assert.assertNotNull(token);
    }

}