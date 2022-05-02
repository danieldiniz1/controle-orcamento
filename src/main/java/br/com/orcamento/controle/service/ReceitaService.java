package br.com.orcamento.controle.service;

import br.com.orcamento.controle.controller.form.ReceitaForm;
import br.com.orcamento.controle.exception.ObjectNotFoundException;
import br.com.orcamento.controle.exception.ValorJaExisteNoBancoDeDadosException;
import br.com.orcamento.controle.model.Receita;
import br.com.orcamento.controle.repository.ReceitaRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReceitaService {

    @Autowired
    private ReceitaRepository receitaRepository;

    private static final Logger logger = LogManager.getLogger(ReceitaService.class);

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

    public List<Receita> buscarListaDeReceitaContendoDescricao(String descricao) {
        List<Receita> receita = new ArrayList<>();
        try {
             receita = receitaRepository.encontraPorDescricao(descricao);
        } catch (ObjectNotFoundException ex){
            logger.info("não foi encontrado no banco uma receita com a descrição: " + descricao);
        } catch (Exception ex) {
            logger.info(ex.getMessage());
            logger.info(ex.getStackTrace());
        }
        return receita;
    }

    public List<Receita> buscasReceitasPorMesEAno(Integer ano, Integer mes) {
        LocalDate dataInicial = LocalDate.of(ano,mes,1);
        logger.info("Data Inicial: " + dataInicial);
        LocalDate dataFinal = LocalDate.of(ano, mes , Month.of(mes).length(Year.of(ano).isLeap()));
        logger.info("Data final: " + dataFinal);

        List<Receita> receitasFilttradasPorData = new ArrayList<>();
        try{
            receitasFilttradasPorData = receitaRepository.findByDataLancamentoBetween(dataInicial, dataFinal);
            receitasFilttradasPorData.forEach(receita -> logger.info(receita.getDataLancamento()));
        } catch (ObjectNotFoundException ex){
            logger.info("Não foi encontrado no BD uma receita com a data informada");
        } catch (Exception ex){
            logger.info(ex.getMessage());
            logger.info(ex.getCause());
        }
        return receitasFilttradasPorData;
    }
}


