package co.com.santander.chatbot.backend.web.integration.steps;

import co.com.santander.chatbot.backend.web.controller.payload.RespuestaPayload;
import co.com.santander.chatbot.backend.web.controller.payload.UsuarioPayload;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public class ValidacionUsuarioStep {
    @Autowired
    protected HttpClient httpClient;
    private ResponseEntity<?> response;
    private UsuarioPayload usuarioPayload;

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
        try {
            response = httpClient.getWithRequest("/v1/validarUsuario/", usuarioPayload);
        } catch (Exception e) {
            e.printStackTrace();
            response = new ResponseEntity<>(RespuestaPayload.builder().build(), HttpStatus.FORBIDDEN);
        }

        Assert.assertNotNull(response);
    }

    @Then("retorna resultastatusCode {string}")
    public void retornaCodRespuesta(String codRespuesta) {
        Assert.assertEquals(response.getStatusCodeValue(), Integer.parseInt(codRespuesta));
    }


}
