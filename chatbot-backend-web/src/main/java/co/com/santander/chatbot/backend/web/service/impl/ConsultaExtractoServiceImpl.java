package co.com.santander.chatbot.backend.web.service.impl;

import co.com.santander.chatbot.acceso.recursos.clients.core.ClienteClient;
import co.com.santander.chatbot.acceso.recursos.clients.core.DocumentosClient;
import co.com.santander.chatbot.acceso.recursos.clients.core.IdDocumentoClient;
import co.com.santander.chatbot.backend.web.common.AppProperties;
import co.com.santander.chatbot.backend.web.common.aspect.log.BussinessLog;
import co.com.santander.chatbot.backend.web.common.utilities.DateUtilities;
import co.com.santander.chatbot.backend.web.common.utilities.SecurityUtilities;
import co.com.santander.chatbot.backend.web.common.utilities.StringUtilities;
import co.com.santander.chatbot.backend.web.service.ConsultaExtractoService;
import co.com.santander.chatbot.domain.enums.ServiciosEnum;
import co.com.santander.chatbot.domain.enums.TipoCredito;
import co.com.santander.chatbot.domain.payload.accesodatos.cliente.ClienteViewPayload;
import co.com.santander.chatbot.domain.payload.accesodatos.documento.IdDocumentoPayload;
import co.com.santander.chatbot.domain.payload.enviarextracto.ConsultarDocumentoPayload;
import co.com.santander.chatbot.domain.payload.enviarextracto.ConsultarDocumentosPayloadResponse;
import co.com.santander.chatbot.domain.payload.enviarextracto.IndicesPayloadResponse;
import co.com.santander.chatbot.domain.payload.enviarextracto.ProductoEnum;
import co.com.santander.chatbot.domain.payload.enviarextracto.response.ResponseExtractosDisponibles;
import co.com.santander.chatbot.domain.payload.enviarextracto.response.VigenciaExtracto;
import co.com.santander.chatbot.domain.payload.service.extracto.EnvioExtractoPayload;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Log
public class ConsultaExtractoServiceImpl implements ConsultaExtractoService {

    private final DocumentosClient documentosClient;

    private final AppProperties appProperties;

    private final ClienteClient clienteClient;

    private final IdDocumentoClient idDocumentoClient;

    private final ModelMapper mapper;

    @Setter
    @Getter
    private String token;
    @Getter
    @Setter
    private ClienteViewPayload clienteViewPayload;
    @Getter
    @Setter
    private String sFechaIni;
    @Setter
    @Getter
    private String sFechaFin;

    @Autowired
    public ConsultaExtractoServiceImpl(DocumentosClient documentosClient, AppProperties appProperties, ClienteClient clienteClient, IdDocumentoClient idDocumentoClient, ModelMapper mapper) {
        this.documentosClient = documentosClient;
        this.appProperties = appProperties;
        this.clienteClient = clienteClient;
        this.idDocumentoClient = idDocumentoClient;
        this.mapper = mapper;
    }

    @Override
    @BussinessLog
    public Optional<ResponseExtractosDisponibles> consultaDocumentos(String token, ServiciosEnum servicio, String telefono, EnvioExtractoPayload envioExtracto) {
        setToken(token);
        //Busca los datos del cliente y del credito que se esta solicitando
        if (findCreditoCliente(envioExtracto)) {
            //Llama el servicio de computec
            Optional<List<ConsultarDocumentosPayloadResponse>> listaDocumentosValidos = callPrincipalService(envioExtracto);
            if (listaDocumentosValidos.isPresent()) {
                //Filtra documentos del credito
                listaDocumentosValidos = filtraDocumentosCredito(listaDocumentosValidos.get(), SecurityUtilities.desencriptarCatch(envioExtracto.getNumeroVerificador()));
                if (listaDocumentosValidos.isPresent()) {
                    //Genera la respuesta que va ha enviar al usuario
                    return generateResponse(listaDocumentosValidos.get(), envioExtracto);
                }
            }else{
                return generateResponseWrong("No hay informacion de credito en el repositorio de extractos", envioExtracto.getNumeroCreditoOfuscado());
            }
        }else{
            return generateResponseWrong("No hay informacion de credito", envioExtracto.getNumeroCreditoOfuscado());
        }
        return Optional.empty();
    }

    private Optional<ResponseExtractosDisponibles> generateResponseWrong(String mensaje, String numeroCreditoOfuscado){
        return Optional.of(ResponseExtractosDisponibles.builder()
                .idRespuesta("2")
                .descripcionRespuesta(mensaje)
                .numeroCreditoOfuscado(numeroCreditoOfuscado)
                .build());
    }

    private Optional<List<ConsultarDocumentosPayloadResponse>> callPrincipalService(EnvioExtractoPayload envioExtracto) {
        SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
        //Obtener las fechas extremas
        Date fechaFin = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, - appProperties.getMesesExtracto().intValue());
        Date fechaIni = calendar.getTime();
        setSFechaIni(d.format(fechaIni));
        setSFechaFin(d.format(fechaFin));
        ProductoEnum producto = null;
        if (TipoCredito.VEHICULO.equals(getClienteViewPayload().getTipoCredito())) {
            producto = ProductoEnum.VEHICULO;
        }
        ResponseEntity<List<ConsultarDocumentosPayloadResponse>> response = documentosClient.consultaDocumentos(token, ConsultarDocumentoPayload.builder()
                .fechaIni(getSFechaIni())
                .fechaFin(getSFechaFin())
                .formatoConsulta("pdf")
                //TODO Validar si 1. Credito consumo 2. Credito Vehiculo
                .producto(producto)
                .valorllave(clienteViewPayload.getCedula())
                .folder("")
                .build());
        if (HttpStatus.OK.equals(response.getStatusCode())) {
            return Optional.of(response.getBody());
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

    private Optional<List<ConsultarDocumentosPayloadResponse>> filtraDocumentosCredito(List<ConsultarDocumentosPayloadResponse> documentos, String numCredito) {
        List<ConsultarDocumentosPayloadResponse> documentosValidos = documentos.stream().parallel()
                .filter(item -> validaNumeroCredito(item.getIndices(), numCredito))
                .filter(item -> validaFecha(item.getIndices()))
                .sorted((item1, item2) -> comparaFechas(item1, item2))
                .collect(Collectors.toList());
        if (documentosValidos.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(documentosValidos);
    }

    private int comparaFechas(ConsultarDocumentosPayloadResponse itemUno, ConsultarDocumentosPayloadResponse itemDos) {
        Optional<String> sFechaUno = obtieneValorIndices(itemUno.getIndices(), "FECHA_FACTURACION");
        Optional<String> sFechaDos = obtieneValorIndices(itemDos.getIndices(), "FECHA_FACTURACION");
        if (sFechaUno.isPresent() && sFechaDos.isPresent()) {
            Date fechaUno = DateUtilities.stringToDate(sFechaUno.get());
            Date fechaDos = DateUtilities.stringToDate(sFechaDos.get());
            return fechaUno.compareTo(fechaDos);
        }
        return 0;
    }

    private Boolean validaNumeroCredito(List<IndicesPayloadResponse> indices, String credito) {
        List<IndicesPayloadResponse> numCreditos = indices.stream().parallel()
                .filter(item -> "NUMCREDITO".equalsIgnoreCase(item.getNombre()))
                .filter(item -> credito.equalsIgnoreCase(item.getValor().trim()))
                .collect(Collectors.toList());
        if (numCreditos.isEmpty()) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    private Boolean validaFecha(List<IndicesPayloadResponse> indices) {
        Optional<String> oFecha = obtieneValorIndices(indices, "FECHA_FACTURACION");
        if (!oFecha.isPresent()) {
            return Boolean.FALSE;
        }
        String sFecha = oFecha.get();
        Date fechaDocumento = DateUtilities.stringToDate(sFecha);
        if (Objects.isNull(fechaDocumento)) {
            return Boolean.FALSE;
        }
        Long differencia = DateUtilities.generateDifferenceDates(fechaDocumento, new Date());
        Long meses = DateUtilities.transformMinutesToMonths(differencia);
        if (meses > appProperties.getMesesExtracto()) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    private Optional<String> obtieneValorIndices(List<IndicesPayloadResponse> indices, String clave) {
        List<IndicesPayloadResponse> numCreditos = indices.stream().parallel()
                .filter(item -> clave.equalsIgnoreCase(item.getNombre()))
                .collect(Collectors.toList());
        if (numCreditos.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(numCreditos.get(0).getValor());
    }

    private Optional<ResponseExtractosDisponibles> generateResponse(List<ConsultarDocumentosPayloadResponse> list, EnvioExtractoPayload envioExtracto) {
        Optional<ResponseExtractosDisponibles> response = Optional.empty();
        response = Optional.of(ResponseExtractosDisponibles.builder()
                .resultadoEnvio("true")
                .idRespuesta("0")
                .descripcionRespuesta("Consulta realizada correctamente")
                .numeroCreditoOfuscado(envioExtracto.getNumeroCreditoOfuscado())
                .numeroVerificador(envioExtracto.getNumeroVerificador())
                .emailOfuscado(StringUtilities.ofuscarCorreo(clienteViewPayload.getEmail(), 5))
                .vigencias(generateVigencia(list))
                .build());
        return response;
    }

    private List<VigenciaExtracto> generateVigencia(List<ConsultarDocumentosPayloadResponse> listDoc) {
        List<VigenciaExtracto> vigencias = listDoc.stream().parallel()
                .map(item -> VigenciaExtracto.builder()
                        .idDocumentos(item.getDocId())
                        .anio((DateUtilities.stringToDate(obtieneValorIndices(item.getIndices(), "FECHA_FACTURACION").get()).getYear() + 1900) + "")
                        .mes((DateUtilities.stringToDate(obtieneValorIndices(item.getIndices(), "FECHA_FACTURACION").get()).getMonth() + 1) + "")
                        .fechaIni(getSFechaIni())
                        .fechaFin(getSFechaFin())
                        .producto( obtieneValorIndices(item.getIndices(), "PRODUCTO").get() )
                        .build())
                .map(item -> persistVigencia(item))
                .filter(item -> Objects.nonNull(item.getId()))
                .collect(Collectors.toList());
        return vigencias;
    }

    private VigenciaExtracto persistVigencia(VigenciaExtracto vigencia) {
        IdDocumentoPayload payload = mapper.map(vigencia, IdDocumentoPayload.class);
        ResponseEntity<IdDocumentoPayload> response = idDocumentoClient.save(getToken(), payload);
        if (HttpStatus.OK.equals(response.getStatusCode())) {
            IdDocumentoPayload responseService = response.getBody();
            VigenciaExtracto retorno = VigenciaExtracto.builder()
                    .id(responseService.getId().toString())
                    .mes(responseService.getMes())
                    .anio(responseService.getAnio())
                    .build();
            return retorno;
        }
        return vigencia;
    }
}
