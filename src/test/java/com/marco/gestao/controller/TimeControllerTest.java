package com.marco.gestao.controller;

import com.marco.gestao.model.Time;
import com.marco.gestao.service.TimeService;
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

public class TimeControllerTest {
        @InjectMocks
        private TimeController timeController;

        @Mock
        private TimeService timeService;

        @BeforeEach
        public void setUp() {
            MockitoAnnotations.openMocks(this);
        }

        @Test
        public void testGetAllTime() {
            Time time = new Time();
            time.setId(1L);
            time.setNome("TI");

            Time time2 = new Time();
            time2.setId(2L);
            time2.setNome("RH");

            List<Time> times = Arrays.asList(time, time2);

            when(timeService.getAll()).thenReturn(times);

            List<Time> response = timeController.getAll();

            assertEquals(2, response.size());
            assertEquals("TI", response.get(0).getNome());
            assertEquals("RH", response.get(1).getNome());
        }

        @Test
        public void testGetTimeById() {
            Time time = new Time();
            time.setId(1L);
            time.setNome("TI");

            when(timeService.getById(anyLong())).thenReturn(Optional.of(time));

            ResponseEntity<Time> response = timeController.getById(1L);

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals("TI", response.getBody().getNome());
        }

        @Test
        public void testCreateTime() {
            Time time = new Time();
            time.setId(1L);
            time.setNome("TI");

            when(timeService.save(time)).thenReturn(time);

            Time response = timeController.create(time);

            assertEquals("TI", response.getNome());
        }

        @Test
        public void testUpdateTime() {
            Time time = new Time();
            time.setId(1L);
            time.setNome("TI");

            when(timeService.getById(anyLong())).thenReturn(Optional.of(time));
            when(timeService.save(any(Time.class))).thenReturn(time);

            ResponseEntity<Time> response = timeController.update(1L, time);

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals("TI", response.getBody().getNome());
        }

        @Test
        public void testDeleteTime() {
            Time time = new Time();
            time.setId(1L);
            time.setNome("TI");

            when(timeService.getById(anyLong())).thenReturn(Optional.of(time));
            doNothing().when(timeService).delete(1L);

            ResponseEntity<Void> response = timeController.delete(1L);

            assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
            verify(timeService, times(1)).delete(1L);
        }
}
