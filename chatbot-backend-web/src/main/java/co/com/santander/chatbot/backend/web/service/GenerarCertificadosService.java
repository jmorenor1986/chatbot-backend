package co.com.santander.chatbot.backend.web.service;

import co.com.santander.chatbot.domain.enums.ServiciosEnum;
import co.com.santander.chatbot.domain.payload.service.certificados.GenericCertificatePayload;
import co.com.santander.chatbot.domain.payload.service.certificados.InformacionCreditoPayload;
import co.com.santander.chatbot.domain.payload.service.certificados.InformacionCreditoResponsePayload;

import java.util.Optional;

public interface GenerarCertificadosService {
    Optional<InformacionCreditoResponsePayload> generarInformacionCredito(String token, ServiciosEnum serviciosEnum, InformacionCreditoPayload informacionCreditoPayload);

    Optional<InformacionCreditoResponsePayload> generarCertificadoEstandar(String token, ServiciosEnum serviciosEnum, GenericCertificatePayload genericCertificatePayload, Long idTransaccion);
}
