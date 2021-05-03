package com.manzanitacreations.proyectofiscalia;

import java.io.File; 
import java.io.FileNotFoundException; 
import java.util.Scanner;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

/*
*@author Marlene Lagos
*@author Valentina San Martin
*@author Matias Mujica
*ICI3241-1
*/

public class ProyectoFiscalia {

/*--------------------------------Metodo main------------------------------------*/

  public static void main(String[] args) throws IOException {
         Fiscalia nueva=new Fiscalia();
         Fiscal fis =new Fiscal();
         int opcion;

/*--------------------------------Lectura archivos .csv------------------------------------*/

         leerFiscales(nueva.getFiscales());
         leerCausas(nueva.getCausas(),nueva.getFiscales());
         
         /*Filtros de busqueda*/
         leerFiscales(fis.getFiscales());
         mapaNombres(fis.getNombres());
         mapaEspecialidades(fis.getEspecialidades());
         mapaDistritos(fis.getDistritos());
         
  
/*--------------------------------Menú------------------------------------*/
     do{
         
         Scanner leer= new Scanner(System.in);
         
         System.out.println("                           Programa fiscalía");
         System.out.println("----------------------------------------------------------");
         
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
         System.out.println("10-Cambiar el fiscal encargado de una causa");
         System.out.println("11-Cambiar el resultado de un procedimiento");
         System.out.println("12-Eliminar un fiscal");
         System.out.println("13-Eliminar una causa");
         System.out.println("14-Eliminar un procedimiento");
         System.out.println("15-Buscar fiscales por filtro de busqueda");
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
           /*Modificar el distrito de un Fiscal*/
            case 9:
                    nueva.modificarDistrito();
                    break;
           /*Cambiar al fiscal encargado de una causa*/
            case 10:
                    nueva.cambiarFiscal();
                    break;
           /*Cambiar el resultado de un procedimiento*/
            case 11:
                    nueva.modificarProcedimiento();
                    break;
           /*Eliminar un fiscal*/
            case 12:
                    nueva.eliminarFiscal();
                    break;
          /*Eliminar una causa*/
            case 13:
                    nueva.eliminarCausa();
                    break;
          /*Eliminar un procedimiento*/
            case 14:
                    nueva.eliminarProcedimiento();
                    break;
          /*Busca fiscales por filtro de busqueda*/
            case 15: 
                    fis.filtroFiscal();
                    break;
            case 0:  
                    System.out.println("Hasta Pronto");
                    break;
            default:
                    System.out.println("Por favor ingrese una opcion valida");
           }
     }while(opcion !=0); 
     /*exportar EXP*/
     exportar(nueva.getFiscales(), nueva.getCausas(),nueva);
}

/*Metodo para leer los fiscales desde archivo*/    
     public static void leerFiscales(HashMap<String,Fiscal>fiscales){
      try{
         File archivo= new File("src/main/resources/Fiscales.csv");//abrir archivo csv
          try (Scanner lector = new Scanner(archivo)) {
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
              }}
     }catch (FileNotFoundException e) {//Dice que hacer en caso de que no exista el archivo
      System.out.println("No se ha encontrado el archivo de Fiscales");
     }
   }
     
/*Metodo para leer las causas desde archivo*/   
  public static void leerCausas(HashMap<String,Causa>causas,HashMap<String,Fiscal> fiscales){
      try{
         File archivo= new File("src/main/resources/causas.csv");//Leer una por una las líneas del archivo
          try (Scanner lector = new Scanner(archivo)) {
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
              }}
     }catch (FileNotFoundException e) {
      System.out.println("No se ha encontrado el archivo de Causas");
     }
  }
//Crea un archivo txt donde se muestra un reporte de las colecciones anidadas
  public static void exportar(HashMap<String,Fiscal> fiscales, HashMap<String,Causa>causas, Fiscalia nueva) {
     try {
         File f = new File("src/main/resources/reporte.txt");
         if (f.exists()) {
             f.delete();
             f=new File("src/main/resources/reporte.txt");
         }
         escribirEnArchivo(f, nueva.getFiscales(), nueva.getCausas(), nueva);
     }
     catch (Exception e) {
         System.err.println(e);
     }
  }
  
  /*--------------------------------------------Escritura-----------------------------------------*/  
/*Escribe los datos de las colecciones anidadas en un archivo*/
/*Recibe como parametros el archivo, los mapas a usar y la fiscalia*/
  public static void escribirEnArchivo(File f, HashMap<String,Fiscal> fiscales, HashMap<String,Causa>causas, Fiscalia nueva) {
         try {
             PrintWriter writer = new PrintWriter(f);
             writer.println("Fiscales");
             fiscales.entrySet().stream().map(entry -> (Fiscal)entry.getValue()).map(aux -> {
                 aux.escribirFiscal(writer);//Muestra los fiscales por pantalla
                 return aux;
             }).map(aux -> {
                 writer.println("Causas:");
                 aux.escribirCausas(writer);//muestra las causas de cada fiscal por pantalla
                 return aux;
             }).forEachOrdered(_item -> {
                 writer.println("----------------------------------------------------------");
             });
             writer.close();
         }
         catch (IOException e) {
            System.out.println("An error occurred.");
         }
  }  
  
/*--------------------------------------------Mapas Experimentales de Filtros de Busqueda-----------------------------------------*/  
/*Metodo para crear mapa de nombres desde archivo*/
     public static void mapaNombres(HashMap<String,String>nombres){
      try{
         File archivo= new File("src/main/resources/Fiscales.csv");//abrir archivo csv
          try (Scanner lector = new Scanner(archivo)) {
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
                  /*Ingresar los datos en el mapa de nombres*/
                  nombres.put(nuevo.getRut(),nuevo.getNombre());
              }}
     }catch (FileNotFoundException e) {//Dice que hacer en caso de que no exista el archivo
      System.out.println("No se ha encontrado el archivo de Fiscales");
     }
   }
     
/*Metodo para crear mapa de especialidades desde archivo*/
     public static void mapaEspecialidades(HashMap<String,String>especialidades){
      try{
         File archivo= new File("src/main/resources/Fiscales.csv");//abrir archivo csv
          try (Scanner lector = new Scanner(archivo)) {
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
                  /*Ingresar los datos en el mapa de especialidades*/
                  especialidades.put(nuevo.getRut(),nuevo.getEspecialidad());
              }}
     }catch (FileNotFoundException e) {//Dice que hacer en caso de que no exista el archivo
      System.out.println("No se ha encontrado el archivo de Fiscales");
     }
   }

/*Metodo para crear mapa de distritos desde archivo*/
     public static void mapaDistritos(HashMap<String,Integer>distritos){
      try{
         File archivo= new File("src/main/resources/Fiscales.csv");//abrir archivo csv
          try (Scanner lector = new Scanner(archivo)) {
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
                  /*Ingresar los datos en el mapa de distritos*/
                  distritos.put(nuevo.getRut(),nuevo.getDistrito());
              }}
     }catch (FileNotFoundException e) {//Dice que hacer en caso de que no exista el archivo
      System.out.println("No se ha encontrado el archivo de Fiscales");
     }
   } 
}