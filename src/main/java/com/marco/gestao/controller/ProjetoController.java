package com.marco.gestao.controller;

import com.marco.gestao.model.Projeto;
import com.marco.gestao.model.Atividade;
import com.marco.gestao.service.AtividadeService;
import com.marco.gestao.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/projeto")
public class ProjetoController {
    @Autowired
    private ProjetoService projetoService;

    @Autowired
    private AtividadeService atividadeService;

    @GetMapping
    public List<Projeto> getAll(){
        return projetoService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Projeto> getById(@PathVariable Long id) {
        Optional<Projeto> projeto = projetoService.getById(id);
        return projeto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Projeto create(@RequestBody Projeto projeto) {
        return projetoService.save(projeto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Projeto> update(@PathVariable Long id, @RequestBody Projeto param) {
        Optional<Projeto> opt = projetoService.getById(id);
        if (opt.isPresent()) {
            Projeto projeto = opt.get();
            projeto.setDescricao(param.getDescricao());
            projeto.setTitulo(param.getTitulo());
            projeto.setCliente(param.getCliente());
            projeto.setStatus(param.getStatus());
            Projeto retorno = projetoService.save(projeto);
            return ResponseEntity.ok(retorno);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        projetoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/em-aberto")
    public ResponseEntity<List<Projeto>> getAllOpenProjects() {
        Long statusId = 1L;
        List<Projeto> projetos = projetoService.findAllOpenProjects(statusId);
        return ResponseEntity.ok(projetos);
    }

    @GetMapping("/{projetoId}/atividades")
    public ResponseEntity<List<Atividade>> getAtividadesByProjeto(@PathVariable Long projetoId) {
        List<Atividade> atividades = atividadeService.findByProjetoId(projetoId);
        return ResponseEntity.ok(atividades);
    }

}
