/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.manzanitacreations.proyectofiscalia;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Causa {
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
                   System.out.println("Resultado"+ peritajes.get(i).getResultado());
           }
        }else{
            System.out.println("Esta causa aún no tiene procedimientos");
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
       System.out.println("Ingrese el rut del fiscal elegido");
       Pattern patron = Pattern.compile("[0-9]{8}-[0-9]{1}");
       String fiscal=leer.nextLine();
       Matcher mat = patron.matcher(fiscal);
       while(!mat.matches()){
          System.out.println("El formato es incorrecto. Por favor intente de nuevo");
          fiscal=leer.nextLine();
          mat=patron.matcher(fiscal);
     }
       encargado= fiscales.get(fiscal);
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
           System.out.println("Encargado:");
           encargado.imprimirFiscal();
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
    



     
}
