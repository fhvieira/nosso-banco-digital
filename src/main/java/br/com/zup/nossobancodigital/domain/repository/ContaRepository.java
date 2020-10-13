package br.com.zup.nossobancodigital.domain.repository;

import br.com.zup.nossobancodigital.domain.model.Conta;
import br.com.zup.nossobancodigital.domain.model.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

}
