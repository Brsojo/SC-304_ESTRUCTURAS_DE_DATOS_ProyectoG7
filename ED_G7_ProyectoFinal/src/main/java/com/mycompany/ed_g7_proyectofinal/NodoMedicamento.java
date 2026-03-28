/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ed_g7_proyectofinal;
import java.time.LocalDateTime;

/**
 * Clase que representa un nodo para almacenar medicamentos prescritos.
 * Cada nodo contiene la información del medicamento y la fecha en que fue registrado.
 * 
 * @author Matthew
 */
public class NodoMedicamento {

    private LocalDateTime fecha;
    private String medicamento;
    private NodoMedicamento siguiente;

    /**
     * Constructor que inicializa el nodo con la información del medicamento
     * @author Matthew
     * @param fecha fecha de prescripción
     * @param medicamento nombre del medicamento
     */
    public NodoMedicamento(LocalDateTime fecha, String medicamento) {
        this.fecha = fecha;
        this.medicamento = medicamento;
        this.siguiente = null;
    }

    /**
     * Obtiene la fecha del medicamento
     */
    public LocalDateTime getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha del medicamento
     */
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    /**
     * Obtiene el nombre del medicamento
     */
    public String getMedicamento() {
        return medicamento;
    }

    /**
     * Establece el nombre del medicamento
     */
    public void setMedicamento(String medicamento) {
        this.medicamento = medicamento;
    }

    /**
     * Obtiene el siguiente nodo
     */
    public NodoMedicamento getSiguiente() {
        return siguiente;
    }

    /**
     * Establece el siguiente nodo
     */
    public void setSiguiente(NodoMedicamento siguiente) {
        this.siguiente = siguiente;
    }
}
