/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ed_g7_proyectofinal;
import java.time.LocalDateTime;

/**
 * Clase que representa un nodo para almacenar citas dentro de una estructura enlazada.
 * Cada nodo contiene una cita y una referencia al siguiente nodo.
 * 
 * @author Matthew
 */
public class NodoCita {

    private LocalDateTime fecha;
    private String doctor;
    private String diagnostico;
    private NodoCita siguiente;

     /**
     * Constructor que inicializa un nodo con los datos de la cita
     * @author Matthew
     * @param fecha fecha y hora de la cita
     * @param doctor nombre del doctor
     * @param diagnostico diagnóstico del paciente
     */
    public NodoCita(LocalDateTime fecha, String doctor, String diagnostico) {
        this.fecha = fecha;
        this.doctor = doctor;
        this.diagnostico = diagnostico;
        this.siguiente = null;
    }
    
    /**
     * Obtiene la fecha de la cita
     */
    public LocalDateTime getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha de la cita
     */
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    /**
     * Obtiene el nombre del doctor
     */
    public String getDoctor() {
        return doctor;
    }

    /**
     * Establece el nombre del doctor
     */
    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    /**
     * Obtiene el diagnóstico
     */
    public String getDiagnostico() {
        return diagnostico;
    }

    /**
     * Establece el diagnóstico
     */
    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    /**
     * Obtiene el siguiente nodo
     */
    public NodoCita getSiguiente() {
        return siguiente;
    }

    /**
     * Establece el siguiente nodo
     */
    public void setSiguiente(NodoCita siguiente) {
        this.siguiente = siguiente;
    }
}
    