package br.com.orcamento.controle.repository;

import br.com.orcamento.controle.model.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long> {

    boolean existsByDataLancamentoAndDescricao(LocalDate dataLancamento, String Descricao);

    List<Despesa> findByDescricaoContaining(String descricao); // problema no hibernate e spring https://github.com/spring-projects/spring-data-jpa/issues/2472

    @Query(value = "SELECT r FROM br.com.orcamento.controle.model.Despesa r WHERE r.descricao like %:descricao%")
    List<Despesa> encontraPorDescricao(@Param("descricao") String descricao);

    List<Despesa> findByDataLancamentoBetween(LocalDate dataInicial, LocalDate dataFinal);
}
