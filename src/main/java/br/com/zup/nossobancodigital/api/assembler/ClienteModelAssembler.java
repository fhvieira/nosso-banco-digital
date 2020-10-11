package br.com.zup.nossobancodigital.api.assembler;

import br.com.zup.nossobancodigital.api.model.ClienteModel;
import br.com.zup.nossobancodigital.domain.model.Cliente;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClienteModelAssembler {

    @Autowired
    ModelMapper modelMapper;

    public ClienteModel toModel(Cliente cliente) {
        return modelMapper.map(cliente, ClienteModel.class);
    }

    public List<ClienteModel> toCollectionModel(List<Cliente> clientes) {
        return clientes.stream()
                .map(cliente -> toModel(cliente))
                .collect(Collectors.toList());
    }
}
