package co.com.santander.chatbot.backend.web.service.impl;

import co.com.santander.chatbot.acceso.recursos.clients.core.ClienteClient;
import co.com.santander.chatbot.acceso.recursos.clients.core.ParametrosAppClient;
import co.com.santander.chatbot.backend.web.common.utilities.DateUtilities;
import co.com.santander.chatbot.backend.web.common.utilities.SecurityUtilities;
import co.com.santander.chatbot.backend.web.service.GenerarCertificadosService;
import co.com.santander.chatbot.backend.web.service.ParametrosAppService;
import co.com.santander.chatbot.backend.web.service.ProxyInformacionCredito;
import co.com.santander.chatbot.domain.enums.ServiciosEnum;
import co.com.santander.chatbot.domain.payload.accesodatos.ParametrosAppPayload;
import co.com.santander.chatbot.domain.payload.accesodatos.cliente.ClienteViewPayload;
import co.com.santander.chatbot.domain.payload.service.certificados.InformacionCreditoPayload;
import co.com.santander.chatbot.domain.payload.service.certificados.InformacionCreditoResponsePayload;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

@Service
public class ProxyInformacionCreditoImpl implements ProxyInformacionCredito {

    private final GenerarCertificadosService generarCertificadosService;
    private final ClienteClient clienteClient;
    private final ParametrosAppService parametrosAppService;
    @Getter @Setter
    private ClienteViewPayload cliente;
    @Getter @Setter
    private Long numDias;

    @Autowired
    public ProxyInformacionCreditoImpl(GenerarCertificadosService generarCertificadosService, ClienteClient clienteClient, ParametrosAppService parametrosAppService) {
        this.generarCertificadosService = generarCertificadosService;
        this.clienteClient = clienteClient;
        this.parametrosAppService = parametrosAppService;
    }

    @Override
    public Optional<InformacionCreditoResponsePayload> generarInformacionCredito(String token, ServiciosEnum serviciosEnum, InformacionCreditoPayload informacionCreditoPayload) {
        if(findCliente(token, informacionCreditoPayload) && findParamDias(token) && formulaDays()){
            //Si llega ha este punto es por que el cliente fue encontrado, esta parametrizado los días
            //y cumple el número de días
            if(percentageEvaluation()){
                //Envia error a cariAi
                return Optional.of(InformacionCreditoResponsePayload.builder()
                        .idRespuesta("1")
                        .descripcionRespuesta("Redirigir con agente")
                        .build());

            }
        }
        return generarCertificadosService.generarInformacionCredito(token, ServiciosEnum.SERVICIO_INFORMACION_CREDITO, informacionCreditoPayload);
    }

    public Boolean findCliente(String token, InformacionCreditoPayload informacionCreditoPayload){
        ResponseEntity<ClienteViewPayload> response = clienteClient.getClientByTelefonoAndNumCredito(token, informacionCreditoPayload.getTelefono(), SecurityUtilities.desencriptarCatch(informacionCreditoPayload.getNumeroVerificador()));
        if(HttpStatus.OK.equals(response.getStatusCode())){
            setCliente(response.getBody());
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public Boolean findParamDias(String token){
        Optional<String> response = parametrosAppService.getParamByKey(token, "DIAS_MACHINELERNING");
        if(response.isPresent()){
            setNumDias(Long.valueOf(response.get()));
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public Boolean formulaDays(){
        Long days = DateUtilities.generateDifferenceInDays(getCliente().getFechaDesembolso(), new Date());
        if(days < numDias)
            return Boolean.TRUE;
        return Boolean.FALSE;
    }

    public Boolean percentageEvaluation(){
        BigDecimal result = getCliente().getSaldoCapital().divide(getCliente().getValorDesembolso());
        BigDecimal porcentagePaidOut = (new BigDecimal(1).subtract(result)).multiply(new BigDecimal(100));
        if(porcentagePaidOut.longValue() < 70L){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}