/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModeloLogin;
import Modelo.ModeloMonedero;
import Vista.Desktop_Monedero;
import Vista.IF_Abono;
import Vista.IF_Cargo;
import Vista.IF_Clientes;
import Vista.IF_HistorialCliente;
import Vista.IF_Premios;
import Vista.login;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author jose_
 */
public class ControlLogin implements ActionListener
{
    private login vista;
    private ModeloLogin modelo;
    
    public ControlLogin(login vista, ModeloLogin modelo){
        this.vista = vista;
        this.modelo = modelo;
        
        this.vista.texto_usuario.addActionListener(this);
        this.vista.texto_contraseña.addActionListener(this);
        this.vista.entrar.addActionListener(this);
    }
    
    public void iniciarVista(){
        vista.setVisible(true);
        vista.setLocationRelativeTo(null);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        String usu = vista.texto_usuario.getText();
        String contra = new String(vista.texto_contraseña.getPassword());
        
        if(vista.entrar == ae.getSource() || vista.texto_usuario == ae.getSource() || vista.texto_contraseña == ae.getSource())
        {
            switch (modelo.ingresar(usu, contra))
            {   
                case 1:
                    //ENTRA EL TIPO EMPLEADO
                    JOptionPane.showMessageDialog(null,"Bienvenido " + usu);
                    vista.setVisible(false);
                    this.vista.dispose();
                    Desktop_Monedero M = new Desktop_Monedero();
                    IF_Abono A = new IF_Abono();
                    IF_Cargo C = new IF_Cargo();
                    IF_Clientes Cl = new IF_Clientes();
                    IF_HistorialCliente HC = new IF_HistorialCliente();
                    IF_Premios P = new IF_Premios();
                    ModeloMonedero modeloMonedero = new ModeloMonedero();
                    ControladorMonedero ctr = new ControladorMonedero(M,modeloMonedero, A, C, Cl, HC, P);
                    ctr.iniciarVista();
                    break;
                case 2:
                    //ENTRA AL ADMINISTRADOR
                    break;
                case 3:
                    //MENSAJE
                    JOptionPane.showMessageDialog(null,"USUARIO/CONTRASEÑA INCORRECTOS");
                    break;
                default:
                    
                    break;
            }
        }
    }
    
}
