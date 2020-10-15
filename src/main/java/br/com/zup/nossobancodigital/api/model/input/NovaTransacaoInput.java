package br.com.zup.nossobancodigital.api.model.input;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NotBlank
public class NovaTransacaoInput {
    BigDecimal valorTransferencia;
    LocalDateTime dataRealizacao;
    String documentoOrigemTransferência;
    String codigoBancoOrigem;
    String contaOrigem;
    String agenciaOrigem;
    Long codigoTransferenciaBancoOrigem;
    String contaDestino;
    String agenciaDestino;
}
