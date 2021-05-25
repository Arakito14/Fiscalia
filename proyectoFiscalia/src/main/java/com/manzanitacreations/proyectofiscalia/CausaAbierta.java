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
//----------------------------------------------------------------------------//
//------------------------------Metodos Generales-----------------------------//
//----------------------------------------------------------------------------//    

    /*Escribe los procedimientos de cada causa en el archivo reporte.Recibe de parametro el writer*/
    public void escribirProcedimientos(PrintWriter writer) {
        try {
            int tam = getPeritajes().size();
            if (tam != 0) {
                writer.println(PROC_DIV);
                writer.println(DIVIDER);
                for (int i = 0; i < getPeritajes().size(); i++) {
                    writer.println((i + 1) + "-Nombre:" + getPeritajes().get(i).getNombreProc());
                    writer.println("Participantes:");
                    for (int j = 0; j < getPeritajes().get(i).getParticipantes().size(); j++) {
                        writer.println(getPeritajes().get(i).obtenerParticipante(j) + "/" + getPeritajes().get(i).obtenerRol(j));
                    }
                    writer.println("Resultado:" + getPeritajes().get(i).getResultado());
                }
            } else {
                writer.println(CAUSA_NO_PROC);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    /*Funcion para asignar un fiscal a la causa*/
 /*Recibe como parámetros el mapa de fiscales y la causa a asignar*/
    public void asignarFiscal(HashMap<String, Fiscal> fiscales, Causa asignada) {
        if (getEncargado().getRut().equals("")) {
            System.out.println(FISCAL_REC);
            for (Map.Entry<String, Fiscal> entry : fiscales.entrySet()) {
                Fiscal aux = entry.getValue();
                if (aux.getEspecialidad().equals(getTipoCaso())) {
                    aux.mostrar();
                    System.out.println(DIVIDER);
                }
            }
            System.out.println(RUT);
            String rut = LEER.nextLine();
            /*verificacion*/
            rut = esRut(rut);
            setEncargado(fiscales.get(rut));
            if (getEncargado() == null) {
                System.out.println(FISCAL_NO_EXISTE);
            }
            getEncargado().getCausasActuales().put(getCodigo(), asignada);
        } else {
            System.out.println(CAUSA_TIENE_FISCAL);
        }
    }

    /*Método para reemplazar al fiscal encargado de una causa*/
 /*Los parámetros son el fiscal nuevo, el mapa de fiscales y la causa*/
    public void reemplazarFiscal(Fiscal nuevo, HashMap<String, Fiscal> fiscales, Causa actual) {
        String rut = getEncargado().getRut();
        Fiscal aux = fiscales.get(rut);
        if (aux != null) {
            aux.getCausasActuales().remove(getCodigo());
        }
        setEncargado(nuevo);
        nuevo.getCausasActuales().put(getCodigo(), actual);
    }

    /*Permite modificar el resultado de un procedimiento*/
    public void modificarProcedimiento() {
        System.out.println("Causa n°:" + getCodigo());
        mostrarProcedimientos();
        System.out.println(PROC_CODIGO);
        int num = Integer.parseInt(LEER.nextLine());
        if (num <= getPeritajes().size()) {
            Procedimiento elegido = getPeritajes().get(num - 1);
            System.out.println(PROC_RESULTADO);
            String nuevo = LEER.nextLine();
            elegido.setResultado(nuevo);
        } else {
            System.out.println(INCORRECTO);
        }
    }

    /*Elimina un procedimiento de la lista de procedimientos*/
    public void eliminarProcedimiento() {
        Scanner leer = new Scanner(System.in);
        System.out.println(PROC_CODIGO);
        int num = Integer.parseInt(leer.nextLine());
        if (num <= getPeritajes().size()) {
            getPeritajes().remove(num - 1);
            System.out.println(PROC_ELIMINAR);
        } else {
            System.out.println(INCORRECTO);
        }
    }
}
//----------------------------------------------------------------------------//
//-------------------------------------Fin------------------------------------//
//----------------------------------------------------------------------------//
