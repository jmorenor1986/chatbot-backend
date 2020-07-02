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
        List<Cliente> clientes = clienteRepository.consultarXCedulaYTelefono(telefono, "%".concat(cedula));
        if(Objects.nonNull(clientes) && !clientes.isEmpty()){
            return Optional.of(Boolean.TRUE);
        }
        return Optional.of(Boolean.FALSE);
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

    @Override
    public Optional<Boolean> validaCreditoByCedula(String cedula, String credito) {
        List<Cliente> creditos = clienteRepository.findByCedulaEndingWithAndNumerCredito(cedula, credito);
        if(creditos.isEmpty()){
            return Optional.of(Boolean.FALSE);
        }
        return Optional.of(Boolean.TRUE);
    }

    @Override
    public Optional<String> findCedulaByCedulaAndCredito(String cedula, String credito) {
        List<Cliente> creditos = clienteRepository.findByCedulaEndingWithAndNumerCredito(cedula, credito);
        if(creditos.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(creditos.get(0).getCedula());
    }

    @Override
    public Optional<Cliente> findByCedulaAndCredito(String cedula, String credito) {
        return clienteRepository.findByCedulaAndNumerCredito(cedula, credito);
    }
}
