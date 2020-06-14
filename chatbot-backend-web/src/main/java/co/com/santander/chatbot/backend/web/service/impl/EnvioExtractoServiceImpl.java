package co.com.santander.chatbot.backend.web.service.impl;

import co.com.santander.chatbot.acceso.recursos.clients.core.ClienteClient;
import co.com.santander.chatbot.acceso.recursos.clients.core.DocumentosClient;
import co.com.santander.chatbot.acceso.recursos.clients.core.IdDocumentoClient;
import co.com.santander.chatbot.backend.web.common.aspect.log.BussinessLog;
import co.com.santander.chatbot.backend.web.common.utilities.StringUtilities;
import co.com.santander.chatbot.backend.web.service.EnvioExtractoService;
import co.com.santander.chatbot.backend.web.service.ParametrosAppService;
import co.com.santander.chatbot.domain.common.utilities.SecurityUtilities;
import co.com.santander.chatbot.domain.enums.ServiciosEnum;
import co.com.santander.chatbot.domain.payload.accesodatos.cliente.ClienteViewPayload;
import co.com.santander.chatbot.domain.payload.accesodatos.documento.IdDocumentoPayload;
import co.com.santander.chatbot.domain.payload.enviarextracto.*;
import co.com.santander.chatbot.domain.payload.service.extracto.EnvioExtractoPayload;
import co.com.santander.chatbot.domain.payload.service.extracto.ResponseEnvioExtractoPayload;
import co.com.santander.chatbot.domain.validators.exceptions.ExtractoDataErrorException;
import co.com.santander.chatbot.domain.validators.exceptions.IdExtractoNotFoundException;
import co.com.santander.chatbot.domain.validators.exceptions.MissingParameterException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.OptionalInt;

@Service
public class EnvioExtractoServiceImpl implements EnvioExtractoService {

    private final DocumentosClient documentosClient;
    private final ClienteClient clienteClient;
    private final IdDocumentoClient idDocumentoClient;
    private final ParametrosAppService parametrosAppService;

    @Getter
    @Setter
    private String token;
    @Getter
    @Setter
    private ClienteViewPayload clienteViewPayload;

    private final String NAME_PARAM_COPIA = "COPIA_ENVIO_EXTRACTO";

    @Autowired
    public EnvioExtractoServiceImpl(DocumentosClient documentosClient, ClienteClient clienteClient, IdDocumentoClient idDocumentoClient, ParametrosAppService parametrosAppService) {
        this.documentosClient = documentosClient;
        this.clienteClient = clienteClient;
        this.idDocumentoClient = idDocumentoClient;
        this.parametrosAppService = parametrosAppService;
    }

    @Override
    @BussinessLog
    public Optional<ResponseEnvioExtractoPayload> envioExtracto(String token, ServiciosEnum servicio, String telefono, EnvioExtractoPayload envioExtracto) {
        setToken(token);
        Boolean valida = findCreditoCliente(envioExtracto);
        if (Boolean.TRUE.equals(valida)) {
            return generarEnvioExtracto(envioExtracto);
        } else {
            return generateResponseWrong("No hay informacion de credito", envioExtracto.getNumeroCreditoOfuscado());
        }

    }

    private Optional<ResponseEnvioExtractoPayload> generateResponseWrong(String mensaje, String numeroCreditoOfuscado) {
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
        Optional<IdDocumentoPayload> idDocumento = getDocumentId(Long.valueOf(envioExtracto.getIdExtracto()));
        if (idDocumento.isPresent()) {
            validaDatosSolicitadosToDatosExtracto(idDocumento.get(), envioExtracto);
            Optional<EnvioDocumentoMailResponsePayload> envio = callService(idDocumento.get());
            if (envio.isPresent()) {
                return generarRespuesta(envio.get());

            }
        }
        return Optional.empty();
    }

    private void validaDatosSolicitadosToDatosExtracto(IdDocumentoPayload idDocumentoPayload, EnvioExtractoPayload envioExtracto) {
        //Validamos la vigencia almacenada en la bd contra la solicitada en el servicio
        Boolean validaMes = envioExtracto.getMes().equals(Integer.parseInt(idDocumentoPayload.getMes()));
        if (Boolean.FALSE.equals(validaMes)) {
            throw new ExtractoDataErrorException("mes");
        }
        Boolean validaVigencia = envioExtracto.getVigencia().equals(Integer.parseInt(idDocumentoPayload.getAnio()));
        if (Boolean.FALSE.equals(validaVigencia)) {
            throw new ExtractoDataErrorException("vigencia");
        }

    }

    private Optional<ResponseEnvioExtractoPayload> generarRespuesta(EnvioDocumentoMailResponsePayload respuesta) {
        String descripcionRespuesta;
        Integer idRespuesta;
        Boolean valida = respuesta.getEnvioExitoso();
        if (Boolean.TRUE.equals(valida)) {
            descripcionRespuesta = "Servicio consumido de forma exitosa";
            idRespuesta = 0;
        } else {
            descripcionRespuesta = respuesta.getRespuesta();
            idRespuesta = 1;
        }
        return Optional.of(ResponseEnvioExtractoPayload.builder()
                .resultadoEnvio(respuesta.getEnvioExitoso())
                .idRespuesta(idRespuesta)
                .descripcionRespuesta(descripcionRespuesta)
                .tipoCredito(clienteViewPayload.getTipoCredito().ordinal())
                .emailOfuscado(StringUtilities.ofuscarCorreo(clienteViewPayload.getEmail(), 5))
                .numeroCreditoOfuscado(StringUtilities.ofuscarCredito(clienteViewPayload.getNumerCredito()))
                .build());
    }

    private Optional<IdDocumentoPayload> getDocumentId(Long id) {
        ResponseEntity<IdDocumentoPayload> response = idDocumentoClient.getDocumentById(getToken(), id);
        if (HttpStatus.OK.equals(response.getStatusCode())) {
            return Optional.of(response.getBody());
        } else if (HttpStatus.NO_CONTENT.equals(response.getStatusCode())) {
            throw new IdExtractoNotFoundException(id.toString());
        }
        return Optional.empty();
    }

    private Optional<EnvioDocumentoMailResponsePayload> callService(IdDocumentoPayload documentoPayload) {
        ProductoEnum producto = null;
        if ("EXTRACTO VEHICULO".equalsIgnoreCase(documentoPayload.getProducto().trim()) || "CREDITO VEHICULO".equalsIgnoreCase(documentoPayload.getProducto().trim())) {
            producto = ProductoEnum.VEHICULO;
        }
        Optional<String> responseCopia = parametrosAppService.getParamByKey(token, NAME_PARAM_COPIA);
        if( !responseCopia.isPresent()){
            throw new MissingParameterException(NAME_PARAM_COPIA);
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
                        .mailCC(responseCopia.get())
                        //TODO CAMBIAR EL CORREO DEL CLIENTE
                        .mailPara("jesus.sierra@samtel.co")
                        //.mailPara(getClienteViewPayload().getEmail())
                        .build())
                .build();
        ResponseEntity<EnvioDocumentoMailResponsePayload> response = documentosClient.envioMailDocumento(getToken(), request);
        if (HttpStatus.OK.equals(response.getStatusCode())) {
            return Optional.of(response.getBody());
        }
        return Optional.empty();
    }
}