package co.com.santander.chatbot.backend.web.service;

import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public interface TokenService {

    String generateToken(String user, List<GrantedAuthority> grantedAuthorities);
}
