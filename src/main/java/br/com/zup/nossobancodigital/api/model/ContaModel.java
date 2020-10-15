package br.com.zup.nossobancodigital.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class ContaModel {
    private Long id;
    private String agencia;
    private String conta;
    private int codigoBanco;
    private BigDecimal Saldo;
}
