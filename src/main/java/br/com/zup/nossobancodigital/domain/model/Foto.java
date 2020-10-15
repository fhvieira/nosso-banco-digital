package br.com.zup.nossobancodigital.domain.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@Embeddable
public class Foto {

    @Column(name = "foto_nome_arquivo")
    private String nomeArquivo;

    @Column(name = "foto_descricao")
    private String descricao;

    @Column(name = "foto_content_type")
    private String contentType;

    @Column(name = "foto_tamanho")
    private Long tamanho;
}
