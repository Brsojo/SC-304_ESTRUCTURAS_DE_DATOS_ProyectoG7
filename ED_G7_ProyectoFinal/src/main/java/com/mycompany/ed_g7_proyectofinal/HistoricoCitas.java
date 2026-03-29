package com.mycompany.ed_g7_proyectofinal;

import java.time.LocalDateTime;

/**
 * Clase que gestiona el histórico de citas médicas utilizando una lista circular.
 * Permite insertar nuevas citas y mostrar el historial completo.
 * 
 * @author Matthew
 */
public class HistoricoCitas extends ListaCircular {

    private NodoCita primero;
    private NodoCita ultimo;

    public HistoricoCitas() {
        this.primero = null;
        this.ultimo = null;
    }

    /**
     * Obtiene el primer nodo de la lista
     */
    public NodoCita getPrimeroCita() {
        return primero;
    }

    /**
     * Establece el primer nodo
     */
    public void setPrimeroCita(NodoCita primero) {
        this.primero = primero;
    }

    /**
     * Obtiene el último nodo
     */
    public NodoCita getUltimoCita() {
        return ultimo;
    }

    /**
     * Establece el último nodo
     */
    public void setUltimoCita(NodoCita ultimo) {
        this.ultimo = ultimo;
    }

    /**
     * Inserta una nueva cita en la lista circular
     * @author Matthew
     * @param fecha fecha de la cita
     * @param doctor doctor que atendió
     * @param diagnostico diagnóstico realizado
     */
    public void insertarCita(LocalDateTime fecha, String doctor, String diagnostico) {

        NodoCita nuevo = new NodoCita(fecha, doctor, diagnostico);

        if (primero == null) {
            primero = nuevo;
            ultimo = nuevo;
            ultimo.setSiguiente(primero);
        } else {
            ultimo.setSiguiente(nuevo);
            nuevo.setSiguiente(primero);
            ultimo = nuevo;
        }
    }

    /**
     * Muestra el historial completo de citas registradas.
     * 
     * Las fechas se presentan en un formato legible (yyyy-MM-dd HH:mm:ss),
     * evitando el formato por defecto de LocalDateTime.
     * 
     * @author Matthew
     * @return texto con todas las citas formateadas
     */
    public String mostrarHistorial() {

        if (primero == null) {
            return "No hay citas registradas.";
        }

        StringBuilder sb = new StringBuilder();
        NodoCita temp = primero;

        do {
            sb.append("Fecha: ")
                    .append(ModuloPrincipal.formatearFecha(temp.getFecha()))
                    .append("\n");
            sb.append("Doctor: ").append(temp.getDoctor()).append("\n");
            sb.append("Diagnóstico: ").append(temp.getDiagnostico()).append("\n\n");

            temp = temp.getSiguiente();
        } while (temp != primero);

        return sb.toString();
    }
}