package com.marco.gestao.controller;

import com.marco.gestao.model.Participante;
import com.marco.gestao.service.ParticipanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/participante")
public class ParticipanteController {
    @Autowired
    private ParticipanteService participanteService;

    @GetMapping
    public List<Participante> getAll(){
        return participanteService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Participante> getById(@PathVariable Long id) {
        Optional<Participante> participante = participanteService.getById(id);
        return participante.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Participante create(@RequestBody Participante participante) {
        return participanteService.save(participante);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Participante> update(@PathVariable Long id, @RequestBody Participante param) {
        Optional<Participante> opt = participanteService.getById(id);
        if (opt.isPresent()) {
            Participante participante = opt.get();
            participante.setNome(param.getNome());
            participante.setTime(param.getTime());
            Participante retorno = participanteService.save(participante);
            return ResponseEntity.ok(retorno);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        participanteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
