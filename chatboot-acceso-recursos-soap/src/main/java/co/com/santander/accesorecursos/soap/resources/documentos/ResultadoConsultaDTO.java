
package co.com.santander.accesorecursos.soap.resources.documentos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Clase Java para resultadoConsultaDTO complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="resultadoConsultaDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="docId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="formatoConsulta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="indices" type="{http://co.com.computec.wsservicioselectronicos.ws/}indiceDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="folder" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "resultadoConsultaDTO", propOrder = {
    "docId",
    "formatoConsulta",
    "indices",
    "folder"
})
public class ResultadoConsultaDTO {

    protected String docId;
    protected String formatoConsulta;
    @XmlElement(nillable = true)
    protected List<IndiceDTO> indices;
    protected String folder;

    /**
     * Obtiene el valor de la propiedad docId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocId() {
        return docId;
    }

    /**
     * Define el valor de la propiedad docId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocId(String value) {
        this.docId = value;
    }

    /**
     * Obtiene el valor de la propiedad formatoConsulta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormatoConsulta() {
        return formatoConsulta;
    }

    /**
     * Define el valor de la propiedad formatoConsulta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormatoConsulta(String value) {
        this.formatoConsulta = value;
    }

    /**
     * Gets the value of the indices property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the indices property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIndices().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IndiceDTO }
     * 
     * 
     */
    public List<IndiceDTO> getIndices() {
        if (indices == null) {
            indices = new ArrayList<IndiceDTO>();
        }
        return this.indices;
    }

    /**
     * Obtiene el valor de la propiedad folder.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFolder() {
        return folder;
    }

    /**
     * Define el valor de la propiedad folder.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFolder(String value) {
        this.folder = value;
    }

}
