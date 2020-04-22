package co.com.santander.chatbot.backend.web.service.impl;

import co.com.santander.chatbot.backend.web.common.aspect.validate.DatosTest;
import co.com.santander.chatbot.backend.web.common.aspect.validate.ValidateState;
import co.com.santander.chatbot.backend.web.service.GenerarCertificadosService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GenerarCertificadosServiceImpl implements GenerarCertificadosService {
    @Override
    @ValidateState
    public Optional<String> generarCertificado(String token, DatosTest datosTest) {
        return Optional.of("OK");
    }
}
