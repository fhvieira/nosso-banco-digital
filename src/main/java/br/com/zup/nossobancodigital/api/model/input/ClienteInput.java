package br.com.zup.nossobancodigital.api.model.input;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Getter
@Setter
public class ClienteInput {
    @NotBlank
    private String nome;

    @NotBlank
    private String sobrenome;

    @NotBlank
    @Email
    private String email;

    @Past
    private LocalDate dataNascimento;

    @NotBlank
    @CPF
    private String cpf;
}
