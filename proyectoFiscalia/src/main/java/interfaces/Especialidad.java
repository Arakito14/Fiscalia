package interfaces;

/**
 *
 * @author Marlene
 */
/*Interfaz para distinguir entre las especialidades*/
public interface Especialidad {
    public static final String DELITOS_ECONOMICOS="Delitos economicos";
    public static final String CRIMEN_ORGANIZADO="Crimen organizado";
    public static final String RESPONSABILIDAD_ADOLESCENTE="Responsabilidad penal adolescente";
    public static final String DELITOS_VIOLENTOS="Delitos violentos";
    public static final String VIOLENCIA_INTRAFAMILIAR="Violencia intrafamiliar";
    //public static final String NARCOTRAFICO="Narcotrafico";
    public static final String TRAFICO_DE_DROGAS="Trafico de drogas";
    public static final String CORRUPCION="Corrupcion";
    public static final String DELITOS_SEXUALES="Delitos sexuales"; 
    
    /**
     *Asigna el número ingresado por el usuario a los nombres de especialidades existentes
     * @param esp numero correspondiente a alguna especialidad
     * @return String con el nombre de la especialidad
     */
    public String asignarEspecialidad(int esp);
    /**
     * Muestra las opciones de especialidad disponibles y los números que les corresponden
     */
    public void mostrarOpciones();
    /**
     * Comprueba que la opcion ingresada corresponda a una especialidad
     * @param esp_str String con el nombre de la especialidad
     * @return int número al que corresponde esa especialidad
     */
    public int esEsp(String esp_str);
}