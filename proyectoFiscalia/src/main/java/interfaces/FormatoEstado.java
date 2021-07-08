package interfaces;

/**
 *
 * @author Marlene
 */
public interface FormatoEstado {
    public static final String ABIERTA = "Abierto";
    public static final String CERRADA = "Cerrada";
    public static final String ARCHIVADA = "Archivada";
    
    /**
     * Comprueba que se haya ingresado una opción válida de estado
     * @param est int del número ingresado
     * @return String el estado correspondiente al número
     */
    public String comprobarEstado(int est);
}

