package br.com.orcamento.controle.repository;

import br.com.orcamento.controle.model.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long> {

    boolean existsByDataLancamentoAndDescricao(LocalDate dataLancamento, String Descricao);
}
