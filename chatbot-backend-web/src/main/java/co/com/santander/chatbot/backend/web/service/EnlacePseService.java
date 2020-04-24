package co.com.santander.chatbot.backend.web.service;

import co.com.santander.chatbot.domain.enums.ServiciosEnum;
import co.com.santander.chatbot.domain.payload.service.enlacePse.ResponseEnlacePsePayload;

import java.util.Optional;

public interface EnlacePseService {

    Optional<ResponseEnlacePsePayload> getEnlacePse(String token, ServiciosEnum serviciosEnum, String telefono, String numcreditoEnc);
}