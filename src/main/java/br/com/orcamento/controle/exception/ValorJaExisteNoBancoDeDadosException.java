package br.com.orcamento.controle.exception;

public class ValorJaExisteNoBancoDeDadosException extends RuntimeException {
    public ValorJaExisteNoBancoDeDadosException(String message) {
        super(message);
    }
}
