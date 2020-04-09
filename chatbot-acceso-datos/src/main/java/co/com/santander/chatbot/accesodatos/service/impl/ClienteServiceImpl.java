package co.com.santander.chatbot.accesodatos.service.impl;

import co.com.santander.chatbot.accesodatos.repository.ClienteRepository;
import co.com.santander.chatbot.accesodatos.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
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
    public Optional<Boolean> consultarCliente(BigInteger aLong, String s) {
        return Optional.of(Objects.nonNull(clienteRepository.consultarClienteXTelefonoId(aLong, new BigInteger(s))));
    }
}
