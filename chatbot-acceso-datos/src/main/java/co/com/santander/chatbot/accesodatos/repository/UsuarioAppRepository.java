package co.com.santander.chatbot.accesodatos.repository;

import co.com.santander.chatbot.accesodatos.entity.UsuarioApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioAppRepository extends JpaRepository<UsuarioApp, Long> {

    Optional<UsuarioApp> findByUsuario(String usuario);
}
