package co.com.santander.accesorecursos.soap.clients.impl;

import co.com.santander.accesorecursos.soap.clients.TokenCliente;
import co.com.santander.accesorecursos.soap.common.exception.BusinessException;
import co.com.santander.accesorecursos.soap.config.properties.ServiceProperties;
import co.com.santander.accesorecursos.soap.resources.token.ComputecSTSDTO;
import co.com.santander.accesorecursos.soap.resources.token.ComputecSTSDelegate;
import co.com.santander.accesorecursos.soap.resources.token.ComputecSTSService;
import co.com.santander.accesorecursos.soap.resources.token.Exception_Exception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenClienteImpl implements TokenCliente {

    private final ComputecSTSService getServiceToken;
    private final ServiceProperties serviceProperties;

    @Autowired
    public TokenClienteImpl(ComputecSTSService getServiceToken, ServiceProperties serviceProperties) {
        this.getServiceToken = getServiceToken;
        this.serviceProperties = serviceProperties;
    }

    @Override
    public String generarToken() {
        try {
            ComputecSTSDelegate port = getServiceToken.getComputecSTSPort();
            ComputecSTSDTO computecSTSDTO = new ComputecSTSDTO();
            computecSTSDTO.setUser(serviceProperties.getUser());
            computecSTSDTO.setPassword(serviceProperties.getPassword());
            return port.obtenerToken(computecSTSDTO);
        } catch (Exception_Exception exc) {
            throw new BusinessException(exc.getMessage());
        }

    }
}
