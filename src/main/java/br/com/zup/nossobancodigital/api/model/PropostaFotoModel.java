package br.com.zup.nossobancodigital.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PropostaFotoModel {

    private String nomeArquivo;
    private String descricao;
    private String contentType;
    private Long tamanho;
}
