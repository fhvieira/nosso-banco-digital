package br.com.zup.nossobancodigital.api.controller;

import br.com.zup.nossobancodigital.api.assembler.ContaModelAssembler;
import br.com.zup.nossobancodigital.api.model.ContaModel;
import br.com.zup.nossobancodigital.domain.model.Conta;
import br.com.zup.nossobancodigital.domain.repository.ContaRepository;
import br.com.zup.nossobancodigital.domain.service.CadastroContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    ContaRepository contaRepository;

    @Autowired
    ContaModelAssembler contaModelAssembler;

    @Autowired
    CadastroContaService cadastroContaService;

    @GetMapping
    public List<ContaModel> listar() {
        return contaModelAssembler.toCollectionModel(contaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ContaModel buscar(@PathVariable Long id) {
        Conta conta = cadastroContaService.buscarOuFalhar(id);

        return contaModelAssembler.toModel(conta);
    }
}
