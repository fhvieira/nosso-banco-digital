package br.com.zup.nossobancodigital.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

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
public class Conta {

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

    @NotNull
    private String agencia;

    @NotNull
    private String conta;

    @NotNull
    private int codigoBanco;

    @NotNull
    private BigDecimal Saldo = BigDecimal.valueOf(0);

    @NotNull
    private Proposta propostaOrigem;
}
