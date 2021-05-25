package com.manzanitacreations.proyectofiscalia;

import java.util.Scanner;
import java.util.regex.Pattern;

/*
*@author Marlene Lagos
*@author Valentina San Martin
*@author Matias Mujica
*ICI3241-1
 */
public interface Formato {
    static final Pattern PATRON_CODIGO = Pattern.compile("[0-9]{6}");
    static final Pattern PATRON_RUT = Pattern.compile("[0-9]{8}-[0-9]{1}");
    static final int MIN_DISTRITO = 1;
    static final int MAX_DISTRITO = 28;
    static final String ABIERTA = "Abierta";
    static final String CERRADA = "Cerrada";
    static final String ARCHIVADA = "Archivada";
    static final Scanner LEER = new Scanner(System.in);
    
    static final String RUT = "Ingrese el rut del fiscal, sin puntos y con guion";
    static final String CODIGO = "Ingrese el codigo de la causa. Debe ser un numero de 6 digitos";
    static final String DIS = "Ingrese el distrito. Debe ser un numero entre 1 y 28";
    static final String TIPO = "Ingrese el tipo de caso";
    static final String NOMBRE = "Ingrese el nombre";
    static final String ESTADO = "Ingrese el estado del caso";
    static final String FISCAL_ESP = "Ingrese la especialidad";
    static final String CAUSA_ESP = "Ingrese el tipo de caso";
    
    static final String PROC = "Ingrese el nombre del nuevo procedimiento";
    static final String PROC_CODIGO = "Ingrese el numero del procedimiento";
    static final String PROC_NOMBRE = "Ingrese el nombre del participante";
    static final String PROC_ROL = "Ingrese el rol del participante (policia, abogado, testigo,etc...)";
    static final String PROC_OPCION = "¿Desea ingresar más participantes?\n0-NO\n1-SI";
    static final String PROC_RESULTADO = "Ingrese el resultado del procedimiento";
    
    static final String DIVIDER = "----------------------------------------------------------";
    static final String FISCAL_DIV = "Encargado: ";
    static final String CAUSA_DIV = "Causas: ";
    static final String PROC_DIV = "Procedimientos: ";
    static final String FISCAL_REC = "Fiscales recomendados: ";
    
    
    static final String FISCAL_NO_EXISTE = "El fiscal buscado no existe";
    static final String FISCAL_NO_CAUSA = "Este fiscal aun no tiene causas";
    static final String CAUSA_NO_EXISTE = "La causa buscada no existe";
    static final String CAUSA_NO_FISCAL = "Esta causa no tiene Fiscal encargado";
    static final String CAUSA_NO_PROC = "Esta causa aún no tiene procedimientos";
    
    static final String FISCAL_EXISTE = "Ya existe un fiscal con ese rut";
    static final String CAUSA_EXISTE = "Ya existe este caso";
    static final String CAUSA_TIENE_FISCAL = "Esta causa ya tiene fiscal";
    
    static final String FISCAL_ELIMINAR = "El fiscal ha sido eliminado con exito";
    static final String CAUSA_ELIMINAR = "La causa ha sido eliminada con exito";
    static final String PROC_ELIMINAR = "El procedimiento ha sido eliminado con exito";
    
    static final String REGRESO = "A su orden, regresando al menu...";
    static final String INCORRECTO = "El formato es incorrecto. Por favor intente de nuevo";
    static final String ELIMINAR_NO_EXISTE = "No hay nada que eliminar";
    
    String comprobarEstado(int est);
    int esDis(String dis_str);
    int esEsp(String esp_str);
    String esRut(String rut);
    String esCodigo(String codigo);
    int esParticipante(String opcion_str);
}