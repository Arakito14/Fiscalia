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
//----------------------------------------------------------------------------//
//------------------------------Metodos Generales-----------------------------//
//----------------------------------------------------------------------------//
    /*Muestra por pantalla la causa*/
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
    
    /*Permite modificar el resultado de un procedimiento*/
    public void modificarProcedimiento(){
        System.out.println("Causa n°:"+ getCodigo());
        mostrarProcedimientos();
        System.out.println(PROC_CODIGO);
        int num=Integer.parseInt(LEER.nextLine());
        if(num<= getPeritajes().size()){
            Procedimiento elegido= getPeritajes().get(num-1);
            System.out.println(PROC_RESULTADO);
            String nuevo= LEER.nextLine();
            elegido.setResultado(nuevo);
        }else{
            System.out.println(INCORRECTO);
        }
    }
    
    /*Elimina un procedimiento de la lista de procedimientos*/
    public void eliminarProcedimiento(){
        System.out.println(PROC_CODIGO);
        int num=Integer.parseInt(LEER.nextLine());
        if(num<= getPeritajes().size()){
            getPeritajes().remove(num-1);
            System.out.println(PROC_ELIMINAR);
        }else{
            System.out.println(INCORRECTO);
        }
    }
    
    public void imprimirFechaDeArchivacion(){}
    public void imprimirCausaDeArchivacion(){}
    
}
//----------------------------------------------------------------------------//
//-------------------------------------Fin------------------------------------//
//----------------------------------------------------------------------------//
