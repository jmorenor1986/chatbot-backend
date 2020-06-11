package co.com.santander.chatbot.backend.web.service.impl;

import co.com.santander.chatbot.acceso.recursos.clients.core.ClienteClient;
import co.com.santander.chatbot.backend.web.service.ValidateClienteService;
import co.com.santander.chatbot.domain.payload.accesodatos.cliente.ClienteViewPayload;
import co.com.santander.chatbot.domain.validators.exceptions.NonExistentCustomerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValidateClienteServiceImpl  implements ValidateClienteService {

    private final ClienteClient clienteClient;

    @Autowired
    public ValidateClienteServiceImpl(ClienteClient clienteClient) {
        this.clienteClient = clienteClient;
    }

    @Override
    public void validateClient(String token, String telefono) {
        callService(token,telefono);
    }

    private Boolean callService(String token,String telefono){
        ResponseEntity<List<ClienteViewPayload>> response = clienteClient.getClientsByTel(token, telefono);
        if( HttpStatus.OK.equals(response.getStatusCode()) && !response.getBody().isEmpty() ){
            return Boolean.TRUE;
        }
        throw new NonExistentCustomerException(telefono);
    }
}
