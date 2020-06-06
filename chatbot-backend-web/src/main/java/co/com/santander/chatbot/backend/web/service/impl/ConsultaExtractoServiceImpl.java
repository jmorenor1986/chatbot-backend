package co.com.santander.chatbot.backend.web.service.impl;

import co.com.santander.chatbot.acceso.recursos.clients.core.ClienteClient;
import co.com.santander.chatbot.acceso.recursos.clients.core.DocumentosClient;
import co.com.santander.chatbot.acceso.recursos.clients.core.IdDocumentoClient;
import co.com.santander.chatbot.backend.web.common.aspect.log.BussinessLog;
import co.com.santander.chatbot.backend.web.common.utilities.DateUtilities;
import co.com.santander.chatbot.backend.web.common.utilities.StringUtilities;
import co.com.santander.chatbot.backend.web.service.ConsultaExtractoService;
import co.com.santander.chatbot.backend.web.service.ParametrosAppService;
import co.com.santander.chatbot.domain.common.utilities.SecurityUtilities;
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
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Log
public class ConsultaExtractoServiceImpl implements ConsultaExtractoService {

    private final DocumentosClient documentosClient;

    private final ParametrosAppService parametrosAppService;

    private final ClienteClient clienteClient;

    private final IdDocumentoClient idDocumentoClient;

    private final ModelMapper mapper;

    private static final String LABEL_FECHA_FACTURACION = "FECHA_FACTURACION";

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
    public ConsultaExtractoServiceImpl(DocumentosClient documentosClient, ParametrosAppService parametrosAppService, ClienteClient clienteClient, IdDocumentoClient idDocumentoClient, ModelMapper mapper) {
        this.documentosClient = documentosClient;
        this.parametrosAppService = parametrosAppService;
        this.clienteClient = clienteClient;
        this.idDocumentoClient = idDocumentoClient;
        this.mapper = mapper;
    }

    @Override
    @BussinessLog
    public Optional<ResponseExtractosDisponibles> consultaDocumentos(String token, ServiciosEnum servicio, String telefono, EnvioExtractoPayload envioExtracto) {
        setToken(token);
        Boolean valida = findCreditoCliente(envioExtracto);
        //Busca los datos del cliente y del credito que se esta solicitando
        if (Boolean.TRUE.equals(valida)) {
            //Llama el servicio de computec
            Optional<List<ConsultarDocumentosPayloadResponse>> listaDocumentosValidos = callPrincipalService();
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

    private Optional<List<ConsultarDocumentosPayloadResponse>> callPrincipalService() {
        SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
        //Obtener las fechas extremas
        Date fechaFin = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        Optional<String> parametro = parametrosAppService.getParamByKey(getToken(),"MESES_EXTRACTO");
        int meses = 0;
        if(parametro.isPresent()){
            meses = Integer.valueOf(parametro.get());
        }
        calendar.add(Calendar.MONTH, - meses);
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
                .sorted(comparaFechas())
                .collect(Collectors.toList());
        if (documentosValidos.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(documentosValidos);
    }

    private Comparator<ConsultarDocumentosPayloadResponse> comparaFechas(){
        return (itemUno, itemDos ) -> {
            Optional<String> sFechaUno = obtieneValorIndices(itemUno.getIndices(), LABEL_FECHA_FACTURACION);
            Optional<String> sFechaDos = obtieneValorIndices(itemDos.getIndices(), LABEL_FECHA_FACTURACION);
            if (sFechaUno.isPresent() && sFechaDos.isPresent()) {
                Date fechaUno = DateUtilities.stringToDate(sFechaUno.get());
                Date fechaDos = DateUtilities.stringToDate(sFechaDos.get());
                return fechaUno.compareTo(fechaDos);
            }
            return 0;
        };
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
        Optional<String> oFecha = obtieneValorIndices(indices, LABEL_FECHA_FACTURACION);
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
        Optional<String> parametro = parametrosAppService.getParamByKey(getToken(),"MESES_EXTRACTO");
        int mesesParam = 0;
        if(parametro.isPresent()){
            mesesParam = Integer.valueOf(parametro.get());
        }
        if (meses > mesesParam) {
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
        return Optional.of(ResponseExtractosDisponibles.builder()
                .resultadoEnvio("true")
                .idRespuesta("0")
                .descripcionRespuesta("Consulta realizada correctamente")
                .numeroCreditoOfuscado(envioExtracto.getNumeroCreditoOfuscado())
                .numeroVerificador(envioExtracto.getNumeroVerificador())
                .emailOfuscado(StringUtilities.ofuscarCorreo(clienteViewPayload.getEmail(), 5))
                .vigencias(generateVigencia(list))
                .build());
    }

    private Function<ConsultarDocumentosPayloadResponse, VigenciaExtracto> mapperToVigenciaExtracto(){
        return entrada ->{
            String anio = DateUtilities.getDataFromDateString(obtieneValorIndices(entrada.getIndices(), LABEL_FECHA_FACTURACION).get(), Calendar.YEAR);
            String mes = DateUtilities.getDataFromDateString(obtieneValorIndices(entrada.getIndices(), LABEL_FECHA_FACTURACION).get(), Calendar.MONTH);
            String producto = obtieneValorIndices(entrada.getIndices(), "PRODUCTO").get();
            return VigenciaExtracto.builder()
                    .idDocumentos(entrada.getDocId())
                    .anio(anio)
                    .mes(mes)
                    .fechaIni(getSFechaIni())
                    .fechaFin(getSFechaFin())
                    .producto( producto )
                    .build();
        };
    }
    private List<VigenciaExtracto> generateVigencia(List<ConsultarDocumentosPayloadResponse> listDoc) {
        return listDoc.stream().parallel()
                .map(mapperToVigenciaExtracto() )
                .map(persistVigencia())
                .filter(item -> Objects.nonNull(item.getId()))
                .collect(Collectors.toList());
    }

    private Function<VigenciaExtracto, VigenciaExtracto >  persistVigencia() {
        return vigencia -> {
            IdDocumentoPayload payload = mapper.map(vigencia, IdDocumentoPayload.class);
            ResponseEntity<IdDocumentoPayload> response = idDocumentoClient.save(getToken(), payload);
            if (HttpStatus.OK.equals(response.getStatusCode())) {
                IdDocumentoPayload responseService = response.getBody();
                return VigenciaExtracto.builder()
                        .id(responseService.getId().toString())
                        .mes(responseService.getMes())
                        .anio(responseService.getAnio())
                        .build();
            }
            return vigencia;
        };
    }

}
