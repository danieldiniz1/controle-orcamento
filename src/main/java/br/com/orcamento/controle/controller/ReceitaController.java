package br.com.orcamento.controle.controller;

import br.com.orcamento.controle.controller.dto.ReceitaDTO;
import br.com.orcamento.controle.controller.form.ReceitaForm;
import br.com.orcamento.controle.model.Receita;
import br.com.orcamento.controle.service.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/receita")
public class ReceitaController {

    @Autowired
    ReceitaService receitaService;

    @PostMapping("/cadastro")
    public ResponseEntity<ReceitaDTO> cadastroReceita(@RequestBody @Valid ReceitaForm receitaForm){
        receitaService.cadastrarReceita(receitaForm);
        return ResponseEntity.ok(new ReceitaDTO(receitaForm));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Receita>> listarTodasReceitas(){
        return ResponseEntity.ok().body(receitaService.listarTodasAsReceitas());
    }

    @GetMapping("listar/{id}")
    public ResponseEntity<Receita> buscarReceitaPorId(@PathVariable Long id){
        return ResponseEntity.ok().body(receitaService.buscarReceitaPorId(id));
    }

    @PostMapping("atualizar/{id}")
    public ResponseEntity<ReceitaDTO> atualizarReceita(@PathVariable Long id,
                                                       @RequestBody @Valid ReceitaForm receitaForm){
        receitaService.atualizarReceita(receitaForm, id);
        return ResponseEntity.ok(new ReceitaDTO(receitaForm));
    }

    @DeleteMapping("deletar/{id}")
    public ResponseEntity deletarPorId(@PathVariable Long id){
        receitaService.deletarReceitaPorId(id);
        return ResponseEntity.status(204).build();
    }


}
