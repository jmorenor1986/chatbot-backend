package co.com.santander.chatbot.backend.web.controller;

import co.com.santander.chatbot.backend.web.common.aspect.validate.DatosTest;
import co.com.santander.chatbot.backend.web.service.GenerarCertificadosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/obtenerCertificados")
public class GenerarCertificadosController {
    private final GenerarCertificadosService generarCertificadosService;

    @Autowired
    public GenerarCertificadosController(GenerarCertificadosService generarCertificadosService) {
        this.generarCertificadosService = generarCertificadosService;
    }

    @PostMapping(value = "/pazYSalvo")
    public String generarCertificadoPazYSalvo(@RequestHeader("Authorization") String bearerToken, @Valid @RequestBody DatosTest datosTest) {
        return generarCertificadosService.generarCertificado(bearerToken, datosTest).get();
    }
}
