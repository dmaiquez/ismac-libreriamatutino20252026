package com.distribuida.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClienteTest {

    private Cliente cliente;

    @BeforeEach
    public void setup(){
        cliente = new Cliente(1
                ,"1701234567"
                ,"Juan"
                ,"Taipe"
                ,"Av. por ahi y mas alla"
                , "0987654321"
                ,"jtaipe@correo.com");
    }

    @Test
    public void testClienteConstructor(){

        assertAll("Validar datos del Cliente con constructor",
                () -> assertEquals(1, cliente.getIdCliente()),
                () -> assertEquals("1701234567",cliente.getCedula()),
                () -> assertEquals("Juan2", cliente.getNombre()),
                () -> assertEquals("Taipe", cliente.getApellido()),
                () -> assertEquals("Av. por ahi y mas alla", cliente.getDireccion()),
                () -> assertEquals("0987654321",cliente.getTelefono()),
                () -> assertEquals("jtaipe@correo.com", cliente.getCorreo())
                );
    }


    @Test
    public void testClienteSetters(){

        cliente.setIdCliente(2);
        cliente.setCedula("1701234562");
        cliente.setNombre("Juan2");
        cliente.setApellido("Taipe2");
        cliente.setDireccion("Direccion2");
        cliente.setTelefono("0987654322");
        cliente.setCorreo("jtaipe2@correo.com");

        assertAll("Validar datos del cliente con setters",
                () -> assertEquals(2, cliente.getIdCliente()),
                () -> assertEquals("1701234562", cliente.getCedula()),
                () -> assertEquals("Juan2",cliente.getNombre()),
                () -> assertEquals("Taipe2", cliente.getApellido()),
                () -> assertEquals("Direccion2", cliente.getDireccion()),
                () -> assertEquals("0987654322", cliente.getTelefono()),
                () -> assertEquals("jtaipe2@correo.com", cliente.getCorreo())
                );

    }


}
