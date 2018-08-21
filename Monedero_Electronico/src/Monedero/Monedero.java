/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Monedero;

import Controlador.ControlLogin;
import Controlador.ControladorMonedero;
import Modelo.ModeloLogin;
import Modelo.ModeloMonedero;
import Vista.Desktop_Monedero;
import Vista.IF_Abono;
import Vista.IF_Cargo;
import Vista.IF_Clientes;
import Vista.IF_HistorialCliente;
import Vista.IF_Premios;
import Vista.elegirsucursal;
import Vista.login;
import controlador.controlelegirsucursal;

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
        elegirsucursal vistasu = new elegirsucursal();
        controlelegirsucursal cs = new controlelegirsucursal(vistasu,0);
        cs.iniciarVista();
    }
    
}
