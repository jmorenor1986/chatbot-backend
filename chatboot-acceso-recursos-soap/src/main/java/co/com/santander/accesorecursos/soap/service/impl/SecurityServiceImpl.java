package co.com.santander.accesorecursos.soap.service.impl;

import co.com.santander.accesorecursos.soap.service.SecurityService;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {

    private final JaxWsProxyFactoryBean jaxWsProxyFactoryBean;
    private final LoggingInInterceptor loggingInInterceptor;
    private final LoggingOutInterceptor loggingOutInterceptor;

    private Object client;

    @Autowired
    public SecurityServiceImpl(JaxWsProxyFactoryBean jaxWsProxyFactoryBean, LoggingInInterceptor loggingInInterceptor, LoggingOutInterceptor loggingOutInterceptor) {
        this.jaxWsProxyFactoryBean = jaxWsProxyFactoryBean;
        this.loggingInInterceptor = loggingInInterceptor;
        this.loggingOutInterceptor = loggingOutInterceptor;
    }

    @Override
    public Object getService(String uri, Class<?> serviceClass) {
        client = createFactory(uri, serviceClass);
        setIntetceptors();
        return client;
    }

    private Object createFactory(String uri, Class<?> serviceClass) {
        jaxWsProxyFactoryBean.setAddress(uri);
        jaxWsProxyFactoryBean.setServiceClass(serviceClass);
        return jaxWsProxyFactoryBean.create();
    }

    private void setIntetceptors() {
        loggingOutInterceptor.setPrettyLogging(true);
        ClientProxy.getClient(client).getOutInterceptors().add(loggingOutInterceptor);
        loggingInInterceptor.setPrettyLogging(true);
        ClientProxy.getClient(client).getInInterceptors().add(loggingInInterceptor);
    }
}
