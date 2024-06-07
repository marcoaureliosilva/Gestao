package com.marco.gestao.repository;

import com.marco.gestao.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto,Long> {
    @Query("SELECT p FROM Projeto p WHERE p.status.id = :statusId")
    List<Projeto> findAllOpenProjects(Long statusId);
}
