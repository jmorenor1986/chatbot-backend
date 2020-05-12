package co.com.santander.accesorecursos.soap.controller;

import co.com.santander.accesorecursos.soap.resources.documentos.ConsultarDocumentosResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/extracto")
public class DocumentosController {

    @PostMapping("/consultar-documentos")
    public ResponseEntity<List<ConsultarDocumentosResponse>> consultaDocumentos() {

        return null;
    }
}
