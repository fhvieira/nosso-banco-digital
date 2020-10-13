package br.com.zup.nossobancodigital.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class FotoDocumento {

    @EqualsAndHashCode.Include
    @Id
    @Column(name = "produto_id")
    private Long id;

    private String nomeArquivo;
    private String descricao;
    private String contentType;
    private Long tamanho;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Produto produto;
}
