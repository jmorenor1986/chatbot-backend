package co.com.santander.chatbot.accesodatos.service.impl;

import co.com.santander.chatbot.accesodatos.entity.ParametrosApp;
import co.com.santander.chatbot.accesodatos.repository.ParametrosAppRepository;
import co.com.santander.chatbot.accesodatos.service.ParametrosAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
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

    @Override
    public Optional<ParametrosApp> save(ParametrosApp entity) {
        return Optional.of( parametrosAppRepository.save(entity) );
    }
}
