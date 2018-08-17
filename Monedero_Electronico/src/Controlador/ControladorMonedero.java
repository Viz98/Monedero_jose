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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author jose_
 */
public class ControladorMonedero implements ActionListener, MouseListener{
    private Desktop_Monedero M;
    private ModeloMonedero Mo;
    private IF_Abono A;
    private IF_Cargo C;
    private IF_Clientes Cl;
    private IF_HistorialCliente HC;
    private IF_Premios P;
    
    public ControladorMonedero(Desktop_Monedero M, ModeloMonedero Mo, IF_Abono A, IF_Cargo C, IF_Clientes CL, IF_HistorialCliente HC, IF_Premios P)
    {
        this.M=M;
        this.Mo=Mo;
        this.A=A;
        this.C=C;
        this.Cl=CL;
        this.HC=HC;
        this.P=P;
        
        this.M.Desktop_Clientes_Btn.addActionListener(this);
        this.M.Desktop_Cargo_Btn.addActionListener(this);
        this.M.Desktop_Abono_Btn.addActionListener(this);
        this.M.Desktop_Historial_Btn.addActionListener(this);
        this.M.Desktop_Premios_Btn.addActionListener(this);
        this.M.Desktop_Salir_Btn.addActionListener(this);
        
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
        this.Cl.IF_Table_Clientes_table.addMouseListener(this);
        
        this.P.IF_Premios_Actualizar_Btn.addActionListener(this);
        this.P.IF_Premios_Borrar_Btn.addActionListener(this);
        this.P.IF_Premios_Insertar_Btn.addActionListener(this);
        this.P.IF_Premios_Limpiar_Btn.addActionListener(this);
        this.P.IF_Table_Premios_table.addMouseListener(this);
    }
    public void iniciarVista(){
        this.M.setTitle("Monedero viz´98");
        this.M.pack();
        this.M.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.M.setLocationRelativeTo(null);
        Cl.IF_Table_Clientes_table.setModel(Mo.mostrarClientes());
        this.M.setVisible(true);
        System.out.println("Prueba");
        System.out.println("");
        System.out.println("");
        
    }
    public void borrarCamposClientes()
    {
        this.Cl.IF_Cliente_Apellidos_Txt.setText("");
        this.Cl.IF_Cliente_Direccion_Txt.setText("");
        this.Cl.IF_Cliente_Email_Txt.setText("");
        this.Cl.IF_Cliente_Nombre_Txt.setText("");
        this.Cl.IF_Cliente_Puntos_Txt.setText("");
        this.Cl.IF_Cliente_Sexo_Txt.setText("");
        this.Cl.IF_Cliente_Telefono_Txt.setText("");
        this.Cl.IF_Cliente_idCliente_Txt.setText("");
    }
    public void borrarCamposPremios()
    {
        this.P.IF_Premios_Nombre_Txt1.setText("");
        this.P.IF_Premios_Puntos_Txt.setText("");
        this.P.IF_Premios_idPremio_Txt.setText("");
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {   
        if(e.getSource() == M.Desktop_Clientes_Btn)
        {   
            this.Cl.toFront();
            Cl.IF_Table_Clientes_table.setModel(Mo.mostrarClientes());

        }
            if(e.getSource() == Cl.IF_Insertar_Clientes_Btn)
            {
                if(Mo.insertarCliente( Cl.IF_Cliente_Nombre_Txt.getText(), Cl.IF_Cliente_Apellidos_Txt.getText(), Integer.parseInt(Cl.IF_Cliente_Puntos_Txt.getText()), Cl.IF_Cliente_Email_Txt.getText(), Cl.IF_Cliente_Direccion_Txt.getText(), Cl.IF_Cliente_Sexo_Txt.getText(),Cl.IF_Cliente_Telefono_Txt.getText()));
                {
                    JOptionPane.showMessageDialog(null, "Resgistro insertado exitosamente");
                    Cl.IF_Table_Clientes_table.setModel(Mo.mostrarClientes());
                    this.borrarCamposClientes();
                }
            }
            if(e.getSource() == Cl.IF_Actualizar_Clientes_Btn)
            {
                if(Mo.actualizarCliente(Cl.IF_Cliente_Nombre_Txt.getText(), Cl.IF_Cliente_Apellidos_Txt.getText(), Integer.parseInt(Cl.IF_Cliente_Puntos_Txt.getText()), Cl.IF_Cliente_Email_Txt.getText(), Cl.IF_Cliente_Direccion_Txt.getText(), Cl.IF_Cliente_Sexo_Txt.getText(),Cl.IF_Cliente_Telefono_Txt.getText(), Integer.parseInt(Cl.IF_Cliente_idCliente_Txt.getText())));
                {
                    JOptionPane.showMessageDialog(null, "Resgistro actualizado exitosamente");
                    Cl.IF_Table_Clientes_table.setModel(Mo.mostrarClientes());
                    this.borrarCamposClientes();
                }
            }
            if(e.getSource() == Cl.IF_Borrar_Clientes_Btn)
            {
                if(Mo.borrarCliente(Cl.IF_Cliente_idCliente_Txt.getText()))
                {
                    JOptionPane.showMessageDialog(null, "Resgistro eliminado exitosamente");
                    Cl.IF_Table_Clientes_table.setModel(Mo.mostrarClientes());
                    this.borrarCamposClientes();
                }
            }
            if(e.getSource() == Cl.IF_Limpiar_Clientes_Btn)
            {
                this.borrarCamposClientes();
            }
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
            P.IF_Table_Premios_table.setModel(Mo.mostrarPremios());
        }
            if(e.getSource() == P.IF_Premios_Insertar_Btn)
            {
                if(Mo.insertarPremios(P.IF_Premios_Nombre_Txt1.getText(), Integer.parseInt(P.IF_Premios_Puntos_Txt.getText())))
                {
                    JOptionPane.showMessageDialog(null, "Resgistro insertado exitosamente");
                    P.IF_Table_Premios_table.setModel(Mo.mostrarPremios());
                    this.borrarCamposPremios();
                }
            }
            if(e.getSource() == P.IF_Premios_Limpiar_Btn)
            {
                this.borrarCamposPremios();
            }
            if(e.getSource() == P.IF_Premios_Borrar_Btn)
            {
                if(Mo.borrarPremio(P.IF_Premios_idPremio_Txt.getText()))
                {
                    JOptionPane.showMessageDialog(null, "Resgistro eliminado exitosamente");
                    P.IF_Table_Premios_table.setModel(Mo.mostrarPremios());
                    this.borrarCamposPremios();
                }
            }
            if(e.getSource() == P.IF_Premios_Actualizar_Btn)
            {
                if(Mo.actualizarPremio(P.IF_Premios_Nombre_Txt1.getText(), Integer.parseInt(P.IF_Premios_Puntos_Txt.getText()), Integer.parseInt(P.IF_Premios_idPremio_Txt.getText())));
                {
                    JOptionPane.showMessageDialog(null, "Resgistro actualizado exitosamente");
                    P.IF_Table_Premios_table.setModel(Mo.mostrarPremios());
                    this.borrarCamposPremios();                    
                }
            }
        if(e.getSource() == M.Desktop_Historial_Btn)
        {
            this.HC.toFront();
        }
        if(e.getSource() == M.Desktop_Salir_Btn)
        {
            System.exit(0);
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) 
    {
        if(me.getSource() == Cl.IF_Table_Clientes_table)
        {
            int fila = Cl.IF_Table_Clientes_table.rowAtPoint(me.getPoint());
            Cl.IF_Cliente_idCliente_Txt.setText(Cl.IF_Table_Clientes_table.getValueAt(fila, 0).toString());
            Cl.IF_Cliente_Nombre_Txt.setText(Cl.IF_Table_Clientes_table.getValueAt(fila, 1).toString());
            Cl.IF_Cliente_Apellidos_Txt.setText(Cl.IF_Table_Clientes_table.getValueAt(fila, 2).toString());
            Cl.IF_Cliente_Puntos_Txt.setText(Cl.IF_Table_Clientes_table.getValueAt(fila, 3).toString());
            Cl.IF_Cliente_Email_Txt.setText(Cl.IF_Table_Clientes_table.getValueAt(fila, 4).toString());
            Cl.IF_Cliente_Direccion_Txt.setText(Cl.IF_Table_Clientes_table.getValueAt(fila, 5).toString());
            Cl.IF_Cliente_Sexo_Txt.setText(Cl.IF_Table_Clientes_table.getValueAt(fila, 6).toString());
            Cl.IF_Cliente_Telefono_Txt.setText(Cl.IF_Table_Clientes_table.getValueAt(fila, 7).toString());
        }
        if(me.getSource() == P.IF_Table_Premios_table)
        {
            int f = P.IF_Table_Premios_table.rowAtPoint(me.getPoint());
            P.IF_Premios_idPremio_Txt.setText(P.IF_Table_Premios_table.getValueAt(f, 0).toString());
            P.IF_Premios_Nombre_Txt1.setText(P.IF_Table_Premios_table.getValueAt(f, 1).toString());
            P.IF_Premios_Puntos_Txt.setText(P.IF_Table_Premios_table.getValueAt(f, 2).toString());
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }
}
