package com.mycompany.ed_g7_proyectofinal;

import javax.swing.JOptionPane;

public class BitacoraCitasDia extends ListaSimple {

    /**
     * Consulta y muestra la bitácora de citas del día con colores según el tiempo de espera. 
     * Verde: <= 30 segundos
     * Amarillo: > 30 segundos y < 60 segundos
     * Rojo: >= 60 segundos
     * @author: Alex Padilla Chinchilla
     */
    
    public void consultarBitacoraDia() {
        //Verificar si se han atendido citas, en caso que no, muestra el mensaje
        if (this.getCabeza() == null) {
            JOptionPane.showMessageDialog(null, "No hay citas registradas en el día de hoy.");
            return;
        }

        //Se construye el mensaje en formato HTML para utilizar colores
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("<html><body style='font-family: monospace;'>");
        mensaje.append("<h2>BITÁCORA DE CITAS DEL DÍA</h2>");
        mensaje.append("<hr>");

        //Se recorre la lista simple
        Nodo actual = this.getCabeza();
        int contador = 1;

        while (actual != null) {
            Paciente p = actual.getMiPaciente();

            //Se calcula el tiempo de espera en segundos
            long tiempoEspera = 0;
            if (p.getHorallegada() != null && p.getHoraAtencion() != null) {
                tiempoEspera = java.time.Duration.between(p.getHorallegada(), p.getHoraAtencion()).getSeconds();
            }

            //Se establece el color según el tiempo de espera
            String color;
            String estado;
            if (tiempoEspera <= 30) {
                color = "#90EE90"; //Verde
                estado = "TIEMPO ÓPTIMO";
            } else if (tiempoEspera < 60) {
                color = "#FFFF99"; //Amarillo
                estado = "TIEMPO ACEPTABLE";
            } else {
                color = "#FFCCCC"; //Rojo
                estado = "DEMORA EXCESIVA";
            }

            //Se formatea las horas
            String horaLlegada = p.getHorallegada() != null
                    ? p.getHorallegada().toString().replace("T", " ") : "No registrada";
            String horaAtencion = p.getHoraAtencion() != null
                    ? p.getHoraAtencion().toString().replace("T", " ") : "No registrada";

            //Se agrega al paciente junto con el color que le corresponde
            mensaje.append("<div style='background-color: ").append(color).append("; padding: 8px; margin: 5px; border-radius: 5px;'>");
            mensaje.append("<b>CITA #").append(contador).append("</b><br>");
            mensaje.append("Ficha: <b>").append(p.getFicha()).append("</b><br>");
            mensaje.append("Nombre: ").append(p.getNombre()).append("<br>");
            mensaje.append("Cédula: ").append(p.getCedula()).append("<br>");
            mensaje.append("Hora llegada: ").append(horaLlegada).append("<br>");
            mensaje.append("Hora atención: ").append(horaAtencion).append("<br>");
            mensaje.append("Tiempo espera: <b>").append(tiempoEspera).append(" segundos</b><br>");
            mensaje.append("Estado: <b>").append(estado).append("</b><br>");
            mensaje.append("</div>");

            actual = actual.getSiguiente();
            contador++;
        }

        //Se agrega una leyenda guía para intepretar el color
        mensaje.append("<hr>");
        mensaje.append("<h3>LEYENDA DE TIEMPOS DE ESPERA:</h3>");
        mensaje.append("<div style='background-color: #90EE90; padding: 5px; margin: 2px;'>≤ 30 segundos - TIEMPO ESPERADO</div>");
        mensaje.append("<div style='background-color: #FFFF99; padding: 5px; margin: 2px;'>31 a 59 segundos - ACEPTABLE</div>");
        mensaje.append("<div style='background-color: #FFCCCC; padding: 5px; margin: 2px;'>≥ 60 segundos - DEMORA EXCESIVA</div>");
        mensaje.append("</body></html>");

        JOptionPane.showMessageDialog(null, mensaje.toString(), "Bitácora de Citas del Día", JOptionPane.PLAIN_MESSAGE);
    }
}
