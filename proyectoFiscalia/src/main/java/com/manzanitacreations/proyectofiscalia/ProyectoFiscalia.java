
package com.manzanitacreations.proyectofiscalia;

import java.io.File; 
import java.io.FileNotFoundException; 
import java.util.Scanner;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/*
*
*
*@author Marlene Lagos
*@author Valentina San Martin
*ICI3241-1
*/

public class ProyectoFiscalia {
/*--------------------------------Método main------------------------------------*/
  public static void main(String[] args) throws IOException {
         Fiscalia nueva=new Fiscalia();
         int opcion=0;
/*--------------------------------Lectura archivos .csv------------------------------------*/
         leerFiscales(nueva.getFiscales());
         leerCausas(nueva.getCausas(),nueva.getFiscales());
/*--------------------------------Menú------------------------------------*/
     do{
         
         Scanner leer= new Scanner(System.in);
         System.out.println("                           Programa fiscalía");
         System.out.println("----------------------------------------------------------");
         System.out.println("¿Que desea Hacer?");
         System.out.println("1-Ver los fiscales");
         System.out.println("2-Ver las causas");
         System.out.println("3-Buscar fiscal");
         System.out.println("4-Buscar causa");
         System.out.println("5-Agregar fiscal nuevo");
         System.out.println("6-Agregar causa nueva");
         System.out.println("7-Agregar procedimiento a una causa");
         System.out.println("8-Asignar una causa a un fiscal");
         System.out.println("9-Modificar el distrito de un fiscal");
         System.out.println("10-Cambiar el fiscal encargado de una causa");
         System.out.println("11-Cambiar el resultado de un procedimiento");
         System.out.println("12-Eliminar un fiscal");
         System.out.println("13-Eliminar una causa");
         System.out.println("14-Eliminar un procedimiento");
         System.out.println("0-Salir del programa");
         System.out.println("----------------------------------------------------------");
         opcion=Integer.parseInt(leer.nextLine());
/*--------------------------------Switch para las posibles opciones------------------------------------*/
         switch(opcion){
             /*Mostrar mapa de fiscales*/
             case 1:
                     nueva.mostrarFiscales();
                     break;
            /*Mostrar mapa de causas*/
            case 2:
                     nueva.mostrarCausas();
                     break;
            /*Buscar fiscal*/
            case 3:
                    nueva.buscarFiscal();
                     break;
            /*Buscar causa*/ 
            case 4:
                    nueva.buscarCausa();
                     break;
             /*Ingresar nuevo fiscal*/
            case 5:
                    nueva.nuevoFiscal();
                     break;
             /*Ingresar nueva causa*/
            case 6:
                    nueva.nuevaCausa();
                     break;
           /*Ingresar nuevo procedimiento a una causa*/
            case 7:
                    nueva.nuevoProcedimiento();
                     break;
           /*Asignar una causa a un fiscal*/
            case 8:
                     nueva.asignarFiscal();
                     break;
            case 0:  
                     System.out.println("Hasta Pronto");
                     break;
            default:
                System.out.println("Por favor ingrese una opcion valida");
           }
     }while(opcion !=0); 
         
}
    /*Método para leer los fiscales desde archivo*/    
     public static void leerFiscales(HashMap<String,Fiscal>fiscales){
      try{
         File archivo= new File("src/main/resources/Fiscales.csv");//abrir archivo csv
         Scanner lector= new Scanner(archivo);
         while (lector.hasNextLine()) {//Leer una por una las líneas del archivo
             String linea = lector.nextLine();
             StringTokenizer partes=new StringTokenizer(linea,";");//Dividir la linea en partes para separar los elementos
             Fiscal nuevo=new Fiscal();//Crear un fiscal para asignarle todos los atributos sacados de la linea
             nuevo.setNombre(partes.nextToken(";"));
             nuevo.setRut(partes.nextToken(";"));
             nuevo.setEspecialidad(partes.nextToken(";"));
             String aux= partes.nextToken(";");
             String dist=partes.nextToken(";");
             nuevo.setDistrito(Integer.parseInt(dist.replace(";"," ")));
             fiscales.put(nuevo.getRut(),nuevo);//Ingresar el fiscal en el mapa de fiscales
         }
         lector.close();
     }catch (FileNotFoundException e) {//Dice que hacer en caso de que no exista el archivo
      System.out.println("No se ha encontrado el archivo de Fiscales");
     }
   }
     
  /*Método para leer las causas desde archivo*/   
  public static void leerCausas(HashMap<String,Causa>causas,HashMap<String,Fiscal> fiscales){
      try{
         File archivo= new File("src/main/resources/causas.csv");//Leer una por una las líneas del archivo
         Scanner lector= new Scanner(archivo);
         while (lector.hasNextLine()) {//Dividir la linea en partes para separar los elementos
             String linea = lector.nextLine();
             StringTokenizer partes=new StringTokenizer(linea,";");//Dividir la linea en partes para separar los elementos
             Causa nuevo=new Causa();//Crear una causa para asignarle todos los atributos sacados de la linea
             nuevo.setCodigo(partes.nextToken(";"));
             String fiscal= partes.nextToken(";");
             Fiscal aux= fiscales.get(fiscal);//Busca al Fiscal de esa causa si es que ya está asignado
             nuevo.setEncargado(aux);
             nuevo.setEstado(partes.nextToken(";"));
             nuevo.setTipoCaso(partes.nextToken(";"));
             nuevo.setDistrito(Integer.parseInt(partes.nextToken(";")));
             aux.agregarCausa(nuevo);
             causas.put(nuevo.getCodigo(),nuevo);//Ingresa la Causa al mapa causas
         }
         lector.close();
     }catch (FileNotFoundException e) {
      System.out.println("No se ha encontrado el archivo de Causas");
     }
  }
  
  
}


