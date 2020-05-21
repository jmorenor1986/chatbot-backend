package co.com.santander.chatbot.backend.web.service;

import co.com.santander.chatbot.domain.enums.ServiciosEnum;
import co.com.santander.chatbot.domain.payload.service.extracto.EnvioExtractoPayload;
import co.com.santander.chatbot.domain.payload.service.extracto.ResponseEnvioExtractoPayload;

import java.util.Optional;

public interface EnvioExtractoService {

    Optional< ResponseEnvioExtractoPayload > envioExtracto(String token, ServiciosEnum servicio, String telefono, EnvioExtractoPayload envioExtracto );
}
