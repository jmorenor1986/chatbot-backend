package co.com.santander.chatbot.backend.web.service;

import co.com.santander.chatbot.domain.enums.ServiciosEnum;
import co.com.santander.chatbot.domain.payload.enviarextracto.response.ResponseExtractosDisponibles;
import co.com.santander.chatbot.domain.payload.service.extracto.EnvioExtractoPayload;

import java.util.Optional;

public interface ConsultaExtractoService {

    Optional<ResponseExtractosDisponibles> consultaDocumentos(String token, ServiciosEnum servicio, String telefono, EnvioExtractoPayload envioExtracto);
}
