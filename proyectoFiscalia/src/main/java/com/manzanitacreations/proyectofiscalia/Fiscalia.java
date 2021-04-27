/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.manzanitacreations.proyectofiscalia;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Marlene
 */
public class Fiscalia {
    private HashMap<String,Fiscal> fiscales;
    private HashMap<String,Causa> causas;
    private String contraseña;

    public Fiscalia() {
        fiscales=new HashMap<>();
        causas=new HashMap<>();
        contraseña="admin";
    }
    
  
  /*Método para mostrar todos los fiscales del Hashtable fiscales*/
 public void mostrarFiscales(){
       for (Map.Entry<String, Fiscal> entry : fiscales.entrySet()) {
             Fiscal aux=entry.getValue();
             aux.imprimirFiscal();//Muestra los fiscales por pantalla
             System.out.println("Causas:");
            mostrarCausas(aux.getCausasActuales());//muestra las causas de cada fiscal por pantalla
            System.out.println("----------------------------------------------------------");
       }
 }
 
  /*Método para mostrar todas las causas de la Hashtable causas*/
 public  void mostrarCausas(){
     int tamaño=causas.size();
     if(tamaño!=0){//Comprueba que si existen causas que mostrar
         for (Map.Entry<String, Causa> entry : causas.entrySet()) {
                Causa aux=entry.getValue();
               System.out.println("Codigo Causa:"+ aux.getCodigo());
               System.out.println("Rut Encargado:"+ aux.getEncargado().getRut());
               System.out.println("Estado:"+ aux.getEstado());
               System.out.println("Tipo de Caso:"+ aux.getTipoCaso());
               System.out.println("Distrito:"+ aux.getDistrito());
               System.out.println("----------------------------------------------------------");
          }
     } 
 }
 
 /*Método para mostrar todas las causas de la Hashtable causas*/
 public  void mostrarCausas(HashMap<String,Causa>causas){
     int tamaño=causas.size();
     if(tamaño!=0){//Comprueba que si existen causas que mostrar
         for (Map.Entry<String, Causa> entry : causas.entrySet()) {
                Causa aux=entry.getValue();
               System.out.println("Codigo Causa:"+ aux.getCodigo());
               System.out.println("Rut Encargado:"+ aux.getEncargado().getRut());
               System.out.println("Estado:"+ aux.getEstado());
               System.out.println("Tipo de Caso:"+ aux.getTipoCaso());
               System.out.println("Distrito:"+ aux.getDistrito());
               System.out.println("----------------------------------------------------------");
          }
     } 
 }
 /*Método para buscar un fiscal y mostrarlo por pantalla*/
 public void buscarFiscal(){
     Scanner leer= new Scanner(System.in);
     System.out.println("Ingrese el rut del fiscal sin puntos y con guion");/**Falta verificacion*/
     String rut= leer.nextLine();//Se lee el rut del fiscal
     Fiscal buscado= fiscales.get(rut);
      if(buscado==null){//Se comprueba que el fiscal exista
           System.out.println("No se ha encontrado al fiscal buscado");
       }else{
           buscado.imprimirFiscal();//Se muestra al fiscal por pantalla
           System.out.println("Causas:");
           mostrarCausas(buscado.getCausasActuales());//Se muestran las causas del fiscal
      }
 }
 
 /*Método para buscar una causa y mostrarla por pantalla*/
 public void buscarCausa(){
     Scanner leer= new Scanner(System.in);
     System.out.println("Ingrese el codigo de la causa");
     String codigo= leer.nextLine();//Se lee el codigo de la causa
     Causa buscar= causas.get(codigo);
      if(buscar!=null){//Se comprueba que la causa exista
                   buscar.imprimirCausa();//Se muestra la causa por pantalla
                   buscar.mostrarProcedimientos();//Se muestran los procedimientos de la causa
                   System.out.println("----------------------------------------------------------");
      }else{
          System.out.println("La causa buscada no existe");
      }
     
 }
 
 /*Método para buscar una causa y mostrarla por pantalla*/
 public void buscarCausa(HashMap<String,Causa> causas){
     Scanner leer= new Scanner(System.in);
     System.out.println("Ingrese el codigo de la causa");
     String codigo= leer.nextLine();//Se lee el codigo de la causa
     Causa buscar= causas.get(codigo);
      if(buscar!=null){//Se comprueba que la causa exista
                   buscar.imprimirCausa();//Se muestra la causa por pantalla
                   buscar.mostrarProcedimientos();//Se muestran los procedimientos de la causa
                   System.out.println("----------------------------------------------------------");
      }else{
          System.out.println("La causa buscada no existe");
      }
     
 }
 
 
  /*Método para leer un fiscal e ingresarlo a la Hashtable fiscales*/
 public void nuevoFiscal(){
     Scanner leer= new Scanner(System.in);
     System.out.println("Ingrese el rut sin puntos y con guion");
     String rut=leer.nextLine();//Se lee le rut del fiscal a ingresar
     boolean existe= fiscales.containsKey(rut);
     if(existe){//Se comprueba que no exista un fiscal con el mismo rut
         System.out.println("Ya existe un fiscal con ese rut");
     }else{
         System.out.println("Ingrese el nombre del fiscal");//Se leen los datos del fiscal
         String nombre=leer.nextLine();
         System.out.println("Ingrese la especialidad del fiscal");
         String especialidad=leer.nextLine();
         System.out.println("Ingrese el distrito");
         int distrito=Integer.parseInt(leer.nextLine());
     
         Fiscal nuevo= new Fiscal(nombre,rut,especialidad,distrito);//Se crea un nuevo objeto Fiscal con los datos
         fiscales.put(rut,nuevo);//El fiscal es ingresado al mapa
     }
     
 }
 
 /*Método para leer una causa e ingresarla a la HashTable causas*/
 public void nuevaCausa(){
      Scanner leer= new Scanner(System.in);
     System.out.println("Ingrese el codigo del caso");
     String codigo=leer.nextLine();//Se lee el codigo de la causa
     boolean existe= causas.containsKey(codigo);
     if(existe){//Se comprueba que no exista una causa con el mismo código
         System.out.println("Ya existe este caso");
     }else{
         System.out.println("Ingrese el nombre del estado del caso");//Se leen los datos de la causa
         String estado=leer.nextLine();
         System.out.println("Ingrese el tipo de caso");
         String tipoCaso=leer.nextLine();
         System.out.println("Ingrese el distrito");
         int distrito=Integer.parseInt(leer.nextLine());
     
         Causa nueva= new Causa(codigo,estado,tipoCaso,distrito);//Se crea un nuevo objeto Causa con los datos
         causas.put(codigo,nueva);//Se ingresa la causa al mapa causas
     }
     
 }
 
 /*Método para leer un nuevo procedimiento e ingresarlo al Vector peritajes dentro de una causa*/
  public void nuevoProcedimiento(){
      Scanner leer= new Scanner(System.in);
      System.out.println("Ingrese el codigo de la causa");
      String codigo= leer.nextLine();//Se lee el codigo de la causa a la que se le va a ingresar un procedimiento
      Causa buscar= causas.get(codigo);
      if(buscar!=null){//Se comprueba que la causa exista
                   buscar.imprimirCausa();//Se muestra la causa por pantalla para que el usuario pueda ver los detalles
                   buscar.mostrarProcedimientos();
                   System.out.println("----------------------------------------------------------");
                   nuevoProcedimiento(buscar);             
      }else{
          System.out.println("La causa buscada no existe");
      }
     }  
  
   public void nuevoProcedimiento(Causa buscar){
       Scanner leer= new Scanner(System.in);
       System.out.println("Ingrese el nombre del nuevo procedimiento");//Se leen los datos del nuevo procedimiento
       String nombreProc=leer.nextLine();
       Procedimiento nuevo=new Procedimiento();
       nuevo.setNombreProc(nombreProc);
       int opcion=0;
       do{/*Se leen los datos de los participantes del procedimiento hasta que el usuario seleccione la opcion 0
       (Los procedimientos deben tener minimo un participante, pero no hay un máximo */
           System.out.println("Ingrese el nombre del participante");
           String nombre=leer.nextLine();
           System.out.println("Ingrese el rol del participante (policia, abogado, testigo,etc...)");
           String rol=leer.nextLine();
           nuevo.ingresarParticipante(nombre, rol);
           System.out.println("¿Desea ingresar más participantes?");
           System.out.println("1-SI/0-NO"); 
           opcion=Integer.parseInt(leer.nextLine());
      }while(opcion!=0);
      System.out.println("Ingrese el resultado del nuevo procedimiento");
      String result=leer.nextLine();
      nuevo.setResultado(result);
      buscar.getPeritajes().addLast(nuevo);//Se ingresa el procedimiento a la lista que hay dentro de la causa
   }  
    
   void asignarFiscal(){
          Scanner leer= new Scanner(System.in);
          System.out.println("Ingrese el codigo de la causa a asignar");
          String codigo=leer.nextLine();
          Causa asignada=causas.get(codigo);
          if(asignada!=null){
                System.out.println(codigo);
                asignada.asignarFiscal(fiscales,asignada);
          }else{
                 System.out.println("La causa requerida no existe");
          }
   }
   
 void modificarDistrito(){
     Scanner leer= new Scanner(System.in);
     System.out.println("Ingrese el rut del fiscal sin puntos y con guion");/**Falta verificacion*/
     String rut= leer.nextLine();//Se lee el rut del fiscal
     Fiscal buscado= fiscales.get(rut);
      if(buscado==null){//Se comprueba que el fiscal exista
           System.out.println("No se ha encontrado al fiscal buscado");
       }else{
           buscado.imprimirFiscal();//Se muestra al fiscal por pantalla
           System.out.println("Ingrese el nuevo distrito");
           int dist= Integer.parseInt(leer.nextLine());
           while(dist<0 || dist>28){
                  System.out.println("El valor ingresado no es valido. Por favor intente de nuevo");
                  dist= Integer.parseInt(leer.nextLine());
           }
           buscado.setDistrito(dist);
      }
 }
 
 
   
/**------------------------------------------Getter y Setter------------------------------------------------*/
    public HashMap<String, Fiscal> getFiscales() {
        return fiscales;
    }

    public void setFiscales(HashMap<String, Fiscal> fiscales) {
        this.fiscales = fiscales;
    }

    public HashMap<String, Causa> getCausas() {
        return causas;
    }

    public void setCausas(HashMap<String, Causa> causas) {
        this.causas = causas;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

}
