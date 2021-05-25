package com.manzanitacreations.proyectofiscalia;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
*@author Marlene Lagos
*@author Valentina San Martin
*@author Matias Mujica
*ICI3241-1
 */
//----------------------------------------------------------------------------//
//------------------------------Fiscalia Child--------------------------------//
//----------------------------------------------------------------------------//
public abstract class Causa extends Fiscalia {

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

    /*Metodo mostrar causa basico*/
    @Override
    public void mostrar() {
        System.out.println("Codigo Causa: " + codigo);
        System.out.println("Estado: " + estado);
        System.out.println("Tipo de Caso: " + tipoCaso);
        System.out.println("Distrito: " + distrito);
    }

    /*Muestra por pantalla la causa*/
    public void mostrarCausa() {
        System.out.println("Codigo Causa: " + codigo);
        System.out.println("Estado: " + estado);
        System.out.println("Tipo de Caso: " + tipoCaso);
        System.out.println("Distrito: " + distrito);
        if (encargado != null && !encargado.getRut().equals("")) {
            System.out.println(FISCAL_DIV);
            encargado.mostrar();
        } else {
            System.out.println(DIVIDER);
            System.out.println(CAUSA_NO_FISCAL);
        }
        System.out.println(DIVIDER);
    }

    /*Metodo que muestra los procedimientos de una causa por pantalla*/
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

    /*Funcion para asignar un fiscal a la causa*/
 /*Recibe como parámetros el mapa de fiscales y la causa a asignar*/
    public void asignarFiscal(HashMap<String, Fiscal> fiscales, Causa asignada) {
        if (encargado.getRut().equals("")) {
            System.out.println(FISCAL_REC);
            for (Entry<String, Fiscal> entry : fiscales.entrySet()) {
                Fiscal aux = entry.getValue();
                if (aux.getEspecialidad().equals(tipoCaso)) {
                    aux.mostrar();
                    System.out.println(DIVIDER);
                }
            }
            System.out.println(RUT);
            String rut = LEER.nextLine();
            /*verificacion*/
            rut = esRut(rut);
            encargado = fiscales.get(rut);
            if (encargado == null) {
                System.out.println(FISCAL_NO_EXISTE);
            }
            encargado.getCausasActuales().put(codigo, asignada);
        } else {
            System.out.println(CAUSA_TIENE_FISCAL);
        }
    }

    /*Metodo para reemplazar al fiscal encargado de una causa*/
 /*Los parámetros son el fiscal nuevo, el mapa de fiscales y la causa*/
    public void reemplazarFiscal(Fiscal nuevo, HashMap<String, Fiscal> fiscales, Causa actual) {
        String rut = encargado.getRut();
        Fiscal aux = fiscales.get(rut);
        if (aux != null) {
            aux.getCausasActuales().remove(codigo);
        }
        encargado = nuevo;
        nuevo.getCausasActuales().put(codigo, actual);
    }

    /*Permite modificar el resultado de un procedimiento*/
    @Override
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

    /*Elimina un procedimiento de la lista de procedimientos*/
    @Override
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

    /*Escribe los procedimientos de cada causa en el archivo reporte.Recibe de parametro el writer*/
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

    /*Causa*/
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
}
//----------------------------------------------------------------------------//
//-------------------------------------Fin------------------------------------//
//----------------------------------------------------------------------------//
