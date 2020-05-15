package co.com.santander.accesorecursos.soap.config;

import co.com.santander.accesorecursos.soap.common.exception.BusinessException;
import co.com.santander.accesorecursos.soap.resources.documentos.WsFelecService;
import co.com.santander.accesorecursos.soap.resources.token.ComputecSTSService;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
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
    public ComputecSTSService getServiceToken() {
        try {
            return new ComputecSTSService(new URL(serviceToken));
        } catch (MalformedURLException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @Bean
    public WsFelecService getServiceDocumentos() {
        try {
            return new WsFelecService(new URL(serviceDocumentos));
        } catch (MalformedURLException e) {
            throw new BusinessException(e.getMessage());
        }
    }


    @Bean
    public LoggingOutInterceptor loggingOutInterceptor() {
        return new LoggingOutInterceptor();
    }

    @Bean
    public LoggingInInterceptor loggingInInterceptor() {
        return new LoggingInInterceptor();
    }

    @Bean
    public JaxWsProxyFactoryBean jaxWsProxyFactoryBean() {
        return new JaxWsProxyFactoryBean();
    }


}
