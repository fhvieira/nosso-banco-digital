package br.com.zup.nossobancodigital.api.controller;

import br.com.zup.nossobancodigital.ResourceUriHelper;
import br.com.zup.nossobancodigital.api.assembler.ClienteInputDisassembler;
import br.com.zup.nossobancodigital.api.assembler.ClienteModelAssembler;
import br.com.zup.nossobancodigital.api.model.ClienteModel;
import br.com.zup.nossobancodigital.api.model.input.ClienteInput;
import br.com.zup.nossobancodigital.domain.exception.NegocioException;
import br.com.zup.nossobancodigital.domain.model.Cliente;
import br.com.zup.nossobancodigital.domain.repository.ClienteRepository;
import br.com.zup.nossobancodigital.domain.service.CadastroClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    ClienteInputDisassembler clienteInputDisassembler;

    @Autowired
    ClienteModelAssembler clienteModelAssembler;

    @Autowired
    CadastroClienteService cadastroClienteService;

    @Autowired
    ClienteRepository clienteRepository;

    @GetMapping
    public List<ClienteModel> listar() {
        return clienteModelAssembler.toCollectionModel(clienteRepository.findAll());
    }

    @GetMapping("{/id}")
    public ClienteModel buscar(@PathVariable Long id) {
        Cliente cliente = cadastroClienteService.buscarOuFalhar(id);

        return clienteModelAssembler.toModel(cliente);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteModel adicionar(@Valid @RequestBody ClienteInput clienteInput) {
        Cliente cliente = clienteInputDisassembler.toDomainObject(clienteInput);

        cliente = cadastroClienteService.salvar(cliente);

        ClienteModel clienteModel = clienteModelAssembler.toModel(cliente);

        ResourceUriHelper.addUriInResponseHeader(clienteModel.getId());

        return clienteModel;
    }

}
