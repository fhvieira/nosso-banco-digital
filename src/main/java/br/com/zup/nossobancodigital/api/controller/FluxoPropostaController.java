package br.com.zup.nossobancodigital.api.controller;

import br.com.zup.nossobancodigital.domain.service.FluxoPropostaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/propostas/{propostaId}")
public class FluxoPropostaController {

    @Autowired
    FluxoPropostaService fluxoPropostaService;

    @PutMapping("/confirmacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirmar(@PathVariable Long propostaId) {
        fluxoPropostaService.confirmar(propostaId);
    }

    @PutMapping("/cancelamento")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelar(@PathVariable Long propostaId) {
        fluxoPropostaService.cancelar(propostaId);
    }

}
