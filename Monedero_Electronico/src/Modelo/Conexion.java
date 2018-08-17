/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import java.sql.*;

//tienes que añadir la libreria MySQL jdbc driver

/**
 *
 * @author office depot
 */
public class Conexion {
    public Connection abrirConexion() throws SQLException{
        
        Connection con;
        //para conectarnos a nuestra base de datos
        
        try{
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            con = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/monedero","root","");//si fuera host no local ps pones la ip, despues de los : es el puerto
        }
        catch(SQLException e){
            System.out.println("No se pudo abrir la conexión");
            con = null;
        }
        return con;
    }
    public void cerrarConexion (Connection c) throws SQLException{
        try{
            if(!c.isClosed()){
                c.close();
            }
        }
        catch(SQLException e){
            System.out.println("Error al cerrar la conexion: tal vez ya esta cerrada o algo, o no ha sido creado tal vez");
        }
    }
}
