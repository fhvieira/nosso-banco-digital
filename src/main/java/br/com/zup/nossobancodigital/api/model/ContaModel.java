package br.com.zup.nossobancodigital.api.model;

import br.com.zup.nossobancodigital.domain.model.StatusConta;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class ContaModel {
    private Long id;
    private StatusConta status;
    private String agencia;
    private String conta;
    private int codigoBanco;
    private BigDecimal Saldo;
}
