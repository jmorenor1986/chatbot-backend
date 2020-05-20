package co.com.santander.chatbot.backend.web.service.impl;

import co.com.santander.chatbot.acceso.recursos.clients.core.ClienteClient;
import co.com.santander.chatbot.acceso.recursos.clients.core.DocumentosClient;
import co.com.santander.chatbot.backend.web.common.AppProperties;
import co.com.santander.chatbot.backend.web.common.aspect.log.BussinessLog;
import co.com.santander.chatbot.backend.web.common.utilities.DateUtilities;
import co.com.santander.chatbot.backend.web.common.utilities.SecurityUtilities;
import co.com.santander.chatbot.backend.web.common.utilities.StringUtilities;
import co.com.santander.chatbot.backend.web.service.EnvioExtractoService;
import co.com.santander.chatbot.domain.enums.ServiciosEnum;
import co.com.santander.chatbot.domain.payload.accesodatos.cliente.ClienteViewPayload;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log
public class EnvioExtractoServiceImpl implements EnvioExtractoService {

    private final DocumentosClient documentosClient;

    private final AppProperties appProperties;

    private final ClienteClient clienteClient;
    @Setter @Getter
    private String token;

    @Autowired
    public EnvioExtractoServiceImpl(DocumentosClient documentosClient, AppProperties appProperties, ClienteClient clienteClient) {
        this.documentosClient = documentosClient;
        this.appProperties = appProperties;
        this.clienteClient = clienteClient;
    }

    @Override
    @BussinessLog
    public Optional<ResponseExtractosDisponibles> consultaDocumentos(String token, ServiciosEnum servicio, String telefono, EnvioExtractoPayload envioExtracto) {
        setToken(token);
        ResponseEntity<List<ConsultarDocumentosPayloadResponse>> response = documentosClient.consultaDocumentos(token, ConsultarDocumentoPayload.builder()
                .fechaIni("01/01/2000")
                .fechaFin("01/05/2020")
                .formatoConsulta("pdf")
                .producto(ProductoEnum.VEHICULO)
                .valorllave("41584206")
                .build());
        if(HttpStatus.OK.equals(response.getStatusCode())){
            Optional<List<ConsultarDocumentosPayloadResponse>> listaDocumentosValidos = filtraDocumentosCredito(response.getBody(), SecurityUtilities.desencriptarCatch(envioExtracto.getNumeroVerificador()));
            if(listaDocumentosValidos.isPresent()){
                return generateResponse(listaDocumentosValidos.get(), envioExtracto);
            }
        }
        return Optional.empty();
    }

    public Optional<List<ConsultarDocumentosPayloadResponse>> filtraDocumentosCredito(List<ConsultarDocumentosPayloadResponse> documentos, String numCredito){
        List<ConsultarDocumentosPayloadResponse> documentosValidos = documentos.stream().parallel()
                .filter( item -> validaNumeroCredito(item.getIndices(), numCredito) )
                .filter( item -> validaFecha(item.getIndices()) )
                .sorted( ( item1 , item2 ) -> comparaFechas(item1, item2) )
                .collect(Collectors.toList());
        if(documentosValidos.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(documentosValidos);
    }

    public int comparaFechas(ConsultarDocumentosPayloadResponse itemUno, ConsultarDocumentosPayloadResponse itemDos ){
        Optional<String> sFechaUno = obtieneValorIndices(itemUno.getIndices(), "FECHA_FACTURACION");
        Optional<String> sFechaDos = obtieneValorIndices(itemDos.getIndices(), "FECHA_FACTURACION");
        if(sFechaUno.isPresent() && sFechaDos.isPresent()){
            Date fechaUno = DateUtilities.stringToDate(sFechaUno.get());
            Date fechaDos = DateUtilities.stringToDate(sFechaDos.get());
            return fechaUno.compareTo(fechaDos);
        }
        return 0;
    }

    public Boolean validaNumeroCredito(List<IndicesPayloadResponse> indices, String credito){
        List<IndicesPayloadResponse> numCreditos = indices.stream().parallel()
                .filter( item -> "NUMCREDITO".equalsIgnoreCase(item.getNombre()) )
                .filter( item -> credito.equalsIgnoreCase(item.getValor().trim()))
                .collect(Collectors.toList());
        if(numCreditos.isEmpty()){
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public Boolean validaFecha(List<IndicesPayloadResponse> indices){
        Optional<String> oFecha = obtieneValorIndices(indices,"FECHA_FACTURACION");
        if(!oFecha.isPresent()){
            return Boolean.FALSE;
        }
        String sFecha = oFecha.get();
        Date fechaDocumento = DateUtilities.stringToDate(sFecha);
        if(Objects.isNull(fechaDocumento)){
            return Boolean.FALSE;
        }
        Long differencia = DateUtilities.generateDifferenceDates(fechaDocumento, new Date());
        Long meses = DateUtilities.transformMinutesToMonths(differencia);
        if( meses > appProperties.getMesesExtracto() ){
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public Optional<String> obtieneValorIndices(List<IndicesPayloadResponse> indices, String clave){
        List<IndicesPayloadResponse> numCreditos = indices.stream().parallel()
                .filter( item -> clave.equalsIgnoreCase(item.getNombre()) )
                .collect(Collectors.toList());
        if(numCreditos.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(numCreditos.get(0).getValor());
    }

    public Optional<ResponseExtractosDisponibles> generateResponse(List<ConsultarDocumentosPayloadResponse> list, EnvioExtractoPayload envioExtracto){
        ResponseEntity<ClienteViewPayload> cliente =  clienteClient.getClientByTelefonoAndNumCredito(getToken(), envioExtracto.getTelefono(), SecurityUtilities.desencriptarCatch(envioExtracto.getNumeroVerificador()));
        Optional<ResponseExtractosDisponibles> response = Optional.empty();
        if(HttpStatus.OK.equals(cliente.getStatusCode())){
            response = Optional.of( ResponseExtractosDisponibles.builder()
                    .resultadoEnvio("true")
                    .idRespuesta("0")
                    .descripcionRespuesta("Consulta realizada correctamente")
                    .numeroCreditoOfuscado(envioExtracto.getNumeroCreditoOfuscado())
                    .numeroVerificador(envioExtracto.getNumeroVerificador())
                    .emailOfuscado(StringUtilities.ofuscarCorreo(cliente.getBody().getEmail(), 5))
                    .vigencias(list.stream().parallel()
                            .map(item -> VigenciaExtracto.builder()
                                    .idDocumento(item.getDocId())
                                    .anio((DateUtilities.stringToDate(obtieneValorIndices(item.getIndices(),"FECHA_FACTURACION" ).get()).getYear() + 1900)+"")
                                    .mes((DateUtilities.stringToDate(obtieneValorIndices(item.getIndices(),"FECHA_FACTURACION" ).get()).getMonth() + 1)+"")
                                    .build())
                            .collect(Collectors.toList())
                    )
                    .build() );
        }
        return response;
    }
}
