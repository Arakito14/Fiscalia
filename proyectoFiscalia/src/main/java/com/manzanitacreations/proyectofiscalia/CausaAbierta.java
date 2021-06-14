package com.manzanitacreations.proyectofiscalia;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.*;

/*
*@author Marlene Lagos
*@author Valentina San Martin
*@author Matias Mujica
*ICI3241-1
 */
//----------------------------------------------------------------------------//
//--------------------------------Causa Child---------------------------------//
//----------------------------------------------------------------------------//
public class CausaAbierta extends Causa {

    public CausaAbierta(String codigo, String estado, String tipoCaso, int distrito) {
        super(codigo, estado, tipoCaso, distrito);
    }
    
    public CausaAbierta(){
        super();
    }
//----------------------------------------------------------------------------//
//------------------------------Metodos Generales-----------------------------//
//----------------------------------------------------------------------------//    

     /*Muestra por pantalla la causa*/
    @Override
    public void imprimirCausa() {
        System.out.println("Codigo Causa:"+ getCodigo());
        System.out.println("Estado:"+ getEstado());
        System.out.println("Tipo de Caso:"+ getTipoCaso());
        System.out.println("Distrito:"+ getDistrito());
        if(getEncargado()!=null && !getEncargado().getRut().equals("")){
           System.out.println("FISCAL_DIV");
           getEncargado().mostrar(); 
        }else{
            System.out.println(DIVIDER);
            System.out.println(CAUSA_NO_FISCAL);
        }
        System.out.println(DIVIDER);
    }
}
//----------------------------------------------------------------------------//
//-------------------------------------Fin------------------------------------//
//----------------------------------------------------------------------------//
