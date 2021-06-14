package com.manzanitacreations.proyectofiscalia;

import ventanas.*;
import interfaces.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import  java.awt.*;
import java.awt.event.ActionEvent;

/*
*@author Marlene Lagos
*@author Valentina San Martin
*@author Matias Mujica
*ICI3241-1
 */
//----------------------------------------------------------------------------//
//------------------------------------Main------------------------------------//
//----------------------------------------------------------------------------//
public class ProyectoFiscalia implements FormatoMenu,FormatoEstado {


    /*principal*/
    public static void main(String[] args) throws IOException {
         MenuPrincipal menu= new MenuPrincipal();
         menu.setVisible(true);
         menu.setResizable(false);
         menu.setLocationRelativeTo(null);
        leerFiscales(menu.getFiscalia().getFiscales());
        leerCausas(menu.getFiscalia().getCausas(), menu.getFiscalia().getFiscales()); 
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
            System.out.println(ERROR_MENU);
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
                    
                    String cod=partes.nextToken(";");
                    String fiscal = partes.nextToken(";");
                    Fiscal aux = fiscales.get(fiscal);//Busca al Fiscal de esa causa si es que ya está asignado
                    String est=partes.nextToken(";");
                    String tipo=partes.nextToken(";");
                    int dis=Integer.parseInt(partes.nextToken(";"));
                    switch (est){
                        case ABIERTA:
                            CausaAbierta nuevaCA=new CausaAbierta();
                            nuevaCA.setCodigo(cod);
                            nuevaCA.setEncargado(aux);
                            nuevaCA.setEstado(est);
                            nuevaCA.setTipoCaso(tipo);
                            nuevaCA.setDistrito(dis);
                            aux.agregarCausa(nuevaCA);
                            causas.put(nuevaCA.getCodigo(),nuevaCA);
                            break;
                        case CERRADA:
                            CausaCerrada nuevaCC=new CausaCerrada();
                            nuevaCC.setCodigo(cod);
                            nuevaCC.setEncargado(aux);
                            nuevaCC.setEstado(est);
                            nuevaCC.setTipoCaso(tipo);
                            nuevaCC.setDistrito(dis);
                            aux.agregarCausa(nuevaCC);
                            causas.put(nuevaCC.getCodigo(),nuevaCC);
                            break;
                        case ARCHIVADA:
                            CausaArchivada nuevaCAR=new CausaArchivada();
                            nuevaCAR.setCodigo(cod);
                            nuevaCAR.setEncargado(aux);
                            nuevaCAR.setEstado(est);
                            nuevaCAR.setTipoCaso(tipo);
                            nuevaCAR.setDistrito(dis);
                            aux.agregarCausa(nuevaCAR);
                            causas.put(nuevaCAR.getCodigo(),nuevaCAR);
                            break;
                            
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(ERROR_MENU);
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
            System.out.println(ERROR_MENU);
        }
    }

    @Override
    public int esParticipante(String opcion_str) {
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
