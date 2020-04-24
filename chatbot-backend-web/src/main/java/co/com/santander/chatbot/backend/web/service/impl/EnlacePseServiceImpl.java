package co.com.santander.chatbot.backend.web.service.impl;

import co.com.santander.chatbot.acceso.recursos.clients.core.ClienteClient;
import co.com.santander.chatbot.backend.web.common.aspect.log.BussinessLog;
import co.com.santander.chatbot.backend.web.common.utilities.SecurityUtilities;
import co.com.santander.chatbot.backend.web.service.EnlacePseService;
import co.com.santander.chatbot.domain.enums.ServiciosEnum;
import co.com.santander.chatbot.domain.payload.accesodatos.cliente.ClienteViewPayload;
import co.com.santander.chatbot.domain.payload.service.enlacePse.ResponseEnlacePsePayload;
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

    @Autowired
    public EnlacePseServiceImpl(ClienteClient clienteClient) {
        this.clienteClient = clienteClient;
    }

    @Override
    @BussinessLog
    public Optional<ResponseEnlacePsePayload> getEnlacePse(String token, ServiciosEnum serviciosEnum, String telefono, String numcreditoEnc) {
        ResponseEntity<ClienteViewPayload> response =  clienteClient.getClientByTelefonoAndNumCredito(token,telefono, desEncriptarNumCredito(numcreditoEnc));
        return generateInfo(response);
    }

    private String desEncriptarNumCredito(String numCredito){
        try {
            return SecurityUtilities.desencriptar(numCredito);
        }catch (Exception e){
            log.severe("Error al desencriptar el credito: ".concat(e.getMessage()));
        }
        return "";
    }

    private Optional<ResponseEnlacePsePayload>  generateInfo(ResponseEntity<ClienteViewPayload> response){
        if(HttpStatus.OK.equals(response.getStatusCode())){
            Optional<String> linkPse = validaBancoEnlacePse(response.getBody().getBanco());
            if(linkPse.isPresent()) {
                return Optional.of(
                        ResponseEnlacePsePayload.builder()
                                .resultadoOperacion("true")
                                .idRespuesta("0")
                                .enlace(linkPse.get())
                                .descripcionRespuesta("Servicio consumido de forma exitosa")
                                .build());
            }else{
                return generateFailedResponse("Banco no existente");
            }
        }else if( HttpStatus.NO_CONTENT.equals(response.getStatusCode()) ){
            return generateFailedResponse("No existe informacion");
        }else{
            return generateFailedResponse("Error de negocio");
        }
    }

    private Optional<String> validaBancoEnlacePse(String banco){
        if("SANTANDER CONSUMER".equalsIgnoreCase(banco)){
            return Optional.of("https://www.pagosvirtualesavvillas.com.co/personal/pagos/12328");
        }else if("SANTANDER VEHICULO".equalsIgnoreCase(banco)){
            return Optional.of("https://www.pagosvirtualesavvillas.com.co/personal/pagos/");
        }else if("JURISCOOP VEHICULO".equalsIgnoreCase(banco)){
            return Optional.of("https://financierajuriscoop.com.co/oficina-virtual/");
        }
        return Optional.empty();
    }

    private Optional<ResponseEnlacePsePayload> generateFailedResponse(String message){
        return Optional.of(ResponseEnlacePsePayload.builder()
                .resultadoOperacion("false")
                .idRespuesta("2")
                .descripcionRespuesta(message)
                .build());
    }
}
