package br.com.orcamento.controle.service;

import br.com.orcamento.controle.controller.dto.ReceitaDTO;
import br.com.orcamento.controle.controller.form.ReceitaForm;
import br.com.orcamento.controle.exception.ParameterInvalidException;
import br.com.orcamento.controle.model.Receita;
import br.com.orcamento.controle.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class ReceitaService {

    @Autowired
    ReceitaRepository receitaRepository;
//    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public void cadastrarReceita(ReceitaForm receitaForm) {
        receitaRepository.save(new Receita(receitaForm.getDescricao(),
                BigDecimal.valueOf(receitaForm.getValor()),
                LocalDate.parse(receitaForm.getDataLancamento())));
    }
}
