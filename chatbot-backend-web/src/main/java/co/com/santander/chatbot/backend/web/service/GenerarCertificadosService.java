package co.com.santander.chatbot.backend.web.service;

import co.com.santander.chatbot.domain.enums.ServiciosEnum;
import co.com.santander.chatbot.domain.payload.accesodatos.ResponsePayload;
import co.com.santander.chatbot.domain.payload.service.certificados.CertificadoPayload;

import java.util.Date;
import java.util.Optional;

public interface GenerarCertificadosService {
    Optional<ResponsePayload> generarCertificado(String token,  ServiciosEnum servicio,CertificadoPayload certificadoPayload, Date date, Long idTransaccion);
}
