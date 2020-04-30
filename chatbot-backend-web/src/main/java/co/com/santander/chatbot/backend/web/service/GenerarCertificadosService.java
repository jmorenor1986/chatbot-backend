package co.com.santander.chatbot.backend.web.service;

import co.com.santander.chatbot.domain.enums.ServiciosEnum;
import co.com.santander.chatbot.domain.payload.service.certificados.InformacionCreditoPayload;
import co.com.santander.chatbot.domain.payload.service.certificados.InformacionCreditoResponsePayload;
import co.com.santander.chatbot.domain.payload.service.certificados.PazYSalvoPayload;

import java.util.Optional;

public interface GenerarCertificadosService {
    public Optional<InformacionCreditoResponsePayload> generarInformacionCredito(String token, InformacionCreditoPayload informacionCreditoPayload);

    public Optional<InformacionCreditoResponsePayload> generarCertificadoEstandar(String token, PazYSalvoPayload pazYSalvoPayload, ServiciosEnum serviciosEnum, Long idTransaccion);
}
