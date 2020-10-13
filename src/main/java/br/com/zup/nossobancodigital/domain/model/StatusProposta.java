package br.com.zup.nossobancodigital.domain.model;

public enum StatusProposta {
    INCOMPLETA( "Incompleta" ),
    COMPLETA ( "Completa" ),
    CONFIRMADA ( "Confirmada" ),
    CANCELADA (  "Cancelada" ),
    APROVADA ( "Aprovada" ),
    RECUSADA ("Recusada" );

    private String descricao;

    StatusProposta(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return this.descricao;
    }
}
