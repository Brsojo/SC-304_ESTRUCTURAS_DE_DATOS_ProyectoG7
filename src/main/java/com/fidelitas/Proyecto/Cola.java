/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fidelitas.Proyecto;

/**
 *
 * @author alexp
 */
public class Cola {
    
    private Nodo primero;
    private Nodo ultimo;
    
    public Cola() {
        this.primero = null;
        this.ultimo = null;
    }
    
    public Nodo getPrimero() {
        return primero;
    }

    public void setPrimero(Nodo primero) {
        this.primero = primero;
    }

    public Nodo getUltimo() {
        return ultimo;
    }

    public void setUltimo(Nodo ultimo) {
        this.ultimo = ultimo;
    }
    
    public void encolar(Paciente paciente) {
        Nodo nuevo = new Nodo(paciente);
        
        if (ultimo != null) {
            ultimo.setAbajo(nuevo);
        }
        ultimo = nuevo;
        
        if (primero == null) {
            primero = nuevo;
        }
    }
    
    public Paciente desencolar() {
        if (primero == null) {
            return null;
        }
        
        Paciente temp = primero.getNuevoPaciente();
        primero = primero.getAbajo();
        
        if (primero == null) {
            ultimo = null;
        }
        
        return temp;
    }
}
