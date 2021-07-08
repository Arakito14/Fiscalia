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
public class RutExiste extends Exception {

    /**
     * Creates a new instance of <code>RutExiste</code> without detail message.
     */
    public RutExiste() {
        super("Ya existe un fiscal con este rut");
    }
}
