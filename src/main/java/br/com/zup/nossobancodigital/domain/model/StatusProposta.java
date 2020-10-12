package br.com.zup.nossobancodigital.domain.model;

public enum StatusProposta {
    INCOMPLETA( "Incompleta" ),
    COMPLETA ( "Completa" ),
    ACEITA( "Aceita" ),
    RECUSADA ("Recusada" ),
    APROVADA( "Aprovada" ),
    NEGADA( "Negada" );

    private String descricao;

    StatusProposta(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return this.descricao;
    }
}
