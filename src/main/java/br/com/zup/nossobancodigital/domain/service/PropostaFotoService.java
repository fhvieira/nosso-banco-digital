package br.com.zup.nossobancodigital.domain.service;

import br.com.zup.nossobancodigital.domain.exception.PropostaFotoNaoEncontradaException;
import br.com.zup.nossobancodigital.domain.model.PropostaFoto;
import br.com.zup.nossobancodigital.domain.repository.PropostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.Optional;

@Service
public class PropostaFotoService {

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private FotoStorageService fotoStorageService;

    @Transactional
    public PropostaFoto salvar(PropostaFoto foto, InputStream dadosArquivo) {
//        var propostaId = foto.getProposta().getId();
//        var nomeArquivo = fotoStorageService.gerarNomeArquivo(foto.getNomeArquivo());
//
//        String nomeArquivoExistente = null;
//
//        Optional<PropostaFoto> fotoExistente = propostaRepository.findFotoById(propostaId);
//
//        if (fotoExistente.isPresent()) {
//            nomeArquivoExistente = fotoExistente.get().getNomeArquivo();
//            propostaRepository.delete(fotoExistente.get());
//        }
//
//        foto.setNomeArquivo(nomeArquivo);

        foto = propostaRepository.save(foto);

//        propostaRepository.flush();
//
//        var novaFoto = FotoStorageService.NovaFoto.builder()
//                .nomeArquivo(foto.getNomeArquivo())
//                .contentType(foto.getContentType())
//                .inputStream(dadosArquivo)
//                .build();
//
//        if (nomeArquivoExistente != null)
//            fotoStorageService.remover(nomeArquivoExistente);
//
//        fotoStorageService.substituir(novaFoto, nomeArquivoExistente);

        return foto;
    }

//    public PropostaFoto buscarOuFalhar(Long propostaId) {
//        return propostaRepository.findFotoById(propostaId)
//                .orElseThrow(() -> new PropostaFotoNaoEncontradaException(propostaId));
//    }
}
