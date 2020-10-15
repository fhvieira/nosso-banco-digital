package br.com.zup.nossobancodigital.domain.service;

import br.com.zup.nossobancodigital.api.model.input.FotoInput;
import br.com.zup.nossobancodigital.domain.exception.NegocioException;
import br.com.zup.nossobancodigital.domain.exception.PropostaNaoEncontradaException;
import br.com.zup.nossobancodigital.domain.model.Foto;
import br.com.zup.nossobancodigital.domain.model.Proposta;
import br.com.zup.nossobancodigital.domain.model.StatusProposta;
import br.com.zup.nossobancodigital.domain.repository.PropostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service
public class CadastroPropostaService {

    @Autowired
    PropostaRepository propostaRepository;

    @Autowired
    CadastroPropostaService cadastroPropostaService;

    @Autowired
    private FotoStorageService fotoStorageService;

    @Transactional
    public Proposta salvar(Proposta proposta) {

        if (proposta.getIdade() < Proposta.IDADE_MINIMA_PROPOSTA) {
            throw new NegocioException(String.format(
                    "Proposta nao permitida para menores de %d anos de idade", Proposta.IDADE_MINIMA_PROPOSTA));
        }

        propostaRepository.findByEmail(proposta.getEmail())
                .ifPresent(x -> {
                        if (x.getId() != proposta.getId()) {
                            throw new NegocioException(
                                String.format("Email %s ja cadastrado para a proposta de id %d", proposta.getEmail(), x.getId()));
                        }
                });

        propostaRepository.findByCpf(proposta.getCpf())
                .ifPresent(x -> {
                    if (x.getId() != proposta.getId()) {
                        throw new NegocioException(
                                String.format("Cpf %s ja cadastrado para a proposta de id %d", proposta.getCpf(), x.getId()));
                    }
                });

        if (proposta.getEndereco() != null && proposta.getStatus() == StatusProposta.INCOMPLETA) {
            proposta.setStatus(StatusProposta.COMPLETA);
        }

        return propostaRepository.save(proposta);
    }

    @Transactional
    public Proposta salvarFoto(FotoInput fotoInput, Long propostaId) throws IOException {
        Proposta proposta = cadastroPropostaService.buscarOuFalhar(propostaId);

        MultipartFile arquivo = fotoInput.getArquivo();

        String nomeArquivo = arquivo.getOriginalFilename();
        String nomeArquivoExistente = (proposta.getFoto() == null) ? "" : proposta.getFoto().getNomeArquivo();

        nomeArquivo = fotoStorageService.gerarNomeArquivo(nomeArquivo);

        Foto foto = new Foto();

        foto.setDescricao(fotoInput.getDescricao());
        foto.setContentType(arquivo.getContentType());
        foto.setTamanho(arquivo.getSize());
        foto.setNomeArquivo(nomeArquivo);

        proposta.setFoto(foto);

        proposta = propostaRepository.save(proposta);

        propostaRepository.flush();

        //
        var novaFoto = FotoStorageService.NovaFoto.builder()
                .nomeArquivo(foto.getNomeArquivo())
                .contentType(foto.getContentType())
                .inputStream(arquivo.getInputStream())
                .build();

        if (!nomeArquivoExistente.isBlank()) {
            fotoStorageService.remover(nomeArquivoExistente);
        }

        fotoStorageService.substituir(novaFoto, nomeArquivoExistente);

        return proposta;
    }

    public Proposta buscarOuFalhar(Long id) {
        return propostaRepository.findById(id)
                .orElseThrow(() -> new PropostaNaoEncontradaException(id));
    }
}
