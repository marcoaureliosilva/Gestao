package com.marco.gestao.controller;

import com.marco.gestao.model.Cliente;
import com.marco.gestao.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import org.springframework.http.HttpStatus;

public class ClientControllerTest {
        @InjectMocks
        private ClienteController clienteController;

        @Mock
        private ClienteService clienteService;

        @BeforeEach
        public void setUp() {
            MockitoAnnotations.openMocks(this);
        }

        @Test
        public void testGetAllClientes() {
            Cliente cliente1 = new Cliente();
            cliente1.setId(1L);
            cliente1.setNome("Cliente 1");

            Cliente cliente2 = new Cliente();
            cliente2.setId(2L);
            cliente2.setNome("Cliente 2");

            List<Cliente> clientes = Arrays.asList(cliente1, cliente2);

            when(clienteService.getAll()).thenReturn(clientes);

            List<Cliente> response = clienteController.getAll();

            assertEquals(2, response.size());
            assertEquals("Cliente 1", response.get(0).getNome());
            assertEquals("Cliente 2", response.get(1).getNome());
        }

        @Test
        public void testGetClienteById() {
            Cliente cliente = new Cliente();
            cliente.setId(1L);
            cliente.setNome("Cliente 1");

            when(clienteService.getById(anyLong())).thenReturn(Optional.of(cliente));

            ResponseEntity<Cliente> response = clienteController.getById(1L);

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals("Cliente 1", response.getBody().getNome());
        }

        @Test
        public void testCreateCliente() {
            Cliente cliente = new Cliente();
            cliente.setId(1L);
            cliente.setNome("Cliente 1");

            when(clienteService.save(cliente)).thenReturn(cliente);

            Cliente response = clienteController.create(cliente);

            assertEquals("Cliente 1", response.getNome());
        }

        @Test
        public void testUpdateCliente() {
            Cliente cliente = new Cliente();
            cliente.setId(1L);
            cliente.setNome("Cliente 1");

            when(clienteService.getById(anyLong())).thenReturn(Optional.of(cliente));
            when(clienteService.save(any(Cliente.class))).thenReturn(cliente);

            ResponseEntity<Cliente> response = clienteController.update(1L, cliente);

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals("Cliente 1", response.getBody().getNome());
        }

        @Test
        public void testDeleteCliente() {
            Cliente cliente = new Cliente();
            cliente.setId(1L);
            cliente.setNome("Cliente 1");

            when(clienteService.getById(anyLong())).thenReturn(Optional.of(cliente));
            doNothing().when(clienteService).delete(1L);

            ResponseEntity<Void> response = clienteController.delete(1L);

            assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
            verify(clienteService, times(1)).delete(1L);
        }
}
