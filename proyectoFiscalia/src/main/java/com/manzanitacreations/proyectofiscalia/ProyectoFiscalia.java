package com.manzanitacreations.proyectofiscalia;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.StringTokenizer;

/*
*@author Marlene Lagos
*@author Valentina San Martin
*@author Matias Mujica
*ICI3241-1
 */
//----------------------------------------------------------------------------//
//------------------------------------Main------------------------------------//
//----------------------------------------------------------------------------//
public class ProyectoFiscalia {

    /*formato*/
    private static final String REPORTE = "src/main/resources/reporte.txt";
    private static final String DIVIDER = "----------------------------------------------------------";
    private static final String FISCALES = "src/main/resources/Fiscales.csv";
    private static final String CAUSAS = "src/main/resources/causas.csv";
    private static final String ERROR = "Se ah producido un error, intente nuevamente";
    private static final String FISCAL_DIV = "Fiscales:";
    private static final String CAUSA_DIV = "Causas:";
    private static final String EXIT = "Hasta Pronto";

    /*principal*/
    public static void main(String[] args) throws IOException {
        /*casting*/
        Fiscalia fiscalia = new Fiscalia();
        /*lectura archivos .csv*/
        leerFiscales(fiscalia.getFiscales());
        leerCausas(fiscalia.getCausas(), fiscalia.getFiscales());
//----------------------------------------------------------------------------//
//------------------------------------Menu------------------------------------//
//----------------------------------------------------------------------------//
        int opcion;
        do {
            Scanner leer = new Scanner(System.in);
            System.out.println("                           Programa fiscalía");
            System.out.println(DIVIDER);
            System.out.println("¿Que desea hacer?");
            System.out.println("1-Ver los fiscales");
            System.out.println("2-Ver las causas");
            System.out.println("3-Buscar fiscal");
            System.out.println("4-Buscar causa");
            System.out.println("5-Agregar fiscal nuevo");
            System.out.println("6-Agregar causa nueva");
            System.out.println("7-Agregar procedimiento a una causa");
            System.out.println("8-Asignar una causa a un fiscal");
            System.out.println("9-Modificar el distrito de un fiscal");
            System.out.println("10-Modificar el estado de una causa");
            System.out.println("11-Cambiar el fiscal encargado de una causa");
            System.out.println("12-Cambiar el resultado de un procedimiento");
            System.out.println("13-Eliminar un fiscal");
            System.out.println("14-Eliminar una causa");
            System.out.println("15-Eliminar un procedimiento");
            System.out.println("16-Buscar Fiscal por filtro de busqueda");
            System.out.println("0-Salir del programa");
            System.out.println(DIVIDER);
            opcion = Integer.parseInt(leer.nextLine());
            switch (opcion) {
                case 1:
                    fiscalia.mostrar();
                    break;
                case 2:
                    fiscalia.mostrarCausas();
                    break;
                case 3:
                    fiscalia.buscarFiscal();
                    break;
                case 4:
                    fiscalia.buscarCausa();
                    break;
                case 5:
                    fiscalia.nuevoFiscal();
                    break;
                case 6:
                    fiscalia.nuevaCausa();
                    break;
                case 7:
                    fiscalia.nuevoProcedimiento();
                    break;
                case 8:
                    fiscalia.asignarFiscal();
                    break;
                case 9:
                    fiscalia.modificarDistrito();
                    break;
                case 10:
                    fiscalia.modificarEstadoCausa();
                    break;
                case 11:
                    fiscalia.cambiarFiscal();
                    break;
                case 12:
                    fiscalia.modificarProcedimiento();
                    break;
                case 13:
                    fiscalia.eliminarFiscal();
                    break;
                case 14:
                    fiscalia.eliminarCausa();
                    break;
                case 15:
                    fiscalia.eliminarProcedimiento();
                    break;
                case 16:
                    fiscalia.filtros();
                    break;
                case 0:
                    System.out.println(EXIT);
                    break;
                default:
                    System.out.println(ERROR);
            }
        } while (opcion != 0);
        /*exportar EXP*/
        exportar(fiscalia.getFiscales(), fiscalia.getCausas(), fiscalia);
    }
//----------------------------------------------------------------------------//
//--------------------Lectura, Escritura y Mapas (ignorar)--------------------//
//----------------------------------------------------------------------------//

    /*Metodo para leer los fiscales desde archivo*/
    public static void leerFiscales(HashMap<String, Fiscal> fiscales) {
        try {
            File archivo = new File(FISCALES);//abrir archivo csv
            try ( Scanner lector = new Scanner(archivo)) {
                while (lector.hasNextLine()) {//Leer una por una las líneas del archivo
                    String linea = lector.nextLine();
                    StringTokenizer partes = new StringTokenizer(linea, ";");//Dividir la linea en partes para separar los elementos
                    Fiscal nuevo = new Fiscal();//Crear un fiscal para asignarle todos los atributos sacados de la linea
                    nuevo.setNombre(partes.nextToken(";"));
                    String rut = partes.nextToken(";");
                    nuevo.setRut(rut);
                    nuevo.setEspecialidad(partes.nextToken(";"));
                    /*Aux de Causas*/
                    String aux = partes.nextToken(";");
                    String dist = partes.nextToken(";");
                    nuevo.setDistrito(Integer.parseInt(dist.replace(";", " ")));
                    /*Ingresar el fiscal en el mapa de fiscales*/
                    fiscales.put(nuevo.getRut(), nuevo);
                }
            }
        } catch (FileNotFoundException e) {//Dice que hacer en caso de que no exista el archivo
            System.out.println(ERROR);
        }
    }

    /*Metodo para leer las causas desde archivo*/
    public static void leerCausas(HashMap<String, Causa> causas, HashMap<String, Fiscal> fiscales) {
        try {
            File archivo = new File(CAUSAS);//Leer una por una las líneas del archivo
            try ( Scanner lector = new Scanner(archivo)) {
                while (lector.hasNextLine()) {//Dividir la linea en partes para separar los elementos
                    String linea = lector.nextLine();
                    StringTokenizer partes = new StringTokenizer(linea, ";");//Dividir la linea en partes para separar los elementos
                    Causa nuevo = new Causa() {};//Crear una causa para asignarle todos los atributos sacados de la linea
                    nuevo.setCodigo(partes.nextToken(";"));
                    String fiscal = partes.nextToken(";");
                    Fiscal aux = fiscales.get(fiscal);//Busca al Fiscal de esa causa si es que ya está asignado
                    nuevo.setEncargado(aux);
                    nuevo.setEstado(partes.nextToken(";"));
                    nuevo.setTipoCaso(partes.nextToken(";"));
                    nuevo.setDistrito(Integer.parseInt(partes.nextToken(";")));
                    aux.agregarCausa(nuevo);
                    causas.put(nuevo.getCodigo(), nuevo);//Ingresa la Causa al mapa causas
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(ERROR);
        }
    }

    /*Crea un archivo txt donde se muestra un reporte de las colecciones anidadas*/
    public static void exportar(HashMap<String, Fiscal> fiscales, HashMap<String, Causa> causas, Fiscalia fiscalia) {
        try {
            File f = new File(REPORTE);
            if (f.exists()) {
                f.delete();
                f = new File(REPORTE);
            }
            escribirEnArchivo(f, fiscalia.getFiscales(), fiscalia.getCausas(), fiscalia);
        } catch (Exception e) {
            System.err.println(e);
        }
    }
//----------------------------------------------------------------------------//
//----------------------------Metodos de Escritura----------------------------//
//----------------------------------------------------------------------------//

    /*Escribe los datos de las colecciones anidadas en un archivo*/
 /*Recibe como parametros el archivo, los mapas a usar y la fiscalia*/
    public static void escribirEnArchivo(File f, HashMap<String, Fiscal> fiscales, HashMap<String, Causa> causas, Fiscalia fiscalia) {
        try {
            try ( PrintWriter writer = new PrintWriter(f)) {
                writer.println(FISCAL_DIV);
                for (Entry<String, Fiscal> entry : fiscales.entrySet()) {
                    Fiscal aux = (Fiscal) entry.getValue();
                    aux.escribirFiscal(writer);//Muestra los fiscales por pantalla
                    writer.println(CAUSA_DIV);
                    aux.escribirCausas(writer);//muestra las causas de cada fiscal por pantalla
                    writer.println(DIVIDER);
                }
                writer.close();
            }
        } catch (IOException e) {
            System.out.println(ERROR);
        }
    }
}
//----------------------------------------------------------------------------//
//-------------------------------------Fin------------------------------------//
//----------------------------------------------------------------------------//
