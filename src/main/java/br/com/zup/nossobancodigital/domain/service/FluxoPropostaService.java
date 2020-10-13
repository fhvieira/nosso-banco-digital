package br.com.zup.nossobancodigital.domain.service;

import br.com.zup.nossobancodigital.domain.model.Proposta;
import br.com.zup.nossobancodigital.domain.repository.PropostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class FluxoPropostaService {

    @Autowired
    PropostaRepository propostaRepository;

    @Autowired
    CadastroPropostaService cadastroPropostaService;

    @Transactional
    public void confirmar(Long propostaId) {
        Proposta proposta = cadastroPropostaService.buscarOuFalhar(propostaId);
        proposta.confirmar();

        // usado aqui somente para disparar o evento de proposta aceita
        propostaRepository.save(proposta);
    }

    @Transactional
    public void cancelar(Long propostaId) {
        Proposta proposta = cadastroPropostaService.buscarOuFalhar(propostaId);
        proposta.cancelar();
    }
}
