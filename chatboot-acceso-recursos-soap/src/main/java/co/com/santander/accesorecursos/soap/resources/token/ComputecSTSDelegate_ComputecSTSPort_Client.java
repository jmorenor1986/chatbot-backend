
package co.com.santander.accesorecursos.soap.resources.token;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.FaultAction;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.2
 * 2020-05-11T08:16:50.839+02:00
 * Generated source version: 2.7.2
 * 
 */
public final class ComputecSTSDelegate_ComputecSTSPort_Client {

    private static final QName SERVICE_NAME = new QName("http://sts.computec.experian.co/", "ComputecSTSService");

    private ComputecSTSDelegate_ComputecSTSPort_Client() {
    }

    public static void main(String args[])  {
        URL wsdlURL = ComputecSTSService.WSDL_LOCATION;
        if (args.length > 0 && args[0] != null && !"".equals(args[0])) { 
            File wsdlFile = new File(args[0]);
            try {
                if (wsdlFile.exists()) {
                    wsdlURL = wsdlFile.toURI().toURL();
                } else {
                    wsdlURL = new URL(args[0]);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
      
        ComputecSTSService ss = new ComputecSTSService(wsdlURL, SERVICE_NAME);
        ComputecSTSDelegate port = ss.getComputecSTSPort();  
        
        {
        System.out.println("Invoking obtenerToken...");
        co.com.santander.accesorecursos.soap.resources.token.ComputecSTSDTO _obtenerToken_arg0 = new ComputecSTSDTO();
        _obtenerToken_arg0.setPassword("12112");
        _obtenerToken_arg0.setUser("sdfsdfsdfsd");
        try {
            String _obtenerToken__return = port.obtenerToken(_obtenerToken_arg0);
            System.out.println("obtenerToken.result=" + _obtenerToken__return);

        } catch (Exception_Exception e) { 
            System.out.println("Expected exception: Exception has occurred.");
            System.out.println(e.toString());
        }
            }

        System.exit(0);
    }

}
