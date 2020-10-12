package br.com.zup.nossobancodigital.domain.exception;

public class PropostaNaoEncontradaException extends EntidadeNaoEncontradaException {

    public PropostaNaoEncontradaException(String msg) {
        super(msg);
    }

    public PropostaNaoEncontradaException(Long propostaId) {
        super(String.format("Proposta de codigo %d nao existe.", propostaId));
    }

}
