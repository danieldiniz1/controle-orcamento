package br.com.orcamento.controle.model;

import br.com.orcamento.controle.controller.form.ReceitaForm;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity(name = "receitas")
public class Receita implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private BigDecimal valor;
    @Column(name = "data_do_lançamento")
    private LocalDate dataLancamento;

    public Receita() {
        // Construtor default
    }

    public Receita(String descricao, BigDecimal valor, LocalDate dataLancamento) {
        this.descricao = descricao;
        this.valor = valor;
        this.dataLancamento = dataLancamento;
    }

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Receita)) return false;
        Receita receita = (Receita) o;
        return getDescricao().equals(receita.getDescricao()) && getDataLancamento().equals(receita.getDataLancamento());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDescricao(), getDataLancamento());
    }

    public static Receita of(ReceitaForm receitaForm){
        Receita receita = new Receita(receitaForm.getDescricao(),
        BigDecimal.valueOf(Double.valueOf(receitaForm.getValor())),
        LocalDate.parse(receitaForm.getDataLancamento()));
        return receita;
    }
}
