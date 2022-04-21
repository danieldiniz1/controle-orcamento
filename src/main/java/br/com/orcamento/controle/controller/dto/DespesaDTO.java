package br.com.orcamento.controle.controller.dto;

import br.com.orcamento.controle.controller.form.DespesaForm;

public class DespesaDTO {

    private String descricao;
    private double valor;
    private String dataLancamento;

    public DespesaDTO(DespesaForm despesa) {
        this.descricao = despesa.getDescricao();
        this.valor = Double.parseDouble(despesa.getValor());
        this.dataLancamento = despesa.getDataLancamento();
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
