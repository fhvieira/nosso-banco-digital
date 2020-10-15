package br.com.zup.nossobancodigital.infrastructure.repository;

import br.com.zup.nossobancodigital.domain.model.PropostaFoto;
import br.com.zup.nossobancodigital.domain.repository.PropostaRepositoryQueries;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class PropostaRepositoryImpl implements PropostaRepositoryQueries {

    @PersistenceContext
    private EntityManager manager;

    @Transactional
    @Override
    public PropostaFoto save(PropostaFoto foto) {
        return manager.merge(foto);
    }

//    @Transactional
//    @Override
//    public void delete(PropostaFoto foto) {
//        entityManager.remove(foto);
//    }
}
