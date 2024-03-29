package br.com.orcamento.controle.controller.dto;

import br.com.orcamento.controle.controller.form.DespesaForm;
import br.com.orcamento.controle.model.Categoria;

public class DespesaDTO {

    private String descricao;
    private double valor;
    private String dataLancamento;
    private String categoria;

    public DespesaDTO(DespesaForm despesa) {
        this.descricao = despesa.getDescricao();
        this.valor = Double.parseDouble(despesa.getValor());
        this.dataLancamento = despesa.getDataLancamento();
        this.categoria = String.valueOf(Categoria.toEnum(Integer.parseInt(despesa.getCodigoCategoria())));
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

    public String getCategoria() {
        return categoria;
    }
}
