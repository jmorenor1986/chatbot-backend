
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package co.com.santander.accesorecursos.soap.resources.documentos;

import javax.jws.WebService;
import java.util.logging.Logger;

/**
 * This class was generated by Apache CXF 2.7.2
 * 2020-05-11T22:29:03.135+02:00
 * Generated source version: 2.7.2
 * 
 */

@WebService(
                      serviceName = "WsFelecService",
                      portName = "WsFelecPort",
                      targetNamespace = "http://co.com.computec.wsservicioselectronicos.ws/",
                      wsdlLocation = "file:/C:/Users/JOJMOR~1/AppData/Local/Temp/tempdir3759899551026961253.tmp/WsFelecService_1.wsdl",
                      endpointInterface = "co.com.santander.accesorecursos.soap.resources.documentos.WsFelec")
                      
public class WsFelecImpl implements WsFelec {

    private static final Logger LOG = Logger.getLogger(WsFelecImpl.class.getName());

    /* (non-Javadoc)
     * @see co.com.santander.accesorecursos.soap.resources.documentos.WsFelec#obtenerDocumento(co.com.santander.accesorecursos.soap.resources.documentos.ConsultaDocDTO  consultaDocDTO ,)co.com.santander.accesorecursos.soap.resources.documentos.BeanDatosCliente  beanDatosCliente )*
     */
    public byte[] obtenerDocumento(co.com.santander.accesorecursos.soap.resources.documentos.ConsultaDocDTO consultaDocDTO,co.com.santander.accesorecursos.soap.resources.documentos.BeanDatosCliente beanDatosCliente) throws WSBusinessRuleException , WSSystemException    { 
        LOG.info("Executing operation obtenerDocumento");
        System.out.println(consultaDocDTO);
        System.out.println(beanDatosCliente);
        try {
            byte[] _return = new byte[0];
            return _return;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new WSBusinessRuleException("WSBusinessRuleException...");
        //throw new WSSystemException("WSSystemException...");
    }

    /* (non-Javadoc)
     * @see co.com.santander.accesorecursos.soap.resources.documentos.WsFelec#enviarMailDocumentoId(co.com.santander.accesorecursos.soap.resources.documentos.ConsultaDocDTO  consultaDocDTO ,)co.com.santander.accesorecursos.soap.resources.documentos.BeanDatosCliente  beanDatosCliente ,)co.com.santander.accesorecursos.soap.resources.documentos.DatoEnvioDTO  datoEnvioDTO )*
     */
    public String enviarMailDocumentoId(co.com.santander.accesorecursos.soap.resources.documentos.ConsultaDocDTO consultaDocDTO,co.com.santander.accesorecursos.soap.resources.documentos.BeanDatosCliente beanDatosCliente,co.com.santander.accesorecursos.soap.resources.documentos.DatoEnvioDTO datoEnvioDTO) throws WSBusinessRuleException , WSSystemException    {
        LOG.info("Executing operation enviarMailDocumentoId");
        System.out.println(consultaDocDTO);
        System.out.println(beanDatosCliente);
        System.out.println(datoEnvioDTO);
        try {
            String _return = "";
            return _return;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new WSBusinessRuleException("WSBusinessRuleException...");
        //throw new WSSystemException("WSSystemException...");
    }

    /* (non-Javadoc)
     * @see co.com.santander.accesorecursos.soap.resources.documentos.WsFelec#consultarDocumentos(co.com.santander.accesorecursos.soap.resources.documentos.ConsultaDocDTO  consultaDocDTO ,)co.com.santander.accesorecursos.soap.resources.documentos.BeanDatosCliente  beanDatosCliente )*
     */
    public java.util.List<ResultadoConsultaDTO> consultarDocumentos(co.com.santander.accesorecursos.soap.resources.documentos.ConsultaDocDTO consultaDocDTO, co.com.santander.accesorecursos.soap.resources.documentos.BeanDatosCliente beanDatosCliente) throws WSBusinessRuleException , WSSystemException    {
        LOG.info("Executing operation consultarDocumentos");
        System.out.println(consultaDocDTO);
        System.out.println(beanDatosCliente);
        try {
            java.util.List<ResultadoConsultaDTO> _return = null;
            return _return;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new WSBusinessRuleException("WSBusinessRuleException...");
        //throw new WSSystemException("WSSystemException...");
    }

    /* (non-Javadoc)
     * @see co.com.santander.accesorecursos.soap.resources.documentos.WsFelec#obtenerDocumentoId(co.com.santander.accesorecursos.soap.resources.documentos.ConsultaDocDTO  consultaDocDTO ,)co.com.santander.accesorecursos.soap.resources.documentos.BeanDatosCliente  beanDatosCliente )*
     */
    public byte[] obtenerDocumentoId(co.com.santander.accesorecursos.soap.resources.documentos.ConsultaDocDTO consultaDocDTO,co.com.santander.accesorecursos.soap.resources.documentos.BeanDatosCliente beanDatosCliente) throws WSBusinessRuleException , WSSystemException    { 
        LOG.info("Executing operation obtenerDocumentoId");
        System.out.println(consultaDocDTO);
        System.out.println(beanDatosCliente);
        try {
            byte[] _return = new byte[0];
            return _return;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new WSBusinessRuleException("WSBusinessRuleException...");
        //throw new WSSystemException("WSSystemException...");
    }

    /* (non-Javadoc)
     * @see co.com.santander.accesorecursos.soap.resources.documentos.WsFelec#enviarMailDocumento(co.com.santander.accesorecursos.soap.resources.documentos.ConsultaDocDTO  consultaDocDTO ,)co.com.santander.accesorecursos.soap.resources.documentos.BeanDatosCliente  beanDatosCliente ,)co.com.santander.accesorecursos.soap.resources.documentos.DatoEnvioDTO  datoEnvioDTO )*
     */
    public String enviarMailDocumento(co.com.santander.accesorecursos.soap.resources.documentos.ConsultaDocDTO consultaDocDTO,co.com.santander.accesorecursos.soap.resources.documentos.BeanDatosCliente beanDatosCliente,co.com.santander.accesorecursos.soap.resources.documentos.DatoEnvioDTO datoEnvioDTO) throws WSBusinessRuleException , WSSystemException    {
        LOG.info("Executing operation enviarMailDocumento");
        System.out.println(consultaDocDTO);
        System.out.println(beanDatosCliente);
        System.out.println(datoEnvioDTO);
        try {
            String _return = "";
            return _return;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new WSBusinessRuleException("WSBusinessRuleException...");
        //throw new WSSystemException("WSSystemException...");
    }

}
