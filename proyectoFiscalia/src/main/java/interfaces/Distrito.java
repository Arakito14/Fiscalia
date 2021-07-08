package interfaces;

/**
 *
 * @author Marlene
 */
public interface Distrito {
        public static final int MIN_DISTRITO = 1;
        public static final int MAX_DISTRITO = 28;
        public static final String DIS = "Ingrese el distrito. Debe ser un numero entre 1 y 28"; 
        /**
         * Método que se asegura de que el distrito ingresado sea válido
         * @param dis_str El número correspondiente al distrito en formato String
         * @return int Devuelve el distrito en formato int en caso de ser válido
         */
        public int esDis(String dis_str);
}
