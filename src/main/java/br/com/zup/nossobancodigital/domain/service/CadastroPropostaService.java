package br.com.zup.nossobancodigital.domain.service;

import br.com.zup.nossobancodigital.domain.exception.NegocioException;
import br.com.zup.nossobancodigital.domain.exception.PropostaNaoEncontradaException;
import br.com.zup.nossobancodigital.domain.model.Proposta;
import br.com.zup.nossobancodigital.domain.model.StatusProposta;
import br.com.zup.nossobancodigital.domain.repository.PropostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.OffsetDateTime;

@Service
public class CadastroPropostaService {

    @Autowired
    PropostaRepository propostaRepository;

    @Autowired
    CadastroPropostaService cadastroPropostaService;

    @Transactional
    public Proposta salvar(Proposta proposta) {

        if (proposta.getIdade() < Proposta.IDADE_MINIMA_PROPOSTA) {
            throw new NegocioException(String.format(
                    "Proposta nao permitida para menores de %d anos de idade", Proposta.IDADE_MINIMA_PROPOSTA));
        }

        propostaRepository.findByEmail(proposta.getEmail())
                .ifPresent(x -> {
                        if (x.getId() != proposta.getId()) {
                            throw new NegocioException(
                                String.format("Email %s ja cadastrado para a proposta de id %d", proposta.getEmail(), x.getId()));
                        }
                });

        propostaRepository.findByCpf(proposta.getCpf())
                .ifPresent(x -> {
                    if (x.getId() != proposta.getId()) {
                        throw new NegocioException(
                                String.format("Cpf %s ja cadastrado para a proposta de id %d", proposta.getCpf(), x.getId()));
                    }
                });

        if (proposta.getEndereco() != null && proposta.getStatus() == StatusProposta.INCOMPLETA) {
            proposta.setStatus(StatusProposta.COMPLETA);
        }

        return propostaRepository.save(proposta);
    }

    public Proposta buscarOuFalhar(Long id) {
        return propostaRepository.findById(id)
                .orElseThrow(() -> new PropostaNaoEncontradaException(id));
    }
}
