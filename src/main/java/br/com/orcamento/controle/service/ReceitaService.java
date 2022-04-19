package br.com.orcamento.controle.service;

import br.com.orcamento.controle.controller.form.ReceitaForm;
import br.com.orcamento.controle.exception.ValorJaExisteNoBancoException;
import br.com.orcamento.controle.model.Receita;
import br.com.orcamento.controle.repository.ReceitaRepository;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Service
public class ReceitaService {

    @Autowired
    ReceitaRepository receitaRepository;
//    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public void cadastrarReceita(ReceitaForm receitaForm) {
        Receita receita = new Receita(receitaForm.getDescricao(),
                BigDecimal.valueOf(Double.valueOf(receitaForm.getValor())),
                LocalDate.parse(receitaForm.getDataLancamento()));
        List<Receita> receitas = receitaRepository.findAllByDataLancamento(LocalDate.parse(receitaForm.getDataLancamento()));

        receitas.forEach(r -> {
            if (r.equals(receita)){
                throw new ValorJaExisteNoBancoException("O lançamento já foi registrado anterioremente");
            } else {
                receitaRepository.save(receita);
            }
        });

    }
}
