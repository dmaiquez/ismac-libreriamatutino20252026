package com.distribuida.service;

import com.distribuida.dao.FacturaDAO;
import com.distribuida.model.Cliente;
import com.distribuida.model.Factura;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FacturaServiceTestUnitaria {

    @Mock
    private FacturaDAO facturaDAO;

    @InjectMocks
    private FacturaServiceImpl facturaService;

    private Factura factura;

    private Cliente cliente;

    @BeforeEach
    public void setUp(){
        cliente = new Cliente(1,"1701234567","Juan","Taipe","Direccion1","0987654321","jtaipe@correo.com");

        factura = new Factura();
        factura.setIdFactura(1);
        factura.setNumFactura("FAC-0001");
        factura.setFecha(new Date());
        factura.setTotalNeto(100.00);
        factura.setIva(15.00);
        factura.setTotal(115.00);
        factura.setCliente(cliente);

    }

    @Test
    public void findAll(){
        when(facturaDAO.findAll()).thenReturn(List.of(factura));
        List<Factura> facturas = facturaService.findAll();

        assertNotNull(facturas);
        assertEquals(1, facturas.size());
        verify(facturaDAO, times(1)).findAll();

    }

    @Test
    public void findOneExistente(){
        when(facturaDAO.findById(1)).thenReturn(Optional.ofNullable(factura));
        Optional<Factura> factura1 = facturaService.findOne(1);

        assertNotNull(factura1);
        assertEquals("FAC-0001", factura1.orElse(null).getNumFactura());
    }

    @Test
    public void findOneNoExistente(){
        when(facturaDAO.findById(2)).thenReturn(null);
        Optional<Factura> factura = facturaService.findOne(2);
        assertNull(factura);
    }


    @Test
    public void save(){
        when(facturaDAO.save(factura)).thenReturn(factura);
        Factura factura1 = facturaService.save(factura);
        assertNotNull(factura1);
        assertEquals("FAC-0001", factura1.getNumFactura());
    }

    @Test
    public void updateExistente(){
        Factura facturaActualizada = new Factura();
        facturaActualizada.setNumFactura("FAC-0002");
        facturaActualizada.setFecha(new Date());
        facturaActualizada.setTotalNeto(200.00);
        facturaActualizada.setIva(30.00);
        facturaActualizada.setTotal(230.00);
        facturaActualizada.setCliente(cliente);

        when(facturaDAO.findById(1)).thenReturn(Optional.ofNullable(factura));
        when(facturaDAO.save(any())).thenReturn(facturaActualizada);

        Factura facturaResultado = facturaService.update(1, facturaActualizada);
        assertNotNull(facturaResultado);
        assertEquals("FAC-0002", facturaResultado.getNumFactura());
        verify(facturaDAO, times(1)).save(factura);

    }

    @Test
    public void updateNoExistente(){
        Factura facturaNueva = new Factura();

        when(facturaDAO.findById(999)).thenReturn(null);
        Factura resultado = facturaService.update(999, facturaNueva);
        assertNull(resultado);
        verify(facturaDAO, never()).save(any());

    }

    @Test
    public void deleteExistente(){
        when(facturaDAO.existsById(1)).thenReturn(true);
        facturaService.delete(1);
        verify(facturaDAO).deleteById(1);
    }

    @Test
    public void deleteNoExistente(){
        when(facturaDAO.existsById(999)).thenReturn(false);
        facturaService.delete(999);
        verify(facturaDAO,never()).deleteById(anyInt());
    }


}
