package com.manzanitacreations.proyectofiscalia;

import java.io.FileWriter;
import java.util.HashMap;
import java.util.Hashtable;
import java.io.File;
import java.io.*;
import java.util.Map;

public class Fiscal {
    private String nombre;
    private String rut;
    private HashMap<String,Causa> causasActuales;
    private String especialidad;
    private int distrito;

    public Fiscal(){
      nombre= new String();
      rut= new String();
      causasActuales= new HashMap<>();
      especialidad = new String();
      distrito=0;
    }
    
    public Fiscal(String nombre, String rut,String especialidad, int distrito){
          this.nombre=nombre;
          this.rut=rut;
          this.especialidad=especialidad;
          this.distrito= distrito;
          causasActuales= new HashMap<>();
    }
    
    /**--------------------------------------------Métodos-------------------------------------------*/ 
    public void quitarCausa(String codigo){
        causasActuales.remove(codigo);
    }
    
    public void imprimirFiscal(){
       System.out.println("Nombre Fiscal:"+ nombre);
       System.out.println("Rut:"+ rut);
       System.out.println("Especialidad:"+ especialidad);
       System.out.println("Distrito:"+ distrito);
    }
  
   /*Escribe los fiscales en el archivo de reporte. recibe de parametro el writer*/
    public void escribirFiscal(PrintWriter writer){
       try {
       writer.println("Nombre Fiscal:"+ nombre);
       writer.println("Rut:"+ rut);
       writer.println("Especialidad:"+ especialidad);
       writer.println("Distrito:"+ distrito);
       }
       catch (Exception e) {
         System.err.println(e);
       }
    }
 /*Escribe las causas de cada fiscal en el archivo de reporte. Recibe de parametro el writer*/   
    public  void escribirCausas(PrintWriter writer){
        int tamaño=causasActuales.size();
        if(tamaño!=0){
            for (Map.Entry<String, Causa> entry : causasActuales.entrySet()) {
                Causa aux=entry.getValue();
                writer.println("Codigo Causa:"+ aux.getCodigo());
                writer.println("Rut Encargado:"+ aux.getEncargado().getRut());
                writer.println("Estado:"+ aux.getEstado());
                writer.println("Tipo de Caso:"+ aux.getTipoCaso());
                writer.println("Distrito:"+ aux.getDistrito());
                writer.println("----------------------------------------------------------");
                aux.escribirProcedimientos(writer);
            }
        }else{
            writer.println("Este fiscal aun no tiene causas");
            writer.println("----------------------------------------------------------");
        }
    
    }  
    /**--------------------------------------------Getter y setter------------------------------------------*/
    public void agregarCausa(Causa nueva){
        causasActuales.put(nueva.getCodigo(), nueva);
    }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getRut() {
            return rut;
        }

        public void setRut(String rut) {
            this.rut = rut;
        }

        public HashMap<String, Causa> getCausasActuales() {
            return causasActuales;
        }

        public void setCausasActuales(HashMap causasActuales) {
            this.causasActuales = causasActuales;
        }

        public String getEspecialidad() {
            return especialidad;
        }

        public void setEspecialidad(String especialidad) {
            this.especialidad = especialidad;
        }

        public int getDistrito() {
            return distrito;
        }

        public void setDistrito(int distrito) {
            this.distrito = distrito;
        }
}
