/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ed_g7_proyectofinal;

/**
 *
 * @author Matthew
 */
public class Nodo {
    private Paciente miPaciente;
    private Nodo siguiente;
    private String abandono;

    public Nodo() {
    }

    public Nodo(Paciente miPaciente) {
        this.miPaciente = miPaciente;
        this.siguiente = null;
    }

    public Paciente getMiPaciente() {
        return miPaciente;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public String getAbandono() {
        return abandono;
    }

             
    public void setMiPaciente(Paciente miPaciente) {
        this.miPaciente = miPaciente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }

     public void setAbandono(String abandono) {
        this.abandono = abandono;
    }
    
    
    
    
    
}
