package br.com.orcamento.controle.repository;

import br.com.orcamento.controle.model.Receita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReceitaRepository extends JpaRepository<Receita, Long> {

    List<Receita> findAllByDataLancamento(LocalDate dataLancamento);
}
