package co.com.santander.chatbot.backend.web.controller;

import co.com.santander.chatbot.backend.web.service.GenerarCertificadosService;
import co.com.santander.chatbot.domain.enums.ServiciosEnum;
import co.com.santander.chatbot.domain.payload.accesodatos.ResponsePayload;
import co.com.santander.chatbot.domain.payload.service.certificados.CertificadoPayload;
import co.com.santander.chatbot.domain.validators.exceptions.ValidateStateCertificateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("v1/obtener-certificados")
public class GenerarCertificadosController {
    private final GenerarCertificadosService generarCertificadosService;

    @Autowired
    public GenerarCertificadosController(GenerarCertificadosService generarCertificadosService) {
        this.generarCertificadosService = generarCertificadosService;
    }


    @PostMapping(value = "/paz-y-salvo")
    public ResponseEntity<ResponsePayload> generarCertificadoPazYSalvo(@RequestHeader("Authorization") String bearerToken, @Valid @RequestBody CertificadoPayload certificadoPayload) {
        Optional<ResponsePayload> result = generarCertificadosService.generarCertificadoPazYSalvo(bearerToken, certificadoPayload, ServiciosEnum.SERVICIO_PAZ_Y_SALVO, new Date());
        if (result.isPresent())
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        throw new ValidateStateCertificateException("Error al consultar los datos");
    }
}
