package com.manzanitacreations.proyectofiscalia;

/*
*@author Marlene Lagos
*@author Valentina San Martin
*@author Matias Mujica
*ICI3241-1
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
