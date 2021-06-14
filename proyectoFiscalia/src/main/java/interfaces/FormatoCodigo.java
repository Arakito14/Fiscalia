package interfaces;

import java.util.regex.Pattern;

/**
 *
 * @author Marlene
 */
public interface FormatoCodigo {
    public static final Pattern PATRON_CODIGO = Pattern.compile("[0-9]{6}");
    public static final String CODIGO = "Ingrese el codigo de la causa. Debe ser un numero de 6 digitos";
    
    public String esCodigo(String codigo);
}

