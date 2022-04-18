package br.com.orcamento.controle.controller;

import br.com.orcamento.controle.controller.dto.ReceitaDTO;
import br.com.orcamento.controle.controller.form.ReceitaForm;
import br.com.orcamento.controle.service.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

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
}
