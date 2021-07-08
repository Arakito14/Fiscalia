package interfaces;
import java.util.regex.Pattern;

/**
 *
 * @author Marlene
 */
public interface FormatoRut {
    public static final Pattern PATRON_RUT = Pattern.compile("[0-9]{8}-[k-kK-K0-9]{1}");
    public static final String RUT = "Ingrese el rut del fiscal, sin puntos y con guion";
    
    /**
     * Comprueba que el rut haya sido ingresado en un formato válido
     * @param rut String el rut ingresado
     * @return String con el rut ya validado
     */
    public  String esRut(String rut);
    /**
     * Comprueba que el rut ingresado sea válido en el formato de las ventanas
     * @param rut String del rut ingresado en la ventana
     * @return boolean, true si es válido y false en caso contrario
     */
    public boolean confirmar(String rut);
}

