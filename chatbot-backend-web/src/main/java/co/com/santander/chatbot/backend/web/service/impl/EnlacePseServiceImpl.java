package co.com.santander.chatbot.backend.web.service.impl;

import co.com.santander.chatbot.acceso.recursos.clients.core.ClienteClient;
import co.com.santander.chatbot.acceso.recursos.clients.core.PseParamClient;
import co.com.santander.chatbot.backend.web.common.aspect.log.BussinessLog;
import co.com.santander.chatbot.backend.web.common.utilities.StringUtilities;
import co.com.santander.chatbot.backend.web.service.EnlacePseService;
import co.com.santander.chatbot.domain.common.utilities.SecurityUtilities;
import co.com.santander.chatbot.domain.enums.ServiciosEnum;
import co.com.santander.chatbot.domain.enums.TipoCredito;
import co.com.santander.chatbot.domain.payload.accesodatos.PseParamPayload;
import co.com.santander.chatbot.domain.payload.accesodatos.cliente.ClienteViewPayload;
import co.com.santander.chatbot.domain.payload.service.enlacePse.ResponseEnlacePsePayload;
import co.com.santander.chatbot.domain.validators.exceptions.NonExistentCustomerException;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log
@Service
public class EnlacePseServiceImpl implements EnlacePseService {

    private final ClienteClient clienteClient;
    private final PseParamClient pseParamClient;
    @Getter
    @Setter
    private String token;
    @Getter
    @Setter
    private ClienteViewPayload clienteViewPayload;

    @Autowired
    public EnlacePseServiceImpl(ClienteClient clienteClient, PseParamClient pseParamClient) {
        this.clienteClient = clienteClient;
        this.pseParamClient = pseParamClient;
    }

    @Override
    @BussinessLog
    public Optional<ResponseEnlacePsePayload> getEnlacePse(String token, ServiciosEnum serviciosEnum, String telefono, String numcreditoEnc) {
        setToken(token);
        Boolean validaCliente = findCliente(telefono, SecurityUtilities.desencriptarCatch(numcreditoEnc));
        if (Boolean.TRUE.equals(validaCliente)) {
            return generateInfo();
        }
        return Optional.empty();
    }

    private Boolean findCliente(String telefono, String numCredito) {
        ResponseEntity<ClienteViewPayload> response = clienteClient.getClientByTelefonoAndNumCredito(getToken(), telefono, numCredito);
        if (HttpStatus.OK.equals(response.getStatusCode())) {
            setClienteViewPayload(response.getBody());
            return Boolean.TRUE;
        }
        throw new NonExistentCustomerException("Telefono: " + telefono + " Numero credito: " + StringUtilities.ofuscarCredito(numCredito));
    }

    private Optional<ResponseEnlacePsePayload> generateInfo() {
        Optional<String> linkPse = validaBancoEnlacePse(Long.valueOf(getClienteViewPayload().getIdBanco())
                , getClienteViewPayload().getTipoCredito());
        if (linkPse.isPresent()) {
            return Optional.of(
                    ResponseEnlacePsePayload.builder()
                            .resultadoOperacion("true")
                            .idRespuesta("0")
                            .tipoCredito(getClienteViewPayload().getTipoCredito().ordinal() +"")
                            .valorPagar(getClienteViewPayload().getValorPagar().toString())
                            .valorMora(getClienteViewPayload().getValorMora().toString())
                            .enlace(linkPse.get())
                            .descripcionRespuesta("Servicio consumido de forma exitosa")
                            .build());
        } else {
            return generateFailedResponse("No hay información de credito ( No existe link para su credito )");
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
