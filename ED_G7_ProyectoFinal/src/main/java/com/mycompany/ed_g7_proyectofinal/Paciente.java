/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ed_g7_proyectofinal;

import java.time.LocalDateTime;

/**
 *
 * @author Matthew
 */
public class Paciente {
    private String nombre;
    private String cedula;    
    private String Ficha;
    private LocalDateTime horallegada;
    private LocalDateTime horaSalida;
    

    public Paciente() {
    }

    public Paciente(String nombre, String cedula) {
        this.nombre = nombre;
        this.cedula = cedula;
        
    }
    
    /**
     * Obtiene el nombre del paciente
     * @author: Matthew Ilama Piedra
     * @return: nombre del paciente
     */

    public String getNombre() {
        return nombre;
    }
    
    /**
     * Obtiene la cédula del paciente
     * @author: Matthew Ilama Piedra
     * @return: cédula del paciente
     */
    
    public String getCedula() {
        return cedula;
    }

    /**
     * Obtiene el número de ficha del paciente
     * @author: Matthew Ilama Piedra
     * @return: número de ficha
     */

    public String getFicha() {
        return Ficha;
    }
    
    /**
     * Obtiene la hora de llegada del paciente
     * @author: Matthew Ilama Piedra
     * @return: hora de llegada
     */

    public LocalDateTime getHorallegada() {
        return horallegada;
    }
    
    /**
     * Obtiene la hora de salida del paciente
     * @author: Matthew Ilama Piedra
     * @return: hora de salida
     */

    public LocalDateTime getHoraSalida() {
        return horaSalida;
    }
    
    /**
     * Establece el nombre del paciente
     * @author: Matthew Ilama Piedra
     * @param nombre: nuevo nombre
     */

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * Establece la cédula del paciente
     * @author: Matthew Ilama Piedra
     * @param cedula: nueva cédula
     */   

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    
    /**
     * Establece el número de ficha del paciente
     * @author: Matthew Ilama Piedra
     * @param Ficha: nuevo número de ficha
     */  

    public void setFicha(String Ficha) {
        this.Ficha = Ficha;
    }
    
    /**
     * Establece la hora de llegada del paciente
     * @author: Matthew Ilama Piedra
     * @param horallegada: nueva hora de llegada
     */

    public void setHorallegada(LocalDateTime horallegada) {
        this.horallegada = horallegada;
    }
    
    /**
     * Establece la hora de salida del paciente
     * @author: Matthew Ilama Piedra
     * @param horaSalida: nueva hora de salida
     */

    public void setHoraSalida(LocalDateTime horaSalida) {
        this.horaSalida = horaSalida;
    }
    
    
    
}
