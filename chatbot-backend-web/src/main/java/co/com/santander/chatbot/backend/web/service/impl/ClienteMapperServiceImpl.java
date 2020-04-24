package co.com.santander.chatbot.backend.web.service.impl;

import co.com.santander.chatbot.backend.web.common.utilities.SecurityUtilities;
import co.com.santander.chatbot.backend.web.service.ClienteMapperService;
import co.com.santander.chatbot.domain.payload.accesodatos.cliente.ClienteViewPayload;
import co.com.santander.chatbot.domain.payload.service.obtenercreditos.ResponseCreditosPayload;
import co.com.santander.chatbot.domain.payload.service.obtenercreditos.ResponseObtenerCreditosPayload;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log
public class ClienteMapperServiceImpl implements ClienteMapperService {

    @Override
    public Optional<ResponseObtenerCreditosPayload> fromListClientView(List<ClienteViewPayload> clients) {
        if (!clients.isEmpty()) {
            ResponseObtenerCreditosPayload response = ResponseObtenerCreditosPayload.builder()
                    .idRespuesta(Long.valueOf("0"))
                    .resultadoConsulta(Boolean.TRUE)
                    .descripcionRespuesta("Servicio consumido de forma exitosa")
                    .build();
            response.setCreditos(
                    clients.stream().parallel()
                            .map(item -> mapper(item))
                            .collect(Collectors.toList())
            );
            return Optional.of(response);
        }
        return Optional.empty();
    }

    private ResponseCreditosPayload mapper(ClienteViewPayload clientes) {
        String creditoEncriptado = "";
        try {
            creditoEncriptado = SecurityUtilities.encriptar(clientes.getNumerCredito());
        } catch (Exception e) {
            log.severe("Error al desencriptar la informacion ".concat(e.getMessage()) );
        }
        return ResponseCreditosPayload.builder()
                .banco(Long.valueOf(clientes.getIdBanco()))
                .convenio(clientes.getConvenio())
                .numeroVerificador(creditoEncriptado)
                .numeroCreditoOfuscado(ofuscarCredito(clientes.getNumerCredito()))
                .build();
    }

    private String ofuscarCredito(String credito) {
        String resultado = "";
        int tamanio = credito.length();
        for (int i = 0; i < tamanio; i++) {
            resultado += "x";
        }
        String temporal = "";
        for(int i = 0 ; i < 5 ; i++){
            String caracter = credito.substring(tamanio-1, tamanio);
            temporal =   caracter + temporal;
            tamanio--;
        }
        resultado = resultado.substring(0, tamanio);
        return resultado + temporal;
    }

}
