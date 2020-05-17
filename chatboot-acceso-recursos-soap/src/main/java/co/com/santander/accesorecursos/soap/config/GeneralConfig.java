package co.com.santander.accesorecursos.soap.config;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.namespace.QName;

@Configuration
public class GeneralConfig {

    private static final QName SERVICE_NAME_TOKEN = new QName("http://sts.computec.experian.co/", "ComputecSTSService");

    @Value("${services.getToken}")
    private String url;

    @Bean
    public ModelMapper getMapper() {
        return new ModelMapper();
    }

}
