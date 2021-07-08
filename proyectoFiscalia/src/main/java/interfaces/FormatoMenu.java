package interfaces;

import java.util.Scanner;

/**
 *
 * @author Marlene
 */
public interface FormatoMenu {
    public static final String REPORTE = "src/main/resources/reporte.txt";
    public static final String FISCALES = "src/main/resources/Fiscales.csv";
    public static final String CAUSAS = "src/main/resources/causas.csv";
    public static final String ERROR_MENU = "Se ha producido un error, intente nuevamente";
    public static final String EXIT = "Hasta Pronto";
    
    public static final Scanner LEER = new Scanner(System.in);
    public static final String TIPO = "Ingrese el tipo de caso";
    public static final String NOMBRE = "Ingrese el nombre";
    public static final String ESTADO = "Ingrese el estado del caso";
    public static final String FISCAL_ESP = "Ingrese la especialidad";
    public static final String CAUSA_ESP = "Ingrese el tipo de caso";
    
    public static final String PROC = "Ingrese el nombre del nuevo procedimiento";
    public static final String PROC_CODIGO = "Ingrese el numero del procedimiento";
    public static final String PROC_NOMBRE = "Ingrese el nombre del participante";
    public static final String PROC_ROL = "Ingrese el rol del participante (policia, abogado, testigo,etc...)";
    public static final String PROC_OPCION = "¿Desea ingresar más participantes?\n0-NO\n1-SI";
    public static final String PROC_RESULTADO = "Ingrese el resultado del procedimiento";
    
    public static final String DIVIDER = "----------------------------------------------------------";
    public static final String FISCAL_DIV = "Encargado: ";
    public static final String CAUSA_DIV = "Causas: ";
    public static final String PROC_DIV = "Procedimientos: ";
    public static final String FISCAL_REC = "Fiscales recomendados: ";
    public static final String FISCAL_NUMERO_DE_CAUSAS = "Numero de causas asignadas: ";
    
    public static final String FISCAL_NO_EXISTE = "El fiscal buscado no existe";
    public static final String FISCAL_NO_CAUSA = "Este fiscal aun no tiene causas";
    public static final String CAUSA_NO_EXISTE = "La causa buscada no existe";
    public static final String CAUSA_NO_FISCAL = "Esta causa no tiene Fiscal encargado";
    public static final String CAUSA_NO_PROC = "Esta causa aún no tiene procedimientos";
    
    public static final String FISCAL_EXISTE = "Ya existe un fiscal con ese rut";
    public static final String CAUSA_EXISTE = "Ya existe este caso";
    public static final String CAUSA_TIENE_FISCAL = "Esta causa ya tiene fiscal";
    
    public static final String FISCAL_ELIMINAR = "El fiscal ha sido eliminado con exito";
    public static final String CAUSA_ELIMINAR = "La causa ha sido eliminada con exito";
    public static final String PROC_ELIMINAR = "El procedimiento ha sido eliminado con exito";
    
    public static final String REGRESO = "A su orden, regresando al menu...";
    public static final String INCORRECTO = "El formato es incorrecto. Por favor intente de nuevo";
    public static final String ELIMINAR_NO_EXISTE = "No hay nada que eliminar";
    
    /**
     * Comprueba que la opción al ingresar participantes sea válida
     * @param opcion_str String la opcion seleccionada
     * @return int la opción seleccionada en formato númerico
     */
    public int esParticipante(String opcion_str);
}
