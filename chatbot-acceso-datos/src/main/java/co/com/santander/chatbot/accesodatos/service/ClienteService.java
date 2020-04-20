package co.com.santander.chatbot.accesodatos.service;

import co.com.santander.chatbot.accesodatos.entity.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {

    Optional<Boolean> consultarCliente(String telefono, String cedula);

    Optional<List<Cliente>> consultarClienteByTelefono(String telefono);
}
