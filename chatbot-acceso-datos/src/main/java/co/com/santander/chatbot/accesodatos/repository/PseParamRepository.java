package co.com.santander.chatbot.accesodatos.repository;

import co.com.santander.chatbot.accesodatos.entity.PseParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PseParamRepository extends JpaRepository<PseParam, Long> {

    Optional<PseParam> findByIdBancoAndTipoCredito(Long idBanco, Long tipoCredito);
}
