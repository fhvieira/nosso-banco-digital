package br.com.zup.nossobancodigital.domain.event;

import br.com.zup.nossobancodigital.domain.model.Conta;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ContaConfirmadaEvent {
    private Conta conta;
}
