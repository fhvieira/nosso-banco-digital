package br.com.zup.nossobancodigital.api.controller;

import br.com.zup.nossobancodigital.api.assembler.PropostaModelAssembler;
import br.com.zup.nossobancodigital.api.model.PropostaModel;
import br.com.zup.nossobancodigital.api.model.input.FotoInput;
import br.com.zup.nossobancodigital.domain.model.Proposta;
import br.com.zup.nossobancodigital.domain.service.CadastroPropostaService;
import br.com.zup.nossobancodigital.domain.service.FotoStorageService;
import br.com.zup.nossobancodigital.domain.service.PropostaFotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/propostas/{propostaId}/foto")
public class PropostaFotoController {

    @Autowired
    PropostaModelAssembler propostaModelAssembler;

    @Autowired
    PropostaFotoService propostaFotoService;

    @Autowired
    CadastroPropostaService cadastroPropostaService;

    @Autowired
    FotoStorageService fotoStorageService;

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public PropostaModel atualizarFoto(@PathVariable Long propostaId,
                                       @Valid FotoInput fotoInput,
                                       HttpServletResponse response) throws IOException {

        Proposta propostaSalva = cadastroPropostaService.salvarFoto(fotoInput, propostaId);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .buildAndExpand(propostaId).toUri();

        response.addHeader(HttpHeaders.LOCATION, uri.toString()
                .replace("foto", "confirmacao"));

        return propostaModelAssembler.toModel(propostaSalva);
    }

//    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//    public PropostaFotoModel buscar(@PathVariable Long propostaId) {
//        PropostaFoto propostaFoto = null; // fixme = propostaFotoService.buscarOuFalhar(propostaId);
//
//        return propostaFotoModelAssembler.toModel(propostaFoto);
//    }

//    @GetMapping
//    public ResponseEntity<?> servirFoto(@PathVariable Long propostaId, @RequestHeader String acceptHeader)
//            throws HttpMediaTypeNotAcceptableException {
//        try {
//            PropostaFoto propostaFoto = null; // fixme propostaFotoService.buscarOuFalhar(propostaId);
//
//            MediaType mediaTypeFoto = MediaType.parseMediaType(propostaFoto.getContentType());
//
//            List<MediaType> mediaTypesAceitas = MediaType.parseMediaTypes(acceptHeader);
//
//            verificarCompatibilidadeMediaType(mediaTypeFoto, mediaTypesAceitas);
//
//            FotoStorageService.FotoRecuperada fotoRecuperada = fotoStorageService.recuperar(propostaFoto.getNomeArquivo());
//
//            if (fotoRecuperada.temInputStream()) {
//                return ResponseEntity.ok()
//                        .contentType(mediaTypeFoto)
//                        .body(new InputStreamResource(fotoRecuperada.getInputStream()));
//            }
//
//            return ResponseEntity
//                    .status(HttpStatus.FOUND)
//                    .header(HttpHeaders.LOCATION, fotoRecuperada.getUrl())
//                    .build();
//
//        } catch(EntidadeNaoEncontradaException e) {
//            return ResponseEntity.notFound().build();
//        }
//    }

    private void verificarCompatibilidadeMediaType(MediaType mediaType, List<MediaType> mediaTypesAceitas)
            throws HttpMediaTypeNotAcceptableException {

         boolean compativel = mediaTypesAceitas.stream()
                 .anyMatch(mediaTypeAceita -> mediaTypeAceita.isCompatibleWith(mediaType));

         if (!compativel) {
             throw new HttpMediaTypeNotAcceptableException(mediaTypesAceitas);
         }

    }

}