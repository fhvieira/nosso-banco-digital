package br.com.zup.nossobancodigital.domain.service;

import br.com.zup.nossobancodigital.domain.model.Conta;
import br.com.zup.nossobancodigital.domain.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FluxoContaService {

    @Autowired
    ContaRepository contaRepository;

    @Autowired
    CadastroContaService cadastroContaService;

    @Transactional
    public void confirmar(Long contaId) {
        Conta conta = cadastroContaService.buscarOuFalhar(contaId);
        conta.confirmar();

        // usado aqui somente para disparar o evento de conta aceita
        contaRepository.save(conta);
    }

    @Transactional
    public void cancelar(Long contaId) {
        Conta conta = cadastroContaService.buscarOuFalhar(contaId);
        conta.cancelar();
    }
}
