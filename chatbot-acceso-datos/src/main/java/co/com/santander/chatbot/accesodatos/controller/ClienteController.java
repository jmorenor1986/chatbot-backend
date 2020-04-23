package co.com.santander.chatbot.accesodatos.controller;

import co.com.santander.chatbot.accesodatos.entity.Cliente;
import co.com.santander.chatbot.accesodatos.service.ClienteService;
import co.com.santander.chatbot.domain.payload.accesodatos.ClientePayload;
import co.com.santander.chatbot.domain.payload.accesodatos.ResponsePayload;
import co.com.santander.chatbot.domain.payload.accesodatos.cliente.ClienteViewPayload;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("v1/cliente")
public class ClienteController {

    private final ClienteService clienteService;
    private final ModelMapper mapper;

    @Autowired
    public ClienteController(ClienteService clienteService, ModelMapper mapper) {
        this.clienteService = clienteService;
        this.mapper = mapper;
    }

    @PostMapping("/")
    public ResponseEntity<ResponsePayload> consultaCliente(@Valid @RequestBody ClientePayload input) {
        Optional<Boolean> result = clienteService.consultarCliente(input.getTelefono(), input.getCedula());
        if (result.isPresent())
            return new ResponseEntity<>(ResponsePayload.builder()
                    .resultadoValidacion(result.get())
                    .idRespuesta(0)
                    .descripcionRespuesta(result.get().toString())
                    .build(), result.get().equals(Boolean.TRUE) ? HttpStatus.OK : HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(ResponsePayload.builder()
                .resultadoValidacion(Boolean.TRUE)
                .idRespuesta(0)
                .descripcionRespuesta("Error consultando el servicio")
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ClienteViewPayload>> getClientsByTel(@RequestParam(value = "telefono") String telefono) {
        Optional<List<Cliente>> listaClientes = clienteService.consultarClienteByTelefono(telefono);
        if (listaClientes.isPresent()) {
            Type listType = new TypeToken<List<ClienteViewPayload>>() {
            }.getType();
            List<ClienteViewPayload> listaCliente = mapper.map(listaClientes.get(), listType);
            return new ResponseEntity<>(listaCliente, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping(value = "/telAndNumCredit/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClienteViewPayload> getClientByTelefonoAndNumCredito(@RequestParam(value = "telefono") String telefono, @RequestParam(value = "numCredito") String numCredito){
        Optional<Cliente> cliente = clienteService.consultarClienteByTelefonoAndNumCredito(telefono,numCredito);
        if(cliente.isPresent()){
            ClienteViewPayload clienteView = mapper.map(cliente.get(), ClienteViewPayload.class);
            return new ResponseEntity<>(clienteView, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}