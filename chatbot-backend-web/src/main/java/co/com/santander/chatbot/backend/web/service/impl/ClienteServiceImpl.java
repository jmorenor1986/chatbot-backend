package co.com.santander.chatbot.backend.web.service.impl;

import co.com.santander.chatbot.acceso.recursos.clients.core.ClienteClient;
import co.com.santander.chatbot.backend.web.service.ClienteService;
import co.com.santander.chatbot.domain.payload.accesodatos.ClientePayload;
import co.com.santander.chatbot.domain.payload.accesodatos.ResponsePayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteClient clienteClient;

    @Autowired
    public ClienteServiceImpl(ClienteClient clienteClient) {
        this.clienteClient = clienteClient;
    }

    @Override
    public ResponseEntity<ResponsePayload> validarCliente(ClientePayload cliente, String token) {
        return clienteClient.conusltarCliente(token, cliente);
    }
}
