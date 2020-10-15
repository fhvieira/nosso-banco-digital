package br.com.zup.nossobancodigital.api.controller;

import br.com.zup.nossobancodigital.api.assembler.ContaModelAssembler;
import br.com.zup.nossobancodigital.api.model.ContaModel;
import br.com.zup.nossobancodigital.domain.model.Conta;
import br.com.zup.nossobancodigital.domain.repository.ContaRepository;
import br.com.zup.nossobancodigital.domain.service.CadastroContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transacoes")
public class NovaTransacaoController {

    @Autowired
    ContaRepository contaRepository;

    @Autowired
    ContaModelAssembler contaModelAssembler;

    @Autowired
    CadastroContaService cadastroContaService;

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void adicionar() {

    }
}
