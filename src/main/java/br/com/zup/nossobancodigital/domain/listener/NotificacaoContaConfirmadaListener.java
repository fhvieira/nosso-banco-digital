package br.com.zup.nossobancodigital.domain.listener;

import br.com.zup.nossobancodigital.domain.event.ContaConfirmadaEvent;
import br.com.zup.nossobancodigital.domain.event.PropostaConfirmadaEvent;
import br.com.zup.nossobancodigital.domain.model.Conta;
import br.com.zup.nossobancodigital.domain.model.Proposta;
import br.com.zup.nossobancodigital.domain.service.CadastroContaService;
import br.com.zup.nossobancodigital.domain.service.EnvioEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class NotificacaoContaConfirmadaListener {

    @Autowired
    EnvioEmailService envioEmailService;

    @Autowired
    CadastroContaService cadastroContaService;

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void aoConfirmarConta(ContaConfirmadaEvent event){
        Conta conta = event.getConta();

        var mensagem = EnvioEmailService.Mensagem.builder()
                .assunto("cponta confirmada")
                .corpo("conta-confirmada.html")
                .variavel("conta", conta)
                .destinatario("") // fixme: precisa da referencia da proposta na conta
                .build();

        envioEmailService.enviar(mensagem);

        // todo: ver o que mais precisa ser feito na confirmacao da contra
    }
}
