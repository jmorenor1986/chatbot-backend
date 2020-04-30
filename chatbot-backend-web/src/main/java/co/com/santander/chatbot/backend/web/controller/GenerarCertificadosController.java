package co.com.santander.chatbot.backend.web.controller;

import co.com.santander.chatbot.backend.web.service.GenerarCertificadosService;
import co.com.santander.chatbot.domain.enums.ServiciosEnum;
import co.com.santander.chatbot.domain.payload.service.certificados.InformacionCreditoPayload;
import co.com.santander.chatbot.domain.payload.service.certificados.InformacionCreditoResponsePayload;
import co.com.santander.chatbot.domain.payload.service.certificados.PazYSalvoPayload;
import co.com.santander.chatbot.domain.validators.exceptions.ValidateStateCertificateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<InformacionCreditoResponsePayload> generarCertificadoPazYSalvo(@RequestHeader("Authorization") String bearerToken, @Valid @RequestBody PazYSalvoPayload pazYSalvoPayload) {
        Optional<InformacionCreditoResponsePayload> result = generarCertificadosService.generarCertificadoEstandar(bearerToken, pazYSalvoPayload, ServiciosEnum.SERVICIO_PAZ_Y_SALVO, 3L);
        if (result.isPresent())
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        throw new ValidateStateCertificateException("Error al consultar los datos");
    }

    @PostMapping(value = "/informacion-credito")
    public ResponseEntity<InformacionCreditoResponsePayload> informacionCredito(@RequestHeader("Authorization") String bearerToken, @Valid @RequestBody InformacionCreditoPayload informacionCreditoPayload) {
        Optional<InformacionCreditoResponsePayload> result = generarCertificadosService.generarInformacionCredito(bearerToken, informacionCreditoPayload);
        if (result.isPresent())
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        throw new ValidateStateCertificateException("Error al consultar los datos");
    }

    @PostMapping(value = "/autorizacion-debito")
    public ResponseEntity<InformacionCreditoResponsePayload> autorizacionDebito(@RequestHeader("Authorization") String bearerToken, @Valid @RequestBody PazYSalvoPayload debitoPayload) {
        Optional<InformacionCreditoResponsePayload> result = generarCertificadosService.generarCertificadoEstandar(bearerToken, debitoPayload, ServiciosEnum.SERVICIO_DEBITO_AUTOMATICO, 4L);
        if (result.isPresent())
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        throw new ValidateStateCertificateException("Error al consultar los datos");
    }

    @PostMapping(value = "/certificacion-declaracion-renta")
    public ResponseEntity<InformacionCreditoResponsePayload> certificacionDeclaracionRenta(@RequestHeader("Authorization") String bearerToken, @Valid @RequestBody PazYSalvoPayload declaracionRentaPayload) {
        Optional<InformacionCreditoResponsePayload> result = generarCertificadosService.generarCertificadoEstandar(bearerToken, declaracionRentaPayload, ServiciosEnum.SERVICIO_DECLARACION_RENTA, 5L);
        if (result.isPresent())
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        throw new ValidateStateCertificateException("Error al consultar los datos");
    }
}
