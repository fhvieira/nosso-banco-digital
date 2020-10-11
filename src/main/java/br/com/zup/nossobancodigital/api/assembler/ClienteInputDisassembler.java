package br.com.zup.nossobancodigital.api.assembler;

import br.com.zup.nossobancodigital.api.model.input.ClienteInput;
import br.com.zup.nossobancodigital.domain.model.Cliente;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClienteInputDisassembler {

    @Autowired
    ModelMapper modelMapper;

    public Cliente toDomainObject(ClienteInput clienteInput) {
        return modelMapper.map(clienteInput, Cliente.class);
    }

    public void copyToDomainObject(ClienteInput clienteInput, Cliente cozinha) {
        modelMapper.map(clienteInput, cozinha);
    }
}
