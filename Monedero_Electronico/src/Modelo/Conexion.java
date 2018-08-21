/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import Vista.elegirsucursal;
import controlador.controlelegirsucursal;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

//tienes que añadir la libreria MySQL jdbc driver

/**
 *
 * @author office depot
 */
public class Conexion 
{
//    public Connection abrirConexion() throws SQLException{
//        
//        Connection con;
//        //para conectarnos a nuestra base de datos
//        
//        try{
//            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
//            con = DriverManager.getConnection(
//            "jdbc:mysql://localhost:3306/monedero","root","");//si fuera host no local ps pones la ip, despues de los : es el puerto
//        }
//        catch(SQLException e){
//            System.out.println("No se pudo abrir la conexión");
//            con = null;
//        }
//        return con;
//    }
//    public void cerrarConexion (Connection c) throws SQLException{
//        try{
//            if(!c.isClosed()){
//                c.close();
//            }
//        }
//        catch(SQLException e){
//            System.out.println("Error al cerrar la conexion: tal vez ya esta cerrada o algo, o no ha sido creado tal vez");
//        }
//    }
    public JFrame vista;
    public Connection abrirConexion() throws SQLException{
        Connection con;
        //Para conectarnos a nuestrza base de datos
        try{
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            con = DriverManager.getConnection("jdbc:mysql://"+controlelegirsucursal.ip+"/monedero", "root", "");
           // con = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca", "root", "");
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "No se pudo abrir la conexión con la BD.");
            if (JOptionPane.showConfirmDialog(vista,"¿Desea cambiar de servidor?", "Libreria",
            JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
            {
               elegirsucursal vistasu = new elegirsucursal();
               controlelegirsucursal cs = new controlelegirsucursal(vistasu,1);
               cs.iniciarVista();
            }
            con=null;
        }
        return con;
    }
            
    public void cerrarConexion(Connection c)
            throws SQLException{
        try{
            if(!c.isClosed()){
                c.close();
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al cerrar la conexión de la BD.");
        }
    }
}
