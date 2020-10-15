package br.com.zup.nossobancodigital.api.assembler;

import br.com.zup.nossobancodigital.api.model.ClienteModel;
import br.com.zup.nossobancodigital.api.model.ContaModel;
import br.com.zup.nossobancodigital.domain.model.Cliente;
import br.com.zup.nossobancodigital.domain.model.Conta;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ContaModelAssembler {

    @Autowired
    ModelMapper modelMapper;

    public ContaModel toModel(Conta conta) {
        return modelMapper.map(conta, ContaModel.class);
    }

    public List<ContaModel> toCollectionModel(List<Conta> contas) {
        return contas.stream()
                .map(conta -> toModel(conta))
                .collect(Collectors.toList());
    }
}
