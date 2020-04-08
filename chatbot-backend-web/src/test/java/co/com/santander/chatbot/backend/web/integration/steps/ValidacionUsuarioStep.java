package co.com.santander.chatbot.backend.web.integration.steps;

import co.com.santander.chatbot.backend.web.controller.payload.RespuestaPayload;
import co.com.santander.chatbot.backend.web.controller.payload.UsuarioPayload;
import co.com.santander.chatbot.backend.web.dto.TokenDto;
import co.com.santander.chatbot.backend.web.dto.UsuarioDto;
import com.google.gson.Gson;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.Getter;
import lombok.Setter;
import org.junit.Assert;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public class ValidacionUsuarioStep {

    private final Logger log = LoggerFactory.getLogger(ValidacionUsuarioStep.class);

    @Autowired
    protected HttpClient httpClient;
    private ResponseEntity<?> response;
    private UsuarioPayload usuarioPayload;

    private UsuarioDto usuarioLoginDto;
    @Getter
    @Setter
    private String token;


    @Given("^un usuario de aplicacion con los siguientes datos$")
    public void usuarioDatosLogin(DataTable payload) {
        List<Map<String, String>> rows = payload.asMaps(String.class, String.class);
        for (Map<String, String> columns : rows) {
            usuarioLoginDto = UsuarioDto.builder()
                    .correo(columns.get("usuario"))
                    .contrasena(columns.get("contrasenia"))
                    .build();
        }
    }

    @When("^envio la informacion al servicio de login$")
    public void envioServicioLogin() {
        ResponseEntity<String> responseEntity = httpClient.postWithRequest("/login/", usuarioLoginDto);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            TokenDto token = new Gson().fromJson(responseEntity.getBody(), TokenDto.class);
            setToken("Bearer ".concat(token.getToken()));
        }
        Assert.assertNotNull(getToken());
    }

    @Then("debo tener un token de autenticacion {string}")
    public void validoToken(String token) {
        Assert.assertEquals(true, getToken().contains(token));
    }

    @Given("^un usuario con los siguientes datos$")
    public void usuarioConDatos(DataTable payload) {
        List<Map<String, String>> rows = payload.asMaps(String.class, String.class);
        for (Map<String, String> columns : rows) {
            usuarioPayload = UsuarioPayload.builder()
                    .colaIdentificacion(columns.get("colaIdentificacion"))
                    .telefono(Long.parseLong(columns.get("telefono")))
                    .build();
        }
    }

    @When("llama el servicio validarUsuario")
    public void llamaServicioValidarUsuario() {
        log.info("Este es el payload que se desea enviar: {} ", usuarioPayload.toString());
        response = httpClient.postWithRequest("/v1/validarUsuario/", usuarioPayload, getToken());
        Assert.assertNotNull(response);
    }

    @Then("retorna resultastatusCode {string}")
    public void retornaCodRespuesta(String codRespuesta) {
        Assert.assertEquals(response.getStatusCodeValue(), Integer.parseInt(codRespuesta));
    }

}
