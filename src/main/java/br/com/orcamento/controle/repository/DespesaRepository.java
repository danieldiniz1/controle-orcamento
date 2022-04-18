package br.com.orcamento.controle.repository;

import br.com.orcamento.controle.model.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DespesaRepository extends JpaRepository<Despesa, Long> {
}
