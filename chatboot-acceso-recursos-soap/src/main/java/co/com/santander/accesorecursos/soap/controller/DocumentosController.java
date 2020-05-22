package co.com.santander.accesorecursos.soap.controller;

import co.com.santander.accesorecursos.soap.service.DocumentosService;
import co.com.santander.chatbot.domain.payload.enviarextracto.ConsultarDocumentoPayload;
import co.com.santander.chatbot.domain.payload.enviarextracto.ConsultarDocumentosPayloadResponse;
import co.com.santander.chatbot.domain.payload.enviarextracto.EnviarMailDocumentoPayload;
import co.com.santander.chatbot.domain.payload.enviarextracto.EnvioDocumentoMailResponsePayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/extracto")
public class DocumentosController {


    private final DocumentosService documentosService;

    @Autowired
    public DocumentosController(DocumentosService documentosService) {
        this.documentosService = documentosService;
    }

    @PostMapping("/consultar-documentos")
    public ResponseEntity<List<ConsultarDocumentosPayloadResponse>> consultaDocumentos(@RequestBody ConsultarDocumentoPayload consultarDocumentoPayload) {
        List<ConsultarDocumentosPayloadResponse> response = documentosService.consultarDocumentos(consultarDocumentoPayload);
        if( !response.isEmpty() )
            return new ResponseEntity<>(response, HttpStatus.OK);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT );
    }

    @PostMapping("/envio-mail-documento")
    public ResponseEntity<EnvioDocumentoMailResponsePayload> envioMailDocumento(@RequestBody EnviarMailDocumentoPayload enviarMailDocumentoPayload) {
        return new ResponseEntity<>(documentosService.enviarMailDocumentoId(enviarMailDocumentoPayload), HttpStatus.OK);
    }
}
