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
    private Categoria categoria;

    public Receita() {
        // Construtor default
    }

    public Receita(String descricao, BigDecimal valor, LocalDate dataLancamento) {
        this.descricao = descricao;
        this.valor = valor;
        this.dataLancamento = dataLancamento;
    }

    public Receita(String descricao, BigDecimal valor, LocalDate dataLancamento, Categoria categoria) {
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

    public Categoria getCategoria() {
        return categoria;
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

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
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
        LocalDate.parse(receitaForm.getDataLancamento()),Categoria.toEnum(Integer.parseInt(receitaForm.getCodigoCategoria())));
        return receita;
    }

    public void alteraCategoria(String numeroCategoria){
        setCategoria(Categoria.toEnum(Integer.parseInt(numeroCategoria)));
    }

}
