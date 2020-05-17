
package co.com.santander.accesorecursos.soap.resources.documentos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para obtenerDocumento complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="obtenerDocumento">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="consultaDocDTO" type="{http://co.com.computec.wsservicioselectronicos.ws/}consultaDocDTO" minOccurs="0"/>
 *         &lt;element name="beanDatosCliente" type="{http://co.com.computec.wsservicioselectronicos.ws/}beanDatosCliente" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "obtenerDocumento", propOrder = {
    "consultaDocDTO",
    "beanDatosCliente"
})
public class ObtenerDocumento {

    protected ConsultaDocDTO consultaDocDTO;
    protected BeanDatosCliente beanDatosCliente;

    /**
     * Obtiene el valor de la propiedad consultaDocDTO.
     * 
     * @return
     *     possible object is
     *     {@link ConsultaDocDTO }
     *     
     */
    public ConsultaDocDTO getConsultaDocDTO() {
        return consultaDocDTO;
    }

    /**
     * Define el valor de la propiedad consultaDocDTO.
     * 
     * @param value
     *     allowed object is
     *     {@link ConsultaDocDTO }
     *     
     */
    public void setConsultaDocDTO(ConsultaDocDTO value) {
        this.consultaDocDTO = value;
    }

    /**
     * Obtiene el valor de la propiedad beanDatosCliente.
     * 
     * @return
     *     possible object is
     *     {@link BeanDatosCliente }
     *     
     */
    public BeanDatosCliente getBeanDatosCliente() {
        return beanDatosCliente;
    }

    /**
     * Define el valor de la propiedad beanDatosCliente.
     * 
     * @param value
     *     allowed object is
     *     {@link BeanDatosCliente }
     *     
     */
    public void setBeanDatosCliente(BeanDatosCliente value) {
        this.beanDatosCliente = value;
    }

}
