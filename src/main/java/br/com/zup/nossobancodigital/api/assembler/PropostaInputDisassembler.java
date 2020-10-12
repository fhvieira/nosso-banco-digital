package br.com.zup.nossobancodigital.api.assembler;

import br.com.zup.nossobancodigital.api.model.input.EnderecoInput;
import br.com.zup.nossobancodigital.api.model.input.PropostaInput;
import br.com.zup.nossobancodigital.domain.model.Proposta;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PropostaInputDisassembler {

    @Autowired
    ModelMapper modelMapper;

    public Proposta toDomainObject(PropostaInput propostaInput) {
        return modelMapper.map(propostaInput, Proposta.class);
    }

    public void copyToDomainObject(PropostaInput propostaInput, Proposta proposta) {
        modelMapper.map(propostaInput, proposta);
    }

    public void copyToDomainObject(EnderecoInput enderecoInput, Proposta proposta) {
        modelMapper.map(enderecoInput, proposta);
    }

}
