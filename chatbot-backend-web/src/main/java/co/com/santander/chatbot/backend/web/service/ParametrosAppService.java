package co.com.santander.chatbot.backend.web.service;


import java.util.Optional;

public interface ParametrosAppService {

    Optional<String> getParamByKey(String clave);
}
