package com.marco.gestao.controller;

import com.marco.gestao.model.Status;
import com.marco.gestao.service.StatusService;
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

public class StatusControllerTest {
        @InjectMocks
        private StatusController statusController;

        @Mock
        private StatusService statusService;

        @BeforeEach
        public void setUp() {
            MockitoAnnotations.openMocks(this);
        }

        @Test
        public void testGetAllStatus() {
            Status status1 = new Status();
            status1.setId(1L);
            status1.setStatus("Em Andamento");

            Status status2 = new Status();
            status2.setId(2L);
            status2.setStatus("Cancelado");

            List<Status> statuss = Arrays.asList(status1, status2);

            when(statusService.getAll()).thenReturn(statuss);

            List<Status> response = statusController.getAll();

            assertEquals(2, response.size());
            assertEquals("Em Andamento", response.get(0).getStatus());
            assertEquals("Cancelado", response.get(1).getStatus());
        }

        @Test
        public void testGetStatusById() {
            Status status = new Status();
            status.setId(1L);
            status.setStatus("Em Andamento");

            when(statusService.getById(anyLong())).thenReturn(Optional.of(status));

            ResponseEntity<Status> response = statusController.getById(1L);

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals("Em Andamento", response.getBody().getStatus());
        }

        @Test
        public void testCreateStatus() {
            Status status = new Status();
            status.setId(1L);
            status.setStatus("Em Andamento");

            when(statusService.save(status)).thenReturn(status);

            Status response = statusController.create(status);

            assertEquals("Em Andamento", response.getStatus());
        }

        @Test
        public void testUpdateStatus() {
            Status status = new Status();
            status.setId(1L);
            status.setStatus("Em Andamento");

            when(statusService.getById(anyLong())).thenReturn(Optional.of(status));
            when(statusService.save(any(Status.class))).thenReturn(status);

            ResponseEntity<Status> response = statusController.update(1L, status);

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals("Em Andamento", response.getBody().getStatus());
        }

        @Test
        public void testDeleteStatus() {
            Status status = new Status();
            status.setId(1L);
            status.setStatus("Em Andamento");

            when(statusService.getById(anyLong())).thenReturn(Optional.of(status));
            doNothing().when(statusService).delete(1L);

            ResponseEntity<Void> response = statusController.delete(1L);

            assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
            verify(statusService, times(1)).delete(1L);
        }
}
