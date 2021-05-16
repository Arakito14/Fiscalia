/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.manzanitacreations.proyectofiscalia;

/**
 *
 * @author Marlene
 */
public interface Especialidad {
    static final String DELITOS_ECONOMICOS="Delitos economicos";
    static final String CRIMEN_ORGANIZADO="Crimen organizado";
    static final String RESPONSABILIDAD_ADOLESCENTE="Responsabilidad penal adolescente";
    static final String DELITOS_VIOLENTOS="Delitos violentos";
    static final String VIOLENCIA_INTRAFAMILIAR="Violencia intrafamiliar";
    static final String NARCOTRAFICO="Narcotrafico";
    static final String CORRUPCION="Corrupcion";
    static final String DELITOS_SEXUALES="Delitos sexuales"; 
    
    String asignarEspecialidad(int esp);
    void mostrarOpciones();
}
