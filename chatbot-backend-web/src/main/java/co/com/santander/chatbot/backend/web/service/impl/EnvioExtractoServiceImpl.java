package co.com.santander.chatbot.backend.web.service.impl;

import co.com.santander.chatbot.acceso.recursos.clients.core.ClienteClient;
import co.com.santander.chatbot.acceso.recursos.clients.core.DocumentosClient;
import co.com.santander.chatbot.acceso.recursos.clients.core.IdDocumentoClient;
import co.com.santander.chatbot.backend.web.common.aspect.log.BussinessLog;
import co.com.santander.chatbot.backend.web.common.utilities.StringUtilities;
import co.com.santander.chatbot.backend.web.service.EnvioExtractoService;
import co.com.santander.chatbot.domain.common.utilities.SecurityUtilities;
import co.com.santander.chatbot.domain.enums.ServiciosEnum;
import co.com.santander.chatbot.domain.payload.accesodatos.cliente.ClienteViewPayload;
import co.com.santander.chatbot.domain.payload.accesodatos.documento.IdDocumentoPayload;
import co.com.santander.chatbot.domain.payload.enviarextracto.*;
import co.com.santander.chatbot.domain.payload.service.extracto.EnvioExtractoPayload;
import co.com.santander.chatbot.domain.payload.service.extracto.ResponseEnvioExtractoPayload;
import lombok.Getter;
import lombok.Setter;
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

    @Getter
    @Setter
    private String token;
    @Getter
    @Setter
    private ClienteViewPayload clienteViewPayload;

    @Autowired
    public EnvioExtractoServiceImpl(DocumentosClient documentosClient, ClienteClient clienteClient, IdDocumentoClient idDocumentoClient) {
        this.documentosClient = documentosClient;
        this.clienteClient = clienteClient;
        this.idDocumentoClient = idDocumentoClient;
    }

    @Override
    @BussinessLog
    public Optional<ResponseEnvioExtractoPayload> envioExtracto(String token, ServiciosEnum servicio, String telefono, EnvioExtractoPayload envioExtracto) {
        setToken(token);
        Boolean valida = findCreditoCliente(envioExtracto);
        if ( Boolean.TRUE.equals(valida) ) {
            return generarEnvioExtracto(envioExtracto);
        }else{
            return generateResponseWrong("No hay informacion de credito", envioExtracto.getNumeroCreditoOfuscado());
        }

    }

    private Optional<ResponseEnvioExtractoPayload> generateResponseWrong(String mensaje, String numeroCreditoOfuscado){
        return Optional.of(ResponseEnvioExtractoPayload.builder()
                .idRespuesta(2)
                .descripcionRespuesta(mensaje)
                .numeroCreditoOfuscado(numeroCreditoOfuscado)
                .build());
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
            Optional<EnvioDocumentoMailResponsePayload> envio = callService(idDocumento.get());
            if (envio.isPresent()) {
                return generarRespuesta(envio.get());

            }
        }
        return Optional.empty();
    }

    private Optional<ResponseEnvioExtractoPayload> generarRespuesta(EnvioDocumentoMailResponsePayload respuesta) {
        return Optional.of(ResponseEnvioExtractoPayload.builder()
                .resultadoEnvio(respuesta.getEnvioExitoso())
                .idRespuesta(respuesta.getEnvioExitoso() ? 0 : 1)
                .descripcionRespuesta(respuesta.getEnvioExitoso() ? "Servicio consumido de forma exitosa" : respuesta.getRespuesta())
                .tipoCredito(clienteViewPayload.getTipoCredito().ordinal())
                .emailOfuscado(StringUtilities.ofuscarCorreo(clienteViewPayload.getEmail(), 5))
                .numeroCreditoOfuscado(StringUtilities.ofuscarCredito(clienteViewPayload.getNumerCredito()))
                .build());
    }

    private Optional<IdDocumentoPayload> getDocumentId(Long id) {
        ResponseEntity<IdDocumentoPayload> response = idDocumentoClient.getDocumentById(getToken(), id);
        if (HttpStatus.OK.equals(response.getStatusCode())) {
            return Optional.of(response.getBody());
        }
        return Optional.empty();
    }


    private Optional<EnvioDocumentoMailResponsePayload> callService(IdDocumentoPayload documentoPayload) {
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
                        //TODO CAMBIAR CUANDO SE PASE  PRODUCCION
                        //.mailPara(getClienteViewPayload().getEmail())
                        .mailPara("jnsierrac@gmail.com")
                        .mailCC("servicioalcliente@santanderconsumer.co")
                        .build())
                .build();
        ResponseEntity<EnvioDocumentoMailResponsePayload> response = documentosClient.envioMailDocumento(getToken(), request);
        if (HttpStatus.OK.equals(response.getStatusCode())) {
            return Optional.of(response.getBody());
        }
        return Optional.empty();
    }
}