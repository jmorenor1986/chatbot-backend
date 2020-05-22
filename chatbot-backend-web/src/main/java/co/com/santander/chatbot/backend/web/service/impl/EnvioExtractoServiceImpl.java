package co.com.santander.chatbot.backend.web.service.impl;

import co.com.santander.chatbot.acceso.recursos.clients.core.ClienteClient;
import co.com.santander.chatbot.acceso.recursos.clients.core.DocumentosClient;
import co.com.santander.chatbot.acceso.recursos.clients.core.IdDocumentoClient;
import co.com.santander.chatbot.backend.web.common.AppProperties;
import co.com.santander.chatbot.backend.web.common.aspect.log.BussinessLog;
import co.com.santander.chatbot.backend.web.common.utilities.SecurityUtilities;
import co.com.santander.chatbot.backend.web.service.EnvioExtractoService;
import co.com.santander.chatbot.domain.enums.ServiciosEnum;
import co.com.santander.chatbot.domain.payload.accesodatos.cliente.ClienteViewPayload;
import co.com.santander.chatbot.domain.payload.accesodatos.documento.IdDocumentoPayload;
import co.com.santander.chatbot.domain.payload.enviarextracto.*;
import co.com.santander.chatbot.domain.payload.service.extracto.EnvioExtractoPayload;
import co.com.santander.chatbot.domain.payload.service.extracto.ResponseEnvioExtractoPayload;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnvioExtractoServiceImpl implements EnvioExtractoService {

    private final DocumentosClient documentosClient;

    private final ClienteClient clienteClient;

    private final IdDocumentoClient idDocumentoClient;

    private final ModelMapper mapper;
    @Getter
    @Setter
    private String token;
    @Getter
    @Setter
    private ClienteViewPayload clienteViewPayload;

    @Autowired
    public EnvioExtractoServiceImpl(DocumentosClient documentosClient, AppProperties appProperties, ClienteClient clienteClient, IdDocumentoClient idDocumentoClient, ModelMapper mapper) {
        this.documentosClient = documentosClient;
        this.clienteClient = clienteClient;
        this.idDocumentoClient = idDocumentoClient;
        this.mapper = mapper;
    }

    @Override
    @BussinessLog
    public Optional<ResponseEnvioExtractoPayload> envioExtracto(String token, ServiciosEnum servicio, String telefono, EnvioExtractoPayload envioExtracto) {
        setToken(token);
        if (findCreditoCliente(envioExtracto)) {
            return generarEnvioExtracto(envioExtracto);
        }
        return Optional.empty();
    }

    private Boolean findCreditoCliente(EnvioExtractoPayload envioExtracto) {
        ResponseEntity<ClienteViewPayload> response = clienteClient.getClientByTelefonoAndNumCredito(getToken(), envioExtracto.getTelefono(), SecurityUtilities.desencriptarCatch(envioExtracto.getNumeroVerificador()));
        if (HttpStatus.OK.equals(response.getStatusCode())) {
            setClienteViewPayload(response.getBody());
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    private Optional<ResponseEnvioExtractoPayload> generarEnvioExtracto(EnvioExtractoPayload envioExtracto) {
        //Busco el documento
        Optional<IdDocumentoPayload> idDocumento = getDocumentId(Long.valueOf(envioExtracto.getIdDocumentos()));
        if (idDocumento.isPresent()) {
            Optional<EnvioDocumentoMailResponsePayload> envio = callService(envioExtracto, idDocumento.get());
            if (envio.isPresent()) {
                return generarRespuesta();

            }
        }
        return Optional.empty();
    }

    private Optional<ResponseEnvioExtractoPayload> generarRespuesta() {
        return Optional.of(ResponseEnvioExtractoPayload.builder().build());
    }

    private Optional<IdDocumentoPayload> getDocumentId(Long id) {
        ResponseEntity<IdDocumentoPayload> response = idDocumentoClient.getDocumentById(getToken(), id);
        if (HttpStatus.OK.equals(response.getStatusCode())) {
            return Optional.of(response.getBody());
        }
        return Optional.empty();
    }

    private Optional<ClienteViewPayload> findClient(String telefono, String codigoVerificador) {
        ResponseEntity<ClienteViewPayload> response = clienteClient.getClientByTelefonoAndNumCredito(getToken(), telefono, SecurityUtilities.desencriptarCatch(codigoVerificador));
        if (HttpStatus.OK.equals(response)) {
            return Optional.of(response.getBody());
        }
        return Optional.empty();
    }

    private Optional<EnvioDocumentoMailResponsePayload> callService(EnvioExtractoPayload envioExtracto, IdDocumentoPayload documentoPayload) {
        ProductoEnum producto = null;
        if("EXTRACTO VEHICULO".equalsIgnoreCase(documentoPayload.getProducto().trim()) || "CREDITO VEHICULO".equalsIgnoreCase(documentoPayload.getProducto().trim())){
            producto = ProductoEnum.VEHICULO;
        }
        EnviarMailDocumentoPayload request = EnviarMailDocumentoPayload.builder()
                .consultarDocumentoPayload(ConsultarDocumentoPayload.builder()
                        .valorllave(getClienteViewPayload().getCedula())
                        .docId(documentoPayload.getIdDocumentos())
                        .fechaIni(documentoPayload.getFechaIni())
                        .fechaFin(documentoPayload.getFechaFin())
                        .formatoConsulta("pdf")
                        .producto(producto)
                        .folder("")
                        .build())
                .envioDocumentoPayload(EnvioDocumentoPayload.builder()
                        .mailPara(getClienteViewPayload().getEmail())
                        .mailCC("johnmorenoing@gmail.com")
                        .build())
                .build();
        ResponseEntity<EnvioDocumentoMailResponsePayload> response = documentosClient.envioMailDocumento(getToken(), request);
        if (HttpStatus.OK.equals(response.getStatusCode())) {
            return Optional.of(response.getBody());
        }
        return Optional.empty();
    }
}