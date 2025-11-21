package com.distribuida.dao;

import com.distribuida.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// @Repository esta anotacion es para hacer a la clase que sea bean
public interface ClienteDAO extends JpaRepository<Cliente, Integer> {

        //    Cliente findByNombreAndApellido(int id);

}
