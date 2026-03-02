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
    
    /**
     * Obtiene el paciente asociado al nodo
     * @author Brandon Sojo Acuña
     * @return paciente del nodo
     */

    public Paciente getMiPaciente() {
        return miPaciente;
    }
    
    /**
     * Obtiene el siguiente nodo en la estructura
     * @author Brandon Sojo Acuña
     * @return siguiente nodo
     */

    public Nodo getSiguiente() {
        return siguiente;
    }
    
    /**
     * Obtiene el motivo de abandono
     * @author Brandon Sojo Acuña
     * @return motivo de abandono
     */

    public String getAbandono() {
        return abandono;
    }
    
    /**
     * Establece el paciente asociado al nodo
     * @author Brandon Sojo Acuña
     * @param miPaciente nuevo paciente
     */  

             
    public void setMiPaciente(Paciente miPaciente) {
        this.miPaciente = miPaciente;
    }
    
    /**
     * Establece el siguiente nodo
     * @author Brandon Sojo Acuña
     * @param siguiente nodo siguiente
     */

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
    
    /**
     * Establece el motivo de abandono
     * @author Brandon Sojo Acuña
     * @param abandono motivo de abandono
     */

     public void setAbandono(String abandono) {
        this.abandono = abandono;
    }
    
    
    
    
    
}
