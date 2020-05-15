package co.com.santander.chatbot.accesodatos.service.impl;

import co.com.santander.chatbot.accesodatos.entity.TerminosCondiciones;
import co.com.santander.chatbot.accesodatos.repository.TerminosCondicionesRepository;
import co.com.santander.chatbot.accesodatos.service.TerminosCondicionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Service
public class TerminosCondicionesServiceImpl implements TerminosCondicionesService {

    private final TerminosCondicionesRepository terminosCondicionesRepository;

    @Autowired
    public TerminosCondicionesServiceImpl(TerminosCondicionesRepository terminosCondicionesRepository) {
        this.terminosCondicionesRepository = terminosCondicionesRepository;
    }

    @Override
    public Optional<TerminosCondiciones> save(TerminosCondiciones terminosCondiciones) {
        terminosCondiciones.setHoraOperacion(new Date());
        TerminosCondiciones response = terminosCondicionesRepository.save(terminosCondiciones);
        if(Objects.nonNull(response.getId())){
            return Optional.of(response);
        }
        return Optional.empty();
    }
}
