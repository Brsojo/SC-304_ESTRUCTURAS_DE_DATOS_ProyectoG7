package com.mycompany.ed_g7_proyectofinal;

public class NodoDoble {
    private HistoricoCitas historicoCitas;
    private HistoricoMedicamentosPrescritos historicoMedicamentos;
    private Paciente paciente;
    private NodoDoble anterior;
    private NodoDoble siguiente;

    

    public NodoDoble(Paciente paciente) {
        this.historicoCitas = new HistoricoCitas();
        this.historicoMedicamentos = new HistoricoMedicamentosPrescritos();
        this.paciente = paciente;
    }

    public HistoricoMedicamentosPrescritos getHistoricoMedicamentosPrescritos() {
        return historicoMedicamentos;
    }

    public HistoricoCitas getHistoricoCitas() {
        return historicoCitas;
    }

     public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public NodoDoble getAnterior() {
        return anterior;
    }

    public NodoDoble getSiguiente() {
        return siguiente;
    }

    public void setAnterior(NodoDoble anterior) {
        this.anterior = anterior;
    }

    public void setSiguiente(NodoDoble siguiente) {
        this.siguiente = siguiente;
    }
    
}
