package co.com.santander.chatbot.accesodatos.repository;

import co.com.santander.chatbot.accesodatos.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogClienteRepository  extends JpaRepository<Log, Long> {
}
