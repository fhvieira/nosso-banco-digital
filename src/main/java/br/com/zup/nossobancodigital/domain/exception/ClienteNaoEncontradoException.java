package br.com.zup.nossobancodigital.domain.exception;

public class ClienteNaoEncontradoException extends NegocioException {

    public ClienteNaoEncontradoException(String msg) {
        super(msg);
    }

    public ClienteNaoEncontradoException(Long clienteId) {
        super(String.format("Cliente de codigo %d nao existe.", clienteId));
    }

}
