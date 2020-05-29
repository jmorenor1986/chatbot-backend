package co.com.santander.chatbot.accesodatos.service;

import co.com.santander.chatbot.accesodatos.entity.ParametrosApp;

import java.util.Optional;

public interface ParametrosAppService {

    Optional<ParametrosApp> findByClave(String clave);

    Optional<ParametrosApp> save(ParametrosApp entity);
}
