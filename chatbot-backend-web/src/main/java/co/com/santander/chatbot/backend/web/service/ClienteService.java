package co.com.santander.chatbot.backend.web.service;


import co.com.santander.chatbot.domain.payload.accesodatos.ClientePayload;
import co.com.santander.chatbot.domain.payload.accesodatos.ResponsePayload;
import co.com.santander.chatbot.domain.payload.service.obtenercreditos.ResponseObtenerCreditosPayload;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface ClienteService {

    ResponseEntity<ResponsePayload> validarCliente(ClientePayload cliente, String token);

    Optional<ResponseObtenerCreditosPayload> obtenerCreditos(String token, String telefono);

}