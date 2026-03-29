package com.mycompany.ed_g7_proyectofinal;

public class NodoDoble {
    private ListaCircular historicoCitas;
    private ListaCircular historicoMedicamentosPrescritos;
    private Paciente paciente;
    private NodoDoble anterior;
    private NodoDoble siguiente;

    

    public NodoDoble(Paciente paciente) {
        this.historicoCitas = new ListaCircular();
        this.historicoMedicamentosPrescritos = new ListaCircular();
        this.paciente = paciente;
    }

    public ListaCircular getHistoricoMedicamentosPrescritos() {
        return historicoMedicamentosPrescritos;
    }

    public ListaCircular getHistoricoCitas() {
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
