package co.com.santander.chatbot.backend.web.controller;

import co.com.santander.chatbot.backend.web.service.GenerarCertificadosService;
import co.com.santander.chatbot.backend.web.service.MapperTelService;
import co.com.santander.chatbot.backend.web.service.ProxyInformacionCredito;
import co.com.santander.chatbot.backend.web.service.ValidateClienteService;
import co.com.santander.chatbot.domain.enums.ServiciosEnum;
import co.com.santander.chatbot.domain.payload.service.certificados.GenericCertificatePayload;
import co.com.santander.chatbot.domain.payload.service.certificados.InformacionCreditoPayload;
import co.com.santander.chatbot.domain.payload.service.certificados.InformacionCreditoResponsePayload;
import co.com.santander.chatbot.domain.validators.exceptions.ValidateStateCertificateException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("v1/obtener-certificados")
@Api(value = "Generación de certificados ofrecidos", tags = {"V1: {Paz y Salvo} {informacion de credito} {autorizacion debito automatico} {certificación declaración de renta}"})
public class GenerarCertificadosController {
    private final GenerarCertificadosService generarCertificadosService;
    private final ProxyInformacionCredito proxyInformacionCredito;
    private final MapperTelService mapperTelService;
    private final ValidateClienteService validateClienteService;

    @Autowired
    public GenerarCertificadosController(GenerarCertificadosService generarCertificadosService, ProxyInformacionCredito proxyInformacionCredito, MapperTelService mapperTelService,  ValidateClienteService validateClienteService) {
        this.generarCertificadosService = generarCertificadosService;
        this.proxyInformacionCredito = proxyInformacionCredito;
        this.mapperTelService = mapperTelService;
        this.validateClienteService = validateClienteService;
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "{Operacion exitosa}, {Error formato telefono}, {Error campo obligatorio}, {Error Número de verificador}"),
            @ApiResponse(code = 401, message = "{No autorizado para este recurso}"),
            @ApiResponse(code = 403, message = "{Peticion Sin token}, {Token expirado}")
    })
    @PostMapping(value = "/paz-y-salvo")
    public ResponseEntity<InformacionCreditoResponsePayload> generarCertificadoPazYSalvo(@RequestHeader("Authorization") String bearerToken, @Valid @RequestBody GenericCertificatePayload genericCertificatePayload) {
        genericCertificatePayload.setTelefono(mapperTelService.mapTelDigits(genericCertificatePayload.getTelefono()));
        validateClienteService.validateClient(bearerToken, genericCertificatePayload.getTelefono());
        Optional<InformacionCreditoResponsePayload> result = generarCertificadosService.generarCertificadoEstandar(bearerToken, ServiciosEnum.SERVICIO_PAZ_Y_SALVO, genericCertificatePayload, 3L);
        if (result.isPresent())
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        throw new ValidateStateCertificateException("Error al consultar los datos al consultar el paz y salvo",0L);
    }
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "{Operacion exitosa}, {Error formato telefono}, {Error campo obligatorio}, {Error Número de verificador}"),
            @ApiResponse(code = 401, message = "{No autorizado para este recurso}"),
            @ApiResponse(code = 403, message = "{Peticion Sin token}, {Token expirado}")
    })
    @PostMapping(value = "/informacion-credito")
    public ResponseEntity<InformacionCreditoResponsePayload> informacionCredito(@RequestHeader("Authorization") String bearerToken, @Valid @RequestBody InformacionCreditoPayload informacionCreditoPayload) {
        informacionCreditoPayload.setTelefono(mapperTelService.mapTelDigits(informacionCreditoPayload.getTelefono()));
        validateClienteService.validateClient(bearerToken, informacionCreditoPayload.getTelefono());
        Optional<InformacionCreditoResponsePayload> result = proxyInformacionCredito.generarInformacionCredito(bearerToken, ServiciosEnum.SERVICIO_INFORMACION_CREDITO, informacionCreditoPayload);
        if (result.isPresent())
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        throw new ValidateStateCertificateException("Error al consultar los datos en informacion de credito", 0L);
    }
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "{Operacion exitosa}, {Error formato telefono}, {Error campo obligatorio}, {Error Número de verificador}"),
            @ApiResponse(code = 401, message = "{No autorizado para este recurso}"),
            @ApiResponse(code = 403, message = "{Peticion Sin token}, {Token expirado}")
    })
    @PostMapping(value = "/autorizacion-debito")
    public ResponseEntity<InformacionCreditoResponsePayload> autorizacionDebito(@RequestHeader("Authorization") String bearerToken, @Valid @RequestBody GenericCertificatePayload debitoPayload) {
        debitoPayload.setTelefono(mapperTelService.mapTelDigits(debitoPayload.getTelefono()));
        validateClienteService.validateClient(bearerToken, debitoPayload.getTelefono());
        Optional<InformacionCreditoResponsePayload> result = generarCertificadosService.generarCertificadoEstandar(bearerToken, ServiciosEnum.SERVICIO_DEBITO_AUTOMATICO, debitoPayload, 4L);
        if (result.isPresent())
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        throw new ValidateStateCertificateException("Error al consultar los datos en autorizacion de debito automatico",0L);
    }
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "{Operacion exitosa}, {Error formato telefono}, {Error campo obligatorio}, {Error Número de verificador}"),
            @ApiResponse(code = 401, message = "{No autorizado para este recurso}"),
            @ApiResponse(code = 403, message = "{Peticion Sin token}, {Token expirado}")
    })
    @PostMapping(value = "/certificacion-declaracion-renta")
    public ResponseEntity<InformacionCreditoResponsePayload> certificacionDeclaracionRenta(@RequestHeader("Authorization") String bearerToken, @Valid @RequestBody GenericCertificatePayload declaracionRentaPayload) {
        declaracionRentaPayload.setTelefono(mapperTelService.mapTelDigits(declaracionRentaPayload.getTelefono()));
        validateClienteService.validateClient(bearerToken, declaracionRentaPayload.getTelefono());
        Optional<InformacionCreditoResponsePayload> result = generarCertificadosService.generarCertificadoEstandar(bearerToken, ServiciosEnum.SERVICIO_DECLARACION_RENTA, declaracionRentaPayload,  5L);
        if (result.isPresent())
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        throw new ValidateStateCertificateException("Error al consultar los datos en la declaracion de renta", 0L);
    }
}
