package com.manzanitacreations.proyectofiscalia;

/*
*@author Marlene Lagos
*@author Valentina San Martin
*@author Matias Mujica
*ICI3241-1
 */
//----------------------------------------------------------------------------//
//--------------------------------Causa Child---------------------------------//
//----------------------------------------------------------------------------//
public class CausaCerrada extends Causa{

    String fechaTerm;
    String resultado;

    public CausaCerrada(String codigo, String estado, String tipoCaso, int distrito, String fechaTerm, String resultado) {
        super(codigo, estado, tipoCaso, distrito);
        this.fechaTerm = fechaTerm;
        this.resultado = resultado;
    }
//----------------------------------------------------------------------------//
//------------------------------Metodos Generales-----------------------------//
//----------------------------------------------------------------------------// 

    /*Muestra por pantalla la causa*/
    public void imprimirCausa() {
        System.out.println("Codigo Causa:" + getCodigo());
        System.out.println("Estado:" + getEstado());
        System.out.println("Tipo de Caso:" + getTipoCaso());
        System.out.println("Distrito:" + getDistrito());
        if (getEncargado() != null && !getEncargado().getRut().equals("")) {
            System.out.println(FISCAL_DIV);
            getEncargado().mostrar();
        } else {
            System.out.println(DIVIDER);
            System.out.println(CAUSA_NO_FISCAL);
        }
        System.out.println(DIVIDER);
    }
}
//----------------------------------------------------------------------------//
//-------------------------------------Fin------------------------------------//
//----------------------------------------------------------------------------//
