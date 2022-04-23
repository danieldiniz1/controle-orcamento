package br.com.orcamento.controle.service;

import br.com.orcamento.controle.controller.form.DespesaForm;
import br.com.orcamento.controle.exception.ObjectNotFoundException;
import br.com.orcamento.controle.exception.ValorJaExisteNoBancoDeDadosException;
import br.com.orcamento.controle.model.Despesa;
import br.com.orcamento.controle.repository.DespesaRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class DespesaService {

    @Autowired
    private DespesaRepository despesaRepository;

    private static final Logger logger = LogManager.getLogger(DespesaService.class);

    public void cadastrarDespesa(DespesaForm despesaForm) {
        validaDespesaExisteNoBancoDeDados(despesaForm);
        despesaRepository.save(Despesa.of(despesaForm));
    }

    public List<Despesa> listarTodasDespesas() {
        return despesaRepository.findAll();
    }

    public Despesa buscarDespesaPorId(Long id) {
        return despesaRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("" +
                "A Despesa de id: " + id + " não foi encontrada no BD, Tipo: " + Despesa.class.getName()));
    }

    public void atualizaDespesaPorId(Long id, DespesaForm despesaForm) {
        validaDespesaExisteNoBancoDeDados(despesaForm);
        despesaRepository.save(atualizaDadosDeDespesa(buscarDespesaPorId(id),despesaForm));
    }

    public void deletaDespesaPorId(Long id) {
        despesaRepository.deleteById(id);
    }

    private Despesa atualizaDadosDeDespesa(Despesa despesa, DespesaForm despesaForm){
        logger.info("Descrição Anterior " + despesa.getDescricao());
        despesa.setDescricao(despesaForm.getDescricao());
        logger.info("Descrição nova " + despesa.getDescricao());
        despesa.setDataLancamento(LocalDate.parse(despesaForm.getDataLancamento()));
        despesa.setValor(BigDecimal.valueOf(Double.parseDouble(despesaForm.getValor())));
        return despesa;
    }

    private Boolean validaDespesaExisteNoBancoDeDados(DespesaForm despesaForm){
        if (despesaRepository.existsByDataLancamentoAndDescricao(LocalDate.parse(despesaForm.getDataLancamento()),despesaForm.getDescricao())){
            throw new ValorJaExisteNoBancoDeDadosException("A despesa já existe no banco de dados");
        }
        return Boolean.FALSE;
    }



}
