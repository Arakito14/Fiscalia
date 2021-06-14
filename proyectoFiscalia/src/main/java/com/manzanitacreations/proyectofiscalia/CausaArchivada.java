package com.manzanitacreations.proyectofiscalia;

import java.util.Scanner;

/*
*@author Marlene Lagos
*@author Valentina San Martin
*@author Matias Mujica
*ICI3241-1
 */
//----------------------------------------------------------------------------//
//--------------------------------Causa Child---------------------------------//
//----------------------------------------------------------------------------//
public class CausaArchivada extends Causa{
    
    String fechaArc;
    String razonArc;
    
    public CausaArchivada(String codigo,String estado,String tipoCaso,int distrito,String fechaArc,String razonArc){
        super(codigo,estado,tipoCaso,distrito);
        this.fechaArc=fechaArc;
        this.razonArc=razonArc;
    }
    
    public CausaArchivada(){
        super();
        fechaArc=new String();
        razonArc=new String();
    }
//----------------------------------------------------------------------------//
//------------------------------Metodos Generales-----------------------------//
//----------------------------------------------------------------------------//
    /*Muestra por pantalla la causa*/
    @Override
    public void imprimirCausa() {
        System.out.println("Codigo Causa: "+ getCodigo());
        System.out.println("Estado: "+ getEstado());
        System.out.println("Fecha de archivacion: "+ fechaArc);
        System.out.println("Razon de archivacion: "+ razonArc);
        System.out.println("Tipo de Caso: "+ getTipoCaso());
        System.out.println("Distrito: "+ getDistrito());
        if(getEncargado()!=null && !getEncargado().getRut().equals("")){
           System.out.println(FISCAL_DIV);
           getEncargado().mostrar(); 
        }else{
            System.out.println(DIVIDER);
            System.out.println(CAUSA_NO_FISCAL);
        }
        System.out.println(DIVIDER);
    }

 //----------------------------------------------------------------------------//
//------------------------------Getter y Setter-------------------------------//
//----------------------------------------------------------------------------//

    public String getFechaArc() {
        return fechaArc;
    }

    public void setFechaArc(String fechaArc) {
        this.fechaArc = fechaArc;
    }

    public String getRazonArc() {
        return razonArc;
    }

    public void setRazonArc(String razonArc) {
        this.razonArc = razonArc;
    }
    
}
//----------------------------------------------------------------------------//
//-------------------------------------Fin------------------------------------//
//----------------------------------------------------------------------------//
