package br.com.zup.nossobancodigital.api.controller;

import br.com.zup.nossobancodigital.api.assembler.PropostaFotoModelAssembler;
import br.com.zup.nossobancodigital.api.model.PropostaFotoModel;
import br.com.zup.nossobancodigital.api.model.input.PropostaFotoInput;
import br.com.zup.nossobancodigital.domain.exception.EntidadeNaoEncontradaException;
import br.com.zup.nossobancodigital.domain.model.Proposta;
import br.com.zup.nossobancodigital.domain.model.PropostaFoto;
import br.com.zup.nossobancodigital.domain.service.CadastroPropostaService;
import br.com.zup.nossobancodigital.domain.service.FotoStorageService;
import br.com.zup.nossobancodigital.domain.service.PropostaFotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/propostas/{propostaId}/foto")
public class PropostaFotoController {

    @Autowired
    PropostaFotoModelAssembler propostaFotoModelAssembler;

    @Autowired
    PropostaFotoService propostaFotoService;

    @Autowired
    CadastroPropostaService cadastroPropostaService;

    @Autowired
    FotoStorageService fotoStorageService;

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public PropostaFotoModel atualizarFoto(@PathVariable Long propostaId,
            @Valid PropostaFotoInput propostaFotoInput) throws IOException {

        Proposta proposta = cadastroPropostaService.buscarOuFalhar(propostaId);
        PropostaFoto foto = new PropostaFoto();

        MultipartFile arquivo = propostaFotoInput.getArquivo();

        foto.setId(propostaId);
        foto.setProposta(proposta);
        foto.setDescricao(propostaFotoInput.getDescricao());
        foto.setContentType(arquivo.getContentType());
        foto.setTamanho(arquivo.getSize());
        foto.setNomeArquivo(arquivo.getOriginalFilename());

        PropostaFoto fotoSalva = propostaFotoService.salvar(foto, arquivo.getInputStream());

        return propostaFotoModelAssembler.toModel(fotoSalva);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public PropostaFotoModel buscar(@PathVariable Long propostaId) {
        PropostaFoto propostaFoto = null; // fixme = propostaFotoService.buscarOuFalhar(propostaId);

        return propostaFotoModelAssembler.toModel(propostaFoto);
    }

    @GetMapping
    public ResponseEntity<?> servirFoto(@PathVariable Long propostaId, @RequestHeader String acceptHeader)
            throws HttpMediaTypeNotAcceptableException {
        try {
            PropostaFoto propostaFoto = null; // fixme propostaFotoService.buscarOuFalhar(propostaId);

            MediaType mediaTypeFoto = MediaType.parseMediaType(propostaFoto.getContentType());

            List<MediaType> mediaTypesAceitas = MediaType.parseMediaTypes(acceptHeader);

            verificarCompatibilidadeMediaType(mediaTypeFoto, mediaTypesAceitas);

            FotoStorageService.FotoRecuperada fotoRecuperada = fotoStorageService.recuperar(propostaFoto.getNomeArquivo());

            if (fotoRecuperada.temInputStream()) {
                return ResponseEntity.ok()
                        .contentType(mediaTypeFoto)
                        .body(new InputStreamResource(fotoRecuperada.getInputStream()));
            }

            return ResponseEntity
                    .status(HttpStatus.FOUND)
                    .header(HttpHeaders.LOCATION, fotoRecuperada.getUrl())
                    .build();

        } catch(EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }

    private void verificarCompatibilidadeMediaType(MediaType mediaType, List<MediaType> mediaTypesAceitas)
            throws HttpMediaTypeNotAcceptableException {

         boolean compativel = mediaTypesAceitas.stream()
                 .anyMatch(mediaTypeAceita -> mediaTypeAceita.isCompatibleWith(mediaType));

         if (!compativel) {
             throw new HttpMediaTypeNotAcceptableException(mediaTypesAceitas);
         }

    }

}