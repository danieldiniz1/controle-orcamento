package br.com.orcamento.controle.exception;

public class ValorJaExisteNoBancoException extends RuntimeException {
    public ValorJaExisteNoBancoException(String message) {
        super(message);
    }
}
