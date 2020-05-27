package co.com.santander.chatbot.accesodatos.service;

import co.com.santander.chatbot.accesodatos.entity.ParametrosApp;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ParametrosAppService {

    Optional<ParametrosApp> findByClave(String clave);
}
