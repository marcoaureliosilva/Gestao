package com.marco.gestao.controller;

import com.marco.gestao.model.Atividade;
import com.marco.gestao.service.AtividadeService;
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

public class AtividadeControllerTest {
        @InjectMocks
        private AtividadeController atividadeController;

        @Mock
        private AtividadeService atividadeService;

        @BeforeEach
        public void setUp() {
            MockitoAnnotations.openMocks(this);
        }

        @Test
        public void testGetAllAtividades() {
            Atividade atividade1 = new Atividade();
            atividade1.setId(1L);
            atividade1.setAtividade("Atividade 1");

            Atividade atividade2 = new Atividade();
            atividade2.setId(2L);
            atividade2.setAtividade("Atividade 2");

            List<Atividade> atividades = Arrays.asList(atividade1, atividade2);

            when(atividadeService.getAll()).thenReturn(atividades);

            List<Atividade> response = atividadeController.getAll();

            assertEquals(2, response.size());
            assertEquals("Atividade 1", response.get(0).getAtividade());
            assertEquals("Atividade 2", response.get(1).getAtividade());
        }

        @Test
        public void testGetAtividadeById() {
            Atividade atividade = new Atividade();
            atividade.setId(1L);
            atividade.setAtividade("Atividade 1");

            when(atividadeService.getById(anyLong())).thenReturn(Optional.of(atividade));

            ResponseEntity<Atividade> response = atividadeController.getById(1L);

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals("Atividade 1", response.getBody().getAtividade());
        }

        @Test
        public void testCreateAtividade() {
            Atividade atividade = new Atividade();
            atividade.setId(1L);
            atividade.setAtividade("Atividade 1");

            when(atividadeService.save(atividade)).thenReturn(atividade);

            Atividade response = atividadeController.create(atividade);

            assertEquals("Atividade 1", response.getAtividade());
        }

        @Test
        public void testUpdateAtividade() {
            Atividade atividade = new Atividade();
            atividade.setId(1L);
            atividade.setAtividade("Atividade 1");

            when(atividadeService.getById(anyLong())).thenReturn(Optional.of(atividade));
            when(atividadeService.save(any(Atividade.class))).thenReturn(atividade);

            ResponseEntity<Atividade> response = atividadeController.update(1L, atividade);

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals("Atividade 1", response.getBody().getAtividade());
        }

        @Test
        public void testDeleteAtividade() {
            Atividade atividade = new Atividade();
            atividade.setId(1L);
            atividade.setAtividade("Atividade 1");

            when(atividadeService.getById(anyLong())).thenReturn(Optional.of(atividade));
            doNothing().when(atividadeService).delete(1L);

            ResponseEntity<Void> response = atividadeController.delete(1L);

            assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
            verify(atividadeService, times(1)).delete(1L);
        }
}
