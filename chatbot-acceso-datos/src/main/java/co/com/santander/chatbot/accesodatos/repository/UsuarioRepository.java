package co.com.santander.chatbot.accesodatos.repository;

import co.com.santander.chatbot.accesodatos.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    public Usuario consultarUsuarioXTelefonoId(Long telefono, String colaIdentificacion);
}
