package br.com.orcamento.controle.controller.form;

import br.com.orcamento.controle.model.Categoria;

import javax.validation.constraints.*;

public class ReceitaForm {

    @NotBlank(message = "Valor Inválido") @Size(min = 3)
    private String descricao;
    @NotBlank(message = "Valor Inválido")
    private String valor;
    @NotBlank(message = "Valor Inválido") @Size(min = 10, max = 11, message = "data deve estar no formado yyyy/MM/dd")
    private String dataLancamento;
    private String codigoCategoria = "0";

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(String dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public String getCodigoCategoria() {
        return codigoCategoria;
    }

    public void setCodigoCategoria(String codigoCategoria) {
        this.codigoCategoria = codigoCategoria;
    }
}
