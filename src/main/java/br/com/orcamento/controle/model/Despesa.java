package br.com.orcamento.controle.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity(name = "despesas")
public class Despesa {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private BigDecimal valor;
    @Column(name = "data_do_lançamento")
    private LocalDateTime dataLancamento;

    public Despesa() {
    }

    public Despesa(String descricao, BigDecimal valor, LocalDateTime dataLancamento) {
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
        if (!(o instanceof Despesa)) return false;
        Despesa receita = (Despesa) o;
        return getId().equals(receita.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
