/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.manzanitacreations.proyectofiscalia;

import java.util.regex.Pattern;

/**
 *
 * @author Marlene
 */
public interface Formato {
    static final Pattern PATRON_CODIGO = Pattern.compile("[0-9]{6}");
    static final Pattern PATRON_RUT = Pattern.compile("[0-9]{8}-[0-9]{1}");
    static final int MIN_DISTRITO=1;
    static final int MAX_DISTRITO=28;
    
    boolean comprobarCodigo(String cod);
    boolean comprobarRut(String rut);
    int comprobarDistrito(String dist);
}
