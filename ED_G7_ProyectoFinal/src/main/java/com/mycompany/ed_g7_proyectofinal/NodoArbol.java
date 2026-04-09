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

    private NodoDoble dato;
    private NodoArbol nodoIzq;
    private NodoArbol nodoDer;

    public NodoArbol() {
    }

    public NodoArbol(NodoDoble dato) {
        this.dato = dato;
        this.nodoIzq = null;
        this.nodoDer = null;
    }

    public NodoDoble getDato() {
        return dato;
    }

    public void setDato(NodoDoble dato) {
        this.dato = dato;
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
