/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author jose_
 */
public class ModeloMonedero {
    private Conexion conexion = new Conexion();
    
    public boolean insertarCliente(String nombre, String apellidos, int puntos, String email, String dire, String sexo, String tele)
    {
        try{
            Connection con = conexion.abrirConexion();
            Statement s = con.createStatement();
            int resultado = s.executeUpdate("insert into cliente(idCliente, Nombre, Apellidos, Puntos, Email, Direccion, Sexo, Telefono) values('"
            + nombre + "', '" + apellidos + "', '" + puntos + "', '" + email + "', '" + dire + "', '" + sexo + "', '" + tele + "');");
            conexion.cerrarConexion(con);
            return true;
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
