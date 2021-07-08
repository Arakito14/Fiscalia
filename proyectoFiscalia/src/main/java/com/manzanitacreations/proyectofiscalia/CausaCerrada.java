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

    private String fechaTerm;
    private String resultado;

    public CausaCerrada(String codigo, String estado, String tipoCaso, int distrito, String fechaTerm, String resultado) {
        super(codigo, estado, tipoCaso, distrito);
        this.fechaTerm = fechaTerm;
        this.resultado = resultado;
    }
    
    public CausaCerrada(){
        super();
        fechaTerm=new String();
        resultado=new String();
    }
//----------------------------------------------------------------------------//
//------------------------------Metodos Generales-----------------------------//
//----------------------------------------------------------------------------// 

    @Override
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
        System.out.println("Fecha de termino: " + fechaTerm);
        System.out.println("Resolucion del caso: " + resultado);
        System.out.println(DIVIDER);
    }
    
 //----------------------------------------------------------------------------//
//------------------------------Getter y Setter-------------------------------//
//----------------------------------------------------------------------------//

    public String getFechaTerm() {
        return fechaTerm;
    }

    public void setFechaTerm(String fechaTerm) {
        this.fechaTerm = fechaTerm;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
    
}
//----------------------------------------------------------------------------//
//-------------------------------------Fin------------------------------------//
//----------------------------------------------------------------------------//
