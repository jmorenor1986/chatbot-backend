
package co.com.santander.accesorecursos.soap.resources.documentos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para datoEnvioDTO complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="datoEnvioDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="mailCC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mailPara" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "datoEnvioDTO", propOrder = {
    "mailCC",
    "mailPara"
})
public class DatoEnvioDTO {

    protected String mailCC;
    protected String mailPara;

    /**
     * Obtiene el valor de la propiedad mailCC.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMailCC() {
        return mailCC;
    }

    /**
     * Define el valor de la propiedad mailCC.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMailCC(String value) {
        this.mailCC = value;
    }

    /**
     * Obtiene el valor de la propiedad mailPara.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMailPara() {
        return mailPara;
    }

    /**
     * Define el valor de la propiedad mailPara.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMailPara(String value) {
        this.mailPara = value;
    }

}
