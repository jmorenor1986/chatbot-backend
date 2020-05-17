
package co.com.santander.accesorecursos.soap.resources.documentos;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the co.com.santander.accesorecursos.soap.resources.documentos package. 
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

    private final static QName _WSBusinessRuleException_QNAME = new QName("http://co.com.computec.wsservicioselectronicos.ws/", "WSBusinessRuleException");
    private final static QName _ObtenerDocumento_QNAME = new QName("http://co.com.computec.wsservicioselectronicos.ws/", "obtenerDocumento");
    private final static QName _WSSystemException_QNAME = new QName("http://co.com.computec.wsservicioselectronicos.ws/", "WSSystemException");
    private final static QName _ObtenerDocumentoResponse_QNAME = new QName("http://co.com.computec.wsservicioselectronicos.ws/", "obtenerDocumentoResponse");
    private final static QName _EnviarMailDocumentoResponse_QNAME = new QName("http://co.com.computec.wsservicioselectronicos.ws/", "enviarMailDocumentoResponse");
    private final static QName _ConsultarDocumentos_QNAME = new QName("http://co.com.computec.wsservicioselectronicos.ws/", "consultarDocumentos");
    private final static QName _ConsultarDocumentosResponse_QNAME = new QName("http://co.com.computec.wsservicioselectronicos.ws/", "consultarDocumentosResponse");
    private final static QName _ObtenerDocumentoIdResponse_QNAME = new QName("http://co.com.computec.wsservicioselectronicos.ws/", "obtenerDocumentoIdResponse");
    private final static QName _EnviarMailDocumentoId_QNAME = new QName("http://co.com.computec.wsservicioselectronicos.ws/", "enviarMailDocumentoId");
    private final static QName _EnviarMailDocumentoIdResponse_QNAME = new QName("http://co.com.computec.wsservicioselectronicos.ws/", "enviarMailDocumentoIdResponse");
    private final static QName _EnviarMailDocumento_QNAME = new QName("http://co.com.computec.wsservicioselectronicos.ws/", "enviarMailDocumento");
    private final static QName _ObtenerDocumentoId_QNAME = new QName("http://co.com.computec.wsservicioselectronicos.ws/", "obtenerDocumentoId");
    private final static QName _ObtenerDocumentoResponseReturn_QNAME = new QName("", "return");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: co.com.santander.accesorecursos.soap.resources.documentos
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ConsultaDocDTO }
     * 
     */
    public ConsultaDocDTO createConsultaDocDTO() {
        return new ConsultaDocDTO();
    }

    /**
     * Create an instance of {@link ConsultaDocDTO.ParaAdi }
     * 
     */
    public ConsultaDocDTO.ParaAdi createConsultaDocDTOParaAdi() {
        return new ConsultaDocDTO.ParaAdi();
    }

    /**
     * Create an instance of {@link EnviarMailDocumento }
     * 
     */
    public EnviarMailDocumento createEnviarMailDocumento() {
        return new EnviarMailDocumento();
    }

    /**
     * Create an instance of {@link ObtenerDocumentoId }
     * 
     */
    public ObtenerDocumentoId createObtenerDocumentoId() {
        return new ObtenerDocumentoId();
    }

    /**
     * Create an instance of {@link EnviarMailDocumentoIdResponse }
     * 
     */
    public EnviarMailDocumentoIdResponse createEnviarMailDocumentoIdResponse() {
        return new EnviarMailDocumentoIdResponse();
    }

    /**
     * Create an instance of {@link EnviarMailDocumentoId }
     * 
     */
    public EnviarMailDocumentoId createEnviarMailDocumentoId() {
        return new EnviarMailDocumentoId();
    }

    /**
     * Create an instance of {@link ObtenerDocumentoIdResponse }
     * 
     */
    public ObtenerDocumentoIdResponse createObtenerDocumentoIdResponse() {
        return new ObtenerDocumentoIdResponse();
    }

    /**
     * Create an instance of {@link ConsultarDocumentosResponse }
     * 
     */
    public ConsultarDocumentosResponse createConsultarDocumentosResponse() {
        return new ConsultarDocumentosResponse();
    }

    /**
     * Create an instance of {@link ConsultarDocumentos }
     * 
     */
    public ConsultarDocumentos createConsultarDocumentos() {
        return new ConsultarDocumentos();
    }

    /**
     * Create an instance of {@link EnviarMailDocumentoResponse }
     * 
     */
    public EnviarMailDocumentoResponse createEnviarMailDocumentoResponse() {
        return new EnviarMailDocumentoResponse();
    }

    /**
     * Create an instance of {@link FaultBean }
     * 
     */
    public FaultBean createFaultBean() {
        return new FaultBean();
    }

    /**
     * Create an instance of {@link ObtenerDocumentoResponse }
     * 
     */
    public ObtenerDocumentoResponse createObtenerDocumentoResponse() {
        return new ObtenerDocumentoResponse();
    }

    /**
     * Create an instance of {@link ObtenerDocumento }
     * 
     */
    public ObtenerDocumento createObtenerDocumento() {
        return new ObtenerDocumento();
    }

    /**
     * Create an instance of {@link IndiceDTO }
     * 
     */
    public IndiceDTO createIndiceDTO() {
        return new IndiceDTO();
    }

    /**
     * Create an instance of {@link ResultadoConsultaDTO }
     * 
     */
    public ResultadoConsultaDTO createResultadoConsultaDTO() {
        return new ResultadoConsultaDTO();
    }

    /**
     * Create an instance of {@link DatoEnvioDTO }
     * 
     */
    public DatoEnvioDTO createDatoEnvioDTO() {
        return new DatoEnvioDTO();
    }

    /**
     * Create an instance of {@link BeanDatosCliente }
     * 
     */
    public BeanDatosCliente createBeanDatosCliente() {
        return new BeanDatosCliente();
    }

    /**
     * Create an instance of {@link ConsultaDocDTO.ParaAdi.Entry }
     * 
     */
    public ConsultaDocDTO.ParaAdi.Entry createConsultaDocDTOParaAdiEntry() {
        return new ConsultaDocDTO.ParaAdi.Entry();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FaultBean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://co.com.computec.wsservicioselectronicos.ws/", name = "WSBusinessRuleException")
    public JAXBElement<FaultBean> createWSBusinessRuleException(FaultBean value) {
        return new JAXBElement<FaultBean>(_WSBusinessRuleException_QNAME, FaultBean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerDocumento }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://co.com.computec.wsservicioselectronicos.ws/", name = "obtenerDocumento")
    public JAXBElement<ObtenerDocumento> createObtenerDocumento(ObtenerDocumento value) {
        return new JAXBElement<ObtenerDocumento>(_ObtenerDocumento_QNAME, ObtenerDocumento.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FaultBean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://co.com.computec.wsservicioselectronicos.ws/", name = "WSSystemException")
    public JAXBElement<FaultBean> createWSSystemException(FaultBean value) {
        return new JAXBElement<FaultBean>(_WSSystemException_QNAME, FaultBean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerDocumentoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://co.com.computec.wsservicioselectronicos.ws/", name = "obtenerDocumentoResponse")
    public JAXBElement<ObtenerDocumentoResponse> createObtenerDocumentoResponse(ObtenerDocumentoResponse value) {
        return new JAXBElement<ObtenerDocumentoResponse>(_ObtenerDocumentoResponse_QNAME, ObtenerDocumentoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EnviarMailDocumentoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://co.com.computec.wsservicioselectronicos.ws/", name = "enviarMailDocumentoResponse")
    public JAXBElement<EnviarMailDocumentoResponse> createEnviarMailDocumentoResponse(EnviarMailDocumentoResponse value) {
        return new JAXBElement<EnviarMailDocumentoResponse>(_EnviarMailDocumentoResponse_QNAME, EnviarMailDocumentoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultarDocumentos }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://co.com.computec.wsservicioselectronicos.ws/", name = "consultarDocumentos")
    public JAXBElement<ConsultarDocumentos> createConsultarDocumentos(ConsultarDocumentos value) {
        return new JAXBElement<ConsultarDocumentos>(_ConsultarDocumentos_QNAME, ConsultarDocumentos.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultarDocumentosResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://co.com.computec.wsservicioselectronicos.ws/", name = "consultarDocumentosResponse")
    public JAXBElement<ConsultarDocumentosResponse> createConsultarDocumentosResponse(ConsultarDocumentosResponse value) {
        return new JAXBElement<ConsultarDocumentosResponse>(_ConsultarDocumentosResponse_QNAME, ConsultarDocumentosResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerDocumentoIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://co.com.computec.wsservicioselectronicos.ws/", name = "obtenerDocumentoIdResponse")
    public JAXBElement<ObtenerDocumentoIdResponse> createObtenerDocumentoIdResponse(ObtenerDocumentoIdResponse value) {
        return new JAXBElement<ObtenerDocumentoIdResponse>(_ObtenerDocumentoIdResponse_QNAME, ObtenerDocumentoIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EnviarMailDocumentoId }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://co.com.computec.wsservicioselectronicos.ws/", name = "enviarMailDocumentoId")
    public JAXBElement<EnviarMailDocumentoId> createEnviarMailDocumentoId(EnviarMailDocumentoId value) {
        return new JAXBElement<EnviarMailDocumentoId>(_EnviarMailDocumentoId_QNAME, EnviarMailDocumentoId.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EnviarMailDocumentoIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://co.com.computec.wsservicioselectronicos.ws/", name = "enviarMailDocumentoIdResponse")
    public JAXBElement<EnviarMailDocumentoIdResponse> createEnviarMailDocumentoIdResponse(EnviarMailDocumentoIdResponse value) {
        return new JAXBElement<EnviarMailDocumentoIdResponse>(_EnviarMailDocumentoIdResponse_QNAME, EnviarMailDocumentoIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EnviarMailDocumento }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://co.com.computec.wsservicioselectronicos.ws/", name = "enviarMailDocumento")
    public JAXBElement<EnviarMailDocumento> createEnviarMailDocumento(EnviarMailDocumento value) {
        return new JAXBElement<EnviarMailDocumento>(_EnviarMailDocumento_QNAME, EnviarMailDocumento.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerDocumentoId }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://co.com.computec.wsservicioselectronicos.ws/", name = "obtenerDocumentoId")
    public JAXBElement<ObtenerDocumentoId> createObtenerDocumentoId(ObtenerDocumentoId value) {
        return new JAXBElement<ObtenerDocumentoId>(_ObtenerDocumentoId_QNAME, ObtenerDocumentoId.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "return", scope = ObtenerDocumentoResponse.class)
    public JAXBElement<byte[]> createObtenerDocumentoResponseReturn(byte[] value) {
        return new JAXBElement<byte[]>(_ObtenerDocumentoResponseReturn_QNAME, byte[].class, ObtenerDocumentoResponse.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "return", scope = ObtenerDocumentoIdResponse.class)
    public JAXBElement<byte[]> createObtenerDocumentoIdResponseReturn(byte[] value) {
        return new JAXBElement<byte[]>(_ObtenerDocumentoResponseReturn_QNAME, byte[].class, ObtenerDocumentoIdResponse.class, ((byte[]) value));
    }

}
