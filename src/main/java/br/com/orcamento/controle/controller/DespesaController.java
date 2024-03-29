package br.com.orcamento.controle.controller;

import br.com.orcamento.controle.controller.dto.DespesaDTO;
import br.com.orcamento.controle.controller.form.DespesaForm;
import br.com.orcamento.controle.model.Despesa;
import br.com.orcamento.controle.service.DespesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/despesa")
public class DespesaController {

    @Autowired
    DespesaService despesaService;

    @GetMapping("listar")
    public ResponseEntity<List<Despesa>> listarTodasDespesas(){
        return ResponseEntity.ok(despesaService.listarTodasDespesas());
    }

    @GetMapping("listar/{id}")
    public ResponseEntity<Despesa> listarDespesaPorId(@PathVariable Long id){
        return ResponseEntity.ok(despesaService.buscarDespesaPorId(id));
    }

    @PostMapping("/cadastro")
    public ResponseEntity cadastrarDespesa(@RequestBody @Valid DespesaForm despesaForm){
        despesaService.cadastrarDespesa(despesaForm);
        return ResponseEntity.status(201).body(new DespesaDTO(despesaForm));
    }

    @PostMapping("/atualiza/{id}")
    public ResponseEntity atualizaDespesaporId(@PathVariable Long id, @RequestBody DespesaForm despesaForm){
        despesaService.atualizaDespesaPorId(id, despesaForm);
        return ResponseEntity.status(201).body(new DespesaDTO(despesaForm));
    }

    @DeleteMapping("/deleta/{id}")
    public ResponseEntity deletaDespesaPorId(@PathVariable Long id){
        despesaService.deletaDespesaPorId(id);
        return ResponseEntity.status(200).build();
    }


}
