package interfaces;

/**
 *
 * @author Marlene
 */
public interface Distrito {
        public static final int MIN_DISTRITO = 1;
        public static final int MAX_DISTRITO = 28;
        public static final String DIS = "Ingrese el distrito. Debe ser un numero entre 1 y 28"; 
        
        public int esDis(String dis_str);
}
