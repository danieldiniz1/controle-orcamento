package br.com.orcamento.controle.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Receita {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private BigDecimal valor;
    private LocalDateTime dataLancamento;

    public Receita() {
    }

    public Receita(String descricao, BigDecimal valor, LocalDateTime dataLancamento) {
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

    public LocalDateTime getDataLancamento() {
        return dataLancamento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Receita)) return false;
        Receita receita = (Receita) o;
        return getId().equals(receita.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
