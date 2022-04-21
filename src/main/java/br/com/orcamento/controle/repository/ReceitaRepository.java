package br.com.orcamento.controle.repository;

import br.com.orcamento.controle.model.Receita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long> {

    boolean existsByDataLancamentoAndDescricao(LocalDate dataLancamento, String descricao);
}
