package co.com.santander.accesorecursos.soap.config;

import co.com.santander.accesorecursos.soap.common.exception.BusinessException;
import co.com.santander.accesorecursos.soap.resources.documentos.WsFelec;
import co.com.santander.accesorecursos.soap.resources.documentos.WsFelecService;
import co.com.santander.accesorecursos.soap.resources.token.ComputecSTSDelegate;
import co.com.santander.accesorecursos.soap.resources.token.ComputecSTSService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.MalformedURLException;
import java.net.URL;

@Configuration
public class SoapConfig {

    @Value("${services.getToken}")
    private String serviceToken;

    @Value("${services.documentos}")
    private String serviceDocumentos;

    @Bean
    public ComputecSTSDelegate getServiceToken() {
        try {
            ComputecSTSService service = new ComputecSTSService(new URL(serviceToken));
            return service.getComputecSTSPort();
        } catch (MalformedURLException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @Bean
    public WsFelec getServiceDocumentos() {
        try {
            WsFelecService ss = new WsFelecService(new URL(serviceDocumentos));
            return ss.getWsFelecPort();
        } catch (MalformedURLException e) {
            throw new BusinessException(e.getMessage());
        }
    }


}
