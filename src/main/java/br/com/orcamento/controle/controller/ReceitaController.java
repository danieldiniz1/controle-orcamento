package br.com.orcamento.controle.controller;

import br.com.orcamento.controle.controller.dto.ReceitaDTO;
import br.com.orcamento.controle.controller.form.ReceitaForm;
import br.com.orcamento.controle.model.Receita;
import br.com.orcamento.controle.service.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
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
        return ResponseEntity.ok().body(receitaService.listarReceitaPorId(id));
    }
}
