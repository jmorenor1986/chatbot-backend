package co.com.santander.chatbot.backend.web.service.impl;

import co.com.santander.chatbot.backend.web.service.ClienteMapperService;
import co.com.santander.chatbot.domain.payload.accesodatos.cliente.ClienteViewPayload;
import co.com.santander.chatbot.domain.payload.service.obtenercreditos.ResponseObtenerCreditosPayload;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteMapperServiceImpl implements ClienteMapperService {




    @Override
    public Optional<ResponseObtenerCreditosPayload> fromListClientView(List<ClienteViewPayload> clients) {
        if(!clients.isEmpty()){
            ResponseObtenerCreditosPayload response = ResponseObtenerCreditosPayload.builder()
                    .idRespuesta(Long.valueOf("0"))
                    .resultadoConsulta(Boolean.TRUE)
                    .descripcionRespuesta("Servicio consumido de forma exitosa")
                    .build();
            //clients.stream().parallel().map(item -> )
        }
        return Optional.empty();
    }


}
