package br.com.zup.nossobancodigital.domain.listener;

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
public class NotificacaoPropostaConfirmadaListener {

    @Autowired
    EnvioEmailService envioEmailService;

    @Autowired
    CadastroContaService cadastroContaService;

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void aoConfirmarProposta(PropostaConfirmadaEvent event){
        Proposta proposta = event.getProposta();

        var mensagem = EnvioEmailService.Mensagem.builder()
                .assunto(proposta.getNome() + " - " + "proposta confirmada")
                .corpo("proposta-confirmada.html")
                .variavel("proposta", proposta)
                .destinatario(proposta.getEmail())
                .build();

        envioEmailService.enviar(mensagem);

        Conta novaConta = new Conta();
        novaConta.setPropostaOrigem(proposta);
        cadastroContaService.salvar(novaConta);
    }
}
