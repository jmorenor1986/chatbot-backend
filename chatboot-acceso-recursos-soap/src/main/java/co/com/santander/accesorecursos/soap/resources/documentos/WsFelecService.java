package co.com.santander.accesorecursos.soap.resources.documentos;

import java.net.MalformedURLException;
import java.net.URL;
import javax.jws.HandlerChain;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.7.2
 * 2020-05-11T22:29:03.229+02:00
 * Generated source version: 2.7.2
 * 
 */
@WebServiceClient(name = "WsFelecService", 
                  wsdlLocation = "file:/C:/Users/JOJMOR~1/AppData/Local/Temp/tempdir3759899551026961253.tmp/WsFelecService_1.wsdl",
                  targetNamespace = "http://co.com.computec.wsservicioselectronicos.ws/")
public class WsFelecService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://co.com.computec.wsservicioselectronicos.ws/", "WsFelecService");
    public final static QName WsFelecPort = new QName("http://co.com.computec.wsservicioselectronicos.ws/", "WsFelecPort");
    static {
        URL url = null;
        try {
            url = new URL("file:/C:/Users/JOJMOR~1/AppData/Local/Temp/tempdir3759899551026961253.tmp/WsFelecService_1.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(WsFelecService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "file:/C:/Users/JOJMOR~1/AppData/Local/Temp/tempdir3759899551026961253.tmp/WsFelecService_1.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public WsFelecService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public WsFelecService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public WsFelecService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public WsFelecService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public WsFelecService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public WsFelecService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     *
     * @return
     *     returns WsFelec
     */
    @WebEndpoint(name = "WsFelecPort")
    public WsFelec getWsFelecPort() {
        return super.getPort(WsFelecPort, WsFelec.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns WsFelec
     */
    @WebEndpoint(name = "WsFelecPort")
    public WsFelec getWsFelecPort(WebServiceFeature... features) {
        return super.getPort(WsFelecPort, WsFelec.class, features);
    }

}
