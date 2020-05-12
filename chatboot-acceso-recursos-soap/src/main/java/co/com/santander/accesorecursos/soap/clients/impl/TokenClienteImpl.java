package co.com.santander.accesorecursos.soap.clients.impl;

import co.com.santander.accesorecursos.soap.clients.TokenCliente;
import co.com.santander.accesorecursos.soap.common.exception.BusinessException;
import co.com.santander.accesorecursos.soap.resources.token.ComputecSTSDTO;
import co.com.santander.accesorecursos.soap.resources.token.ComputecSTSDelegate;
import co.com.santander.accesorecursos.soap.resources.token.ComputecSTSService;
import co.com.santander.accesorecursos.soap.resources.token.Exception_Exception;
import co.com.santander.chatbot.domain.payload.enviarextracto.TokenPayload;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenClienteImpl implements TokenCliente {

    private final ModelMapper getMapper;
    private final ComputecSTSService getServiceToken;

    @Autowired
    public TokenClienteImpl(ModelMapper getMapper, ComputecSTSService getServiceToken) {
        this.getMapper = getMapper;
        this.getServiceToken = getServiceToken;
    }

    @Override
    public String generarToken(TokenPayload user) {
        try {
            ComputecSTSDelegate port = getServiceToken.getComputecSTSPort();
            return port.obtenerToken(getMapper.map(user, ComputecSTSDTO.class));
        } catch (Exception_Exception exc) {
            throw new BusinessException(exc.getMessage());
        }

    }
}
