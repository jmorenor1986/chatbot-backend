package co.com.santander.chatbot.backend.web.service.impl;

import co.com.santander.chatbot.acceso.recursos.clients.core.TerminosCondicionesClient;
import co.com.santander.chatbot.backend.web.service.TerminosCondicionesService;
import co.com.santander.chatbot.domain.enums.ServiciosEnum;
import co.com.santander.chatbot.domain.payload.accesodatos.TerminosCondicionesPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TerminosCondicionesServiceImpl implements TerminosCondicionesService {

    private final TerminosCondicionesClient terminosCondicionesClient;

    @Autowired
    public TerminosCondicionesServiceImpl(TerminosCondicionesClient terminosCondicionesClient) {
        this.terminosCondicionesClient = terminosCondicionesClient;
    }

    @Override
    public Optional<TerminosCondicionesPayload> save(String token, ServiciosEnum servicio, TerminosCondicionesPayload terminosCondiciones) {
        ResponseEntity<TerminosCondicionesPayload> response = terminosCondicionesClient.save(token,terminosCondiciones);
        if(HttpStatus.OK.equals(response.getStatusCode())){
            return Optional.of(response.getBody());
        }
        return Optional.empty();
    }
}
