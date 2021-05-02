package com.manzanitacreations.proyectofiscalia;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Map;
import java.util.stream.Collectors;

public class Fiscal {
    private String nombre;
    private String rut;
    private HashMap<String,Causa> causasActuales;
    private String especialidad;
    
    /*Distrito en entero y string*/
    private int distrito;
    private String distrito_str;
    
    private int opcion;
    private int cont;
    private Scanner leer;
    
    private HashMap<String,Fiscal> fiscales;
    private HashMap<String,Fiscal> vacio;

    public Fiscal(){
        nombre= new String();
        rut= new String();
        causasActuales= new HashMap<>();
        especialidad = new String();
        
        /*distrito*/
        distrito=0;
        distrito_str= new String();
        
        fiscales = new HashMap<>();
        vacio = new HashMap<>();
    }

    public Fiscal(String nombre, String rut, String especialidad, int distrito) {
        this.nombre = nombre;
        this.rut = rut;
        this.especialidad = especialidad;
        this.distrito = distrito;
        causasActuales = new HashMap<>();
    }
    
    

/*--------------------------------------------Metodos de verificación-----------------------------------------*/
    
    public int esNum(String dis_str){
        boolean esNum;
        int dis_int = 0;
        
         do{
            esNum=true;
            try {
                dis_int=Integer.parseInt(dis_str);
                /*Volver a menu*/
                if(dis_int==0){
                    break;
                }
                if(dis_int<1|| dis_int>28){
                    esNum=false;
                    System.out.println("El numero ingresado debe ser entre 1 y 28. Por favor intente de nuevo");
                    dis_str=leer.nextLine();
                }
            } catch (NumberFormatException nfe){
                esNum=false;
                System.out.println("El formato es incorrecto. Por favor intente de nuevo");
                dis_str=leer.nextLine();
            }
         }while(!esNum);
         
         /*Retorna distrito en entero*/
         return dis_int;
    }
    
    public String esRut(String rut_user){
        boolean esRut;
        Pattern patron = Pattern.compile("[0-9]{8}-[0-9]{1}");
        
        do{
            esRut=true;
            try {
                Matcher mat = patron.matcher(rut_user);
                /*Volver a menu*/
                if("0".equals(rut_user)){
                    break;
                }
                if(!mat.matches()){
                    esRut=false;
                    System.out.println("El formato es incorrecto. Por favor intente de nuevo");
                    rut_user=leer.nextLine();
                }
            }catch (Exception e) {
                esRut=false;
                System.out.println("El formato es incorrecto EX. Por favor intente de nuevo");
                rut_user=leer.nextLine();
            }
        }while(!esRut);
        
        /*Retorna rut en formato correcto*/
        return rut_user;
    }
 
/*--------------------------------------------Métodos------------------------------------------
     * @param codigo-*/ 
    public void quitarCausa(String codigo){
        causasActuales.remove(codigo);
    }
    
/*Metodo para decidir el atributo a buscar*/
    public void filtroFiscal(){
        
/*--------------------------------Menú de Busqueda------------------------------------*/
    do{

        leer= new Scanner(System.in);
        
        System.out.println("                           Filtro de busqueda");
        System.out.println("----------------------------------------------------------");
         
        System.out.println("¿Que desea hacer?");
        System.out.println("1-Mostrar fiscales por nombre, rut, especialidad y distrito");
        System.out.println("2-Por nombre");
        System.out.println("3-Por rut");
        System.out.println("4-Por especialidad");
        System.out.println("5-Por distrito");
        System.out.println("0-Volver al menú principal (funciona dentro de cada opción)");
        
        System.out.println("----------------------------------------------------------");
        
        opcion=Integer.parseInt(leer.nextLine());
        
/*--------------------------------Switch para seleccion de metodo de sobrecarga------------------------------------*/
            switch(opcion){
            /*Muestra todos los fiscales y sus atributos principales*/
                case 1: 
                    imprimirFiscal();
                    break;
            /*Busca por nombre*/
                case 2:
                    System.out.println("Ingrese nombre a buscar");
                    leer= new Scanner(System.in);
                    String nombre_filtro  = leer.nextLine();
                    cont = 0;
                    
                    /*Volver a menú*/
                    if("0".equals(nombre_filtro)){
                        break;
                    }
                    
                    imprimirFiscal(cont,nombre_filtro);
                    break;
            /*Busca por RUT*/
                case 3:
                    System.out.println("Ingrese rut a buscar, sin puntos y con guion");
                    leer= new Scanner(System.in);
                    String rut_filtro  = leer.nextLine();
                    cont = 0;
                    
                    /*verificación*/
                    rut_filtro=esRut(rut_filtro);
                    /*Volver a menú*/
                    if("0".equals(rut_filtro)){
                        break;
                    }
                    
                    imprimirFiscal(rut_filtro);
                    break;
            /*Busca por especialidad*/
                case 4:
                    System.out.println("Ingrese especialidad a buscar");
                    leer= new Scanner(System.in);
                    String esp_filtro  = leer.nextLine();
                    cont = 0;
                    
                    /*Volver a menú*/
                    if("0".equals(esp_filtro)){
                        break;
                    }
                    
                    imprimirFiscal(esp_filtro,cont);
                    break;
            /*Busca por distrito*/
                case 5:
                    System.out.println("Ingrese distrito a buscar, entre 1 y 28");
                    leer= new Scanner(System.in);
                    
                    /*Distrito en entero y string*/
                    String dis_str  = leer.nextLine();
                    int dis_int;
                    cont = 0;
                    
                    /*verificación*/
                    dis_int=esNum(dis_str);
                    /*Volver a menú*/
                    if(dis_int==0){
                        break;
                    }
                    
                    imprimirFiscal(dis_int,cont);
                    break;
            /*Vuelve a menú enterior*/
                case 0:
                    System.out.println("A su servicio!");
                    break;
                default:
                    System.out.println("Por favor ingrese una opcion valida");
           }
     }while(opcion !=0); 
         
    }
    
    public void imprimirFiscal(){
        System.out.println("Nombre Fiscal: "+ nombre);
        System.out.println("Rut: "+ rut);
        System.out.println("Especialidad: "+ especialidad);
        System.out.println("Distrito: "+ distrito);
    }
    
    public void imprimirFiscal(String rut_filtro){
        System.out.println("Rut a buscar: "+rut_filtro+"\nCargando...");
        Fiscal buscado= fiscales.get(rut_filtro);
        if(buscado==null){
            System.out.println("El Rut buscado no existe");
       }else{
           buscado.imprimirFiscal();
        }
    }

    public void imprimirFiscal(String esp_filtro, int cont){
        System.out.println("Especialidad a buscar: "+esp_filtro+"\nCargando...");
        Fiscal buscado= vacio.get(esp_filtro);
        
        Iterator itr = vacio.entrySet().iterator();
        vacio.forEach(
                (key,value) -> {
                        if(key.equals(esp_filtro)){
                            buscado.imprimirFiscal();
                        }
                    }
            );
    }

    public void imprimirFiscal(int cont, String nombre_filtro){
        System.out.println("Nombre a buscar: "+nombre_filtro+"\nCargando...");
    }
    
    public void imprimirFiscal(int dis_filtro, int cont){
        System.out.println("Distrito a buscar: "+dis_filtro+"\nCargando...");
    }
    
/*--------------------------------------------Getter y setter-----------------------------------------
     * @param nueva-*/
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

    public HashMap<String, Fiscal> getFiscales() {
        return fiscales;
    }

    public void setFiscales(HashMap<String, Fiscal> fiscales) {
        this.fiscales = fiscales;
    }

    public HashMap<String, Fiscal> getVacio() {
        return vacio;
    }

    public void setVacio(HashMap<String, Fiscal> vacio) {
        this.vacio = vacio;
    }
    
}