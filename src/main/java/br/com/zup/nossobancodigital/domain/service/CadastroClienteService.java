package br.com.zup.nossobancodigital.domain.service;

import br.com.zup.nossobancodigital.domain.exception.ClienteNaoEncontradoException;
import br.com.zup.nossobancodigital.domain.exception.NegocioException;
import br.com.zup.nossobancodigital.domain.model.Cliente;
import br.com.zup.nossobancodigital.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastroClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    @Transactional
    public Cliente salvar(Cliente cliente) {

        if (cliente.getIdade() < Cliente.IDADE_MINIMA_CLIENTE) {
            throw new NegocioException(String.format(
                    "O cliente nao pode ter menos de %d anos de idade", Cliente.IDADE_MINIMA_CLIENTE));
        }

        clienteRepository.findByEmail(cliente.getEmail())
                .ifPresent(x -> {
                        if (x.getId() != cliente.getId()) {
                            throw new NegocioException(
                                String.format("Email %s ja cadastrado para o cliente %s", cliente.getEmail(), x.getNome()));
                        }
                });

        clienteRepository.findByCpf(cliente.getCpf())
                .ifPresent(x -> {
                    if (x.getId() != cliente.getId()) {
                        throw new NegocioException(
                                String.format("Cpf %s ja cadastrado para o cliente %s", cliente.getCpf(), x.getNome()));
                    }
                });

        return clienteRepository.save(cliente);
    }

    public Cliente buscarOuFalhar(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNaoEncontradoException(id));
    }
}
