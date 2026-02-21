/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fidelitas.Proyecto;

/**
 *
 * @author alexp
 */
public class Nodo {
    
    private Paciente nuevoPaciente;
    private Nodo abajo;

    public Nodo() {
    }

    public Nodo(Paciente nuevoPaciente) {
        this.nuevoPaciente = nuevoPaciente;
        this.abajo = abajo;
    }

    public Paciente getNuevoPaciente() {
        return nuevoPaciente;
    }

    public void setNuevoPaciente(Paciente nuevoPaciente) {
        this.nuevoPaciente = nuevoPaciente;
    }

    public Nodo getAbajo() {
        return abajo;
    }

    public void setAbajo(Nodo abajo) {
        this.abajo = abajo;
    }
    
}
