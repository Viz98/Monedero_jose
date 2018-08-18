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
import javax.swing.JOptionPane;
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
    public boolean insertarAbono(String fecha, int punto, Double importe, String idT, String idC, String idE){
        try{
            Connection con = conexion.abrirConexion();
            Statement s = con.createStatement();
            int resultado = s.executeUpdate("INSERT INTO abono (Fecha, Punto, Importe, idTicket, Cliente_idCliente, Empleado_idEmpleado) VALUES('"
                     + fecha + "', '" + punto + "', '" + importe + "', '" + idT + "', '" + idC + "', '" + idE + "');");
            
            conexion.cerrarConexion(con);
            return true;
        }
        catch(SQLException e)
        {
            //System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Ya se uso este ticket.");
            return false;
        }
    }
    public boolean EnviarPuntosCliente(int puntos, String idC){
        try{
            Connection con = conexion.abrirConexion();
            Statement s = con.createStatement();
            int resultado = s.executeUpdate("UPDATE cliente SET Puntos = '" + puntos +"' WHERE cliente.idCliente = '" + idC + "';");
            
            conexion.cerrarConexion(con);
            return true;
        }
        catch(SQLException e)
        {
            //System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Ya se uso este ticket.");
            return false;
        }
    }
    public String ObtenerPuntosClientes(String idC)
    {
        ResultSet sql;       
         try {
            Connection con = conexion.abrirConexion();
            Statement s = con.createStatement();
            sql = s.executeQuery("SELECT cliente.Puntos FROM cliente WHERE cliente.idCliente = " + idC);
            //sql = s.executeQuery("SELECT empleado.nombre, empleado.apellidos, login.empleado_idEmpleado, empleado.sucursal_idSucursal FROM login INNER JOIN empleado ON empleado.idEmpleado = login.empleado_idEmpleado WHERE usuario='" + usu + "' && contraseña='" + contra + "' ");
            String a="";
            int i=0;
            while(sql.next())
            {
                //Aqui se guarda el resultado de la consulta en un array
                a= sql.getString(1);
            }
           conexion.cerrarConexion(con);
           return a;
        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al intentar abrir la base de datos.");
            return null;
        }
         catch(NullPointerException e){
//            JOptionPane.showMessageDialog(null, "Error al intentar conectar con el servidor.");
            return null;
        }
    }
     public DefaultTableModel MostrarHistorialAbono(String idC)
    {
        try
        {
            Connection con = conexion.abrirConexion();
            Statement s = con.createStatement();
            DefaultTableModel modelo;
            try
            {
                 ResultSet rs = s.executeQuery("SELECT abono.Fecha, abono.idTicket, abono.Importe ,abono.Punto "
                         + "FROM abono INNER JOIN "
                         + "cliente ON cliente.idCliente = abono.Cliente_idCliente "
                         + "WHERE cliente.idCliente = " + idC);
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
}
