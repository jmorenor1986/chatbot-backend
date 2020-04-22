package co.com.santander.chatbot.backend.web.service.impl;

import co.com.santander.chatbot.acceso.recursos.clients.core.ClienteClient;
import co.com.santander.chatbot.backend.web.service.ClienteMapperService;
import co.com.santander.chatbot.backend.web.service.ClienteService;
import co.com.santander.chatbot.domain.payload.accesodatos.ClientePayload;
import co.com.santander.chatbot.domain.payload.accesodatos.ResponsePayload;
import co.com.santander.chatbot.domain.payload.accesodatos.cliente.ClienteViewPayload;
import co.com.santander.chatbot.domain.payload.service.obtenercreditos.ResponseObtenerCreditosPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteClient clienteClient;
    private final ClienteMapperService clienteMapper;

    @Autowired
    public ClienteServiceImpl(ClienteClient clienteClient, ClienteMapperService clienteMapper) {
        this.clienteClient = clienteClient;
        this.clienteMapper = clienteMapper;
    }

    @Override
    public ResponseEntity<ResponsePayload> validarCliente(ClientePayload cliente, String token) {
        return clienteClient.conusltarCliente(token, cliente);
    }

    @Override
    public Optional<ResponseObtenerCreditosPayload> obtenerCreditos(String token, String telefono) {
        Optional<List<ClienteViewPayload>> clientesCreditos = callServiceCreditosByTel(token, telefono);
        if (clientesCreditos.isPresent()) {
            if (Boolean.FALSE.equals(validateClients(clientesCreditos.get()))) {
                return Optional.of(
                        ResponseObtenerCreditosPayload.builder()
                                .idRespuesta(Long.valueOf("2"))
                                .resultadoConsulta(false)
                                .descripcionRespuesta("Numero de telefono asociado a dos clientes")
                                .build()
                );
            }
            return clienteMapper.fromListClientView(clientesCreditos.get());
        }
        return Optional.empty();
    }

    private Optional<List<ClienteViewPayload>> callServiceCreditosByTel(String token, String telefono) {
        ResponseEntity<List<ClienteViewPayload>> respuesta = clienteClient.getClientsByTel(token, telefono);
        if (HttpStatus.OK.equals(respuesta.getStatusCode())) {
            return Optional.of(respuesta.getBody());
        }
        return Optional.empty();
    }

    private Boolean validateClients(List<ClienteViewPayload> clients) {
        Map<String, Long> counting = clients.stream().collect(Collectors.groupingBy(ClienteViewPayload::getCedula, Collectors.counting()));
        if (counting.size() == 1) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
