package br.com.orcamento.controle.model;

import br.com.orcamento.controle.controller.form.DespesaForm;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity(name = "despesas")
public class Despesa implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private BigDecimal valor;
    @Column(name = "data_do_lançamento")
    private LocalDate dataLancamento;
    private Categoria categoria = Categoria.toEnum(1);


    public Despesa() {
    }

    public Despesa(String descricao, BigDecimal valor, LocalDate dataLancamento) {
        this.descricao = descricao;
        this.valor = valor;
        this.dataLancamento = dataLancamento;
    }

    public Despesa(String descricao, BigDecimal valor, LocalDate dataLancamento, Categoria categoria) {
        this.descricao = descricao;
        this.valor = valor;
        this.dataLancamento = dataLancamento;
        this.categoria = categoria;
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

    public Categoria getCategoria() { return categoria; }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public void setCategoria(Categoria categoria) { this.categoria = categoria; }

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

    public static Despesa of(DespesaForm despesaForm){
        return new Despesa(despesaForm.getDescricao(),
                BigDecimal.valueOf(Double.parseDouble(despesaForm.getValor())),
                LocalDate.parse(despesaForm.getDataLancamento()));
    }
}
