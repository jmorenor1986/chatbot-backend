package co.com.santander.chatbot.backend.web.service;

import co.com.santander.chatbot.domain.enums.ServiciosEnum;
import co.com.santander.chatbot.domain.payload.accesodatos.TerminosCondicionesPayload;

import java.util.Optional;

public interface TerminosCondicionesService {

    Optional<TerminosCondicionesPayload> save(String token, ServiciosEnum servicio, TerminosCondicionesPayload terminosCondiciones);
}
