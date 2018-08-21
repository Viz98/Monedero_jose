/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Modelo.ModeloLogin;
import Vista.elegirsucursal;
import Vista.login;
import Controlador.ControlLogin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
/**
 *
 * @author alfredo
 */
public class controlelegirsucursal implements ActionListener
{
    private elegirsucursal vista;
    public static String guardar="";
    public static String ip="";
    public int bandera = 1;
    public static String getGuardar() {
        return guardar;
    }

    public static void setGuardar(String guardar) {
        controlelegirsucursal.guardar = guardar;
    }

    public String getIp() {
        return ip;
    }

    public static void setIp(String ip) {
        controlelegirsucursal.ip = ip;
    }
    
    public controlelegirsucursal(elegirsucursal vista, int bandera)
    {
        this.vista = vista;
        this.bandera= bandera; 
        this.vista.combo.addActionListener(this);
        this.vista.botonaceptar.addActionListener(this);
    }
    
    public controlelegirsucursal()
    {
        this.guardar = "";
        this.ip = "";
    }
    
    public void iniciarVista()
    {
        this.vista.setLocationRelativeTo(null);
        this.vista.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String valor1 = "SUR";
        String valor2 = "NORTE";
        String valor3 = "CENTRO";
        if(vista.botonaceptar == e.getSource())
        {
            guardar = vista.combo.getSelectedItem().toString();
            if(guardar == valor1)
            {
                //ip = "192.168.63.12";
                //ip = "192.168.43.65";
                ip = "localhost";
            }
            else if(guardar == valor2)
            {
               // ip = "192.168.1.71";
                ip = "localhost";
               // ip = "192.168.43.12";
            }
            else if(guardar == valor3)
            {
                //ip = "192.168.43.141";
                ip = "localhost";
            }
            if (bandera != 1)
            {  
                vista.setVisible(false);
                login lo = new login();
                ModeloLogin mode = new ModeloLogin();
                ControlLogin c = new ControlLogin(lo,mode,ip);
                c.iniciarVista();
            }  
            else 
                vista.setVisible(false);
        }
    }
}
