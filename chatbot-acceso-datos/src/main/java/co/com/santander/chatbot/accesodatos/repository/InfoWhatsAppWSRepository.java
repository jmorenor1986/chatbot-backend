package co.com.santander.chatbot.accesodatos.repository;

import co.com.santander.chatbot.accesodatos.entity.InfoWhatsAppWS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InfoWhatsAppWSRepository extends JpaRepository<InfoWhatsAppWS, Long> {

    List<InfoWhatsAppWS> findByNumCreditoBancoAndNumeroIdentificacionAndNumPeticionServicioAndEstado(String numCreditoBanco, String numeroIdentificacion, Long numPeticionServicio, Long estado );
}
