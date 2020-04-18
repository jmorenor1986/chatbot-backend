package co.com.santander.chatbot.accesodatos.repository;

import co.com.santander.chatbot.accesodatos.entity.InfoWhatsAppWS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfoWhatsAppWSRepository extends JpaRepository<InfoWhatsAppWS, Long> {
}
