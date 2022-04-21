package br.com.orcamento.controle.service;

import br.com.orcamento.controle.controller.dto.DespesaDTO;
import br.com.orcamento.controle.controller.form.DespesaForm;
import br.com.orcamento.controle.exception.ObjectNotFoundException;
import br.com.orcamento.controle.exception.ValorJaExisteNoBancoDeDadosException;
import br.com.orcamento.controle.model.Despesa;
import br.com.orcamento.controle.model.Receita;
import br.com.orcamento.controle.repository.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class DespesaService {

    @Autowired
    private DespesaRepository despesaRepository;

    public void cadastrarDespesa(DespesaForm despesaForm) {
        if (despesaRepository.existsByDataLancamentoAndDescricao(LocalDate.parse(despesaForm.getDataLancamento()),despesaForm.getDescricao())){
            throw new ValorJaExisteNoBancoDeDadosException("A despesa já existe no banco de dados");
        }
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
        despesaRepository.save(atualizaDadosDeDespesa(buscarDespesaPorId(id),despesaForm));
    }

    public void deletaDespesaPorId(Long id) {
        despesaRepository.deleteById(id);
    }

    public Despesa atualizaDadosDeDespesa(Despesa despesa, DespesaForm despesaForm){
        despesa.setDescricao(despesaForm.getDescricao());
        despesa.setDataLancamento(LocalDate.parse(despesaForm.getDataLancamento()));
        despesa.setValor(BigDecimal.valueOf(Double.parseDouble(despesaForm.getValor())));
        return despesa;
    }


}
