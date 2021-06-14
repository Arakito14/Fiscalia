package com.manzanitacreations.proyectofiscalia;

import interfaces.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.regex.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/*
*@author Marlene Lagos
*@author Valentina San Martin
*@author Matias Mujica
*ICI3241-1
 */
public class Fiscalia implements FormatoMenu, Especialidad, FormatoRut, Distrito, FormatoCodigo, FormatoEstado, FormatoFecha {

    /*usuario*/
    private String contrasena;
    /*generales*/
    private HashMap<String, Causa> causas;
    private HashMap<String, Fiscal> fiscales;

    /*clase publica de mapas*/
    public Fiscalia() {
        fiscales = new HashMap<>();
        causas = new HashMap<>();
        contrasena = "admin";
    }
//----------------------------------------------------------------------------//
//------------------------------Metodos Generales-----------------------------//
//----------------------------------------------------------------------------//
//-----------------------------Filtros de Busqueda----------------------------//    

    public void filtros() {
        int opcion;
        do {
            System.out.println(DIVIDER);
            System.out.println("                           Filtro de busqueda");
            System.out.println(DIVIDER);
            System.out.println("¿Que desea hacer?");
            System.out.println("1-Mostrar fiscales por nombre, rut, especialidad y distrito");
            System.out.println("2-Por nombre");
            System.out.println("3-Por rut");
            System.out.println("4-Por especialidad");
            System.out.println("5-Por distrito");
            System.out.println("6-Muestra causas(s) abierta(s) de fiscal buscado");
            System.out.println("7-Muestra causas(s) cerradas(s) de fiscal buscado");
            System.out.println("8-Muestra causas(s) archivadas(s) de fiscal buscado");
            System.out.println("9-Muestra el Fiscal con el mayor número de casos");
            System.out.println("0-Regresar");
            System.out.println(DIVIDER);
            opcion = Integer.parseInt(LEER.nextLine());
            switch (opcion) {
                case 1:
                    imprimirFiscal();
                    break;
                case 2:
                    System.out.println(NOMBRE);
                    String nombre_filtro = LEER.nextLine();
                    imprimirFiscal(nombre_filtro, opcion, fiscales);
                    break;
                case 3:
                    System.out.println(RUT);
                    String rut_filtro = LEER.nextLine();
                    /*verificacion*/
                    rut_filtro = esRut(rut_filtro);
                    imprimirFiscal(rut_filtro, opcion, fiscales);
                    break;
                case 4:
                    System.out.println(FISCAL_ESP);
                    mostrarOpciones();
                    String esp_str = LEER.nextLine();
                    int esp_int;
                    /*verificacion*/
                    esp_int = esEsp(esp_str);
                    esp_str = asignarEspecialidad(esp_int);
                    imprimirFiscal(esp_str, opcion, fiscales);
                    break;
                case 5:
                    System.out.println(DIS);
                    /*Distrito en entero y string*/
                    String dis_str = LEER.nextLine();
                    int dis_int;
                    /*verificacion*/
                    dis_int = esDis(dis_str);
                    /*Volver a menu*/
                    imprimirFiscal(dis_int, fiscales);
                    break;
                case 6:
                    imprimirFiscal(opcion);
                    break;
                case 7:
                    imprimirFiscal(opcion);
                    break;
                case 8:
                    imprimirFiscal(opcion);
                    break;
                case 9:
                    maxFiscal();
                    break;
                case 0:
                    System.out.println(REGRESO);
                    break;
                default:
                    System.out.println(INCORRECTO);
            }
        } while (opcion != 0);
    }
//Imprime todos los Fiscales en el mapa

    public void imprimirFiscal() {
        System.out.println(DIVIDER);
        for (Entry<String, Fiscal> aux : fiscales.entrySet()) {
            Fiscal values = aux.getValue();
            values.mostrar();
            System.out.println(DIVIDER);
        }
    }
//Imprime Fiscal por Filtro a buscar

    public void imprimirFiscal(String filtro, int atributoFiscal, HashMap<String, Fiscal> fiscales) {
        Fiscal fiscal = new Fiscal();
        fiscal.imprimirFiscal(filtro, atributoFiscal, fiscales);
    }
//Imprime Fiscal por Distrito a buscar

    public void imprimirFiscal(int dis_filtro, HashMap<String, Fiscal> fiscales) {
        Fiscal fiscal = new Fiscal();
        fiscal.imprimirFiscal(dis_filtro, fiscales);
    }
//Imprime Fiscal buscado por tipo de causa

    public void imprimirFiscal(int tipoCausa) {
        System.out.println(RUT);
        String rut = LEER.nextLine();
        /*verificacion*/
        rut = esRut(rut);
        /*buscar*/
        Fiscal buscado = fiscales.get(rut);
        if (buscado == null) {
            System.out.println(FISCAL_NO_EXISTE);
        } else {
            System.out.println(DIVIDER);
            buscado.imprimirFiscal(tipoCausa);
        }
    }
//Imprime Fiscal con el mayor numero de casos
    
    public void maxFiscal() {
        System.out.println(DIVIDER);
        int max = 0;
        for (Entry<String, Fiscal> aux : fiscales.entrySet()) {
            Fiscal values = aux.getValue();
            values.maxFiscal(fiscales);
        }
        for (Entry<String, Fiscal> aux : fiscales.entrySet()) {
            Fiscal values = aux.getValue();
            for (Entry<String, Integer> abc : values.getCausasMax().entrySet()) {
                if (values.getCausasMax() == null || abc.getValue() > 0) {
                    max = abc.getValue();
                }
            }
        }
        for (Entry<String, Fiscal> aux : fiscales.entrySet()) {
            Fiscal values = aux.getValue();
            for (Entry<String, Integer> abc : values.getCausasMax().entrySet()) {
                if (max == abc.getValue()) {
                    values.mostrar();
                    System.out.println(FISCAL_NUMERO_DE_CAUSAS + max);
                }
            }
        }
    }

//----------------------------------Impresion---------------------------------//
//Metodo para mostrar todos los fiscales del Hashtable fiscales
//Sobre escritura de metodo
    public void mostrar() {
        System.out.println(DIVIDER);
        for (Entry<String, Fiscal> aux : fiscales.entrySet()) {
            Fiscal values = aux.getValue();
            values.mostrar();
            System.out.println(CAUSA_DIV);
            System.out.println(DIVIDER);
            mostrarCausas(values.getCausasActuales());
        }
    }
//-----------------------------------Edicion----------------------------------//    
//Metodo para asignar un fiscal a una causa

    public void asignarFiscal() {
        System.out.println(CODIGO);
        String codigo = LEER.nextLine();
        /*verificacion*/
        codigo = esCodigo(codigo);
        Causa asignada = causas.get(codigo);
        if (asignada != null) {
            System.out.println(DIVIDER);
            System.out.println("Causa n°: " + codigo);
            System.out.println(DIVIDER);
            asignada.asignarFiscal(fiscales, asignada);
        } else {
            System.out.println(CAUSA_NO_EXISTE);
        }
    }
//Metodo para buscar un fiscal y modificar su distrito

    public void modificarDistrito() {
        System.out.println(RUT);
        String rut = LEER.nextLine();
        /*verificacion*/
        rut = esRut(rut);
        Fiscal buscado = fiscales.get(rut);
        if (buscado == null) {
            System.out.println(FISCAL_NO_EXISTE);
        } else {
            buscado.mostrar();
            System.out.println(DIS);
            String dist = LEER.nextLine();
            int distrito;
            /*verificacion*/
            distrito = esDis(dist);
            buscado.setDistrito(distrito);
        }
    }
//Metodo para cambiar el fiscal de una causa

    public void cambiarFiscal() {
        System.out.println(CODIGO);
        String codigo = LEER.nextLine();
        /*verificacion*/
        codigo = esCodigo(codigo);
        Causa asignada = causas.get(codigo);
        if (asignada != null) {
            System.out.println(RUT);
            String rut = LEER.nextLine();
            /*verificacion*/
            rut = esRut(rut);
            Fiscal nuevo = fiscales.get(rut);
            asignada.reemplazarFiscal(nuevo, fiscales, asignada);
        } else {
            System.out.println(CAUSA_NO_EXISTE);
        }
    }
//Modifica un procedimiento de una causa

    public void modificarProcedimiento() {
        Causa buscar = buscarCausa();
        buscar.modificarProcedimiento();
    }
//----------------------------------------------------------------------------//
//------------------------------Metodos Fiscales------------------------------//
//----------------------------------------------------------------------------//
//----------------------------------Busqueda----------------------------------//
//Metodo para buscar un fiscal y mostrarlo por pantalla*/
//Retorna el fiscal si es que existe y null en caso contrario*/

    public Fiscal buscarFiscal() {
        System.out.println(RUT);
        String rut = LEER.nextLine();
        /*verificacion*/
        rut = esRut(rut);
        /*buscar*/
        Fiscal buscado = fiscales.get(rut);
        if (buscado == null) {
            System.out.println(FISCAL_NO_EXISTE);
            return null;
        } else {
            buscado.mostrar();
            System.out.println(DIVIDER);
            System.out.println(CAUSA_DIV);
            System.out.println(DIVIDER);
            mostrarCausas(buscado.getCausasActuales());
        }
        return buscado;
    }
//----------------------------------Creacion----------------------------------//    
//Metodo para leer un fiscal e ingresarlo a la Hashtable fiscales//

    public void nuevoFiscal() {
        System.out.println(RUT);
        String rut = LEER.nextLine();
        /*verificacion*/
        rut = esRut(rut);
        boolean existe = fiscales.containsKey(rut);
        if (existe) {
            System.out.println(FISCAL_EXISTE);
        } else {
            /*nombre*/
            System.out.println(NOMBRE);
            String nombre = LEER.nextLine();
            /*especialidad*/
            System.out.println(FISCAL_ESP);
            mostrarOpciones();
            String esp_str = LEER.nextLine();
            int esp_int;
            /*verificacion*/
            esp_int = esEsp(esp_str);
            esp_str = asignarEspecialidad(esp_int);
            /*distrito*/
            System.out.println(DIS);
            String dist = LEER.nextLine();
            int distrito;
            /*verificacion*/
            distrito = esDis(dist);
            /*agrega fiscal nuevo al mapa de fiscales*/
            Fiscal nuevo = new Fiscal(rut, nombre, esp_str, distrito);
            fiscales.put(rut, nuevo);
        }
    }

//-----------------------------------Elminar----------------------------------//
//Elimina un fiscal de los mapas
    public void eliminarFiscal() {
        Fiscal eliminado = buscarFiscal();
        if (eliminado != null) {
            eliminarFiscal(eliminado.getCausasActuales());
            fiscales.remove(eliminado.getRut());
            System.out.println(FISCAL_ELIMINAR);
        } else {
            System.out.println(ELIMINAR_NO_EXISTE);
        }
    }
//Elimina el fiscal de todas las causas que tenía a su cargo

    public void eliminarFiscal(HashMap<String, Causa> causas) {
        for (Entry<String, Causa> entry : causas.entrySet()) {
            Causa aux = entry.getValue();
            aux.setEncargado(new Fiscal());
        }
        causas.clear();
    }
//----------------------------------------------------------------------------//
//-------------------------------Metodos Causas-------------------------------//
//----------------------------------------------------------------------------//
//----------------------------------Impresion---------------------------------//
//Metodo para mostrar todas las causas de la Hashtable causas

    public void mostrarCausas() {
        for (Entry<String, Causa> aux : causas.entrySet()) {
            Causa values = aux.getValue();
            values.mostrar();
            System.out.println(DIVIDER);
        }
    }
//Metodo para mostrar todas las causas que tiene un fiscal

    public void mostrarCausas(HashMap<String, Causa> causas) {
        for (Entry<String, Causa> aux : causas.entrySet()) {
            Causa values = aux.getValue();
            values.mostrar();
            System.out.println(DIVIDER);
        }
    }
//----------------------------------Busqueda----------------------------------//
//Metodo para buscar una causa y mostrarla por pantalla
//Retorna la causa si es que existe y null en caso contrario

    public Causa buscarCausa() {
        System.out.println(CODIGO);
        String codigo = LEER.nextLine();
        /*verificacion*/
        codigo = esCodigo(codigo);
        /*buscar*/
        Causa buscar = causas.get(codigo);
        if (buscar != null) {
            buscar.imprimirCausa();
            buscar.mostrarProcedimientos();
            System.out.println(DIVIDER);
            return buscar;
        } else {
            System.out.println(CAUSA_NO_EXISTE);
            return null;
        }
    }
//Metodo para buscar una de las causas de un fiscal y mostrarla por pantalla
//Recibe como parámetro el mapa de causas dentro del fiscal

    public void buscarCausa(HashMap<String, Causa> causas) {
        System.out.println(CODIGO);
        String codigo = LEER.nextLine();
        /*verificacion*/
        codigo = esCodigo(codigo);
        Causa buscar = causas.get(codigo);
        if (buscar != null) {
            buscar.imprimirCausa();
            buscar.mostrarProcedimientos();
            System.out.println(DIVIDER);
        } else {
            System.out.println(CAUSA_NO_EXISTE);
        }
    }
//----------------------------------Creacion----------------------------------//
//Metodo para leer una causa e ingresarla a la HashTable causas

    public void nuevaCausa() {
        System.out.println(CODIGO);
        String codigo = LEER.nextLine();
        /*verificacion*/
        codigo = esCodigo(codigo);
        boolean existe = causas.containsKey(codigo);
        if (existe) {
            System.out.println(CAUSA_EXISTE);
        } else {
            /*estado*/
            String estado;
            /*verificacion*/
            do {
                System.out.println(ESTADO);
                System.out.println("0-" + ABIERTA);
                System.out.println("1-" + CERRADA);
                System.out.println("2-" + ARCHIVADA);
                int est = Integer.parseInt(LEER.nextLine());
                estado = comprobarEstado(est);
            } while (estado == null);
            /*especialidad*/
            System.out.println(CAUSA_ESP);
            mostrarOpciones();
            String tipoCaso = LEER.nextLine();
            int especialidad;
            /*verificacion*/
            especialidad = esEsp(tipoCaso);
            tipoCaso = asignarEspecialidad(especialidad);
            /*distrito*/
            System.out.println(DIS);
            String dist = LEER.nextLine();
            int distrito;
            /*verificacion*/
            distrito = esDis(dist);
            switch (estado) {
                case ABIERTA:
                    CausaAbierta nueva = new CausaAbierta(codigo, estado, tipoCaso, distrito);
                    causas.put(codigo, nueva);
                    break;
                case CERRADA:
                    System.out.println("Ingrese la fecha de cierre del caso");
                    String fecha = esFecha();
                    System.out.println("Ingrese la resolucion del caso");
                    String resolucion = LEER.nextLine();
                    CausaCerrada nuevaC = new CausaCerrada(codigo, estado, tipoCaso, distrito, fecha, resolucion);
                    causas.put(codigo, nuevaC);
                    break;
                case ARCHIVADA:
                    System.out.println("Ingrese la fecha de archivacion del caso");
                    String fechaArc = esFecha();
                    System.out.println("Ingrese la razon por la que el caso se archiva");
                    String razon = LEER.nextLine();
                    CausaArchivada nuevaArc = new CausaArchivada(codigo, estado, tipoCaso, distrito, fechaArc, razon);
                    causas.put(codigo, nuevaArc);
            }
        }
    }
//-----------------------------------Elminar----------------------------------//    
//Elimina la causa de todos los mapas    

    public void eliminarCausa() {
        Causa eliminada = buscarCausa();
        if (eliminada != null) {
            Fiscal encargado = fiscales.get(eliminada.getEncargado().getRut());
            if (encargado != null) {
                encargado.getCausasActuales().remove(eliminada.getCodigo());
                eliminada.getPeritajes().clear();
            }
            causas.remove(eliminada.getCodigo());
            System.out.println(CAUSA_ELIMINAR);
        } else {
            System.out.println(CAUSA_NO_EXISTE);
        }
    }
//Elimina una causa previamente seleccionada de todos los mapas

    public void eliminarCausa(Causa eliminada) {
        Fiscal encargado = fiscales.get(eliminada.getEncargado().getRut());
        if (encargado != null) {
            encargado.getCausasActuales().remove(eliminada.getCodigo());
            eliminada.getPeritajes().clear();
        }
        causas.remove(eliminada.getCodigo());
    }
//----------------------------------------------------------------------------//
//----------------------------Metodos Procedimiento---------------------------//
//----------------------------------------------------------------------------//
//----------------------------------Creacion----------------------------------//    
//Metodo para buscar la causa a la que se le agregará un nuevo procedimiento

    public void nuevoProcedimiento() {
        System.out.println(CODIGO);
        String codigo = LEER.nextLine();
        /*verificacion*/
        codigo = esCodigo(codigo);
        Causa buscar = causas.get(codigo);
        if (buscar != null) {
            buscar.imprimirCausa();
            buscar.mostrarProcedimientos();
            System.out.println(DIVIDER);
            nuevoProcedimiento(buscar);
        } else {
            System.out.println(CAUSA_NO_EXISTE);
        }
    }
//Metodo que agrega el nuevo procedimiento a la causa

    public void nuevoProcedimiento(Causa buscar) {
        System.out.println(PROC);
        String nombreProc = LEER.nextLine();
        Procedimiento nuevo = new Procedimiento();
        nuevo.setNombreProc(nombreProc);
        int opcion;
        do {
            System.out.println(PROC_NOMBRE);
            String nombre = LEER.nextLine();
            System.out.println(PROC_ROL);
            String rol = LEER.nextLine();
            nuevo.ingresarParticipante(nombre, rol);
            /*opciones*/
            System.out.println(PROC_OPCION);
            String opcion_str = LEER.nextLine();
            /*verificacion*/
            opcion = esParticipante(opcion_str);
        } while (opcion != 0);
        System.out.println(PROC_RESULTADO);
        String result = LEER.nextLine();
        nuevo.setResultado(result);
        buscar.getPeritajes().addLast(nuevo);
    }
//-----------------------------------Elminar----------------------------------//    
//Busca la causa a la que se le va a eliminar un procedimiento

    public void eliminarProcedimiento() {
        Causa eliminada = buscarCausa();
        eliminada.eliminarProcedimiento();
    }
//----------------------------------------------------------------------------//
//------------------------------Metodo EstadoCausa----------------------------//
//----------------------------------------------------------------------------//

    public void modificarEstadoCausa() {
        System.out.println(CODIGO);
        String codigo = LEER.nextLine();
        /*verificacion*/
        codigo = esCodigo(codigo);
        Causa buscar = causas.get(codigo);
        if (buscar != null) {
            String estado;
            String estadoOriginal = buscar.getEstado();
            do {
                System.out.println(ESTADO);
                System.out.println("0-" + ABIERTA);
                System.out.println("1-" + CERRADA);
                System.out.println("2-" + ARCHIVADA);
                int est = Integer.parseInt(LEER.nextLine());
                estado = comprobarEstado(est);
            } while (estado == null);
            if (estadoOriginal.equals(estado)) {
                System.out.println("El estado de la causa ya es " + estado + ". No se han realizado cambios");
            } else {
                if (estadoOriginal.equals(CERRADA) && estado.equals(ARCHIVADA)) {
                    System.out.println("No se puede archivar una causa cerrada. No se han realizado cambios");
                } else {
                    switch (estado) {
                        case ABIERTA:
                            abrirCausa(estadoOriginal, buscar);
                            break;

                        case CERRADA:
                            cerrarCausa(estadoOriginal, buscar);
                            break;

                        case ARCHIVADA:
                            archivarCausa(estadoOriginal, buscar);
                            break;
                    }
                }
            }
        } else {
            System.out.println(CAUSA_NO_EXISTE);
        }
    }

    public void abrirCausa(String estadoOriginal, Causa buscar) {
        CausaAbierta abierta = new CausaAbierta(buscar.getCodigo(), buscar.getEstado(), buscar.getTipoCaso(), buscar.getDistrito());
        switch (estadoOriginal) {
            case CERRADA:
                CausaAbierta abierta_cerrada = new CausaAbierta(buscar.getCodigo(), buscar.getEstado(), buscar.getTipoCaso(), buscar.getDistrito());
                abierta_cerrada.setEncargado(buscar.getEncargado());
                abierta_cerrada.setPeritajes((LinkedList<Procedimiento>) buscar.getPeritajes().clone());
                eliminarCausa(buscar);
                break;
            case ARCHIVADA:
                CausaAbierta abierta_archivada = new CausaAbierta(buscar.getCodigo(), buscar.getEstado(), buscar.getTipoCaso(), buscar.getDistrito());
                abierta_archivada.setEncargado(buscar.getEncargado());
                abierta_archivada.setPeritajes((LinkedList<Procedimiento>) buscar.getPeritajes().clone());
                eliminarCausa(buscar);
                break;
            default:
                System.out.println("Esta causa ya se encuentra " + buscar.getEstado() + ".No se han realizado cambios");
                break;
        }
    }

    public void cerrarCausa(String estadoOriginal, Causa buscar) {
        switch (estadoOriginal) {
            case ABIERTA:
                System.out.println("Ingrese la fecha de cierre del caso. El formato debe ser DD/MM/AAAA");
                String fecha = LEER.nextLine();
                System.out.println("Ingrese la resolucion del caso");
                String resolucion = LEER.nextLine();
                CausaCerrada cerrada_abierta = new CausaCerrada(buscar.getCodigo(), buscar.getEstado(), buscar.getTipoCaso(), buscar.getDistrito(), fecha, resolucion);
                cerrada_abierta.setEncargado(buscar.getEncargado());
                cerrada_abierta.setPeritajes((LinkedList<Procedimiento>) buscar.getPeritajes().clone());
                eliminarCausa(buscar);
                break;
            case ARCHIVADA:
                System.out.println("Ingrese la fecha de cierre del caso");
                String fechaTerm = esFecha();
                System.out.println("Ingrese la resolucion del caso");
                String resultado = LEER.nextLine();
                CausaCerrada cerrada_archivada = new CausaCerrada(buscar.getCodigo(), buscar.getEstado(), buscar.getTipoCaso(), buscar.getDistrito(), fechaTerm, resultado);
                cerrada_archivada.setEncargado(buscar.getEncargado());
                cerrada_archivada.setPeritajes((LinkedList<Procedimiento>) buscar.getPeritajes().clone());
                eliminarCausa(buscar);
                break;
            default:
                System.out.println("Esta causa ya se encuentra " + buscar.getEstado() + ".No se han realizado cambios");
                break;
        }
    }

    public void archivarCausa(String estadoOriginal, Causa buscar) {
        System.out.println("Ingrese la fecha de archivacion del caso");
        String fechaArc = esFecha();
        System.out.println("Ingrese la razon por la que el caso se archiva");
        String razon = LEER.nextLine();
        CausaArchivada archivada = new CausaArchivada(buscar.getCodigo(), buscar.getEstado(), buscar.getTipoCaso(), buscar.getDistrito(), fechaArc, razon);
        archivada.setEncargado(buscar.getEncargado());
        archivada.setPeritajes((LinkedList<Procedimiento>) buscar.getPeritajes().clone());
        eliminarCausa(buscar);
    }

//----------------------------------------------------------------------------//
//------------------------------Getter y Setter-------------------------------//
//----------------------------------------------------------------------------//
    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

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
//----------------------------------------------------------------------------//
//-----------------------Implementacion Interfaces------------------------//
//----------------------------------------------------------------------------//

    @Override
    public String asignarEspecialidad(int esp) {
        String asignada = new String();
        switch (esp) {
            case 1:
                asignada = DELITOS_ECONOMICOS;
                break;
            case 2:
                asignada = CRIMEN_ORGANIZADO;
                break;
            case 3:
                asignada = RESPONSABILIDAD_ADOLESCENTE;
                break;
            case 4:
                asignada = DELITOS_VIOLENTOS;
                break;
            case 5:
                asignada = VIOLENCIA_INTRAFAMILIAR;
                break;
            case 6:
                asignada = TRAFICO_DE_DROGAS;
                break;
            case 7:
                asignada = CORRUPCION;
                break;
            case 8:
                asignada = DELITOS_SEXUALES;
                break;
        }
        return asignada;
    }

    @Override
    public void mostrarOpciones() {
        System.out.println(DIVIDER);
        System.out.println("Opciones:");
        System.out.println("1-" + DELITOS_ECONOMICOS);
        System.out.println("2-" + CRIMEN_ORGANIZADO);
        System.out.println("3-" + RESPONSABILIDAD_ADOLESCENTE);
        System.out.println("4-" + DELITOS_VIOLENTOS);
        System.out.println("5-" + VIOLENCIA_INTRAFAMILIAR);
        System.out.println("6-" + TRAFICO_DE_DROGAS);
        System.out.println("7-" + CORRUPCION);
        System.out.println("8-" + DELITOS_SEXUALES);
        System.out.println(DIVIDER);
    }

    @Override
    public String comprobarEstado(int est) {
        switch (est) {
            case 0:
                return ABIERTA;
            case 1:
                return CERRADA;
            case 2:
                return ARCHIVADA;
            default:
                return null;
        }
    }

    @Override
    public int esDis(String dis_str) {
        boolean esNum;
        int dis_int = 0;

        do {
            esNum = true;
            try {
                dis_int = Integer.parseInt(dis_str);
                if (dis_int < MIN_DISTRITO || dis_int > MAX_DISTRITO) {
                    esNum = false;
                    System.out.println(INCORRECTO);
                    dis_str = LEER.nextLine();
                }
            } catch (NumberFormatException nfe) {
                esNum = false;
                System.out.println(INCORRECTO);
                dis_str = LEER.nextLine();
            }
        } while (!esNum);

        /*Retorna distrito en entero*/
        return dis_int;
    }

    @Override
    public int esEsp(String esp_str) {
        boolean esEsp;
        int esp_int = 0;

        do {
            esEsp = true;

            try {
                esp_int = Integer.parseInt(esp_str);
                if (esp_int > 8 || esp_int < 1) {
                    esEsp = false;
                    System.out.println(INCORRECTO);
                    mostrarOpciones();
                    esp_str = LEER.nextLine();
                }
            } catch (NumberFormatException e) {
                esEsp = false;
                System.out.println(INCORRECTO);
                mostrarOpciones();
                esp_str = LEER.nextLine();
            }
        } while (!esEsp);

        /*Retorna especialidad en formato numerico (usar asignarEspecialidad)*/
        return esp_int;
    }

    @Override
    public String esRut(String rut) {
        boolean esRut;

        do {
            esRut = true;
            try {
                Matcher mat = PATRON_RUT.matcher(rut);
                if (!mat.matches()) {
                    esRut = false;
                    System.out.println(INCORRECTO);
                    rut = LEER.nextLine();
                }
            } catch (Exception e) {
                esRut = false;
                System.out.println(INCORRECTO);
                rut = LEER.nextLine();
            }
        } while (!esRut);

        /*Retorna rut en formato correcto*/
        return rut;
    }

    @Override
    public String esCodigo(String codigo) {
        boolean esCodigo;

        do {
            esCodigo = true;
            try {
                Matcher mat = PATRON_CODIGO.matcher(codigo);
                if (!mat.matches()) {
                    esCodigo = false;
                    System.out.println(INCORRECTO);
                    codigo = LEER.nextLine();
                }
            } catch (Exception e) {
                esCodigo = false;
                System.out.println(INCORRECTO);
                codigo = LEER.nextLine();
            }
        } while (!esCodigo);

        /*Retorna codigo de causa en formato correcto*/
        return codigo;
    }

    @Override
    public int esParticipante(String opcion_str) {
        boolean esParticipante;
        int opcion_int = 1;

        do {
            esParticipante = true;

            try {
                opcion_int = Integer.parseInt(opcion_str);

                if (opcion_int > 1 || opcion_int < 0) {
                    esParticipante = false;
                    System.out.println(INCORRECTO);
                    opcion_str = LEER.nextLine();
                }
            } catch (NumberFormatException e) {
                esParticipante = false;
                System.out.println(INCORRECTO);
                opcion_str = LEER.nextLine();
            }
        } while (!esParticipante);

        /*Retorna la opcion en formato correcto*/
        return opcion_int;
    }

    @Override
    public String esFecha() {
        boolean result;
        String fecha = new String();
        do {
            result = true;
            System.out.println("Ingrese el año");
            int anio = Integer.parseInt(LEER.nextLine());
            System.out.println("Ingrese el numero correspondiente al mes");
            int mes = Integer.parseInt(LEER.nextLine());
            System.out.println("Ingrese el dia");
            int dia = Integer.parseInt(LEER.nextLine());
            //dia=esDia(dia,mes, esBisiesto(anio));

            if (anio < 1900 || anio > 2100) {
                System.out.println("No se permiten años anteriores al 1900 o posteriores al 2100");
                result = false;
            }

            try {
                LocalDate today = LocalDate.of(anio, mes, dia);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATRON_FECHA);
                fecha = formatter.format(today);
            } catch (DateTimeException e) {
                System.out.println(INCORRECTO);
                result = false;
            }
        } while (!result);
        return fecha;
    }

    @Override
    public boolean confirmar(String rut) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
//----------------------------------------------------------------------------//
//-------------------------------------Fin------------------------------------//
//----------------------------------------------------------------------------//
