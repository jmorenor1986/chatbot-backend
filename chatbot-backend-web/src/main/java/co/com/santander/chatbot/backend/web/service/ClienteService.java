package co.com.santander.chatbot.backend.web.service;


import co.com.santander.chatbot.domain.payload.accesodatos.ClientePayload;
import co.com.santander.chatbot.domain.payload.accesodatos.ResponsePayload;
import org.springframework.http.ResponseEntity;

public interface ClienteService {

    ResponseEntity<ResponsePayload> validarCliente(ClientePayload cliente, String token);
}
