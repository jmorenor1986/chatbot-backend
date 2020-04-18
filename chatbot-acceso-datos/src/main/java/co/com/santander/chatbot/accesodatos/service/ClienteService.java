package co.com.santander.chatbot.accesodatos.service;

import java.util.Optional;

public interface ClienteService {

    Optional<Boolean> consultarCliente(String telefono, String cedula);
}
