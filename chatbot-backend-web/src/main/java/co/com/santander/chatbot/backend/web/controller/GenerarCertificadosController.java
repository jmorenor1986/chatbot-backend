package co.com.santander.chatbot.backend.web.controller;

import co.com.santander.chatbot.backend.web.service.GenerarCertificadosService;
import co.com.santander.chatbot.backend.web.service.ProxyInformacionCredito;
import co.com.santander.chatbot.domain.enums.ServiciosEnum;
import co.com.santander.chatbot.domain.payload.service.certificados.GenericCertificatePayload;
import co.com.santander.chatbot.domain.payload.service.certificados.InformacionCreditoPayload;
import co.com.santander.chatbot.domain.payload.service.certificados.InformacionCreditoResponsePayload;
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
    private final ProxyInformacionCredito proxyInformacionCredito;

    @Autowired
    public GenerarCertificadosController(GenerarCertificadosService generarCertificadosService, ProxyInformacionCredito proxyInformacionCredito) {
        this.generarCertificadosService = generarCertificadosService;
        this.proxyInformacionCredito = proxyInformacionCredito;
    }


    @PostMapping(value = "/paz-y-salvo")
    public ResponseEntity<InformacionCreditoResponsePayload> generarCertificadoPazYSalvo(@RequestHeader("Authorization") String bearerToken, @Valid @RequestBody GenericCertificatePayload genericCertificatePayload) {
        Optional<InformacionCreditoResponsePayload> result = generarCertificadosService.generarCertificadoEstandar(bearerToken, ServiciosEnum.SERVICIO_PAZ_Y_SALVO, genericCertificatePayload, 3L);
        if (result.isPresent())
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        throw new ValidateStateCertificateException("Error al consultar los datos",0L);
    }

    @PostMapping(value = "/informacion-credito")
    public ResponseEntity<InformacionCreditoResponsePayload> informacionCredito(@RequestHeader("Authorization") String bearerToken, @Valid @RequestBody InformacionCreditoPayload informacionCreditoPayload) {
        Optional<InformacionCreditoResponsePayload> result = proxyInformacionCredito.generarInformacionCredito(bearerToken, ServiciosEnum.SERVICIO_INFORMACION_CREDITO, informacionCreditoPayload);
        if (result.isPresent())
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        throw new ValidateStateCertificateException("Error al consultar los datos", 0L);
    }

    @PostMapping(value = "/autorizacion-debito")
    public ResponseEntity<InformacionCreditoResponsePayload> autorizacionDebito(@RequestHeader("Authorization") String bearerToken, @Valid @RequestBody GenericCertificatePayload debitoPayload) {
        Optional<InformacionCreditoResponsePayload> result = generarCertificadosService.generarCertificadoEstandar(bearerToken, ServiciosEnum.SERVICIO_DEBITO_AUTOMATICO, debitoPayload, 4L);
        if (result.isPresent())
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        throw new ValidateStateCertificateException("Error al consultar los datos",0L);
    }

    @PostMapping(value = "/certificacion-declaracion-renta")
    public ResponseEntity<InformacionCreditoResponsePayload> certificacionDeclaracionRenta(@RequestHeader("Authorization") String bearerToken, @Valid @RequestBody GenericCertificatePayload declaracionRentaPayload) {
        Optional<InformacionCreditoResponsePayload> result = generarCertificadosService.generarCertificadoEstandar(bearerToken, ServiciosEnum.SERVICIO_DECLARACION_RENTA, declaracionRentaPayload,  5L);
        if (result.isPresent())
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        throw new ValidateStateCertificateException("Error al consultar los datos", 0L);
    }
}
