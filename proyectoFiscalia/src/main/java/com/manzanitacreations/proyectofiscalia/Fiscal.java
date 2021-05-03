package com.manzanitacreations.proyectofiscalia;

import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Fiscal {
    private String nombre;
    private String rut;
    private HashMap<String,Causa> causasActuales;
    private String especialidad;
    
    /*Distritos*/
    private int distrito;
    private String distrito_str;
    
    private int opcion;
    private int cont;
    private Scanner leer;
    
    /*Mapas para Filtro de Busqueda*/
    private HashMap<String,Fiscal> fiscales;
    private HashMap<String,String> nombres;
    private HashMap<String,String> especialidades;
    private HashMap<String,Integer> distritos;

    public Fiscal(){
        nombre= new String();
        rut= new String();
        causasActuales= new HashMap<>();
        especialidad = new String();
        
        distrito=0;
        
        /*Filtros de Busqueda*/
        fiscales = new HashMap<>();
        nombres = new HashMap<>();
        especialidades = new HashMap<>();
        distritos = new HashMap<>();
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
 
/*--------------------------------------------Metodos------------------------------------------
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
                    System.out.println("----------------------------------------------------------");
                    nombres.forEach(
                        (key,value) -> {
                            Fiscal buscado= fiscales.get(key);
                            buscado.imprimirFiscal();
                            System.out.println("----------------------------------------------------------");
                        }
                    );
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
        System.out.println("----------------------------------------------------------");
        System.out.println("Rut a buscar: "+rut_filtro+"\nCargando...");
        Fiscal buscado= fiscales.get(rut_filtro);
        System.out.println("----------------------------------------------------------");
        
        if(buscado==null){
            System.out.println("El Rut buscado no existe");
       }else{
           buscado.imprimirFiscal();
        }
        System.out.println("----------------------------------------------------------");
    }

    public void imprimirFiscal(String esp_filtro, int cont){
        System.out.println("----------------------------------------------------------");
        System.out.println("Especialidad a buscar: "+esp_filtro+"\nCargando...");
        System.out.println("----------------------------------------------------------");
        
        especialidades.forEach(
                (key,value) -> {
                        if(value.equals(esp_filtro)){
                            Fiscal buscado= fiscales.get(key);
                            buscado.imprimirFiscal();
                            System.out.println("----------------------------------------------------------");
                        }
                    }
            );
    }

    public void imprimirFiscal(int cont, String nombre_filtro){
        System.out.println("----------------------------------------------------------");
        System.out.println("Nombre a buscar: "+nombre_filtro+"\nCargando...");
        System.out.println("----------------------------------------------------------");
        
        nombres.forEach(
                (key,value) -> {
                        if(value.equals(nombre_filtro)){
                            Fiscal buscado= fiscales.get(key);
                            buscado.imprimirFiscal();
                            System.out.println("----------------------------------------------------------");
                        }
                    }
            );
    }
    
    public void imprimirFiscal(int dis_filtro, int cont){
        System.out.println("----------------------------------------------------------");
        System.out.println("Distrito a buscar: "+dis_filtro+"\nCargando...");
        System.out.println("----------------------------------------------------------");
        
        distritos.forEach(
                (key,value) -> {
                        if(value.equals(dis_filtro)){
                            Fiscal buscado= fiscales.get(key);
                            buscado.imprimirFiscal();
                            System.out.println("----------------------------------------------------------");
                        }
                    }
            );
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

/*Distritos*/
    public int getDistrito() {
            return distrito;
    }

    public void setDistrito(int distrito) {
        this.distrito = distrito;
    }

    public String getDistrito_str() {
        return distrito_str;
    }

    public void setDistrito_str(String distrito_str) {
        this.distrito_str = distrito_str;
    }

/*--------------------------------------------Getter y setter de Filtros de Busqueda-----------------------------------------*/
    public HashMap<String, Fiscal> getFiscales() {
        return fiscales;
    }

    public void setFiscales(HashMap<String, Fiscal> fiscales) {
        this.fiscales = fiscales;
    }
    
    public HashMap<String, String> getNombres() {
        return nombres;
    }

    public void setNombres(HashMap<String, String> nombres) {
        this.nombres = nombres;
    }

    public HashMap<String, String> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(HashMap<String, String> especialidades) {
        this.especialidades = especialidades;
    }

    public HashMap<String, Integer> getDistritos() {
        return distritos;
    }

    public void setDistritos(HashMap<String, Integer> distritos) {
        this.distritos = distritos;
    }
}