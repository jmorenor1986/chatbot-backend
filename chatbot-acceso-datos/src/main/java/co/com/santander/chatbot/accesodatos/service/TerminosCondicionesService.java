package co.com.santander.chatbot.accesodatos.service;

import co.com.santander.chatbot.accesodatos.entity.TerminosCondiciones;

import java.util.Optional;

public interface TerminosCondicionesService {

    Optional<TerminosCondiciones> save(TerminosCondiciones terminosCondiciones);
}
