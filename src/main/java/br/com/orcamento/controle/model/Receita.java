package br.com.orcamento.controle.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    private LocalDate dataLancamento;

    public Receita() {
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
}
