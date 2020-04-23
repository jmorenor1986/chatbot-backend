package co.com.santander.chatbot.accesodatos.service.impl;

import co.com.santander.chatbot.accesodatos.entity.Cliente;
import co.com.santander.chatbot.accesodatos.repository.ClienteRepository;
import co.com.santander.chatbot.accesodatos.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {
    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Optional<Boolean> consultarCliente(String telefono, String cedula) {
        return Optional.of(Objects.nonNull(clienteRepository.consultarXCedulaYTelefono(telefono, "%".concat(cedula))));
    }

    @Override
    public Optional<List<Cliente>> consultarClienteByTelefono(String telefono) {
        List<Cliente> listaCliente =  clienteRepository.findByTelefono(telefono);
        if(listaCliente.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(listaCliente);
    }

    @Override
    public Optional<Cliente> consultarClienteByTelefonoAndNumCredito(String telefono, String numCredito) {
        List<Cliente> listaCliente =  clienteRepository.findByTelefonoAndNumerCredito(telefono, numCredito);
        if( listaCliente.isEmpty() ) {
            return Optional.empty();
        }
        return Optional.of( listaCliente.get(0) );
    }

}
