package co.com.santander.chatbot.backend.web.service.impl;

import co.com.santander.chatbot.acceso.recursos.clients.core.InfoWhatsAppWSClient;
import co.com.santander.chatbot.backend.web.common.aspect.log.BussinessLog;
import co.com.santander.chatbot.backend.web.common.aspect.validate.ValidateState;
import co.com.santander.chatbot.backend.web.common.utilities.SecurityUtilities;
import co.com.santander.chatbot.backend.web.service.GenerarCertificadosService;
import co.com.santander.chatbot.domain.enums.ServiciosEnum;
import co.com.santander.chatbot.domain.payload.accesodatos.InfoWhatsAppWSPayload;
import co.com.santander.chatbot.domain.payload.accesodatos.ResponsePayload;
import co.com.santander.chatbot.domain.payload.service.certificados.CertificadoPayload;
import co.com.santander.chatbot.domain.validators.exceptions.ValidateStateCertificateException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Optional;

@Service
public class GenerarCertificadosServiceImpl implements GenerarCertificadosService {

    private final InfoWhatsAppWSClient infoWhatsAppWSClient;

    public GenerarCertificadosServiceImpl(InfoWhatsAppWSClient infoWhatsAppWSClient) {
        this.infoWhatsAppWSClient = infoWhatsAppWSClient;
    }

    @Override
    @BussinessLog
    @ValidateState
    public Optional<ResponsePayload> generarCertificado(String token, ServiciosEnum servicio, CertificadoPayload certificadoPayload, Date date, Long idTransaccion) {

        try {
            ResponseEntity<InfoWhatsAppWSPayload> result = infoWhatsAppWSClient.save(token, InfoWhatsAppWSPayload.builder()
                    .estado(0L)
                    .fechaEnvio(date)
                    .numCreditoBanco(SecurityUtilities.desencriptar(certificadoPayload.getNumeroCredito()))
                    .numeroIdentificacion(certificadoPayload.getIdentificacion())
                    .numPeticionServicio(idTransaccion)
                    .build());
            if (result.getStatusCodeValue() == 200)
                return Optional.of(ResponsePayload.builder()
                        .descripcionRespuesta("Transaccion realizada")
                        .resultadoValidacion(Boolean.TRUE)
                        .idRespuesta(0)
                        .build());
        } catch (GeneralSecurityException e) {
            throw new ValidateStateCertificateException("Error en los datos ingresados");
        }
        throw new ValidateStateCertificateException("Error al consultar la informacion");

    }
}