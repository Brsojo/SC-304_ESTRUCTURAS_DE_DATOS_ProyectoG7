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

    public String getNombre() {
        return nombre;
    }
    
    public String getCedula() {
        return cedula;
    }

 

    public String getFicha() {
        return Ficha;
    }

    public LocalDateTime getHorallegada() {
        return horallegada;
    }

    public LocalDateTime getHoraSalida() {
        return horaSalida;
    }
    
    

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
   

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

  

    public void setFicha(String Ficha) {
        this.Ficha = Ficha;
    }

    public void setHorallegada(LocalDateTime horallegada) {
        this.horallegada = horallegada;
    }

    public void setHoraSalida(LocalDateTime horaSalida) {
        this.horaSalida = horaSalida;
    }
    
    
    
}
