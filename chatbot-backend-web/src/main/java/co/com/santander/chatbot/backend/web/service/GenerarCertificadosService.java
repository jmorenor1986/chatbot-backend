package co.com.santander.chatbot.backend.web.service;

import co.com.santander.chatbot.backend.web.common.aspect.validate.DatosTest;

import java.util.Optional;

public interface GenerarCertificadosService {
    Optional<String> generarCertificado(String token, DatosTest datosTest);
}
