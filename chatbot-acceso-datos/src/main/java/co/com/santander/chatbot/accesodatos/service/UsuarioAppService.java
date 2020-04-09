package co.com.santander.chatbot.accesodatos.service;

import co.com.santander.chatbot.accesodatos.entity.UsuarioApp;

import java.util.Optional;

public interface UsuarioAppService {

    UsuarioApp createUser(UsuarioApp usuarioApp);

    Optional<Boolean> validateLoginUser(String user, String password);
}
