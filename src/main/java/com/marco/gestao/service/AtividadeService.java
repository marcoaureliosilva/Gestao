package com.marco.gestao.service;
import com.marco.gestao.model.Atividade;
import com.marco.gestao.repository.AtividadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AtividadeService {

    @Autowired
    private AtividadeRepository atividadeRepository;

    public List<Atividade> getAll() {
        return atividadeRepository.findAll();
    }

    public Optional<Atividade> getById(Long id) {
        return atividadeRepository.findById(id);
    }

    public Atividade save(Atividade atividade) {
        return atividadeRepository.save(atividade);
    }

    public void delete(Long id) {
        atividadeRepository.deleteById(id);
    }

    public List<Atividade> findByProjetoId(Long projetoId){
        return atividadeRepository.findByProjetoId(projetoId);
    }
}
