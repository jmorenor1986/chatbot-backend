package co.com.santander.chatbot.backend.web.service.impl;

import co.com.santander.chatbot.backend.web.common.aspect.log.BussinessLog;
import co.com.santander.chatbot.backend.web.service.ClienteService;
import co.com.santander.chatbot.domain.enums.ServiciosEnum;
import co.com.santander.chatbot.domain.payload.accesodatos.ClientePayload;
import co.com.santander.chatbot.domain.payload.accesodatos.ResponsePayload;
import co.com.santander.chatbot.domain.payload.service.obtenercreditos.CreditosUsuarioPayload;
import co.com.santander.chatbot.domain.payload.service.obtenercreditos.ResponseCreditosPayload;
import co.com.santander.chatbot.domain.payload.service.obtenercreditos.ResponseObtenerCreditosPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service("proxyClienteServiceImpl")
public class ProxyClienteServiceImpl implements ClienteService {

    private ClienteService clienteService;

    @Autowired
    public ProxyClienteServiceImpl(@Qualifier("clienteServiceImpl") ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Override
    public ResponseEntity<ResponsePayload> validarCliente(String token, ServiciosEnum serviciosEnum, String telefono, ClientePayload cliente) {
        return clienteService.validarCliente(token, serviciosEnum, telefono, cliente);
    }

    @Override
    @BussinessLog
    public Optional<ResponseObtenerCreditosPayload> obtenerCreditos(String token, ServiciosEnum serviciosEnum, String telefono, CreditosUsuarioPayload credito) {
        Optional<ResponseObtenerCreditosPayload> response = clienteService.obtenerCreditos(token, serviciosEnum, telefono, credito);
        return filterCreditsByBank(response, credito.getTipoOperacion());
    }

    private Optional<ResponseObtenerCreditosPayload> filterCreditsByBank(Optional<ResponseObtenerCreditosPayload> creditos, Long tipoOperacion) {
        List<ResponseCreditosPayload> response = null;
        if (creditos.isPresent()) {
            response = creditos.get().getCreditos();
            if(Long.valueOf(2L).equals(tipoOperacion) || Long.valueOf(3L).equals(tipoOperacion) || Long.valueOf(5L).equals(tipoOperacion) || Long.valueOf(7L).equals(tipoOperacion) ){
                response = proccessToStatusCredits(response, "Cerrado", Boolean.FALSE);
            }
            //Operacion Informacion de credito
            if (Long.valueOf(2L).equals(tipoOperacion)) {
                response = proccessToVehicleCredits(response);
            }
            //Operacion Paz y Salvo
            if(Long.valueOf(4L).equals(tipoOperacion)){
                response = proccessToStatusCredits(response, "Cerrado", Boolean.TRUE);
            }
            creditos.get().setCreditos(response);
        }
        return creditos;
    }

    private List<ResponseCreditosPayload> proccessToVehicleCredits(List<ResponseCreditosPayload> credits) {
        return credits.stream().parallel()
                .filter(filterCreditosVehiculo())
                .collect(Collectors.toList());
    }

    private Predicate<ResponseCreditosPayload> filterCreditosVehiculo() {
        return (ResponseCreditosPayload credit) -> {
            //Id Banco ->  3: Av Villas  2: Juriscoop
            if( Long.valueOf(3).equals(credit.getBanco()) || Long.valueOf(2).equals(credit.getBanco()) ){
                return Boolean.FALSE;
            }else if(Long.valueOf(1).equals(credit.getBanco()) && Long.valueOf(2).equals(credit.getTipoCredito()) ){
                //Id Banco ->  1: Santander     Tipo Credito -> 2: Consumo
                return Boolean.FALSE;
            }
            return Boolean.TRUE;
        };
    }

    private List<ResponseCreditosPayload> proccessToStatusCredits(List<ResponseCreditosPayload> credits, String status, Boolean closed) {
        return credits.stream().parallel()
                .filter(filterStatusCredits(status, closed))
                .collect(Collectors.toList());
    }

    private Predicate<ResponseCreditosPayload> filterStatusCredits(String status, Boolean closed) {
        return (ResponseCreditosPayload credit) -> {
            if( status.equalsIgnoreCase(credit.getEstado()) ){
                return closed;
            }
            return !closed;
        };
    }
}
