package br.com.zup.nossobancodigital.domain.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@Embeddable
public class Endereco {

    @Column(name = "endereco_cep")
    private String cep;

    @Column(name = "endereco_rua")
    private String rua;

    @Column(name = "endereco_bairro")
    private String bairro;

    @Column(name = "endereco_complemento")
    private String complemento;

    @Column(name = "endereco_cidade")
    private String cidade;

    @Column(name = "endereco_estado")
    private String estado;
}
