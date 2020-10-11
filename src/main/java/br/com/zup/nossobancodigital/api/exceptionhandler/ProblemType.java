package br.com.zup.nossobancodigital.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    ACESSO_NEGADO("/acesso-negado", "Acesso negado"),
    DADOS_INVALIDOS("/dados-invalidos", "dados invalidos"),
    ERRO_DE_SISTEMA("/erro-de-sistema", "erro de sistema"),
    PARAMETRO_INVALIDO("/parametro-invaldo", "parametro invalido"),
    RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "recurso nao encontrado"),
    ENTIDADE_EM_USO("/entidade-em-usop", "entidade em uso"),
    ERRO_NEGOCIO("/erro-negocio", "violacao de regra de negocio"),
    MENSAGEM_INCOMPREENSSIVEL("/mensagem-incompreesinvel", "mensagem incompreensivel");

    private String title;
    private String uri;

    ProblemType(String path, String title) {
        this.uri = "https://nossobancodigital.com.br/" + path;
        this.title = title;
    }

}
