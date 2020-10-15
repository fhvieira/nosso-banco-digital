package br.com.zup.nossobancodigital.domain.repository;

import br.com.zup.nossobancodigital.domain.model.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, Long> {

    Optional<Proposta> findByEmail(String email);

    Optional<Proposta> findByCpf(String cpf);
}
