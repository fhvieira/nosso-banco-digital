package br.com.zup.nossobancodigital.domain.model;

public enum StatusConta {
    AGUARDANDO_CONFIRMACAO( "Aguadando confirmacao" ),
    APROVADA ( "Aprovada" ),
    RECUSADA ("Recusada" );

    private String descricao;

    StatusConta(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return this.descricao;
    }
}
