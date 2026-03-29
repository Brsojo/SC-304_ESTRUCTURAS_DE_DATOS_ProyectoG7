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
    private int edad;
    private String genero;
    private String Ficha;
    private LocalDateTime horallegada;
    private LocalDateTime horaSalida;
    private LocalDateTime horaAtencion;
    

    

    public Paciente() {
    }

    public Paciente(String nombre, String cedula, int edad, String genero) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.edad = edad;
        this.genero = genero;
        
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
    

    public int getEdad() {
        return edad;
    }


    public void setEdad(int edad) {
        this.edad = edad;
    }


    public String getGenero() {
        return genero;
    }


    public void setGenero(String genero) {
        this.genero = genero;
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

    public LocalDateTime getHoraAtencion() {
        return horaAtencion;
    }

    public void setHoraAtencion(LocalDateTime horaAtencion) {
        this.horaAtencion = horaAtencion;
    }
    
    /**
     * convierte el objeto a String, en este caso para poder usarlo para mostrar citas en la bitacora
     * @return
     */
    public String toStringBitacora(){

        return "Ficha: " + Ficha + " Fecha de atencion: " + horaAtencion + toString();
    }
    /**
     * 
     * @return
     */
    @Override
    public String toString() {
        return "nombre: " + nombre + ", cedula: " + cedula + ", Ficha: " + Ficha + ", horallegada: " + horallegada
                + ", horaAtencion: " + horaAtencion;
    }
}
