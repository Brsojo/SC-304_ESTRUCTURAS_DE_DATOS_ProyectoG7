/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ed_g7_proyectofinal;

/**
 *
 * @author matth
 */
public class NodoArbol {

    private HistoricoCitas historicoCitas;
    private HistoricoMedicamentosPrescritos historicoMedicamentos;
    private Paciente paciente;
    private NodoArbol nodoIzq;
    private NodoArbol nodoDer;

    
    public NodoArbol() {
    }

    public NodoArbol(Paciente paciente) {
        this.paciente = paciente;
        this.historicoCitas = new HistoricoCitas();
        this.historicoMedicamentos = new HistoricoMedicamentosPrescritos();
        this.nodoIzq = null;
        this.nodoDer = null;
    }

    public HistoricoCitas getHistoricoCitas() {
        return historicoCitas;
    }

    public void setHistoricoCitas(HistoricoCitas historicoCitas) {
        this.historicoCitas = historicoCitas;
    }

    public HistoricoMedicamentosPrescritos getHistoricoMedicamentos() {
        return historicoMedicamentos;
    }

    public void setHistoricoMedicamentos(HistoricoMedicamentosPrescritos historicoMedicamentos) {
        this.historicoMedicamentos = historicoMedicamentos;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public NodoArbol getNodoIzq() {
        return nodoIzq;
    }

    public void setNodoIzq(NodoArbol nodoIzq) {
        this.nodoIzq = nodoIzq;
    }

    public NodoArbol getNodoDer() {
        return nodoDer;
    }

    public void setNodoDer(NodoArbol nodoDer) {
        this.nodoDer = nodoDer;
    }

    
}
