package co.com.santander.chatbot.backend.web.service.impl;

import co.com.santander.chatbot.backend.web.service.GenerarCertificadosService;
import co.com.santander.chatbot.backend.web.service.ProxyInformacionCredito;
import co.com.santander.chatbot.domain.enums.ServiciosEnum;
import co.com.santander.chatbot.domain.payload.service.certificados.InformacionCreditoPayload;
import co.com.santander.chatbot.domain.payload.service.certificados.InformacionCreditoResponsePayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProxyInformacionCreditoImpl implements ProxyInformacionCredito {

    private final GenerarCertificadosService generarCertificadosService;

    @Autowired
    public ProxyInformacionCreditoImpl(GenerarCertificadosService generarCertificadosService) {
        this.generarCertificadosService = generarCertificadosService;
    }

    @Override
    public Optional<InformacionCreditoResponsePayload> generarInformacionCredito(String token, ServiciosEnum serviciosEnum, InformacionCreditoPayload informacionCreditoPayload) {
        return generarCertificadosService.generarInformacionCredito(token, ServiciosEnum.SERVICIO_INFORMACION_CREDITO, informacionCreditoPayload);
    }
}