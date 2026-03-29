package com.mycompany.ed_g7_proyectofinal;


/**
 * Representa un nodo de una lista doblemente enlazada circular.
 *
 * Cada NodoDoble tiene
 *  un paciente, el historial de citas, el historial de medicamentos, 
 * y referencias al anterior y al siguiente
 *
 * @author Matthew
 * @version 1.0
 */

public class NodoDoble {
    /** Historial de citas asociadas al paciente. */
    private HistoricoCitas historicoCitas;
    /** Historial de medicamentos prescritos al paciente. */
    private HistoricoMedicamentosPrescritos historicoMedicamentos;
    /** Paciente almacenado en el nodo. */
    private Paciente paciente;
    /** Referencia al nodo anterior en la lista doble circular. */
    private NodoDoble anterior;
    /** Referencia al nodo siguiente en la lista doble circular. */
    private NodoDoble siguiente;

    

    /**
     * Constructor que crea un nodo doble a partir de un paciente.
     *
     * Al crear el nodo:
     *   Se inicializa automáticamente un {@code HistoricoCitas} vacío.
     *   Se inicializa automáticamente un {@code HistoricoMedicamentosPrescritos} vacío.
     *
     * @param paciente el Paciente que se almacenará en el nodo.
     */

    public NodoDoble(Paciente paciente) {
        this.historicoCitas = new HistoricoCitas();
        this.historicoMedicamentos = new HistoricoMedicamentosPrescritos();
        this.paciente = paciente;
    }

    
    /**
     * Retorna el historial de medicamentos prescritos del paciente.
     *
     * @return HistoricoMedicamentosPrescritos asociado al nodo.
     */

    public HistoricoMedicamentosPrescritos getHistoricoMedicamentosPrescritos() {
        return historicoMedicamentos;
    }

    
    /**
     * Retorna el historial de citas del paciente.
     *
     * @returnHistoricoCitas asociado al nodo.
     */
    public HistoricoCitas getHistoricoCitas() {
        return historicoCitas;
    }

    
    /**
     * Retorna el paciente almacenado en el nodo.
     *
     * @return Paciente asociado a este nodo.
     */
     public Paciente getPaciente() {
        return paciente;
    }

     
    /**
     * Asigna el paciente al nodo.
     *
     * @param paciente el nuevo Paciente a asociar con el nodo.
     */
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    
    /**
     * Retorna el nodo anterior en la lista doble circular.
     *
     * @return el NodoDoble anterior, o null si no ha sido enlazado.
     */
    public NodoDoble getAnterior() {
        return anterior;
    }

    
    /**
     * Retorna el nodo siguiente en la lista doble circular.
     *
     * @return el NodoDoble siguiente, o  null si no ha sido enlazado.
     */
    public NodoDoble getSiguiente() {
        return siguiente;
    }

    
    /**
     * Asigna el nodo anterior en la lista.
     *
     * @param anterior el NodoDoble que precede a este nodo.
     */
    public void setAnterior(NodoDoble anterior) {
        this.anterior = anterior;
    }

    
    /**
     * Asigna el nodo siguiente en la lista.
     *
     * @param siguiente el NodoDoble que sigue a este nodo.
     */
    public void setSiguiente(NodoDoble siguiente) {
        this.siguiente = siguiente;
    }
    
}
