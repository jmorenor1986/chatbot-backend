package co.com.santander.chatbot.accesodatos.service.impl;

import co.com.santander.chatbot.accesodatos.entity.UsuarioApp;
import co.com.santander.chatbot.accesodatos.repository.UsuarioAppRepository;
import co.com.santander.chatbot.accesodatos.service.UsuarioAppService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioAppServiceImpl implements UsuarioAppService {

    private final UsuarioAppRepository usuarioAppRepository;
    private final PasswordEncoder passwordEncoder;
    private final Logger log = LoggerFactory.getLogger(UsuarioAppServiceImpl.class);

    @Autowired
    public UsuarioAppServiceImpl(UsuarioAppRepository usuarioAppRepository, PasswordEncoder passwordEncoder) {
        this.usuarioAppRepository = usuarioAppRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UsuarioApp createUser(UsuarioApp usuarioApp) {
        String passEnc = passwordEncoder.encode(usuarioApp.getContra());
        usuarioApp.setContra(passEnc);
        return usuarioAppRepository.save(usuarioApp);
    }

    @Override
    public Optional<Boolean> validateLoginUser(String user, String password) {
        Optional<UsuarioApp> usuario = usuarioAppRepository.findByUsuario(user);
        if(usuario.isPresent()){
            Boolean validaPass = passwordEncoder.matches(password, usuario.get().getContra());
            if(validaPass){
                return Optional.of(Boolean.TRUE);
            }
        }
        return Optional.of(Boolean.FALSE);
    }
}
