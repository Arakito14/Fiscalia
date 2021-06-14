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
    
    public String asignarEspecialidad(int esp);
    public void mostrarOpciones();
    public int esEsp(String esp_str);
}