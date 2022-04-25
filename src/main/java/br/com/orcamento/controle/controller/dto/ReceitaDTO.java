package br.com.orcamento.controle.controller.dto;

import br.com.orcamento.controle.controller.form.ReceitaForm;
import br.com.orcamento.controle.model.Categoria;

public class ReceitaDTO {

    private String descricao;
    private double valor;
    private String dataLancamento;
    private String categoria;

    public ReceitaDTO(ReceitaForm receita) {
        this.descricao = receita.getDescricao();
        this.valor = Double.parseDouble(receita.getValor());
        this.dataLancamento = receita.getDataLancamento();
        this.categoria = String.valueOf(Categoria.toEnum(Integer.parseInt(receita.getCodigoCategoria())));
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(String dataLancamento) {
        this.dataLancamento = dataLancamento;
    }


}
