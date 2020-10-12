package br.com.zup.nossobancodigital.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoModel {

    private String cep;
    private String rua;
    private String bairro;
    private String complemento;
    private String cidade;
    private String estado;
}
