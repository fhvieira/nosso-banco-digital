package br.com.zup.nossobancodigital.domain.event;

import br.com.zup.nossobancodigital.domain.model.Proposta;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PropostaConfirmadaEvent {

    private Proposta proposta;
}
