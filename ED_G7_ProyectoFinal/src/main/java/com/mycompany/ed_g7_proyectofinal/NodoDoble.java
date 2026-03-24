package com.mycompany.ed_g7_proyectofinal;

public class NodoDoble {

    private NodoDoble anterior;
    private int dato;
    private NodoDoble siguiente;

    public NodoDoble(int dato) {
        this.dato = dato;
        this.anterior = this.siguiente = null;
    }

    public NodoDoble getAnterior() {
        return anterior;
    }

    public int getDato() {
        return dato;
    }

    public NodoDoble getSiguiente() {
        return siguiente;
    }

    public void setAnterior(NodoDoble anterior) {
        this.anterior = anterior;
    }

    public void setDato(int dato) {
        this.dato = dato;
    }

    public void setSiguiente(NodoDoble siguiente) {
        this.siguiente = siguiente;
    }
    
}
