package co.com.santander.chatbot.backend.web.service;


import co.com.santander.chatbot.domain.enums.ServiciosEnum;
import co.com.santander.chatbot.domain.payload.accesodatos.ClientePayload;
import co.com.santander.chatbot.domain.payload.accesodatos.ResponsePayload;
import co.com.santander.chatbot.domain.payload.service.obtenercreditos.ResponseObtenerCreditosPayload;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface ClienteService {

    ResponseEntity<ResponsePayload> validarCliente(String token, ServiciosEnum serviciosEnum, String telefono, ClientePayload cliente);

    Optional<ResponseObtenerCreditosPayload> obtenerCreditos(String token, ServiciosEnum serviciosEnum, String telefono);

}