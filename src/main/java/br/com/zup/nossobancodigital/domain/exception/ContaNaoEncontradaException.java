package br.com.zup.nossobancodigital.domain.exception;

public class ContaNaoEncontradaException extends EntidadeNaoEncontradaException {

    public ContaNaoEncontradaException(String msg) {
        super(msg);
    }

    public ContaNaoEncontradaException(Long contaId) {
        super(String.format("Copnta de codigo %d nao existe.", contaId));
    }

}
