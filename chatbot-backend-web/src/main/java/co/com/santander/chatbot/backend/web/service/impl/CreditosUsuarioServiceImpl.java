
package co.com.santander.chatbot.backend.web.service.impl;

import co.com.santander.chatbot.acceso.recursos.clients.core.CreditosUsuarioClient;
import co.com.santander.chatbot.acceso.recursos.clients.core.dto.RequestDto;
import co.com.santander.chatbot.acceso.recursos.clients.core.dto.ResponseDto;
import co.com.santander.chatbot.backend.web.service.CreditosUsuarioService;
import co.com.santander.chatbot.domain.dto.accesorecursos.RespuestaDto;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log
public class CreditosUsuarioServiceImpl implements CreditosUsuarioService {

    private final CreditosUsuarioClient creditosUsuarioClient;

    @Autowired
    public CreditosUsuarioServiceImpl(CreditosUsuarioClient creditosUsuarioClient) {
        this.creditosUsuarioClient = creditosUsuarioClient;
    }

    @Override
    public Optional<RespuestaDto> consultarCreditosUsuario(String telefono, int tipoOperacion) {
        ResponseEntity<ResponseDto> resultClient = creditosUsuarioClient.conusltarCreditosCliente(RequestDto.builder()
                .telefono(telefono)
                .tipoOperacion(tipoOperacion)
                .build());
        return Optional.empty();
    }
}
