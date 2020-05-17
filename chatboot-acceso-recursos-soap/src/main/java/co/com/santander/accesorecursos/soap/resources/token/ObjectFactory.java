
package co.com.santander.accesorecursos.soap.resources.token;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the co.com.santander.accesorecursos.soap.resources.token package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ObtenerToken_QNAME = new QName("http://sts.computec.experian.co/", "obtenerToken");
    private final static QName _Exception_QNAME = new QName("http://sts.computec.experian.co/", "Exception");
    private final static QName _ObtenerTokenResponse_QNAME = new QName("http://sts.computec.experian.co/", "obtenerTokenResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: co.com.santander.accesorecursos.soap.resources.token
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ObtenerTokenResponse }
     * 
     */
    public ObtenerTokenResponse createObtenerTokenResponse() {
        return new ObtenerTokenResponse();
    }

    /**
     * Create an instance of {@link Exception }
     * 
     */
    public Exception createException() {
        return new Exception();
    }

    /**
     * Create an instance of {@link ObtenerToken }
     * 
     */
    public ObtenerToken createObtenerToken() {
        return new ObtenerToken();
    }

    /**
     * Create an instance of {@link ComputecSTSDTO }
     * 
     */
    public ComputecSTSDTO createComputecSTSDTO() {
        return new ComputecSTSDTO();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerToken }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sts.computec.experian.co/", name = "obtenerToken")
    public JAXBElement<ObtenerToken> createObtenerToken(ObtenerToken value) {
        return new JAXBElement<ObtenerToken>(_ObtenerToken_QNAME, ObtenerToken.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Exception }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sts.computec.experian.co/", name = "Exception")
    public JAXBElement<Exception> createException(Exception value) {
        return new JAXBElement<Exception>(_Exception_QNAME, Exception.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerTokenResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sts.computec.experian.co/", name = "obtenerTokenResponse")
    public JAXBElement<ObtenerTokenResponse> createObtenerTokenResponse(ObtenerTokenResponse value) {
        return new JAXBElement<ObtenerTokenResponse>(_ObtenerTokenResponse_QNAME, ObtenerTokenResponse.class, null, value);
    }

}
