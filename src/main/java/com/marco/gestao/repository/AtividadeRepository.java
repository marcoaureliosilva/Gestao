package com.marco.gestao.repository;

import com.marco.gestao.model.Atividade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AtividadeRepository extends JpaRepository<Atividade,Long> {
    List<Atividade> findByProjetoId(Long projetoId);
}
