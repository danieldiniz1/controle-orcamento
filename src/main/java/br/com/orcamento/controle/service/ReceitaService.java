package br.com.orcamento.controle.service;

import br.com.orcamento.controle.controller.form.ReceitaForm;
import br.com.orcamento.controle.exception.ObjectNotFoundException;
import br.com.orcamento.controle.exception.ValorJaExisteNoBancoDeDadosException;
import br.com.orcamento.controle.model.Categoria;
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
    private ReceitaRepository receitaRepository;

    public void cadastrarReceita(ReceitaForm receitaForm) {
        validaReceitaExisteNoBancoDeDados(receitaForm);
        receitaRepository.save(Receita.of(receitaForm));
    }

    public List<Receita> listarTodasAsReceitas() {
        return receitaRepository.findAll();
    }

    public Receita buscarReceitaPorId(Long id) {
        return receitaRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("" +
                "A Receita de id: " + id + " não foi encontrada no BD, Tipo: " + Receita.class.getName()));
    }

    public void atualizarReceita(ReceitaForm receitaForm, Long id) {
        validaReceitaExisteNoBancoDeDados(receitaForm);
        receitaRepository.save(atualizaDadosDeReceita(buscarReceitaPorId(id),receitaForm));
    }

    public void deletarReceitaPorId(Long id) {
        receitaRepository.deleteById(id);
    }

    private Receita atualizaDadosDeReceita(Receita receita, ReceitaForm receitaForm){
        receita.setDescricao(receitaForm.getDescricao());
        receita.setDataLancamento(LocalDate.parse(receitaForm.getDataLancamento()));
        receita.setValor(BigDecimal.valueOf(Double.valueOf(receitaForm.getValor())));
        receita.alteraCategoria(receitaForm.getCodigoCategoria());
        return receita;
    }

    private void validaReceitaExisteNoBancoDeDados(ReceitaForm receitaForm) {
        if (receitaRepository.existsByDataLancamentoAndDescricao(LocalDate.parse(receitaForm.getDataLancamento()), receitaForm.getDescricao())) {
            throw new ValorJaExisteNoBancoDeDadosException("O lançamento já existe no banco de dados!");
        }
    }


}


