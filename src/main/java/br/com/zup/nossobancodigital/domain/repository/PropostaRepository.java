package br.com.zup.nossobancodigital.domain.repository;

import br.com.zup.nossobancodigital.domain.model.Proposta;
import br.com.zup.nossobancodigital.domain.model.PropostaFoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, Long>, PropostaRepositoryQueries {

    Optional<Proposta> findByEmail(String email);

    Optional<Proposta> findByCpf(String cpf);

//    @Query("select f from PropostaFoto f join f.proposta p where f.proposta.id = :propostaId")
//    Optional<PropostaFoto> findFotoById(Long propostaId);
}
