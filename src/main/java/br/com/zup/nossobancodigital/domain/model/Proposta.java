package br.com.zup.nossobancodigital.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Proposta {

    public static final int IDADE_MINIMA_PROPOSTA = 18;

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String sobrenome;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private LocalDate dataNascimento = LocalDate.now();

    @Column(nullable = false)
    private String cpf;

    @Column(nullable = false)
    private StatusProposta status = StatusProposta.INCOMPLETA;

    @Embedded
    private Endereco endereco;

    public int getIdade() {
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }

    public boolean
    podeAceitar() {
        return this.status == StatusProposta.COMPLETA;
    }

    public boolean podeRecusar() {
        return (this.status == StatusProposta.INCOMPLETA || this.status == StatusProposta.COMPLETA);
    }
}
