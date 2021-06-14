package com.manzanitacreations.proyectofiscalia;

import interfaces.*;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map.Entry;

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
    private HashMap<String, Integer> causasMax;

    public Fiscal() {
        rut = new String();
        nombre = new String();
        especialidad = new String();
        distrito = 0;
        causasActuales = new HashMap<>();
        causasMax = new HashMap<>();
    }

    public Fiscal(String rut, String nombre, String especialidad, int distrito) {
        this.rut = rut;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.distrito = distrito;
        causasActuales = new HashMap<>();
        causasMax = new HashMap<>();
    }
//----------------------------------------------------------------------------//
//------------------------------Metodos Generales-----------------------------//
//----------------------------------------------------------------------------//

    /*Metodo Causas*/
    public void quitarCausa(String codigo) {
        causasActuales.remove(codigo);
    }

 /*Metodo mostrar Fiscal*/
    public void mostrar() {
        System.out.println("Nombre Fiscal: " + nombre);
        System.out.println("Rut: " + rut);
        System.out.println("Especialidad: " + especialidad);
        System.out.println("Distrito: " + distrito);
    }

    /*Imprime Fiscal por Filtro a buscar*/
    public void imprimirFiscal(String filtro, int atributoFiscal, HashMap<String,Fiscal> fiscales) {
        /*Nombre*/
        if (atributoFiscal == 2) {
            System.out.println(DIVIDER);
            System.out.println("Nombre a buscar: " + filtro + "\nCargando...");
            System.out.println(DIVIDER);
            for (Entry<String, Fiscal> aux : fiscales.entrySet()) {
                Fiscal values = aux.getValue();
                if (values.nombre.equals(filtro)) {
                    values.mostrar();
                    System.out.println(DIVIDER);
                }
            }
        }
        /*RUT*/
        if (atributoFiscal == 3) {
            System.out.println(DIVIDER);
            System.out.println("Rut a buscar: " + filtro + "\nCargando...");
            Fiscal buscado = fiscales.get(filtro);
            System.out.println(DIVIDER);
            /*Mensaje en caso de no existir rut*/
            if (buscado == null) {
                System.out.println(FISCAL_NO_EXISTE);
            } else {
                buscado.mostrar();
            }
            System.out.println(DIVIDER);
        }
        /*Especialidad*/
        if (atributoFiscal == 4) {
            System.out.println(DIVIDER);
            System.out.println("Especialidad a buscar: " + filtro + "\nCargando...");
            System.out.println(DIVIDER);
            for (Entry<String, Fiscal> aux : fiscales.entrySet()) {
                Fiscal values = aux.getValue();
                if (values.especialidad.equals(filtro)) {
                    values.mostrar();
                    System.out.println(DIVIDER);
                }
            }
        }
    }

    /*Imprime Fiscal por Distrito a buscar*/
    public void imprimirFiscal(int dis_filtro, HashMap<String,Fiscal> fiscales) {
        System.out.println(DIVIDER);
        System.out.println("Distrito a buscar: " + dis_filtro + "\nCargando...");
        System.out.println(DIVIDER);

        for (Entry<String, Fiscal> aux : fiscales.entrySet()) {
            Fiscal values = aux.getValue();
            if (values.distrito == dis_filtro) {
                values.mostrar();
                System.out.println(DIVIDER);
            }
        }
    }
    
    /*Imprime Fiscal por tipo de Causa*/
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
        
    public void maxFiscal(HashMap<String,Fiscal> fiscales){
        int max=0;
        for (Entry<String, Causa> aux : causasActuales.entrySet()) {
            Causa values = aux.getValue();
            String codigo = aux.getKey();
            max++;
            causasMax.put(codigo,max);
        }
    }

//----------------------------------------------------------------------------//
//----------------------------Metodos de Escritura----------------------------//
//----------------------------------------------------------------------------//

    /*Escribe los fiscales en el archivo de reporte. recibe de parametro el writer*/
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

    /*Escribe las causas de cada fiscal en el archivo de reporte. Recibe de parametro el writer*/
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

    /*Causa Actual*/
    public HashMap<String, Causa> getCausasActuales() {
        return causasActuales;
    }

    public void setCausasActuales(HashMap causasActuales) {
        this.causasActuales = causasActuales;
    }

    public void agregarCausa(Causa nueva) {
        causasActuales.put(nueva.getCodigo(), nueva);
    }

    public HashMap<String, Integer> getCausasMax() {
        return causasMax;
    }

    public void setCausasMax(HashMap<String, Integer> causasMax) {
        this.causasMax = causasMax;
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
