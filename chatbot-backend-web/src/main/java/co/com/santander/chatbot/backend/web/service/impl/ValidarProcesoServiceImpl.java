package co.com.santander.chatbot.backend.web.service.impl;

import co.com.santander.chatbot.acceso.recursos.clients.core.ClienteClient;
import co.com.santander.chatbot.acceso.recursos.clients.core.InfoWhatsAppWSClient;
import co.com.santander.chatbot.acceso.recursos.clients.core.ParametrosServiceClient;
import co.com.santander.chatbot.backend.web.common.utilities.DateUtilities;
import co.com.santander.chatbot.backend.web.common.utilities.StringUtilities;
import co.com.santander.chatbot.backend.web.service.ValidarProcesoService;
import co.com.santander.chatbot.domain.dto.aspects.CommonAspectDto;
import co.com.santander.chatbot.domain.payload.accesodatos.InfoWhatsAppWSPayload;
import co.com.santander.chatbot.domain.payload.accesodatos.ResponsePayload;
import co.com.santander.chatbot.domain.payload.accesodatos.ValidarProcesoPayload;
import co.com.santander.chatbot.domain.payload.accesodatos.cliente.ClienteViewPayload;
import co.com.santander.chatbot.domain.validators.exceptions.ValidateStateCertificateException;
import co.com.santander.chatbot.domain.validators.exceptions.ValidateStatusAfterProcess;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

@Log
@Getter
@Setter
@Service
public class ValidarProcesoServiceImpl implements ValidarProcesoService {

    public static final String ERROR_CONSUMO_SERVICE = "Error al consultar los datos";
    public static final String ERROR_CIPHER = "Número de credito invalido";
    public static final String ERROR_SOLICITUD_CURSO = "Ya existe una solicitud pendiente en curso";


    private CommonAspectDto commonAspectDto;
    private ClienteViewPayload cliente;

    private final InfoWhatsAppWSClient infoWhatsAppWSClient;
    private final ParametrosServiceClient parametrosServiceClient;
    private final ClienteClient clienteClient;

    @Autowired
    public ValidarProcesoServiceImpl(InfoWhatsAppWSClient infoWhatsAppWSClient, ParametrosServiceClient parametrosServiceClient, ClienteClient clienteClient) {
        this.infoWhatsAppWSClient = infoWhatsAppWSClient;
        this.parametrosServiceClient = parametrosServiceClient;
        this.clienteClient = clienteClient;
    }

    @Override
    public Boolean validateExistingProcesss(Object[] args) {
        getParams(args);
        Boolean valida = findClient();
        if (Boolean.TRUE.equals(valida)) {
            ResponseEntity<InfoWhatsAppWSPayload> result = infoWhatsAppWSClient.validateExistingProcess(commonAspectDto.getToken(), commonAspectDto.getCredito(), commonAspectDto.getIdentificacion(), commonAspectDto.getNumPeticionServicio());
            if (result.getStatusCodeValue() == 200) {
                if (result.getBody().getEstado() == 0) {
                    return validateProcessWithParams(commonAspectDto.getToken(), result.getBody().getFechaEnvio(), commonAspectDto.getServicioEnum().getMessage());
                }
                return Boolean.TRUE;
            } else if (result.getStatusCodeValue() == 204) {
                return Boolean.TRUE;
            }
            throw new ValidateStateCertificateException(ERROR_CONSUMO_SERVICE, 0L);
        }
        return Boolean.FALSE;
    }

    private Boolean findClient() {
        ResponseEntity<ClienteViewPayload> response = clienteClient.getClientByCedulaAndNumCredito(commonAspectDto.getToken(), commonAspectDto.getIdentificacion(), commonAspectDto.getCredito());
        if (HttpStatus.OK.equals(response.getStatusCode())) {
            setCliente(response.getBody());
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    private Boolean validateProcessWithParams(String token, Date fechaEnvio, String servicio) {
        ValidarProcesoPayload valida = ValidarProcesoPayload.builder()
                .fechaUltimaSolicitud(fechaEnvio)
                .servicio(servicio)
                .canal("WhatsApp")
                .build();
        ResponseEntity<ResponsePayload> resultProcessWithParams = parametrosServiceClient.consultarProcesoParametros(token, valida);
        if (resultProcessWithParams.getStatusCodeValue() == 200) {
            Boolean resValidacion = resultProcessWithParams.getBody().getResultadoEnvio();
            if (Boolean.TRUE.equals(resValidacion)) {
                return Boolean.TRUE;
            } else {
                Long minutos = DateUtilities.generateDifferenceDates(fechaEnvio, new Date());
                throw new ValidateStatusAfterProcess(resultProcessWithParams.getBody().getDescripcionRespuesta().toString(), StringUtilities.ofuscarCorreo(getCliente().getEmail(), 5), StringUtilities.ofuscarCredito(commonAspectDto.getCredito()), getCliente().getConvenio(), minutos, "7", cliente.getTipoCredito().ordinal()+"");
            }
        }
        throw new ValidateStateCertificateException(ERROR_CONSUMO_SERVICE, 0L);
    }

    private void getParams(Object[] args) {
        setCommonAspectDto( StringUtilities.getCommon(args) );
    }
}
