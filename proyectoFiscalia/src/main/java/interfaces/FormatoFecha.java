/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;


/**
 *
 * @author Marlene
 */
public interface FormatoFecha {
    public static final String PATRON_FECHA="dd/MM/yyyy";
    
    /**
     * Comprueba que la fecha se haya ingresado en el formato correcto
     * @return String la fecha una vez que ya es válida
     */
    public String esFecha();
}
