package br.com.zup.nossobancodigital.domain.service;

import br.com.zup.nossobancodigital.domain.exception.ContaNaoEncontradaException;
import br.com.zup.nossobancodigital.domain.model.Conta;
import br.com.zup.nossobancodigital.domain.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CadastroContaService {

    @Autowired
    ContaRepository contaRepository;

    @Transactional
    public Conta salvar(Conta conta) {
        return contaRepository.save(conta);
    }

    public Conta buscarOuFalhar(Long id) {
        return contaRepository.findById(id)
                .orElseThrow(() -> new ContaNaoEncontradaException(id));
    }
}
