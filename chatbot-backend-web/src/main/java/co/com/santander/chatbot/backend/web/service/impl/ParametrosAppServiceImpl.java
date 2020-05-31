package co.com.santander.chatbot.backend.web.service.impl;

import co.com.santander.chatbot.acceso.recursos.clients.core.ParametrosAppClient;
import co.com.santander.chatbot.backend.web.service.ParametrosAppService;
import co.com.santander.chatbot.domain.payload.accesodatos.ParametrosAppPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ParametrosAppServiceImpl implements ParametrosAppService {

    private final ParametrosAppClient parametrosAppClient;

    @Autowired
    public ParametrosAppServiceImpl(ParametrosAppClient parametrosAppClient) {
        this.parametrosAppClient = parametrosAppClient;
    }

    @Override
    public Optional<String> getParamByKey(String token, String clave) {
        ResponseEntity<ParametrosAppPayload> response = parametrosAppClient.getByClave(token, clave);
        if(HttpStatus.OK.equals(response.getStatusCode())){
            ParametrosAppPayload responseObj = response.getBody();
            return  Optional.of(responseObj.getValor());
        }
        return Optional.empty();
    }
}
