package br.com.orcamento.controle.repository;

import br.com.orcamento.controle.model.Receita;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceitaRepository extends JpaRepository<Receita, Long> {
}
