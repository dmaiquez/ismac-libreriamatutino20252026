package com.distribuida.principal;

import com.distribuida.model.Cliente;

public class ClientePrincipal {

    public static void main(String[] args){

            ///  ejemplos de model

        Cliente cliente = new Cliente(1, "1701234567","Juan"
                ,"Taipe","av por ahi", "0987654321","jtaipe@correo.com");

    /// / otro ejemplo de otro codigo

        System.out.println(cliente.toString());
    }
}
