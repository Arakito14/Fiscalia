package com.manzanitacreations.proyectofiscalia;

import java.util.HashMap;
import java.util.Hashtable;

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
