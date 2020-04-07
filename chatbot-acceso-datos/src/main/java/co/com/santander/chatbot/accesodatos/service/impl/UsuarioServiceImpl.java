package co.com.santander.chatbot.accesodatos.service.impl;

import co.com.santander.chatbot.accesodatos.repository.UsuarioRepository;
import co.com.santander.chatbot.accesodatos.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Objects;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Optional<Boolean> consultarUsuario(BigInteger aLong, String s) {
        return Optional.of(Objects.nonNull(usuarioRepository.consultarUsuarioXTelefonoId(aLong, s)));
    }
}
