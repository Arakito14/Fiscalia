package interfaces;

/**
 *
 * @author Marlene
 */
public interface FormatoEstado {
    public static final String ABIERTA = "Abierto";
    public static final String CERRADA = "Cerrada";
    public static final String ARCHIVADA = "Archivada";
    
    public String comprobarEstado(int est);
}

