package co.com.santander.accesorecursos.soap.common.handler;

import javax.xml.namespace.QName;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.util.Set;


public class LogChain implements SOAPHandler<SOAPMessageContext> {
    @Override
    public Set<QName> getHeaders() {
        return null;
    }

    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        System.out.println(context);
        return true;
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        System.out.println(context);
        return true;
    }

    @Override
    public void close(MessageContext context) {

    }
}
