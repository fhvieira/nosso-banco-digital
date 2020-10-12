package br.com.zup.nossobancodigital.domain.exception;

public abstract class EntidadeNaoEncontradaException extends NegocioException {
    private static final long serialVersionUID = 1L;

    public static final String MSG_ENTIDADE_NAO_ENCONTRADA = "%s de codigo %d nao existe.";

    public EntidadeNaoEncontradaException(String msg) {
        super(msg);
    }

}
