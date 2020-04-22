package co.com.santander.chatbot.backend.web.service;

import co.com.santander.chatbot.domain.payload.accesodatos.cliente.ClienteViewPayload;
import co.com.santander.chatbot.domain.payload.service.obtenercreditos.ResponseObtenerCreditosPayload;

import java.util.List;
import java.util.Optional;

public interface ClienteMapperService {

    Optional<ResponseObtenerCreditosPayload> fromListClientView(List<ClienteViewPayload> clients);
}
