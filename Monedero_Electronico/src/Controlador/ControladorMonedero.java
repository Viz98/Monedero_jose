/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModeloMonedero;
import Vista.Desktop_Monedero;
import Vista.IF_Abono;
import Vista.IF_Cargo;
import Vista.IF_Clientes;
import Vista.IF_HistorialCliente;
import Vista.IF_Premios;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 *
 * @author jose_
 */
public class ControladorMonedero implements ActionListener{
    private Desktop_Monedero M;
    private ModeloMonedero Mo;
    private IF_Abono A;
    private IF_Cargo C;
    private IF_Clientes Cl;
    private IF_HistorialCliente HC;
    private IF_Premios P;
    public static String [] empleado;
    
    public ControladorMonedero(Desktop_Monedero M, ModeloMonedero Mo, IF_Abono A, IF_Cargo C, IF_Clientes CL, IF_HistorialCliente HC, IF_Premios P, String [] empleado)
    {
        this.M=M;
        this.Mo=Mo;
        this.A=A;
        this.C=C;
        this.Cl=CL;
        this.HC=HC;
        this.P=P;
        this.empleado = empleado;
        
        this.M.Desktop_Clientes_Btn.addActionListener(this);
        this.M.Desktop_Cargo_Btn.addActionListener(this);
        this.M.Desktop_Abono_Btn.addActionListener(this);
        this.M.Desktop_Historial_Btn.addActionListener(this);
        this.M.Desktop_Premios_Btn.addActionListener(this);
        
        this.M.Desktop_Escritorio_Monedero.add(this.A);
        this.M.Desktop_Escritorio_Monedero.add(this.C);
        this.M.Desktop_Escritorio_Monedero.add(this.Cl);
        this.M.Desktop_Escritorio_Monedero.add(this.HC);
        this.M.Desktop_Escritorio_Monedero.add(this.P);
        
        this.A.show();
        this.C.show();
        this.Cl.show();
        this.HC.show();
        this.P.show();
        
        this.A.setLocation(-6, -25);
        this.C.setLocation(-6, -25);
        this.Cl.setLocation(-6, -25);
        this.HC.setLocation(-6, -25);
        this.P.setLocation(-6, -25);
        
        this.Cl.toFront();
        
        this.Cl.IF_Actualizar_Clientes_Btn.addActionListener(this);
        this.Cl.IF_Borrar_Clientes_Btn.addActionListener(this);
        this.Cl.IF_Insertar_Clientes_Btn.addActionListener(this);
        this.Cl.IF_Limpiar_Clientes_Btn.addActionListener(this);
//        this.Cl.IF_Table_Clientes_table.addMouseListener(this);
    }
    public void iniciarVista(){
        this.M.setTitle("Monedero viz´98");
        this.M.pack();
        this.M.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.M.setLocationRelativeTo(null);
        //AUS.Administrador_Usuarios_TablaUsuarios_Table.setModel(modelo.usuariosUsuariosConsultar());
        this.M.setVisible(true);
        System.out.println("Prueba");
        System.out.println("");
        System.out.println("");
        
    }

//    @Override
//    public void actionPerformed(ActionEvent ae) 
//    {   System.out.println("1");
//        if(ae.getSource()== M.Desktop_Clientes_Btn)
//        {
//            System.out.println("ASDasda");
//            Cl.toFront();
//        }
//    }
    @Override
    public void actionPerformed(ActionEvent e)
    {   
        if(e.getSource() == M.Desktop_Clientes_Btn)
        {   
            this.Cl.toFront();
            System.out.println("El nombre del empleado: " + empleado[2]);
            System.out.println("El id del empleado: " + empleado[0]);
            System.out.println("La sucursal donde trabaja es: " + empleado[1]);
        }
//            if(e.getSource() == Cl.IF_Insertar_Clientes_Btn)
//            {
//                if(Mo.insertarCliente(nombre, apellidos, 0, email, dire, sexo, tele))
//            }
        if(e.getSource() == M.Desktop_Abono_Btn)
        {
            this.A.toFront();
        }
        if(e.getSource() == M.Desktop_Cargo_Btn)
        {
            this.C.toFront();
        }
        if(e.getSource() == M.Desktop_Premios_Btn)
        {
            this.P.toFront();
        }
        if(e.getSource() == M.Desktop_Historial_Btn)
        {
            this.HC.toFront();
        }
    }
}
