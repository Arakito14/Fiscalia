package com.manzanitacreations.proyectofiscalia;

import java.util.ArrayList;

/*
*@author Marlene Lagos
*@author Valentina San Martin
*@author Matias Mujica
*ICI3241-1
 */
//----------------------------------------------------------------------------//
//--------------------------------Causa Child---------------------------------//
//----------------------------------------------------------------------------//
public class Procedimiento{

    private String nombreProc;
    private ArrayList<String> participantes;
    private ArrayList<String> roles;
    private String resultado;

    public Procedimiento() {
        nombreProc = new String();
        participantes = new ArrayList<>();
        roles = new ArrayList<>();
        resultado = new String();
    }
//----------------------------------------------------------------------------//
//------------------------------Metodos Generales-----------------------------//
//----------------------------------------------------------------------------//

    public String obtenerParticipante(int i) {
        return participantes.get(i);
    }

    public String obtenerRol(int i) {
        return roles.get(i);
    }

    public void ingresarParticipante(String nombre, String rol) {
        participantes.add(nombre);
        roles.add(rol);
    }

    /*--------------------------------------------Getter y setter-----------------------------------------
    * @return -*/
    public String getNombreProc() {
        return nombreProc;
    }

    public void setNombreProc(String nombreProc) {
        this.nombreProc = nombreProc;
    }

    public ArrayList<String> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(ArrayList<String> participantes) {
        this.participantes = participantes;
    }

    public ArrayList<String> getRoles() {
        return roles;
    }

    public void setRoles(ArrayList<String> roles) {
        this.roles = roles;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
}
//----------------------------------------------------------------------------//
//-------------------------------------Fin------------------------------------//
//----------------------------------------------------------------------------//
