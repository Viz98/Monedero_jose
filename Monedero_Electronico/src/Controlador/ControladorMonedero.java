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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
    public static String [] empleado;
    public static int punto;
    private double calcular;
    private String cliente;
    private String cliente2;
    private String idInventario;
    private double temporal;
    private int puntos;
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
        
        this.A.insertar.addActionListener(this);
        this.A.tabla_abono.addMouseListener(this);
        this.A.texto_importe.addActionListener(this);
        this.A.texto_puntos.addActionListener(this);
        this.A.texto_ticket.addActionListener(this);
        
        this.HC.tabla_ClientesHistorial.addMouseListener(this);
        this.HC.tabla_abonos.addMouseListener(this);
        this.HC.tabla_cargos.addMouseListener(this);
        
        this.P.IF_Table_Premios_table.addMouseListener(this);
        this.P.IF_Premios_TablaGeneral_Table.addMouseListener(this);
        this.P.IF_Premios_AgregarInventario_Btn.addActionListener(this);
        
        System.out.println("1");
        System.out.println("2");
        System.out.println("3");
        System.out.println("4");
        
        this.C.IF_Cargo_Canjear_Btn.addActionListener(this);
        this.C.IF_Cargo_TablaClientes_Table.addMouseListener(this);
        this.C.IF_Cargo_Inventario_Table.addMouseListener(this);
        System.out.println("asdasdad");
        System.out.println("jajsdjasdj");
        System.out.println("ahora siiiii");
        System.out.println("ahora si mejor");
        
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
        this.Cl.IF_Cliente_Sexo_Txt.setText("");
        this.Cl.IF_Cliente_Telefono_Txt.setText("");
        this.Cl.IF_Cliente_idCliente_Txt.setText("");
    }
    
    public void borrarCamposAbono()
    {
        this.A.texto_importe.setText("");
        this.A.texto_ticket.setText("");
        this.A.texto_nombreCliente.setText("");
    }
    public void borrarCamposCargo()
    {
        this.C.IF_Cargo_Nombre_Txt.setText("");
        this.C.IF_Cargo_Premio_Txt.setText("");
        this.C.IF_Cargo_PuntosCliente_Txt.setText("");
        this.C.IF_Cargo_PuntosPremio_Txt.setText("");
    }
    public boolean vaciosONoTxt(javax.swing.JTextField... args)
    {
        
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9@.ñ _]*$");
        Matcher matcher;
        for(javax.swing.JTextField arg : args){
            matcher = pattern.matcher(arg.getText());
            if(!arg.getText().equals("") && matcher.matches())
                ;
            else
                return false;
        }
        return true;
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
            System.out.println("El nombre del empleado: " + empleado[2]);
            System.out.println("El id del empleado: " + empleado[0]);
            System.out.println("La sucursal donde trabaja es: " + empleado[1]);
            Cl.IF_Table_Clientes_table.setModel(Mo.mostrarClientes());
        }
            if(e.getSource() == Cl.IF_Insertar_Clientes_Btn)
                {
                    if(vaciosONoTxt(Cl.IF_Cliente_Apellidos_Txt, Cl.IF_Cliente_Direccion_Txt, Cl.IF_Cliente_Email_Txt, Cl.IF_Cliente_Nombre_Txt,Cl.IF_Cliente_Sexo_Txt,Cl.IF_Cliente_Telefono_Txt))
                    {
                        if(Mo.insertarCliente( Cl.IF_Cliente_Nombre_Txt.getText(), Cl.IF_Cliente_Apellidos_Txt.getText(),Cl.IF_Cliente_Email_Txt.getText(), Cl.IF_Cliente_Direccion_Txt.getText(), Cl.IF_Cliente_Sexo_Txt.getText(),Cl.IF_Cliente_Telefono_Txt.getText()));
                        {
                            JOptionPane.showMessageDialog(null, "Resgistro insertado exitosamente");
                            Cl.IF_Table_Clientes_table.setModel(Mo.mostrarClientes());
                            this.borrarCamposClientes();
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Error: Campos vacios o caracteres invalidos");                    
                    }
                }
            if(e.getSource() == Cl.IF_Actualizar_Clientes_Btn)
                {
                    if(vaciosONoTxt(Cl.IF_Cliente_Apellidos_Txt, Cl.IF_Cliente_Direccion_Txt, Cl.IF_Cliente_Email_Txt, Cl.IF_Cliente_Nombre_Txt,Cl.IF_Cliente_Sexo_Txt,Cl.IF_Cliente_Telefono_Txt))
                    {
                        if(Mo.actualizarCliente(Cl.IF_Cliente_Nombre_Txt.getText(), Cl.IF_Cliente_Apellidos_Txt.getText(), Cl.IF_Cliente_Email_Txt.getText(), Cl.IF_Cliente_Direccion_Txt.getText(), Cl.IF_Cliente_Sexo_Txt.getText(),Cl.IF_Cliente_Telefono_Txt.getText(), Integer.parseInt(Cl.IF_Cliente_idCliente_Txt.getText())));
                        {
                            JOptionPane.showMessageDialog(null, "Resgistro actualizado exitosamente");
                            Cl.IF_Table_Clientes_table.setModel(Mo.mostrarClientes());
                            this.borrarCamposClientes();
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Error: Campos vacios o caracteres invalidos");                    
                    }
                }
            if(e.getSource() == Cl.IF_Borrar_Clientes_Btn)
                {
                    if(vaciosONoTxt(Cl.IF_Cliente_Apellidos_Txt, Cl.IF_Cliente_Direccion_Txt, Cl.IF_Cliente_Email_Txt, Cl.IF_Cliente_Nombre_Txt,Cl.IF_Cliente_Sexo_Txt,Cl.IF_Cliente_Telefono_Txt))
                    {
                        if(Mo.borrarCliente(Cl.IF_Cliente_idCliente_Txt.getText()))
                        {
                         JOptionPane.showMessageDialog(null, "Resgistro eliminado exitosamente");
                         Cl.IF_Table_Clientes_table.setModel(Mo.mostrarClientes());
                         this.borrarCamposClientes();
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Error: Campos vacios o caracteres invalidos");                                        
                    }
                }
            if(e.getSource() == Cl.IF_Limpiar_Clientes_Btn)
                {
                    this.borrarCamposClientes();
                }   
        if(e.getSource() == M.Desktop_Abono_Btn)
        {
            this.A.toFront();
            this.A.texto_puntos.setText("");
            this.A.texto_nombreCliente.setText("");
            A.tabla_abono.setModel(Mo.mostrarClientes());
        }
        
            if(A.insertar == e.getSource())
            {
                if(vaciosONoTxt(A.texto_importe, A.texto_ticket))
                {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String date = sdf.format(new Date()); 
                    calcular = Double.parseDouble(A.texto_importe.getText());
                    temporal = calcular/10;
                    punto = (int)temporal;
                    System.out.println("Los puntos son: " + punto);
                    System.out.println("El id del cliente es: " + cliente);
                    System.out.println("El id del empleado es: " + empleado[0]);
                    if(Mo.insertarAbono(date, punto, Double.parseDouble(A.texto_importe.getText()), A.texto_ticket.getText(), cliente, empleado[0]))
                    {
                        JOptionPane.showMessageDialog(null, "Resgistro insertado exitosamente");
                        int valor = Integer.parseInt(Mo.ObtenerPuntosClientes(cliente));
                        valor = valor + punto;
                        A.texto_puntos.setText(""+punto);
                        if(Mo.EnviarPuntosCliente(valor, cliente))
                        {
                            System.out.println("Se le envio los puntos al cliente");
                            A.tabla_abono.setModel(Mo.mostrarClientes());
                        }
                        this.borrarCamposAbono();
                        calcular = 0;
                        temporal = 0;
                        punto = 0;
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Ingrese importe y el ticket");
                }
            
            }
        if(e.getSource() == M.Desktop_Cargo_Btn)
        {
            this.C.toFront();
            C.IF_Cargo_TablaClientes_Table.setModel(Mo.mostrarClientes());
            C.IF_Cargo_Inventario_Table.setModel(Mo.mostrarPremios2(ControladorMonedero.empleado[1]));
        }
            if(e.getSource() == C.IF_Cargo_Canjear_Btn)
            {
                SimpleDateFormat sdff = new SimpleDateFormat("yyyy-MM-dd");
                String date2 = sdff.format(new Date());
                System.out.println("la fecha: " + date2);
                System.out.println("el id cliente es: "+ cliente);
                System.out.println("el id empleado es: "+ControladorMonedero.empleado[1]);
                System.out.println("el id inventario es: "+idInventario);
                
                int puntoscliente = Integer.parseInt(C.IF_Cargo_PuntosCliente_Txt.getText());
                int puntospremio = Integer.parseInt(C.IF_Cargo_PuntosPremio_Txt.getText());
                System.out.println("puntos cliente: "+puntoscliente);
                System.out.println("puntos premio: "+puntospremio);
                if(puntoscliente >= puntospremio)
                {
                    if(Mo.agregarCargo(date2, cliente, empleado[0], idInventario))
                    {
                        JOptionPane.showMessageDialog(null, "Registro insertado exitosamente");
                        int valor2 = puntoscliente-puntospremio;
                        Mo.EnviarPuntosCliente(valor2, cliente);
                        C.IF_Cargo_TablaClientes_Table.setModel(Mo.mostrarClientes());
                        this.borrarCamposCargo();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Error: No pudo insertar el cargo");                      
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Error: No cuenta con los puntos suficientes");                                          
                }
            }
        if(e.getSource() == M.Desktop_Premios_Btn)
        {
            this.P.toFront();
            P.IF_Table_Premios_table.setModel(Mo.mostrarPremios(ControladorMonedero.empleado[1]));
            P.IF_Premios_TablaGeneral_Table.setModel(Mo.mostrarPremiosGeneral());
        }
            if(e.getSource() == P.IF_Premios_Insertar_Btn)
            {
                if(vaciosONoTxt(P.IF_Premios_Nombre_Txt1,P.IF_Premios_Puntos_Txt))
                {
                    if(Mo.insertarPremios(P.IF_Premios_Nombre_Txt1.getText(), Integer.parseInt(P.IF_Premios_Puntos_Txt.getText())))
                    {
                        JOptionPane.showMessageDialog(null, "Resgistro insertado exitosamente");
                        P.IF_Table_Premios_table.setModel(Mo.mostrarPremios(ControladorMonedero.empleado[1]));
                        P.IF_Premios_TablaGeneral_Table.setModel(Mo.mostrarPremiosGeneral());
                        this.borrarCamposPremios();
                    }
                    else
                    {
                    JOptionPane.showMessageDialog(null, "Error: No se pudo insertar el premio");                                                            
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Error: Campos vacios o caracteres invalidos");                                                            
                }
            }
            if(e.getSource() == P.IF_Premios_Limpiar_Btn)
            {
                this.borrarCamposPremios();
            }
            if(e.getSource() == P.IF_Premios_Borrar_Btn)
            {
                if(vaciosONoTxt(P.IF_Premios_Nombre_Txt1,P.IF_Premios_Puntos_Txt,P.IF_Premios_idPremio_Txt))
                {
                    if(Mo.borrarPremio(P.IF_Premios_idPremio_Txt.getText()))
                    {
                        JOptionPane.showMessageDialog(null, "Resgistro eliminado exitosamente");
                        P.IF_Table_Premios_table.setModel(Mo.mostrarPremios(ControladorMonedero.empleado[1]));
                        P.IF_Premios_TablaGeneral_Table.setModel(Mo.mostrarPremiosGeneral());
                        this.borrarCamposPremios();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Error: Primero elimine el premio del inventario");
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Error: Campos vacios o caracteres invalidos");                                                            
                }
            }
            if(e.getSource() == P.IF_Premios_Actualizar_Btn)
            {
                if(vaciosONoTxt(P.IF_Premios_Nombre_Txt1,P.IF_Premios_Puntos_Txt,P.IF_Premios_idPremio_Txt))
                {
                    if(Mo.actualizarPremio(P.IF_Premios_Nombre_Txt1.getText(), Integer.parseInt(P.IF_Premios_Puntos_Txt.getText()), Integer.parseInt(P.IF_Premios_idPremio_Txt.getText())));
                    {
                        JOptionPane.showMessageDialog(null, "Resgistro actualizado exitosamente");
                        P.IF_Table_Premios_table.setModel(Mo.mostrarPremios(ControladorMonedero.empleado[1]));
                        P.IF_Premios_TablaGeneral_Table.setModel(Mo.mostrarPremiosGeneral());
                        this.borrarCamposPremios();                    
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Error: Campos vacios o caracteres invalidos");                                                               
                }
            }
            if(e.getSource() == P.IF_Premios_AgregarInventario_Btn)
            {
                if(vaciosONoTxt(P.IF_Premios_Nombre_Txt1,P.IF_Premios_Puntos_Txt,P.IF_Premios_idPremio_Txt))
                {
                    if(Mo.agregarInventarioPremio(Integer.parseInt(P.IF_Premios_idPremio_Txt.getText()), ControladorMonedero.empleado[1]));
                    {
                        JOptionPane.showMessageDialog(null, "Resgistro actualizado exitosamente");
                        P.IF_Table_Premios_table.setModel(Mo.mostrarPremios(ControladorMonedero.empleado[1]));
                        this.borrarCamposPremios(); 
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Error: Campos vacios o caracteres invalidos");                                                               
                }
            }
        if(e.getSource() == M.Desktop_Historial_Btn)
        {
            this.HC.toFront();
            HC.tabla_ClientesHistorial.setModel(Mo.mostrarClientes());
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
            cliente = (Cl.IF_Table_Clientes_table.getValueAt(fila, 0).toString());
            System.out.println("Cliente id: " + cliente);
            Cl.IF_Cliente_Nombre_Txt.setText(Cl.IF_Table_Clientes_table.getValueAt(fila, 1).toString());
            Cl.IF_Cliente_Apellidos_Txt.setText(Cl.IF_Table_Clientes_table.getValueAt(fila, 2).toString());
            Cl.IF_Cliente_Email_Txt.setText(Cl.IF_Table_Clientes_table.getValueAt(fila, 4).toString());
            Cl.IF_Cliente_Direccion_Txt.setText(Cl.IF_Table_Clientes_table.getValueAt(fila, 5).toString());
            Cl.IF_Cliente_Sexo_Txt.setText(Cl.IF_Table_Clientes_table.getValueAt(fila, 6).toString());
            Cl.IF_Cliente_Telefono_Txt.setText(Cl.IF_Table_Clientes_table.getValueAt(fila, 7).toString());
        }
        
        if(me.getSource() == A.tabla_abono)
        {
            System.out.println("Entra aqui");
            int fila = A.tabla_abono.rowAtPoint(me.getPoint());
            cliente = (A.tabla_abono.getValueAt(fila, 0).toString()); 
            System.out.println("Cliente id: " + cliente);
            A.texto_nombreCliente.setText(A.tabla_abono.getValueAt(fila, 1).toString());
        }
        if(me.getSource() == HC.tabla_ClientesHistorial)
        {
            System.out.println("Entra aqui");
            int fila = HC.tabla_ClientesHistorial.rowAtPoint(me.getPoint());
            cliente = (HC.tabla_ClientesHistorial.getValueAt(fila, 0).toString()); 
            System.out.println("Cliente id: " + cliente);
            HC.tabla_abonos.setModel(Mo.MostrarHistorialAbono(cliente));
        }
        if(me.getSource() == HC.tabla_cargos)
        {
            System.out.println("Entra aqui");
            int fila = HC.tabla_cargos.rowAtPoint(me.getPoint());
            cliente = (HC.tabla_cargos.getValueAt(fila, 0).toString()); 
            System.out.println("Cliente id: " + cliente);
            HC.tabla_cargos.setModel(Mo.MostrarHistorialAbono(cliente));
        }
        if(me.getSource() == P.IF_Premios_TablaGeneral_Table)
        {
            int f = P.IF_Premios_TablaGeneral_Table.rowAtPoint(me.getPoint());
            P.IF_Premios_idPremio_Txt.setText(P.IF_Premios_TablaGeneral_Table.getValueAt(f, 0).toString());
            P.IF_Premios_Nombre_Txt1.setText(P.IF_Premios_TablaGeneral_Table.getValueAt(f, 1).toString());
            P.IF_Premios_Puntos_Txt.setText(P.IF_Premios_TablaGeneral_Table.getValueAt(f, 2).toString());

        }
        if(me.getSource() == C.IF_Cargo_TablaClientes_Table)
        {
            int f = C.IF_Cargo_TablaClientes_Table.rowAtPoint(me.getPoint());
            cliente = (C.IF_Cargo_TablaClientes_Table.getValueAt(f, 0).toString());
            C.IF_Cargo_Nombre_Txt.setText(C.IF_Cargo_TablaClientes_Table.getValueAt(f, 1).toString());
            C.IF_Cargo_PuntosCliente_Txt.setText(C.IF_Cargo_TablaClientes_Table.getValueAt(f, 3).toString());
        }
        if(me.getSource() == C.IF_Cargo_Inventario_Table)
        {
            int f = C.IF_Cargo_Inventario_Table.rowAtPoint(me.getPoint());
            idInventario = (C.IF_Cargo_Inventario_Table.getValueAt(f, 0).toString());
            C.IF_Cargo_Premio_Txt.setText(C.IF_Cargo_Inventario_Table.getValueAt(f, 1).toString());
            C.IF_Cargo_PuntosPremio_Txt.setText(C.IF_Cargo_Inventario_Table.getValueAt(f, 2).toString());
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
//    public boolean vaciosONoTxt(javax.swing.JTextField... args)
//    {
//        
//        Pattern pattern = Pattern.compile("^[a-zA-Z0-9@.ñ _]*$");
//        Matcher matcher;
//        for(javax.swing.JTextField arg : args){
//            matcher = pattern.matcher(arg.getText());
//            if(!arg.getText().equals("") && matcher.matches())
//                ;
//            else
//                return false;
//        }
//        return true;
//    }
}
