package co.com.santander.chatbot.backend.web.service.impl;

import co.com.santander.chatbot.acceso.recursos.clients.core.ClienteClient;
import co.com.santander.chatbot.acceso.recursos.clients.core.PseParamClient;
import co.com.santander.chatbot.backend.web.common.aspect.log.BussinessLog;
import co.com.santander.chatbot.backend.web.common.utilities.SecurityUtilities;
import co.com.santander.chatbot.backend.web.service.EnlacePseService;
import co.com.santander.chatbot.domain.enums.ServiciosEnum;
import co.com.santander.chatbot.domain.enums.TipoCredito;
import co.com.santander.chatbot.domain.payload.accesodatos.PseParamPayload;
import co.com.santander.chatbot.domain.payload.accesodatos.cliente.ClienteViewPayload;
import co.com.santander.chatbot.domain.payload.service.enlacePse.ResponseEnlacePsePayload;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Log
@Service
public class EnlacePseServiceImpl implements EnlacePseService {

    private final ClienteClient clienteClient;
    private final PseParamClient pseParamClient;
    @Getter
    @Setter
    private String token;

    @Autowired
    public EnlacePseServiceImpl(ClienteClient clienteClient, PseParamClient pseParamClient) {
        this.clienteClient = clienteClient;
        this.pseParamClient = pseParamClient;
    }

    @Override
    @BussinessLog
    public Optional<ResponseEnlacePsePayload> getEnlacePse(String token, ServiciosEnum serviciosEnum, String telefono, String numcreditoEnc) {
        setToken(token);
        ResponseEntity<ClienteViewPayload> response = clienteClient.getClientByTelefonoAndNumCredito(token, telefono, desEncriptarNumCredito(numcreditoEnc));
        return generateInfo(response);
    }

    private String desEncriptarNumCredito(String numCredito) {
        try {
            return SecurityUtilities.desencriptar(numCredito);
        } catch (Exception e) {
            log.severe("Error al desencriptar el credito: ".concat(e.getMessage()));
        }
        return "";
    }

    private Optional<ResponseEnlacePsePayload> generateInfo(ResponseEntity<ClienteViewPayload> response) {
        if (HttpStatus.OK.equals(response.getStatusCode())) {
            Optional<String> linkPse = validaBancoEnlacePse(Long.valueOf(response.getBody().getIdBanco()), response.getBody().getTipoCredito());
            if (linkPse.isPresent()) {
                return Optional.of(
                        ResponseEnlacePsePayload.builder()
                                .resultadoOperacion("true")
                                .idRespuesta("0")
                                .tipoCredito(response.getBody().getTipoCredito().name())
                                .valorPagar(response.getBody().getValorPagar().toString())
                                .enlace(linkPse.get())
                                .descripcionRespuesta("Servicio consumido de forma exitosa")
                                .build());
            } else {
                return generateFailedResponse("Banco no existente");
            }
        } else if (HttpStatus.NO_CONTENT.equals(response.getStatusCode())) {
            return generateFailedResponse("No existe informacion");
        } else {
            return generateFailedResponse("Error de negocio");
        }
    }

    private Optional<String> validaBancoEnlacePse(Long idBanco, TipoCredito tipoCredito) {
        ResponseEntity<PseParamPayload> response = pseParamClient.getByIdBancoAndTipoCredito(getToken(), idBanco, Long.valueOf(tipoCredito.ordinal()));
        if (HttpStatus.OK.equals(response.getStatusCode())) {
            return Optional.of(response.getBody().getUrl());
        }
        return Optional.empty();
    }

    private Optional<ResponseEnlacePsePayload> generateFailedResponse(String message) {
        return Optional.of(ResponseEnlacePsePayload.builder()
                .resultadoOperacion("false")
                .idRespuesta("2")
                .descripcionRespuesta(message)
                .build());
    }
}
