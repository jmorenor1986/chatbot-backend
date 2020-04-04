package co.com.santander.chatbot.backend.web.service.impl;

import co.com.santander.chatbot.backend.web.service.KeepAliveService;
import org.springframework.stereotype.Service;

@Service
public class KeepAliveServiceImpl implements KeepAliveService {
    @Override
    public String getAlive() {
        return "Hi!  it´s alive";
    }
}
