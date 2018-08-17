/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author jose_
 */
public class ModeloLogin {
      private Conexion conexion = new Conexion();
      
      public int ingresar(String usu, String contra)
    {
        String capturar="";
        int control=0;
        ResultSet sql;       
         try {
            Connection con = conexion.abrirConexion();
            Statement s = con.createStatement();
            //En este variable se guarda la consulta y que es de tipo result set
            sql = s.executeQuery("SELECT * FROM empleado WHERE Usuario='" + usu + "' && Contraseña='" + contra + "' ");
            //este se usa para recorrer toda la consulta
            while(sql.next())
            {
                //Lo adquirido de la consukta se pasa a una variable de tipo string llamada captura
                capturar = sql.getString("Tipo");
            }
            //Se compara el tipo de usuario
            if(capturar.equals("Empleado"))
            {
                //Se retorna un 1 si captura es Administrador
                control = 1;
            }
            if(capturar.equals("Administrador"))
            {
                //Se retorna un 2 si captura es Usuario
                control = 2;
            }
            if((!capturar.equals("Administrador")) && (!capturar.equals("Empleado")))
            {
                //Se retorna un 3 si no es nignuno de los dos
                control = 3;
            }
           conexion.cerrarConexion(con);
        }
        catch (SQLException ex)
        {
            //AQUI COMENTE LO DE REGISTRAR
           // Logger.getLogger(modeloRegistrar.class.getName()).log(Level.SEVERE, null, ex);
        }
         catch(NullPointerException e){
             //Arroja un mensaje 
            JOptionPane.showMessageDialog(null, "Error al intentar conectar con el servidor.");
        }
         return control;
    }
}
