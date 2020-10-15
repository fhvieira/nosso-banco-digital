package br.com.zup.nossobancodigital.domain.model;

import br.com.zup.nossobancodigital.domain.event.ContaConfirmadaEvent;
import br.com.zup.nossobancodigital.domain.exception.NegocioException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Random;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Conta extends AbstractAggregateRoot {

    public Conta() {
        Random r = new Random();

        this.agencia = String.format("%04d", r.nextInt(1001));
        this.conta = String.format("%04d", r.nextInt(10000001));
        this.codigoBanco = 101;
    }

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private StatusConta status = StatusConta.AGUARDANDO_CONFIRMACAO;

    @NotNull
    private String agencia;

    @NotNull
    private String conta;

    @NotNull
    private int codigoBanco;

    @NotNull
    private BigDecimal Saldo = BigDecimal.valueOf(0);

//    @NotNull
//    private Proposta propostaOrigem;

    public void confirmar() {
        if (!this.podeConfirmar()) {
            throw new NegocioException("A proposta nao esta completa");
        }

        setStatus(StatusConta.APROVADA);

        registerEvent(new ContaConfirmadaEvent(this));
    }

    public void cancelar() {

    }

    public boolean podeConfirmar() {
        return  false;
    }
}
