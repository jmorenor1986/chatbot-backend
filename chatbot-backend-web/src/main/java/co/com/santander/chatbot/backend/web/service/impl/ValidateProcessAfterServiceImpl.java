package co.com.santander.chatbot.backend.web.service.impl;

import co.com.santander.chatbot.acceso.recursos.clients.core.ClienteClient;
import co.com.santander.chatbot.acceso.recursos.clients.core.InfoWhatsAppWSClient;
import co.com.santander.chatbot.acceso.recursos.clients.core.ParametrosServiceClient;
import co.com.santander.chatbot.backend.web.common.utilities.DateUtilities;
import co.com.santander.chatbot.backend.web.common.utilities.StringUtilities;
import co.com.santander.chatbot.backend.web.service.ValidateProcessAfterService;
import co.com.santander.chatbot.domain.dto.aspects.CommonAspectDto;
import co.com.santander.chatbot.domain.payload.accesodatos.InfoWhatsAppWSPayload;
import co.com.santander.chatbot.domain.payload.accesodatos.ParametrosServicioPayload;
import co.com.santander.chatbot.domain.payload.accesodatos.cliente.ClienteViewPayload;
import co.com.santander.chatbot.domain.validators.exceptions.ValidateStatusAfterProcess;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

@Getter
@Setter
@Log
@Service
public class ValidateProcessAfterServiceImpl implements ValidateProcessAfterService {

    private CommonAspectDto commonAspectDto;
    private ClienteViewPayload cliente;
    private Long minutosRestantes;

    private ClienteClient clienteClient;
    private InfoWhatsAppWSClient infoWhatsAppWSClient;
    private ParametrosServiceClient parametrosServiceClient;

    @Autowired
    public ValidateProcessAfterServiceImpl(ClienteClient clienteClient, InfoWhatsAppWSClient infoWhatsAppWSClient, ParametrosServiceClient parametrosServiceClient) {
        this.clienteClient = clienteClient;
        this.infoWhatsAppWSClient = infoWhatsAppWSClient;
        this.parametrosServiceClient = parametrosServiceClient;
    }

    @Override
    public Boolean validateExistingAfterProcess(Object[] args) {
        getParams(args);
        Boolean valida = findClient();
        if(Boolean.TRUE.equals(valida)){
            return validacionProcessoInfoWhatsApp();
        }
        return Boolean.FALSE;
    }

    private void getParams(Object[] args){
        setCommonAspectDto( StringUtilities.getCommon(args) );
    }

    private Boolean findClient(){
        ResponseEntity<ClienteViewPayload> response =  clienteClient.getClientByCedulaAndNumCredito(commonAspectDto.getToken(), commonAspectDto.getIdentificacion(), commonAspectDto.getCredito());
        if(HttpStatus.OK.equals(response.getStatusCode())){
            setCliente(response.getBody());
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    private Boolean validacionProcessoInfoWhatsApp() {
        ResponseEntity<InfoWhatsAppWSPayload> response = infoWhatsAppWSClient.validateExistingProcess(commonAspectDto.getToken(),commonAspectDto.getCredito(),cliente.getCedula(), commonAspectDto.getNumPeticionServicio());
        if(HttpStatus.OK.equals(response.getStatusCode())){
            InfoWhatsAppWSPayload info = response.getBody();
            if(info.getEstado().equals(1L)){
                Long tiempo = DateUtilities.generateDifferenceDates(new Date(), info.getFechaEnvio());
                return evaluaTiempo(tiempo);
            }else if(info.getEstado().equals(0L)){
                //Tiene una solicitud pendiente sin procesar lo cual no es ingerencia de este aspecto
                return Boolean.TRUE;
            }
            //En el caso de que en la tabla MTF no encuentre informacion quiere decir que es la primera solicitud que realiza el usuario
        }else if(HttpStatus.NO_CONTENT.equals(response.getStatusCode())){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    private Boolean evaluaTiempo(Long tiempoUltimaSolicitud){
        ResponseEntity<ParametrosServicioPayload> response = parametrosServiceClient.getParametroService(commonAspectDto.getToken(), commonAspectDto.getServicioEnum().name());
        if( HttpStatus.OK.equals(response.getStatusCode()) ){
            ParametrosServicioPayload parametro = response.getBody();
            if( tiempoUltimaSolicitud > parametro.getTiempoPosterior() ){
                //En el caso de ingresar quiere decir que supero el tiempo para realizar una nueva solicitud
                return Boolean.TRUE;
            }
            setMinutosRestantes(parametro.getTiempoPosterior() - tiempoUltimaSolicitud);
            throw new ValidateStatusAfterProcess("No supera el tiempo para una nueva solicitud", StringUtilities.ofuscarCorreo(cliente.getEmail(), 5) , StringUtilities.ofuscarCredito(commonAspectDto.getCredito()), cliente.getConvenio(), getMinutosRestantes(), "8", cliente.getTipoCredito().ordinal()+"");
        }
        return Boolean.FALSE;
    }
}
