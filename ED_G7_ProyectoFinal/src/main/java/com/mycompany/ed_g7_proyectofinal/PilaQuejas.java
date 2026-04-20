package com.mycompany.ed_g7_proyectofinal;

import java.time.LocalDateTime;

import javax.swing.JOptionPane;

public class PilaQuejas extends Pila{

    /**
     * Muestra todas las quejas recibidas en un cuadro de diálogo
     * @author: Brandon Sojo Acuña
     */
    
    public void quejasRecibidas(){
        Nodo temp = getCima(); // crear una variable temp para recorrer la estructura de datos sin alterar        
        //ciclo para recorrer pila
        while (temp != null){
           JOptionPane.showMessageDialog(null,"Ficha #: "+temp.getMiPaciente().getFicha()+" con cedula "+temp.getMiPaciente().getCedula()
           +" abandona la cola sin ser atendido (a) a la fecha y hora "+formatearFecha(temp.getMiPaciente().getHoraSalida())+" por el siguiente motivo: "+temp.getAbandono());
           
            temp = temp.getSiguiente();
        }        
    }

    public static String formatearFecha(LocalDateTime fecha) {
        if (fecha == null) return "Sin fecha";

        return fecha.getYear() + "-" +
            String.format("%02d", fecha.getMonthValue()) + "-" +
            String.format("%02d", fecha.getDayOfMonth()) + " " +
            String.format("%02d", fecha.getHour()) + ":" +
            String.format("%02d", fecha.getMinute()) + ":" +
            String.format("%02d", fecha.getSecond());         
    }
}
