package co.com.santander.chatbot.accesodatos.repository;

import co.com.santander.chatbot.accesodatos.entity.ParametrosApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParametrosAppRepository extends JpaRepository<ParametrosApp, Long> {

    Optional<ParametrosApp> findByClave(String clave);
}
