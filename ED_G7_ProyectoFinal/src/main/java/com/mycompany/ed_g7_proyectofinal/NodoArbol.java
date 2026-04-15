/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ed_g7_proyectofinal;


/**
 * Representa un nodo dentro de un Árbol Binario de Búsqueda (ABB)
 * utilizado para gestionar el Expediente Único de Pacientes.
 *
 * Cada nodo almacena:
 * 
 *   La información general de un paciente
 *   El histórico de citas médicas del paciente
 *   El histórico de medicamentos prescritos
 *   Referencias al subárbol izquierdo y derecho
 * 
 *
 * Esta clase permite mantener un expediente completo por paciente,
 * donde los datos generales forman la clave del ABB (cédula),
 * y los historiales se almacenan en estructuras independientes.
 *
 * @author Rolando Salas
 * @version 3.0
 */

public class NodoArbol {
    /** Histórico de citas médicas asociadas al paciente*/
    private HistoricoCitas historicoCitas;
    /** Histórico de medicamentos prescritos al paciente */
    private HistoricoMedicamentosPrescritos historicoMedicamentos;
    /** Información general del paciente */
    private Paciente paciente;
    /** Referencia al nodo hijo izquierdo del árbol */
    private NodoArbol nodoIzq;
    /** Referencia al nodo hijo derecho del árbol */
    private NodoArbol nodoDer;
    

    
    /**
     * Constructor vacío del nodo.
     * Inicializa el nodo sin información almacenada.
     */
    public NodoArbol() {
    }

    
    /**
     * Constructor que crea un nodo del árbol para un paciente específico.
     *
     * Inicializa automáticamente los historiales de citas y medicamentos,
     * y establece los hijos izquierdo y derecho como nulos.
     *
     * @param paciente Paciente que se almacenará en el nodo
     */
    public NodoArbol(Paciente paciente) {
        this.paciente = paciente;
        this.historicoCitas = new HistoricoCitas();
        this.historicoMedicamentos = new HistoricoMedicamentosPrescritos();
        this.nodoIzq = null;
        this.nodoDer = null;
    }

    
    /**
     * Retorna el histórico de citas médicas del paciente.
     *
     * @return Histórico de citas
     */
    public HistoricoCitas getHistoricoCitas() {
        return historicoCitas;
    }

    
    /**
     * Asigna el histórico de citas médicas del paciente.
     *
     * @param historicoCitas Histórico de citas
     */
    public void setHistoricoCitas(HistoricoCitas historicoCitas) {
        this.historicoCitas = historicoCitas;
    }

    
    /**
     * Retorna el histórico de medicamentos prescritos al paciente.
     *
     * @return Histórico de medicamentos prescritos
     */
    public HistoricoMedicamentosPrescritos getHistoricoMedicamentos() {
        return historicoMedicamentos;
    }


    /**
     * Asigna el histórico de medicamentos prescritos al paciente.
     *
     * @param historicoMedicamentos Histórico de medicamentos
     */
    public void setHistoricoMedicamentos(HistoricoMedicamentosPrescritos historicoMedicamentos) {
        this.historicoMedicamentos = historicoMedicamentos;
    }

    
    /**
     * Retorna el paciente almacenado en el nodo.
     *
     * @return Paciente asociado al nodo
     */
    public Paciente getPaciente() {
        return paciente;
    }

    
    /**
     * Asigna el paciente que se almacenará en el nodo.
     *
     * @param paciente Paciente a asociar
     */
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    
    /**
     * Retorna el nodo hijo izquierdo.
     *
     * @return Nodo izquierdo del árbol
     */
    public NodoArbol getNodoIzq() {
        return nodoIzq;
    }

    /**
     * Asigna el nodo hijo izquierdo.
     *
     * @param nodoIzq Nodo izquierdo
     */
    public void setNodoIzq(NodoArbol nodoIzq) {
        this.nodoIzq = nodoIzq;
    }

    
    /**
     * Retorna el nodo hijo derecho.
     *
     * @return Nodo derecho del árbol
     */
    public NodoArbol getNodoDer() {
        return nodoDer;
    }

    
    /**
     * Asigna el nodo hijo derecho.
     *
     * @param nodoDer Nodo derecho
     */
    public void setNodoDer(NodoArbol nodoDer) {
        this.nodoDer = nodoDer;
    }

    
}
