package br.com.zup.nossobancodigital.api.controller;

import br.com.zup.nossobancodigital.domain.service.FluxoContaService;
import br.com.zup.nossobancodigital.domain.service.FluxoPropostaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contas/{contaId}")
public class FluxoContaController {

    @Autowired
    FluxoContaService fluxoContaService;

    @PutMapping("/confirmacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirmar(@PathVariable Long contaId) {
        fluxoContaService.confirmar(contaId);
    }

    @PutMapping("/cancelamento")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelar(@PathVariable Long contaId) {
        fluxoContaService.cancelar(contaId);
    }

}
