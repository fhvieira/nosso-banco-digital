package br.com.zup.nossobancodigital.api.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PropostaModel {
    private Long id;
    private String nome;
    private String sobrenome;
    private String email;
    private LocalDate dataNascimento;
    private String cpf;
    private String status;
    private EnderecoModel endereco;
}
