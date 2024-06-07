package com.marco.gestao.controller;

import com.marco.gestao.model.Time;
import com.marco.gestao.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/time")
public class TimeController {
    @Autowired
    private TimeService timeService;

    @GetMapping
    public List<Time> getAll(){
        return timeService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Time> getById(@PathVariable Long id) {
        Optional<Time> time = timeService.getById(id);
        return time.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Time create(@RequestBody Time time) {
        return timeService.save(time);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Time> update(@PathVariable Long id, @RequestBody Time param) {
        Optional<Time> opt = timeService.getById(id);
        if (opt.isPresent()) {
            Time time = opt.get();
            time.setNome(param.getNome());
            Time retorno = timeService.save(time);
            return ResponseEntity.ok(retorno);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        timeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
