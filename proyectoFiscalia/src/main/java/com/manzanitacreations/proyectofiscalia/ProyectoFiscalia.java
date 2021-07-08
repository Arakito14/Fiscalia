package com.manzanitacreations.proyectofiscalia;

import ventanas.*;
import interfaces.*;
import java.io.IOException;

/*
*@author Marlene Lagos
*@author Valentina San Martin
*@author Matias Mujica
*ICI3241-1
 */
//----------------------------------------------------------------------------//
//------------------------------------Main------------------------------------//
//----------------------------------------------------------------------------//
public class ProyectoFiscalia implements FormatoMenu,FormatoEstado {


    /*principal*/
    public static void main(String[] args) throws IOException {
         MenuPrincipal menu= new MenuPrincipal();
         menu.setVisible(true);
         menu.setResizable(false);
         menu.setLocationRelativeTo(null);
        menu.getFiscalia().leerFiscales();
        menu.getFiscalia().leerCausas(); 
        menu.getFiscalia().llenarMax();
    }

    @Override
    public int esParticipante(String opcion_str) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String comprobarEstado(int est) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
//----------------------------------------------------------------------------//
//-------------------------------------Fin------------------------------------//
//----------------------------------------------------------------------------//
