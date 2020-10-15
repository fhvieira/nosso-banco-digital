package br.com.zup.nossobancodigital.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PropostaFoto {

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "proposta_id")
    private Long id;

    private String nomeArquivo;
    private String descricao;
    private String contentType;
    private Long tamanho;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Proposta proposta;
}
