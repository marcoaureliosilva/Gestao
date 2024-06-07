package com.marco.gestao.controller;

import com.marco.gestao.model.Atividade;
import com.marco.gestao.model.Projeto;
import com.marco.gestao.service.AtividadeService;
import com.marco.gestao.service.ProjetoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class ProjetoControllerTest {
    @InjectMocks
    private ProjetoController projetoController;

    @Mock
    private ProjetoService projetoService;

    @Mock
    private AtividadeService atividadeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllOpenProjects() {
        // Mock data
        Projeto projeto1 = new Projeto();
        projeto1.setId(1L);
        projeto1.setTitulo("Projeto 1");

        Projeto projeto2 = new Projeto();
        projeto2.setId(2L);
        projeto2.setTitulo("Projeto 2");

        List<Projeto> projetos = Arrays.asList(projeto1, projeto2);

        when(projetoService.findAllOpenProjects(anyLong())).thenReturn(projetos);

        ResponseEntity<List<Projeto>> response = projetoController.getAllOpenProjects();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
        assertEquals("Projeto 1", response.getBody().get(0).getTitulo());
        assertEquals("Projeto 2", response.getBody().get(1).getTitulo());
    }

    @Test
    public void testGetAtividadesByProjeto() {
        // Mock data
        Atividade atividade1 = new Atividade();
        atividade1.setId(1L);
        atividade1.setAtividade("Atividade 1");

        Atividade atividade2 = new Atividade();
        atividade2.setId(2L);
        atividade2.setAtividade("Atividade 2");

        List<Atividade> atividades = Arrays.asList(atividade1, atividade2);

        when(atividadeService.findByProjetoId(anyLong())).thenReturn(atividades);

        ResponseEntity<List<Atividade>> response = projetoController.getAtividadesByProjeto(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
        assertEquals("Atividade 1", response.getBody().get(0).getAtividade());
        assertEquals("Atividade 2", response.getBody().get(1).getAtividade());
    }

    @Test
    public void testGetAllProjetos() {
        Projeto projeto = new Projeto();
        projeto.setId(1L);
        projeto.setTitulo("Projeto 1");

        Projeto projeto2 = new Projeto();
        projeto2.setId(2L);
        projeto2.setTitulo("Projeto 2");

        List<Projeto> projetos = Arrays.asList(projeto, projeto2);

        when(projetoService.getAll()).thenReturn(projetos);

        List<Projeto> response = projetoController.getAll();

        assertEquals(2, response.size());
        assertEquals("Projeto 1", response.get(0).getTitulo());
        assertEquals("Projeto 2", response.get(1).getTitulo());
    }

    @Test
    public void testGetProjetoById() {
        Projeto projeto = new Projeto();
        projeto.setId(1L);
        projeto.setTitulo("Projeto 1");

        when(projetoService.getById(anyLong())).thenReturn(Optional.of(projeto));

        ResponseEntity<Projeto> response = projetoController.getById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Projeto 1", response.getBody().getTitulo());
    }

    @Test
    public void testCreateProjeto() {
        Projeto projeto = new Projeto();
        projeto.setId(1L);
        projeto.setTitulo("Projeto 1");

        when(projetoService.save(projeto)).thenReturn(projeto);

        Projeto response = projetoController.create(projeto);

        assertEquals("Projeto 1", response.getTitulo());
    }

    @Test
    public void testUpdateProjeto() {
        Projeto projeto = new Projeto();
        projeto.setId(1L);
        projeto.setTitulo("Projeto 1");

        when(projetoService.getById(anyLong())).thenReturn(Optional.of(projeto));
        when(projetoService.save(any(Projeto.class))).thenReturn(projeto);

        ResponseEntity<Projeto> response = projetoController.update(1L, projeto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Projeto 1", response.getBody().getTitulo());
    }

    @Test
    public void testDeleteProjeto() {
        Projeto projeto = new Projeto();
        projeto.setId(1L);
        projeto.setTitulo("Projeto 1");

        when(projetoService.getById(anyLong())).thenReturn(Optional.of(projeto));
        doNothing().when(projetoService).delete(1L);

        ResponseEntity<Void> response = projetoController.delete(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(projetoService, times(1)).delete(1L);
    }
}
