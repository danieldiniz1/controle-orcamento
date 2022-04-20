package br.com.orcamento.controle.service;

import br.com.orcamento.controle.controller.form.ReceitaForm;
import br.com.orcamento.controle.exception.ObjectNotFoundException;
import br.com.orcamento.controle.exception.ValorJaExisteNoBancoException;
import br.com.orcamento.controle.model.Receita;
import br.com.orcamento.controle.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

    public List<Receita> listarTodasAsReceitas() {
        return receitaRepository.findAll();
    }

    public Receita listarReceitaPorId(Long id) {
        return receitaRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("" +
                "A Receita de id: " + id + " não foi encontrada no BD, Tipo: " + Receita.class.getName()));
    }
}
