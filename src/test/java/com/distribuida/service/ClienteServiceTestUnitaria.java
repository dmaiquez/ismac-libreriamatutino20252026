package com.distribuida.service;

import com.distribuida.dao.ClienteDAO;
import com.distribuida.model.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTestUnitaria {

    @Mock
    private ClienteDAO clienteDAO;

    @InjectMocks
    private ClienteServiceImpl clienteService;

    private Cliente cliente;

    @BeforeEach
    public void setUp(){
        cliente = new Cliente();
        cliente.setIdCliente(1);
        cliente.setCedula("171234567");
        cliente.setNombre("Juan");
        cliente.setApellido("Taipe");
        cliente.setDireccion("Av. por ahi y mas alla.");
        cliente.setTelefono("0987654321");
        cliente.setCorreo("jtaipe@correo.com");
    }

    @Test
    public void findAll(){
        when(clienteDAO.findAll()).thenReturn(List.of(cliente));
        List<Cliente> clientes = clienteService.findAll();

        assertNotNull(clientes);
        assertEquals(1, clientes.size());
        verify(clienteDAO, times(1)).findAll();
    }

    @Test
    public void testFindOneExistente(){
        when(clienteDAO.findById(1)).thenReturn(Optional.ofNullable(cliente));
        Optional<Cliente> cliente = clienteService.findOne(1);

        assertNotNull(cliente);
        assertEquals("Juan", cliente.orElse(null).getNombre());
    }

    @Test
    public void testFindOneNoExitente(){
        when(clienteDAO.findById(2)).thenReturn(null);
        Optional<Cliente> cliente = clienteService.findOne(2);
        assertNull(cliente);
    }

    @Test
    public void testSave(){
        when(clienteDAO.save(cliente)).thenReturn(cliente);
        Cliente clienteGuardado = clienteService.save(cliente);
        assertNotNull(clienteGuardado);
        assertEquals("Juan", clienteGuardado.getNombre());
    }

    @Test
    public void testUpdateExistente(){
        Cliente  clienteActualizado = new Cliente();
        clienteActualizado.setCedula("17012345666");
        clienteActualizado.setNombre("Juan66");
        clienteActualizado.setApellido("Taipe66");
        clienteActualizado.setDireccion("Direccion66");
        clienteActualizado.setTelefono("0987654666");
        clienteActualizado.setCorreo("jtaipe66@correo.com");

        when(clienteDAO.findById(1)).thenReturn(Optional.ofNullable(cliente));
        when(clienteDAO.save(any())).thenReturn(clienteActualizado);

        Cliente clienteResultado = clienteService.update(1, clienteActualizado);

        assertNotNull(clienteResultado);
        assertEquals("Juan66", clienteResultado.getNombre());
        verify(clienteDAO, times(1)).save(cliente);
    }

    @Test
    public void testUpdateNoExistente(){
        Cliente clienteNuevo = new Cliente();
        when(clienteDAO.findById(999)).thenReturn(null);
        Cliente resultado = clienteService.update(999, clienteNuevo);

        assertNull(resultado);
        verify(clienteDAO, never()).save(any());

    }

    @Test
    public void testDeleteExistente(){
        when(clienteDAO.existsById(1)).thenReturn(true);
        clienteService.delete(1);
        verify(clienteDAO).deleteById(1);
    }

    @Test
    public void testDeleteNoExistente(){
        when(clienteDAO.existsById(999)).thenReturn(false);
        clienteService.delete(999);
        verify(clienteDAO, never()).deleteById(anyInt());
    }

}
