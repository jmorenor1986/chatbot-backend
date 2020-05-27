package co.com.santander.chatbot.accesodatos.service.impl;

import co.com.santander.chatbot.accesodatos.entity.ParametrosApp;
import co.com.santander.chatbot.accesodatos.repository.ParametrosAppRepository;
import co.com.santander.chatbot.accesodatos.service.ParametrosAppService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ParametrosAppServiceImpl implements ParametrosAppService {

    private final ParametrosAppRepository parametrosAppRepository;

    @Autowired
    public ParametrosAppServiceImpl(ParametrosAppRepository parametrosAppRepository) {
        this.parametrosAppRepository = parametrosAppRepository;
    }

    @Override
    public Optional<ParametrosApp> findByClave(String clave) {
        return parametrosAppRepository.findByClave(clave);
    }
}
