package com.marco.gestao.controller;

import com.marco.gestao.model.Atividade;
import com.marco.gestao.service.AtividadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/atividade")
public class AtividadeController {
    @Autowired
    private AtividadeService atividadeService;

    @GetMapping
    public List<Atividade> getAll(){
        return atividadeService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Atividade> getById(@PathVariable Long id) {
        Optional<Atividade> atividade = atividadeService.getById(id);
        return atividade.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Atividade create(@RequestBody Atividade atividade) {
        return atividadeService.save(atividade);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Atividade> update(@PathVariable Long id, @RequestBody Atividade param) {
        Optional<Atividade> opt = atividadeService.getById(id);
        if (opt.isPresent()) {
            Atividade atividade = opt.get();
            atividade.setAtividade(param.getAtividade());
            atividade.setFinalizada(param.getFinalizada());
            atividade.setDescricao(param.getDescricao());
            atividade.setProjeto(param.getProjeto());
            atividade.setTamanho(param.getTamanho());
            atividade.setParticipante(param.getParticipante());
            Atividade retorno = atividadeService.save(atividade);
            return ResponseEntity.ok(retorno);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        atividadeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
