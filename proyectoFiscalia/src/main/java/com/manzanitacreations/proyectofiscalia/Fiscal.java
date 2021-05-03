package com.manzanitacreations.proyectofiscalia;

import java.io.FileWriter;
import java.io.File;
import java.util.HashMap;
import java.util.Hashtable;
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
    
    public void escribirFiscal(File f){
       try {
       FileWriter writer = new FileWriter(f);
       writer.write("Nombre Fiscal:"+ nombre+"\n");
       writer.write("Rut:"+ rut+"\n");
       writer.write("Especialidad:"+ especialidad+"\n");
       writer.write("Distrito:"+ distrito+"\n");
       }
       catch (Exception e) {
         System.err.println(e);
       }
    }
    
    public  void escribirCausas(HashMap<String,Causa>causas, File f){
     try {
         FileWriter writer = new FileWriter(f);
         int tamaño=causas.size();
         if(tamaño!=0){
             for (Map.Entry<String, Causa> entry : causas.entrySet()) {
                 Causa aux=entry.getValue();
                 writer.write("Codigo Causa:"+ aux.getCodigo()+"\n");
                 writer.write("Rut Encargado:"+ aux.getEncargado().getRut()+"\n");
                 writer.write("Estado:"+ aux.getEstado()+"\n");
                 writer.write("Tipo de Caso:"+ aux.getTipoCaso()+"\n");
                 writer.write("Distrito:"+ aux.getDistrito()+"\n");
                 writer.write("----------------------------------------------------------\n");
                 aux.escribirProcedimientos(f);
             }
         }
     }
      catch (Exception e) {
         System.err.println(e);
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
