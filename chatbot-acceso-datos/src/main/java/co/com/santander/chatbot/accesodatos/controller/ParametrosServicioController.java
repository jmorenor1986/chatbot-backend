package co.com.santander.chatbot.accesodatos.controller;

import co.com.santander.chatbot.accesodatos.entity.ParametrosServicio;
import co.com.santander.chatbot.accesodatos.service.ParametrosServicioService;
import co.com.santander.chatbot.domain.enums.ServiciosEnum;
import co.com.santander.chatbot.domain.payload.accesodatos.ParametrosServicioPayload;
import co.com.santander.chatbot.domain.payload.accesodatos.ResponsePayload;
import co.com.santander.chatbot.domain.payload.accesodatos.ValidarProcesoPayload;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("v1/parametros-servicio")
public class ParametrosServicioController {
    private final ParametrosServicioService parametrosServicioService;
    private final ModelMapper map;

    public ParametrosServicioController(ParametrosServicioService parametrosServicioService, ModelMapper map) {
        this.parametrosServicioService = parametrosServicioService;
        this.map = map;
    }

    @PostMapping("/consultar-proceso")
    public ResponseEntity<ResponsePayload> consultarProceso(@RequestBody ValidarProcesoPayload validarProcesoPayload) {

        return new ResponseEntity<>(parametrosServicioService.validarSolicitud(validarProcesoPayload.getCanal(),
                validarProcesoPayload.getServicio(), validarProcesoPayload.getFechaUltimaSolicitud()), HttpStatus.OK);

    }

    @GetMapping(value = "/servicio/")
    public ResponseEntity<ParametrosServicioPayload> getParametroService(@RequestParam(value = "servicio")ServiciosEnum servicioEnum){
        Optional<ParametrosServicio> response = parametrosServicioService.findByServicio(servicioEnum);
        if(response.isPresent()){
            ParametrosServicioPayload dto = map.map(response.get(), ParametrosServicioPayload.class );
            return new ResponseEntity<ParametrosServicioPayload>(dto , HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
