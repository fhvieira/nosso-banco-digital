package br.com.zup.nossobancodigital.api.controller;

import br.com.zup.nossobancodigital.ResourceUriHelper;
import br.com.zup.nossobancodigital.api.assembler.PropostaInputDisassembler;
import br.com.zup.nossobancodigital.api.assembler.PropostaModelAssembler;
import br.com.zup.nossobancodigital.api.model.PropostaModel;
import br.com.zup.nossobancodigital.api.model.input.PropostaInput;
import br.com.zup.nossobancodigital.domain.exception.NegocioException;
import br.com.zup.nossobancodigital.domain.exception.PropostaNaoEncontradaException;
import br.com.zup.nossobancodigital.domain.model.Proposta;
import br.com.zup.nossobancodigital.domain.repository.PropostaRepository;
import br.com.zup.nossobancodigital.domain.service.CadastroPropostaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/propostas")
public class PropostaController {

    @Autowired
    PropostaInputDisassembler propostaInputDisassembler;

    @Autowired
    PropostaModelAssembler propostaModelAssembler;

    @Autowired
    CadastroPropostaService cadastroPropostaService;

    @Autowired
    PropostaRepository propostaRepository;

    @GetMapping
    public List<PropostaModel> listar() {
        return propostaModelAssembler.toCollectionModel(propostaRepository.findAll());
    }

    @GetMapping("/{id}")
    public PropostaModel buscar(@PathVariable Long id) {
        Proposta proposta = cadastroPropostaService.buscarOuFalhar(id);

        return propostaModelAssembler.toModel(proposta);
    }

    @PutMapping("/{propostaId}")
    public PropostaModel atualizar(@PathVariable Long propostaId,
              @RequestBody @Valid PropostaInput propostaInput) {
        try {
            Proposta proposta = cadastroPropostaService.buscarOuFalhar(propostaId);

            propostaInputDisassembler.copyToDomainObject(propostaInput, proposta);

            PropostaModel propostaModel = propostaModelAssembler.toModel(proposta);

            ResourceUriHelper.addUriInResponseHeader(propostaModel.getId());

            return propostaModelAssembler.toModel(cadastroPropostaService.salvar(proposta));
        } catch(PropostaNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PropostaModel adicionar(@Valid @RequestBody PropostaInput propostaInput) {

        Proposta proposta = propostaInputDisassembler.toDomainObject(propostaInput);

        proposta = cadastroPropostaService.salvar(proposta);

        PropostaModel propostaModel = propostaModelAssembler.toModel(proposta);

        ResourceUriHelper.addUriInResponseHeader(propostaModel.getId());

        return propostaModel;
    }

    @PostMapping("/{propostaId}/aceite")
    @ResponseStatus(HttpStatus.CREATED)
    public PropostaModel aceitar(@PathVariable Long propostaId) {
        Proposta proposta = cadastroPropostaService.buscarOuFalhar(propostaId);

        proposta = cadastroPropostaService.aceitar(proposta);

        PropostaModel propostaModel = propostaModelAssembler.toModel(proposta);

        ResourceUriHelper.addUriInResponseHeader(propostaModel.getId());

        return propostaModel;
    }

    @PutMapping("/{propostaId}/aceite")
    @ResponseStatus(HttpStatus.CREATED)
    public PropostaModel recusar(@PathVariable Long propostaId) {
        Proposta proposta = cadastroPropostaService.buscarOuFalhar(propostaId);

        proposta = cadastroPropostaService.recusar(proposta);

        PropostaModel propostaModel = propostaModelAssembler.toModel(proposta);

        ResourceUriHelper.addUriInResponseHeader(propostaModel.getId());

        return propostaModel;
    }
}
