/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Monedero;

import Controlador.ControladorMonedero;
import Modelo.ModeloMonedero;
import Vista.Desktop_Monedero;
import Vista.IF_Abono;
import Vista.IF_Cargo;
import Vista.IF_Clientes;
import Vista.IF_HistorialCliente;
import Vista.IF_Premios;

/**
 *
 * @author jose_
 */
public class Monedero {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        Desktop_Monedero M = new Desktop_Monedero();
        IF_Abono A = new IF_Abono();
        IF_Cargo C = new IF_Cargo();
        IF_Clientes Cl = new IF_Clientes();
        IF_HistorialCliente HC = new IF_HistorialCliente();
        IF_Premios P = new IF_Premios();
        ModeloMonedero modeloMonedero = new ModeloMonedero();
        ControladorMonedero ctr = new ControladorMonedero(M,modeloMonedero, A, C, Cl, HC, P);
        ctr.iniciarVista();
    }
    
}
