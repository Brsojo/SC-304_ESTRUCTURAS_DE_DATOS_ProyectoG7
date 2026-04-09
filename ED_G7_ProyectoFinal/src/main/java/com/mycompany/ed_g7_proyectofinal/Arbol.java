/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ed_g7_proyectofinal;

import javax.swing.JOptionPane;

/**
 *
 * @author matth
 */
public class Arbol {
    private NodoArbol raiz;

    public Arbol() {
        raiz = null;
    }

    public NodoArbol getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoArbol raiz) {
        this.raiz = raiz;
    }

    public NodoDoble buscar(String cedula) {
        return buscarRec(raiz, cedula);
    }

    private NodoDoble buscarRec(NodoArbol nodoActual, String cedula) {

        if (nodoActual == null) {
            return null;
        }

        String cedulaActual = nodoActual.getDato().getPaciente().getCedula();

        if (cedula.equals(cedulaActual)) {
            return nodoActual.getDato();
        }

        if (cedula.compareTo(cedulaActual) < 0) {
            return buscarRec(nodoActual.getNodoIzq(), cedula);
        } else {
            return buscarRec(nodoActual.getNodoDer(), cedula);
        }
    }

    public void segmentacionPacientes() {

        if (raiz == null) {
            JOptionPane.showMessageDialog(null, "No hay pacientes registrados.");
            return;
        }

        int[] contadores = new int[3];

        segmentacionRec(raiz, contadores);

        JOptionPane.showMessageDialog(null,
                "***** SEGMENTACIÓN DE PACIENTES ******\n\n" +
                        "Menores de Edad: " + contadores[0] +
                        "\nAdultos: " + contadores[1] +
                        "\nAdultos Mayores: " + contadores[2]);
    }

    private void segmentacionRec(NodoArbol nodoActual, int[] contadores) {

        if (nodoActual != null) {

            segmentacionRec(nodoActual.getNodoIzq(), contadores);

            Paciente paciente = nodoActual.getDato().getPaciente();
            int edad = paciente.getEdad();

            if (edad >= 0 && edad < 18) {
                contadores[0]++;
            } else if (edad < 65) {
                contadores[1]++;
            } else {
                contadores[2]++;
            }

            segmentacionRec(nodoActual.getNodoDer(), contadores);
        }
    }
}
