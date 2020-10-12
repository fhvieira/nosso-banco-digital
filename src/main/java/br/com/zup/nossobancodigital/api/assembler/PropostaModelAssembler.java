package br.com.zup.nossobancodigital.api.assembler;

import br.com.zup.nossobancodigital.api.model.PropostaModel;
import br.com.zup.nossobancodigital.domain.model.Proposta;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PropostaModelAssembler {

    @Autowired
    ModelMapper modelMapper;

    public PropostaModel toModel(Proposta proposta) {
        return modelMapper.map(proposta, PropostaModel.class);
    }

    public List<PropostaModel> toCollectionModel(List<Proposta> propostas) {
        return propostas.stream()
                .map(proposta -> toModel(proposta))
                .collect(Collectors.toList());
    }
}
