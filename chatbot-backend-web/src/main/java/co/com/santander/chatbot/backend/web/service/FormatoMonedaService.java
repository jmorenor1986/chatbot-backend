package co.com.santander.chatbot.backend.web.service;

import co.com.santander.chatbot.domain.payload.service.enlacePse.ResponseEnlacePsePayload;

public interface FormatoMonedaService {

    ResponseEnlacePsePayload currencyFormat(String token, ResponseEnlacePsePayload response);
}
