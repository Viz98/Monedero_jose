/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package Monedero;

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
import Vista.login;

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
        login login = new login();
        ModeloLogin modelologin = new ModeloLogin();
        ControlLogin contrologin = new ControlLogin(login,modelologin);
        contrologin.iniciarVista();
        login.setLocationRelativeTo(null);
    }
    
}
