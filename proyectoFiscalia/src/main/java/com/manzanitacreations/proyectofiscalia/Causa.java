package com.manzanitacreations.proyectofiscalia;

import interfaces.*;
import static interfaces.FormatoMenu.INCORRECTO;
import static interfaces.FormatoMenu.LEER;
import static interfaces.FormatoRut.PATRON_RUT;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Matcher;
/*
*@author Marlene Lagos
*@author Valentina San Martin
*@author Matias Mujica
*ICI3241-1
 */
//----------------------------------------------------------------------------//
//------------------------------Fiscalia Child--------------------------------//
//----------------------------------------------------------------------------//

public abstract class Causa implements FormatoMenu,FormatoRut {

    /*Primary Key*/
    private String codigo;
    /*Attributes*/
    private String estado;
    private String tipoCaso;
    /*Distrito*/
    private int distrito;
    /*Fiscal Encargado*/
    private Fiscal encargado;
    /*LinkedList*/
    private LinkedList<Procedimiento> peritajes;

    public Causa() {
        codigo = new String();
        estado = new String();
        tipoCaso = new String();
        distrito = 0;
        encargado = new Fiscal();
        peritajes = new LinkedList<>();
    }

    public Causa(String codigo, String estado, String tipoCaso, int distrito) {
        this.codigo = codigo;
        this.estado = estado;
        this.tipoCaso = tipoCaso;
        this.distrito = distrito;
        peritajes = new LinkedList<>();
        encargado = new Fiscal();
    }
//----------------------------------------------------------------------------//
//------------------------------Metodos Generales-----------------------------//
//----------------------------------------------------------------------------//

    /**
     * Metodo mostrar causa basico
     */
    public void mostrar() {
        System.out.println("Codigo Causa: " + codigo);
        System.out.println("Estado: " + estado);
        System.out.println("Tipo de Caso: " + tipoCaso);
        System.out.println("Distrito: " + distrito);
    }
/**
 * Muestra la información de la causa por pantalla
 */
    abstract public void imprimirCausa();

    /**
     * Metodo que muestra los procedimientos de una causa por pantalla
     * @return int, 0 si la causa no tiene procedimientos y 1 en cualquier otro caso
     */
    public int mostrarProcedimientos() {
        int tam = peritajes.size();
        if (tam != 0) {
            System.out.println(PROC_DIV);
            System.out.println(DIVIDER);
            for (int i = 0; i < peritajes.size(); i++) {
                System.out.println((i + 1) + "-Nombre:" + peritajes.get(i).getNombreProc());
                System.out.println("Participantes: ");
                for (int j = 0; j < peritajes.get(i).getParticipantes().size(); j++) {
                    System.out.println(peritajes.get(i).obtenerParticipante(j) + "/" + peritajes.get(i).obtenerRol(j));
                }
                System.out.println("Resultado: " + peritajes.get(i).getResultado());
            }
        } else {
            System.out.println(CAUSA_NO_PROC);
            return 1;
        }
        return 0;

    }

 /**
  * Metodo para reemplazar al fiscal encargado de una causa
  * @param nuevo Objeto tipo Fiscal que contiene al nuevo encargado
  * @param fiscales Mapa de fiscales
  * @param actual Objeto tipo Causa que contiene la causa a modificar
  */
    public void reemplazarFiscal(Fiscal nuevo, HashMap<String, Fiscal> fiscales, Causa actual) {
        String rut = encargado.getRut();
        Fiscal aux = fiscales.get(rut);
        if (aux != null) {
            aux.quitarCausa(codigo);
        }
        encargado = nuevo;
        encargado.agregarCausa(actual);
    }

/**
 * Permite modificar el resultado de un procedimiento
 */
    public void modificarProcedimiento() {
        System.out.println("Causa n°:" + codigo);
        int flag = mostrarProcedimientos();
        if (flag == 0) {
            System.out.println(PROC_CODIGO);
            int num = Integer.parseInt(LEER.nextLine());
            if (num <= peritajes.size()) {
                Procedimiento elegido = peritajes.get(num - 1);
                System.out.println(PROC_RESULTADO);
                String nuevo = LEER.nextLine();
                elegido.setResultado(nuevo);
            } else {
                System.out.println(INCORRECTO);
            }
        }
    }

 /**
  * Elimina un procedimiento de la lista de procedimientos
  */
    public void eliminarProcedimiento() {
        Scanner leer = new Scanner(System.in);
        System.out.println(PROC_CODIGO);
        int num = Integer.parseInt(leer.nextLine());
        if (num <= peritajes.size()) {
            peritajes.remove(num - 1);
            System.out.println(PROC_ELIMINAR);
        } else {
            System.out.println(INCORRECTO);
        }
    }
//----------------------------------------------------------------------------//
//----------------------------Metodos de Escritura----------------------------//
//----------------------------------------------------------------------------//

/**
 * Escribe los procedimientos de cada causa en el archivo reporte
 * @param writer PrintWriter que va a escribir en el archivo
 */
    public void escribirProcedimientos(PrintWriter writer) {
        try {
            int tam = peritajes.size();
            if (tam != 0) {
                writer.println(PROC_DIV);
                writer.println(DIVIDER);
                for (int i = 0; i < peritajes.size(); i++) {
                    writer.println((i + 1) + "-Nombre:" + peritajes.get(i).getNombreProc());
                    writer.println("Participantes:");
                    for (int j = 0; j < peritajes.get(i).getParticipantes().size(); j++) {
                        writer.println(peritajes.get(i).obtenerParticipante(j) + "/" + peritajes.get(i).obtenerRol(j));
                    }
                    writer.println("Resultado:" + peritajes.get(i).getResultado());
                }
            } else {
                writer.println(CAUSA_NO_PROC);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }
//----------------------------------------------------------------------------//
//------------------------------Getter y Setter-------------------------------//
//----------------------------------------------------------------------------//
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Fiscal getEncargado() {
        return encargado;
    }

    public void setEncargado(Fiscal encargado) {
        this.encargado = encargado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipoCaso() {
        return tipoCaso;
    }

    public void setTipoCaso(String tipoCaso) {
        this.tipoCaso = tipoCaso;
    }

    /*Distrito*/
    public int getDistrito() {
        return distrito;
    }

    public void setDistrito(int distrito) {
        this.distrito = distrito;
    }

    /*Peritajes*/
    public LinkedList<Procedimiento> getPeritajes() {
        return peritajes;
    }

    public void setPeritajes(LinkedList<Procedimiento> peritajes) {
        this.peritajes = peritajes;
    }
    
    
        @Override
    public int esParticipante(String opcion_str) {
               boolean esParticipante;
        int opcion_int = 1;

        do {
            esParticipante = true;

            try {
                opcion_int = Integer.parseInt(opcion_str);

                if (opcion_int > 1 || opcion_int < 0) {
                    esParticipante = false;
                    System.out.println(INCORRECTO);
                    opcion_str = LEER.nextLine();
                }
            } catch (NumberFormatException e) {
                esParticipante = false;
                System.out.println(INCORRECTO);
                opcion_str = LEER.nextLine();
            }
        } while (!esParticipante);

        /*Retorna la opcion en formato correcto*/
        return opcion_int;
    }
    @Override
    public String esRut(String rut) {
              boolean esRut;

        do {
            esRut = true;
            try {
                Matcher mat = PATRON_RUT.matcher(rut);
                if (!mat.matches()) {
                    esRut = false;
                    System.out.println(INCORRECTO);
                    rut = LEER.nextLine();
                }
            } catch (Exception e) {
                esRut = false;
                System.out.println(INCORRECTO);
                rut = LEER.nextLine();
            }
        } while (!esRut);

        /*Retorna rut en formato correcto*/
        return rut;
    }
    
     @Override
    public boolean confirmar(String rut) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
//----------------------------------------------------------------------------//
//-------------------------------------Fin------------------------------------//
//----------------------------------------------------------------------------//
