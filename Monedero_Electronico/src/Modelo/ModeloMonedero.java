/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

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
            int resultado = s.executeUpdate("insert into cliente(Nombre, Apellidos, Puntos, Email, Direccion, Sexo, Telefono) values('"
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
    public boolean actualizarCliente(String nombre, String apellidos, int puntos, String email, String dire, String sexo, String tele)
    {
        try{
            Connection con = conexion.abrirConexion();
            Statement s = con.createStatement();
            int resultado = s.executeUpdate("update cliente set Nombre = '" + nombre + "', Apellidos = '" + apellidos + "', Puntos = '" + puntos + "', Email = '" + email + "', Direccion = '" + dire + "', Telefono = '" + tele + "';");
            conexion.cerrarConexion(con);
            return true;
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
            return false;
        }
    }
    public boolean borrarCliente(String idCliente)
    {
        try
        {
            Connection con = conexion.abrirConexion();
            Statement s = con.createStatement();
            int resultado = s.executeUpdate("delete from cliente where idCliente= '" + idCliente + "';");
            conexion.cerrarConexion(con);
            return true;
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
            return false;
        }
    }
    public DefaultTableModel mostrarClientes()
    {
        try
        {
            Connection con = conexion.abrirConexion();
            Statement s = con.createStatement();
            DefaultTableModel modelo;
            try
            {
                 ResultSet rs = s.executeQuery(
                    "select idCliente as 'Id',"
                    + "Nombre as 'Nombre',"
                    + "Apellidos as 'Apellidos',"
                    + "Puntos as 'Puntos',"
                    + "Email as 'Email',"
                    + "Direccion as 'Direccion',"
                    + "Sexo as 'Sexo',"
                    + "Telefono as 'Telefono'"
                    + "from cliente");
                modelo = new DefaultTableModel();
                ResultSetMetaData rsMd = rs.getMetaData();
                int cantidadColumnas = rsMd.getColumnCount();
                for(int i=1;i <=cantidadColumnas;i++)
                {
                    modelo.addColumn(rsMd.getColumnLabel(i));
                }
                while(rs.next()){
                Object[] fila = new Object[cantidadColumnas];
                for(int i = 0; i<cantidadColumnas; i++)
                {
                    fila[i]=rs.getObject(i+1);
                }
                    modelo.addRow(fila);
                }
                return modelo;
            }finally{
                conexion.cerrarConexion(con);
            }
        }
            catch(SQLException e){
                    System.out.println(e.getMessage());
                    return null;
            }
    }
    public boolean insertarPremios(String Nombre, int Puntos)
    {
        try
        {
            Connection con = conexion.abrirConexion();
            Statement s = con.createStatement();
            int r = s.executeUpdate("insert into premios(Nombre, Puntos) values('" + Nombre + "', '" + Puntos + "';");
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
