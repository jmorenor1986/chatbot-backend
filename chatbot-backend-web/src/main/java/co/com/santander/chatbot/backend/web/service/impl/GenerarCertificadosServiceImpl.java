package co.com.santander.chatbot.backend.web.service.impl;

import co.com.santander.chatbot.acceso.recursos.clients.core.ClienteClient;
import co.com.santander.chatbot.backend.web.common.aspect.log.BussinessLog;
import co.com.santander.chatbot.domain.common.utilities.SecurityUtilities;
import co.com.santander.chatbot.backend.web.common.utilities.StringUtilities;
import co.com.santander.chatbot.backend.web.service.GenerarCertificadosService;
import co.com.santander.chatbot.backend.web.service.GuardarTransaccionCertificadoService;
import co.com.santander.chatbot.domain.enums.ServiciosEnum;
import co.com.santander.chatbot.domain.payload.accesodatos.ResponsePayload;
import co.com.santander.chatbot.domain.payload.accesodatos.cliente.ClienteViewPayload;
import co.com.santander.chatbot.domain.payload.service.certificados.CertificadoPayload;
import co.com.santander.chatbot.domain.payload.service.certificados.GenericCertificatePayload;
import co.com.santander.chatbot.domain.payload.service.certificados.InformacionCreditoPayload;
import co.com.santander.chatbot.domain.payload.service.certificados.InformacionCreditoResponsePayload;
import co.com.santander.chatbot.domain.validators.exceptions.ValidateStateCertificateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Optional;

@Service
public class GenerarCertificadosServiceImpl implements GenerarCertificadosService {

    private final GuardarTransaccionCertificadoService guardarTransaccionCertificadoService;
    private final ClienteClient clienteClient;

    public GenerarCertificadosServiceImpl(GuardarTransaccionCertificadoService guardarTransaccionCertificadoService, ClienteClient clienteClient) {
        this.clienteClient = clienteClient;
        this.guardarTransaccionCertificadoService = guardarTransaccionCertificadoService;
    }

    @Override
    @BussinessLog
    public Optional<InformacionCreditoResponsePayload> generarInformacionCredito(String token, ServiciosEnum serviciosEnum, InformacionCreditoPayload informacionCreditoPayload) {
        ResponseEntity<ClienteViewPayload> cliente = null;
        try {
            cliente = clienteClient.getClientByTelefonoAndNumCredito(token, informacionCreditoPayload.getTelefono(), SecurityUtilities.desencriptar(informacionCreditoPayload.getNumeroVerificador()));
        } catch (GeneralSecurityException e) {
            throw new ValidateStateCertificateException("Número de crédito incorrecto", 0L);
        }
        if (HttpStatus.OK.equals(cliente.getStatusCode())) {
            Optional<ResponsePayload> respuestaGuardarCliente = guardarTransaccionCertificadoService.generarCertificado(token, ServiciosEnum.SERVICIO_INFORMACION_CREDITO, CertificadoPayload.builder()
                    .numeroCredito(informacionCreditoPayload.getNumeroVerificador())
                    .identificacion(cliente.getBody().getCedula())
                    .build(), new Date(), 1L);
            if (respuestaGuardarCliente.isPresent()){
                Boolean valida = respuestaGuardarCliente.get().getResultadoValidacion();
                if (Boolean.TRUE.equals(valida)){
                    return Optional.of(generarRespuesta(cliente.getBody()));
                }
            }
        }
        throw new ValidateStateCertificateException("Cliente con datos incorrectos",0L);

    }

    @Override
    @BussinessLog
    public Optional<InformacionCreditoResponsePayload> generarCertificadoEstandar(String token, ServiciosEnum serviciosEnum, GenericCertificatePayload genericCertificatePayload, Long idTransaccion) {
        ResponseEntity<ClienteViewPayload> cliente = null;
        try {
            cliente = clienteClient.getClientByTelefonoAndNumCredito(token, genericCertificatePayload.getTelefono(), SecurityUtilities.desencriptar(genericCertificatePayload.getNumeroVerificador()));
        } catch (GeneralSecurityException e) {
            throw new ValidateStateCertificateException("Número de crédito incorrecto", 0L);
        }
        if (HttpStatus.OK.equals(cliente.getStatusCode())) {
            Optional<ResponsePayload> respuestaGuardarCliente = guardarTransaccionCertificadoService.generarCertificado(token, serviciosEnum, CertificadoPayload.builder()
                    .numeroCredito(genericCertificatePayload.getNumeroVerificador())
                    .identificacion(cliente.getBody().getCedula())
                    .build(), new Date(), idTransaccion);
            if (respuestaGuardarCliente.isPresent()){
                Boolean valida = respuestaGuardarCliente.get().getResultadoValidacion();
                if (Boolean.TRUE.equals( valida )){
                    return Optional.of(generarRespuesta(cliente.getBody()));
                }

            }

        }
        throw new ValidateStateCertificateException("Cliente con datos incorrectos", 0L);
    }


    private InformacionCreditoResponsePayload generarRespuesta(ClienteViewPayload clienteViewPayload) {
        return InformacionCreditoResponsePayload.builder()
                .convenio(clienteViewPayload.getConvenio())
                .emailOfuscado(StringUtilities.ofuscarCorreo(clienteViewPayload.getEmail(), 4))
                .numeroCreditoOfuscado(StringUtilities.ofuscarString(clienteViewPayload.getNumerCredito(), 5))
                .resultadoEnvio("true")
                .tipoCredito(clienteViewPayload.getIdProducto())
                .idRespuesta("0")
                .descripcionRespuesta("Servicio consumido de forma exitosa")
                .build();
    }

}
