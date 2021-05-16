/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.manzanitacreations.proyectofiscalia;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;


public class Causa implements Especialidad, Formato{
    private String codigo;
    private Fiscal encargado;
    private LinkedList<Procedimiento> peritajes;
    private String estado;
    private String tipoCaso;
    private int distrito;

    public Causa(){
      codigo=new String();
      encargado= new Fiscal();
      peritajes= new LinkedList<>();
      estado= new String();
      tipoCaso= new String();
      distrito= 0;
    }
    
    public Causa(String codigo,String estado, String tipoCaso, int distrito){
        this.codigo=codigo;
        this.estado=estado;
        this.tipoCaso=tipoCaso;
        this.distrito=distrito;
        peritajes= new LinkedList<>();
        encargado= new Fiscal();
    }
    
    
  /**--------------------------------------------Métodos-------------------------------------------*/
    
 /*Método que muestra los procedimientos de una causa por pantalla*/
    public void mostrarProcedimientos(){
        int tam=peritajes.size();
        if(tam!=0){
            System.out.println("Procedimientos");
            System.out.println("----------------------------------------------------------");
            for(int i=0;i<peritajes.size();i++){
                   System.out.println((i+1)+"-Nombre:"+ peritajes.get(i).getNombreProc());
                   System.out.println("Participantes:");
                   for(int j=0;j<peritajes.get(i).getParticipantes().size();j++){
                        System.out.println(peritajes.get(i).obtenerParticipante(j)+"/"+peritajes.get(i).obtenerRol(j));
                    }
                   System.out.println("Resultado:"+ peritajes.get(i).getResultado());
           }
        }else{
            System.out.println("Esta causa aun no tiene procedimientos");
        }
        
    }
    
    /*Escribe los procedimientos de cada causa en el archivo reporte.Recibe de parametro el writer*/
    public void escribirProcedimientos(PrintWriter writer){
        try {
            int tam=peritajes.size();
            if(tam!=0){
            writer.println("Procedimientos");
            writer.println("----------------------------------------------------------");
            for(int i=0;i<peritajes.size();i++){
                   writer.println((i+1)+"-Nombre:"+ peritajes.get(i).getNombreProc());
                   writer.println("Participantes:");
                   for(int j=0;j<peritajes.get(i).getParticipantes().size();j++){
                        writer.println(peritajes.get(i).obtenerParticipante(j)+"/"+peritajes.get(i).obtenerRol(j));
                    }
                   writer.println("Resultado:"+ peritajes.get(i).getResultado());
           }
           }else{
            writer.println("Esta causa aun no tiene procedimientos");
           }
        }
        catch (Exception e) {
             System.err.println(e);
        }
        
        
    }
 
/*Funcion para asignar un fiscal a la causa*/
/*Recibe como parámetros el mapa de fiscales y la causa a asignar*/
public void asignarFiscal(HashMap<String,Fiscal> fiscales, Causa asignada){
       
    if(encargado.getRut().equals("")){
       Scanner leer= new Scanner(System.in);
       System.out.println("Fiscales recomendados:");
       for (Map.Entry<String, Fiscal> entry : fiscales.entrySet()) {
             Fiscal aux=entry.getValue();
             if(aux.getEspecialidad().equals(tipoCaso)){
               aux.imprimirFiscal();
               System.out.println("----------------------------------------------------------");
            }
       }
       
       do{
          System.out.println("Ingrese el rut del fiscal elegido");
          String fiscal;
         boolean isValid;
         do{
                 fiscal= leer.nextLine();
                 isValid= comprobarRut(fiscal);
                 if(!isValid){
                  System.out.println("El formato es incorrecto. Por favor intente de nuevo");
                   }
           }while(!isValid);
          encargado= fiscales.get(fiscal);
          if(encargado!=null){
              break;
          }else{
               System.out.println("El fiscal ingresado no existe. Por favor intente de nuevo");
          }
       }while(true);
        encargado.getCausasActuales().put(codigo, asignada);
    }else{
           System.out.println("Esta causa ya tiene fiscal");
    }
       
       
    
}

/*Método para reemplazar al fiscal encargado de una causa*/
/*Los parámetros son el fiscal nuevo, el mapa de fiscales y la causa*/
public void reemplazarFiscal(Fiscal nuevo, HashMap<String,Fiscal> fiscales, Causa actual){
          String rut= encargado.getRut();
          Fiscal aux= fiscales.get(rut);
          if(aux!=null){
              aux.getCausasActuales().remove(codigo);
          }
          encargado=nuevo;
          nuevo.getCausasActuales().put(codigo, actual);
    }

/*Muestra por pantalla la causa*/
public void imprimirCausa(){
           System.out.println("Codigo Causa:"+ codigo);
           System.out.println("Estado:"+ estado);
           System.out.println("Tipo de Caso:"+ tipoCaso);
           System.out.println("Distrito:"+ distrito);
           if(encargado!=null && !encargado.getRut().equals("")){
              System.out.println("Encargado:");
              encargado.imprimirFiscal(); 
           }else{
               System.out.println("----------------------------------------------------------");
               System.out.println("Esta causa no tiene Fiscal encargado");
           }
           System.out.println("----------------------------------------------------------");
}


/*Permite modificar el resultado de un procedimiento*/
public void modificarProcedimiento(){
    System.out.println("Causa n°:"+ codigo);
    mostrarProcedimientos();
    Scanner leer= new Scanner(System.in);
    System.out.println("Ingrese el numero del procedimiento a modificar");
    int num=Integer.parseInt(leer.nextLine());
    if(num<= peritajes.size()){
        Procedimiento elegido= peritajes.get(num-1);
        System.out.println("Ingrese el nuevo resultado del procedimiento");
        String nuevo= leer.nextLine();
        elegido.setResultado(nuevo);
    }else{
        System.out.println("El numero de procedimiento es incorrecto");
    }
}
/*Elimina un procedimiento de la lista de procedimientos*/
public void eliminarProcedimiento(){
    Scanner leer= new Scanner(System.in);
    System.out.println("Ingrese el numero del procedimiento a eliminar");
    int num=Integer.parseInt(leer.nextLine());
    if(num<= peritajes.size()){
        peritajes.remove(num-1);
        System.out.println("El procedimiento fue eliminado exitosamente.");
    }else{
        System.out.println("El numero de procedimiento es incorrecto");
    }
}
/**--------------------------------------------Getter y setter-------------------------------------------*/
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

    public LinkedList<Procedimiento> getPeritajes() {
        return peritajes;
    }

    public void setPeritajes(LinkedList<Procedimiento> peritajes) {
        this.peritajes = peritajes;
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

    public int getDistrito() {
        return distrito;
    }

    public void setDistrito(int distrito) {
        this.distrito = distrito;
    }
 /**---------------------------------------------Implementacion interfaz Especialidad--------------------------------------*/   
 @Override
    public String asignarEspecialidad(int esp) {
          String asignada=new String();
          switch(esp){
              case 1:
                  asignada=DELITOS_ECONOMICOS;
                  break;
              case 2:
                  asignada=CRIMEN_ORGANIZADO;
                  break;
              case 3:
                 asignada=RESPONSABILIDAD_ADOLESCENTE;
                  break;
              case 4:
                  asignada=DELITOS_VIOLENTOS;
                  break;
              case 5:
                  asignada=VIOLENCIA_INTRAFAMILIAR;
                  break;
              case 6:
                  asignada=NARCOTRAFICO;
                  break;
              case 7:
                  asignada=CORRUPCION;
                  break;
              case 8:
                  asignada=DELITOS_SEXUALES;
                  break;
          }
         return asignada;
    }

    @Override
    public void mostrarOpciones() {
        System.out.println("Opciones:");
        System.out.println("1-"+DELITOS_ECONOMICOS);
        System.out.println("2-"+CRIMEN_ORGANIZADO);
        System.out.println("3-"+RESPONSABILIDAD_ADOLESCENTE);
        System.out.println("4-"+DELITOS_VIOLENTOS);
        System.out.println("5-"+VIOLENCIA_INTRAFAMILIAR);
        System.out.println("6-"+NARCOTRAFICO);
        System.out.println("7-"+CORRUPCION);
        System.out.println("8-"+DELITOS_SEXUALES);   
    }

    @Override
    public boolean comprobarCodigo(String cod) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean comprobarRut(String rut) {
       Matcher mat = PATRON_RUT.matcher(rut);
        if(mat.matches()){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public int comprobarDistrito(String dist) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


     
}
