package co.com.santander.chatbot.accesodatos.repository;

import co.com.santander.chatbot.accesodatos.entity.ParametrosServicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParametrosServicioRepository extends JpaRepository<ParametrosServicio, Long> {

    List<ParametrosServicio> findByNameService(String servicio);
}
