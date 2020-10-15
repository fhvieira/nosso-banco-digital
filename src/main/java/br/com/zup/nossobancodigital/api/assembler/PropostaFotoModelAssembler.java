package br.com.zup.nossobancodigital.api.assembler;

import br.com.zup.nossobancodigital.api.model.PropostaFotoModel;
import br.com.zup.nossobancodigital.domain.model.PropostaFoto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PropostaFotoModelAssembler {

    @Autowired
    ModelMapper modelMapper;

    public PropostaFotoModel toModel(PropostaFoto propostaFoto) {
        return modelMapper.map(propostaFoto, PropostaFotoModel.class);
    }

}
