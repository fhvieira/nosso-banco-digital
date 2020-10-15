package br.com.zup.nossobancodigital.domain.exception;

public class PropostaFotoNaoEncontradaException extends EntidadeNaoEncontradaException {
    private static final long serialVersionUID = 1L;

    public PropostaFotoNaoEncontradaException(String msg) {
        super(msg);
    }

    public PropostaFotoNaoEncontradaException(Long propostaId) {
        this(String.format("Não existe um cadastro de foto proposta com código %d", propostaId));
    }

}
