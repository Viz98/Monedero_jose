/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controlador.ControladorMonedero;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
public class ModeloMonedero 
{
    private Conexion conexion = new Conexion();
    public boolean insertarCliente(String nombre, String apellidos, String email, String dire, String sexo, String tele)
    {
        try{
            Connection con = conexion.abrirConexion();
            con.setAutoCommit(false);
            Statement s = con.createStatement();
            int resultado = s.executeUpdate("insert into cliente(Nombre, Apellidos, Puntos, Email, Direccion, Sexo, Telefono) values('"
            + nombre + "', '" + apellidos + "', '" + 0 + "', '" + email + "', '" + dire + "', '" + sexo + "', '" + tele + "');");
            con.commit();
            conexion.cerrarConexion(con);
            return true;
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
            return false;
        }
    }
    public boolean actualizarCliente(String nombre, String apellidos, String email, String dire, String sexo, String tele, int idCliente)
    {
        try{
            Connection con = conexion.abrirConexion();
            Statement s = con.createStatement();
            int resultado = s.executeUpdate("update cliente set Nombre = '" + nombre + "', Apellidos = '" + apellidos + "', Puntos = '" + 0 + "', Email = '" + email + "', Direccion = '" + dire + "', Telefono = '" + tele + "' where idCliente = '" + idCliente + "';");
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
            int r = s.executeUpdate("insert into premios(Nombre, Puntos) values('" + Nombre + "', '" + Puntos + "');");
            conexion.cerrarConexion(con);
            return true;
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
            return false;
        }
    }
    public DefaultTableModel mostrarPremios(String idSu)
    {
        try
        {
            Connection con = conexion.abrirConexion();
            Statement s = con.createStatement();
            DefaultTableModel modelo;
            try
            {
                ResultSet rs = s.executeQuery("SELECT premios.idPremios, premios.Nombre, premios.Puntos, "
                        + "sucursal.Nombre as 'Sucursal' FROM premios "
                        + "INNER JOIN inventario ON inventario.Premios_idPremios = premios.idPremios "
                        + "INNER JOIN sucursal on sucursal.idSucursal = inventario.Sucursal_idSucursal "
                        + "WHERE sucursal.idSucursal = '" + idSu + "';");
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
            }
            finally
            {
                conexion.cerrarConexion(con);
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
            return null;
        }
    }
    public DefaultTableModel mostrarPremios2(String idSu)
    {
        try
        {
            Connection con = conexion.abrirConexion();
            Statement s = con.createStatement();
            DefaultTableModel modelo;
            try
            {
                ResultSet rs = s.executeQuery("SELECT inventario.idInventario, premios.Nombre, premios.Puntos, "
                        + "sucursal.Nombre as 'Sucursal' FROM premios "
                        + "INNER JOIN inventario ON inventario.Premios_idPremios = premios.idPremios "
                        + "INNER JOIN sucursal on sucursal.idSucursal = inventario.Sucursal_idSucursal "
                        + "WHERE sucursal.idSucursal = '" + idSu + "';");
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
            }
            finally
            {
                conexion.cerrarConexion(con);
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
            return null;
        }
    }
    public DefaultTableModel mostrarPremiosGeneral()
    {
        try
        {
            Connection con = conexion.abrirConexion();
            Statement s = con.createStatement();
            DefaultTableModel modelo;
            try
            {
                 ResultSet rs = s.executeQuery("select idPremios, Nombre, Puntos from premios");
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
            }
            finally
            {
                conexion.cerrarConexion(con);
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
            return null;
        }
    }
    public boolean borrarPremio(String idPremio)
    {
        try
        {
            Connection con = conexion.abrirConexion();
            Statement s = con.createStatement();
            int resultado = s.executeUpdate("delete from premios where idPremios = '" + idPremio + "';");
            conexion.cerrarConexion(con);
            return true;
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
            return false;
        }
    }
    public boolean actualizarPremio(String Nombre, int Puntos, int idP)
    {
        try{
            Connection con = conexion.abrirConexion();
            Statement s = con.createStatement();
            int resultado = s.executeUpdate("update premios set Nombre = '" + Nombre + "', Puntos = '" + Puntos + "' where idPremios = '" + idP + "';");
            conexion.cerrarConexion(con);
            return true;
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
            return false;
        }
    }
    public boolean agregarInventarioPremio(int idP, String idS)
    {
        try
        {
            Connection con = conexion.abrirConexion();
            Statement s = con.createStatement();
            int resultado = s.executeUpdate("insert into inventario(Premios_idPremios, Sucursal_idSucursal) values('"
            + idP + "', '" + idS + "');");
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
//    public boolean EnviarPuntosCliente(int puntos, String idC){
//        try{
//            Connection con = conexion.abrirConexion();
//            Statement s = con.createStatement();
//            int resultado = s.executeUpdate("UPDATE cliente SET Puntos = '" + puntos +"' WHERE cliente.idCliente = '" + idC + "';");
//            
//            conexion.cerrarConexion(con);
//            return true;
//        }
//        catch(SQLException e)
//        {
//            //System.out.println(e.getMessage());
//            JOptionPane.showMessageDialog(null, "Ya se uso este ticket.");
//            return false;
//        }
//    }
//    public boolean EnviarPuntosClientepordemientras(int puntos, String idC){
//        try{
//            Connection con = conexion.abrirConexion();
//            Statement s = con.createStatement();
//            int resultado = s.executeUpdate("UPDATE cliente SET Puntos = '" + puntos +"' WHERE cliente.idCliente = '" + idC + "';");
//            
//            conexion.cerrarConexion(con);
//            return true;
//        }
//        catch(SQLException e)
//        {
//            //System.out.println(e.getMessage());
//            JOptionPane.showMessageDialog(null, "Ya se uso este ticket.");
//            return false;
//        }
//    }
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
    public DefaultTableModel cargos(String idC){
         try
         {
            //Abrir la conexión
            Connection con = conexion.abrirConexion();
            //Para generar la consulta
            Statement s = con.createStatement();
            //Para establecer el modelo al JTable
            DefaultTableModel modelo=null;
            
            try{
                //Ejecuta la consulta
                ResultSet rs = s.executeQuery("SELECT `Fecha`, cargo.Empleado_idEmpleado as 'ID Empleado', empleado.Nombre as 'Nombre Empleado', "
                        + "empleado.Apellidos as 'Apellido Empleado', premios.Nombre as 'Premio', premios.Puntos as 'Puntos', "
                        + "sucursal.Nombre as 'Sucursal' FROM cargo " +
                        "INNER JOIN empleado ON empleado.idEmpleado = cargo.Empleado_idEmpleado " +
                        "INNER JOIN inventario ON inventario.idInventario = cargo.Inventario_idInventario " +
                        "INNER JOIN premios ON premios.idPremios = inventario.Premios_idPremios " +
                        "INNER JOIN sucursal ON sucursal.idSucursal = inventario.Sucursal_idSucursal " +
                        "WHERE cargo.Cliente_idCliente = " + idC);
                //Para establecer el modelo al JTable
                modelo = new DefaultTableModel();
                //Obtengo la información de las consultas que se están consultando
                ResultSetMetaData rsMd = rs.getMetaData();
                //La cantidad de columnas que tiene la consulta
                int cantidadColumnas = rsMd.getColumnCount();
                //Establecer como cabeceras el nombre de las columnas
                for(int i = 1; i <= cantidadColumnas; i++){
                    modelo.addColumn(rsMd.getColumnLabel(i));
                }
                //Creando las filas para el JTable
                while(rs.next()){
                    Object[] fila = new Object[cantidadColumnas];
                    for(int i = 0; i < cantidadColumnas; i++){
                        fila[i] = rs.getObject(i+1);
                    }
                    modelo.addRow(fila);
                }
                return modelo;
            }finally{//Cuando se usa excepciones; siempre se ejecuta no importa que haya error o no
                //Cerrar conexión (objeto de resultSet)
                conexion.cerrarConexion(con);
            }
        }catch(SQLException e)
        {
             return null;
        }
    }
    public boolean agregarCargo(String Fecha, String idC, String idE, String idI)
     {
         try
         {
             Connection con = conexion.abrirConexion();
             Statement s = con.createStatement();
             int resultado = s.executeUpdate("INSERT INTO cargo (Fecha, Cliente_idCliente, Empleado_idEmpleado, Inventario_idInventario) VALUES ('" + Fecha + "', '" + idC + "', '" + idE + "', '" + idI + "');");
             return true;
         }
         catch(SQLException e)
         {
             System.out.println(e.getMessage());
             return false;
         }
     }
    public boolean agregarCargopordemientras(String Fecha, String idC, String idE, String idI, int puntos, String idC2)
     {
        PreparedStatement updateTransaccion1 = null;
        PreparedStatement updateTransaccion2 = null;
        
        String agregarcargo = "INSERT INTO cargo (Fecha, Cliente_idCliente, Empleado_idEmpleado, Inventario_idInventario) VALUES ('" + Fecha + "', '" + idC + "', '" + idE + "', '" + idI + "');";
        String enviarpuntos = "UPDATE cliente SET Puntos = '" + puntos +"' WHERE cliente.idCliente = '" + idC2 + "';";
        Connection con = null;
        try
         {
            con = conexion.abrirConexion();
            con.setAutoCommit(false);
            updateTransaccion1 = con.prepareStatement(agregarcargo);
            updateTransaccion2 = con.prepareStatement(enviarpuntos);
            int r1=updateTransaccion1.executeUpdate(); 
            int r2=updateTransaccion2.executeUpdate(); 
            if(r1 == 0 || r2 ==0)
                throw new SQLException("La cuenta no existe");
            
            con.commit();
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
