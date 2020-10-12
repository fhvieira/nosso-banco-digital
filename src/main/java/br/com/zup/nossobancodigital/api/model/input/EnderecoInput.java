package br.com.zup.nossobancodigital.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class EnderecoInput {

    @Pattern(regexp = "[0-9]{5}-[0-9]{3}")
    @NotBlank
    private String cep;

    @NotBlank
    private String rua;

    @NotBlank
    private String bairro;

    @NotBlank
    private String complemento;

    @NotBlank
    private String cidade;

    @NotBlank
    private String estado;
}
