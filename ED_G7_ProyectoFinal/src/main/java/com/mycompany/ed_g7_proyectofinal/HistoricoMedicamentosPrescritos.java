package com.mycompany.ed_g7_proyectofinal;

import java.time.LocalDateTime;

/**
 * Clase que gestiona el histórico de medicamentos prescritos utilizando una lista circular.
 * Permite insertar medicamentos y mostrar el registro completo.
 * 
 * @author Matthew
 */
public class HistoricoMedicamentosPrescritos extends ListaCircular{

    private NodoMedicamento primero;
    private NodoMedicamento ultimo;

    public HistoricoMedicamentosPrescritos() {
        this.primero = null;
        this.ultimo = null;
    }

    /**
     * Obtiene el primer nodo
     */
    public NodoMedicamento getPrimeroMedicamento() {
        return primero;
    }

    /**
     * Inserta un medicamento en la lista circular
     */
    public void setPrimeroMedicamento(NodoMedicamento primero) {
        this.primero = primero;
    }

    /**
     * Obtiene el último nodo
     */
    public NodoMedicamento getUltimoMedicamento() {
        return ultimo;
    }

    /**
     * Establece el último nodo
     */
    public void setUltimoMedicamento(NodoMedicamento ultimo) {
        this.ultimo = ultimo;
    }

    /**
     * Inserta un medicamento en la lista circular
     * @author Matthew
     * @param fecha fecha de registro
     * @param medicamento nombre del medicamento
     */
    public void insertarMedicamento(LocalDateTime fecha, String medicamento) {

        NodoMedicamento nuevo = new NodoMedicamento(fecha, medicamento);

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
     * Muestra el historial de medicamentos registrados
     * @author Matthew
     * @return texto con los medicamentos
     */
    public String mostrarMedicamentos() {

        if (primero == null) {
            return "No hay medicamentos registrados.";
        }

        StringBuilder sb = new StringBuilder();
        NodoMedicamento temp = primero;

        do {
            sb.append("Fecha: ").append(temp.getFecha()).append("\n");
            sb.append("Medicamento: ").append(temp.getMedicamento()).append("\n\n");

            temp = temp.getSiguiente();
        } while (temp != primero);

        return sb.toString();
    }
}