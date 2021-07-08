package com.manzanitacreations.proyectofiscalia;

import interfaces.*;
import static interfaces.FormatoEstado.ARCHIVADA;
import static interfaces.FormatoEstado.CERRADA;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import javax.swing.JTextArea;

/*
*@author Marlene Lagos
*@author Valentina San Martin
*@author Matias Mujica
*ICI3241-1
 */
//----------------------------------------------------------------------------//
//------------------------------Fiscalia Child--------------------------------//
//----------------------------------------------------------------------------//
public class Fiscal implements FormatoMenu, FormatoRut, FormatoEstado {

    /*Primary Key*/
    private String rut;
    /*Attributes*/
    private String nombre;
    private String especialidad;
    /*Distrito*/
    private int distrito;
    /*Mapas*/
    private HashMap<String, Causa> causasActuales;


    public Fiscal() {
        rut = new String();
        nombre = new String();
        especialidad = new String();
        distrito = 0;
        causasActuales = new HashMap<>();
    }

    public Fiscal(String rut, String nombre, String especialidad, int distrito) {
        this.rut = rut;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.distrito = distrito;
        causasActuales = new HashMap<>();
    }
//----------------------------------------------------------------------------//
//------------------------------Metodos Generales-----------------------------//
//----------------------------------------------------------------------------//




/**
 * Metodo mostrar Fiscal
 */
    public void mostrar() {
        System.out.println("Nombre Fiscal: " + nombre);
        System.out.println("Rut: " + rut);
        System.out.println("Especialidad: " + especialidad);
        System.out.println("Distrito: " + distrito);
    }


    

 /**
  * Imprime Fiscal por tipo de Causa
  * @param tipoCausa int con el número correspondiente al tipo de Causa
  */
    public void imprimirFiscal(int tipoCausa) {
        //CausaArchivada archivada = new CausaArchivada(codigo, estado, tipoCaso, distrito, fechaArc, razon);
        //CausaCerrada cerrada = new CausaCerrada(codigo, estado, tipoCaso, distrito, fecha, resolucion);
        //CausaAbierta abierta = new CausaAbierta(values.getCodigo(), values.getEstado(), values.getTipoCaso(), values.getDistrito());
        /*Abierta*/
        if (tipoCausa == 6) {
            for (Entry<String, Causa> aux : causasActuales.entrySet()) {
            //for (Entry<String, Fiscal> aux : fiscales.entrySet()) {
                Causa values = aux.getValue();
                if (values.getEstado().equals(ABIERTA)) {
                    values.mostrar();
                    System.out.println(DIVIDER);
                }
            }
        }
        /*Cerrada*/
        if (tipoCausa == 7) {
            for (Entry<String, Causa> aux : causasActuales.entrySet()) {
            //for (Entry<String, Fiscal> aux : fiscales.entrySet()) {
                Causa values = aux.getValue();
                if (values.getEstado().equals(CERRADA)) {
                    values.mostrar();
                    System.out.println(DIVIDER);
                }
            }
        }
        /*Archivada*/
        if (tipoCausa == 8) {
            for (Entry<String, Causa> aux : causasActuales.entrySet()) {
            //for (Entry<String, Fiscal> aux : fiscales.entrySet()) {
                Causa values = aux.getValue();
                if (values.getEstado().equals(ARCHIVADA)) {
                    values.mostrar();
                    System.out.println(DIVIDER);
                }
            }
        }
    }
   /**
    * Cuenta el numero de causas de un fiscal
    * @return int numero de causas
    */ 
   public int contarCausas(){
          return causasActuales.size(); 
    }    
//----------------------------------------------------------------------------//
//----------------------------Metodos de Escritura----------------------------//
//----------------------------------------------------------------------------//


 /**
  * Escribe los fiscales en el archivo de reporte
  * @param writer PrintWriter que va a escribir en el archivo
  */
    public void escribirFiscal(PrintWriter writer) {
        try {
            writer.println("Nombre Fiscal:" + nombre);
            writer.println("Rut:" + rut);
            writer.println("Especialidad:" + especialidad);
            writer.println("Distrito:" + distrito);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

/**
 * Escribe las causas de cada fiscal en el archivo de reporte
 * @param writer  PrintWriter que va a escribir en el archivo
 */
    public void escribirCausas(PrintWriter writer) {
        int tamano = causasActuales.size();
        if (tamano != 0) {
            for (Entry<String, Causa> entry : causasActuales.entrySet()) {
                Causa aux=entry.getValue();
                writer.println("Codigo Causa:"+ aux.getCodigo());
                writer.println("Rut Encargado:"+ aux.getEncargado().getRut());
                writer.println("Estado:"+ aux.getEstado());
                writer.println("Tipo de Caso:"+ aux.getTipoCaso());
                writer.println("Distrito:"+ aux.getDistrito());
                writer.println(DIVIDER);
                aux.escribirProcedimientos(writer);
            }
        } else {
            writer.println(FISCAL_NO_CAUSA);
            writer.println(DIVIDER);
        }
    }
    

/**
 * Elimina el fiscal de todas las causas que tenía a su cargo
 */
    public void eliminarFiscal() {
        for (Entry<String, Causa> entry : causasActuales.entrySet()) {
            Causa aux = entry.getValue();
            aux.setEncargado(new Fiscal());
        }
        causasActuales.clear();
    }
 /**
  * Muestra todas las causas del fiscal
  */  
    public void mostrarCausas() {
        for (Entry<String, Causa> aux : causasActuales.entrySet()) {
            Causa values = aux.getValue();
            values.mostrar();
            System.out.println(DIVIDER);
        }
    }
    
    /**
     * Agrega una causa al mapa de causasActuales
     * @param nueva Objeto tipo Causa que guarda la causa a ingresar
     */
    public void agregarCausa(Causa nueva) {
        causasActuales.put(nueva.getCodigo(), nueva);
    }
    
    /**
     * Quita una causa del mapa de causasActuales
     * @param codigo String con el codigo de la causa a eliminar
     */
    public void quitarCausa(String codigo) {
        causasActuales.remove(codigo);
    }
    
  /**
   * Muestra las causas del fiscal en la ventana ListarFiscales
   * @param aux JTextArea en donde se presentará la información
   */
    public void mostrarCausasVentana(JTextArea aux){
       for(Map.Entry<String,Causa> aux2 : causasActuales.entrySet()){
                Causa actual=aux2.getValue();
                aux.append("Codigo: "+ actual.getCodigo()+"\nEstado: "+actual.getEstado()+"\nTipo de Caso "+actual.getTipoCaso()+"\nDistrito: "+actual.getDistrito());
                switch (actual.getEstado()){
                    case CERRADA:
                        CausaCerrada cerrada=(CausaCerrada)actual;
                        aux.append("Fecha de Termino:"+ cerrada.getFechaTerm()+"\nResultado: "+ cerrada.getResultado());
                        break;
                    case ARCHIVADA:
                        CausaArchivada archivada=(CausaArchivada)actual;
                        aux.append("Fecha de Termino:"+ archivada.getFechaArc()+"\nRazon de archivacion: "+ archivada.getRazonArc());
                        break;
                }
               aux.append("\n-------------------------------------------"); 
               aux.append("\n-------------------------------------------\n");
       }
    }
//----------------------------------------------------------------------------//
//------------------------------Getter y Setter-------------------------------//
//----------------------------------------------------------------------------//

    /*Fiscal*/
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    /*Distrito*/
    public int getDistrito() {
        return distrito;
    }

    public void setDistrito(int distrito) {
        this.distrito = distrito;
    }

    /*Override*/
    @Override
    public int esParticipante(String opcion_str) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String esRut(String rut) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean confirmar(String rut) {
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
