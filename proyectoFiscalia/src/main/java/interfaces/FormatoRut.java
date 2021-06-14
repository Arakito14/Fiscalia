package interfaces;
import java.util.regex.Pattern;

/**
 *
 * @author Marlene
 */
public interface FormatoRut {
    public static final Pattern PATRON_RUT = Pattern.compile("[0-9]{8}-[k-kK-K0-9]{1}");
    public static final String RUT = "Ingrese el rut del fiscal, sin puntos y con guion";
    
    public  String esRut(String rut);
    public boolean confirmar(String rut);
}

