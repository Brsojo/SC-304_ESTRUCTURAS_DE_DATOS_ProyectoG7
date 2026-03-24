package com.mycompany.ed_g7_proyectofinal;

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
           +"abandona la cola sin ser atendido (a) a la fecha y hora "+temp.getMiPaciente().getHoraSalida()+" por el siguiente motivo: "+temp.getAbandono());
           
            temp = temp.getSiguiente();
        }        
    }
}
