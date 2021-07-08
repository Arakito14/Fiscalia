/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Excepciones;

/**
 *
 * @author Marlene
 */
public class RutInvalido extends Exception {

    /**
     * Creates a new instance of <code>RutInvalido</code> without detail
     * message.
     */
    public RutInvalido() {
        super("El rut ingresado es invalido");
    }

}
