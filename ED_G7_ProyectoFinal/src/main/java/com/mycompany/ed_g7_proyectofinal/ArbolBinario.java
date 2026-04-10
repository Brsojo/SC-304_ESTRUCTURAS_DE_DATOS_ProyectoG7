/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ed_g7_proyectofinal;

import java.util.Stack;
/**
 ** Gestiona un expediente que carga pacientes desde un archivo JSON
 * 
 * @author EQUIPO
 */
  public class ArbolBinario {

    private NodoArbol raiz;

    /**
     * crea un arbol binario con su raiz en null
     */
    public ArbolBinario() {
        raiz = null;        // Indicar que el arbol está vacío.
    }

    /**
     * Obtiene la raiz del arbol
     * @return raiz del arbol
     */
    public NodoArbol getRaiz() {
        return raiz;
    }

    /**
     * setea la raiz del arbol
     * @param raiz nodo que va a ser seteado como la raiz
     */
    public void setRaiz(NodoArbol raiz) {
        this.raiz = raiz;
    }

    /**
     * metodo wrapper que llama al metodo recursivo para insertar expedientes al arbol
     * inicia desde la raiz
     * @param paciente
     */
    public void insertar(Paciente paciente) {
        raiz = insertarRec(raiz, paciente);
    }


    /**
     * Inserta un paciente en el árbol binario de búsqueda de forma recursiva.
     * La inserción se realiza comparando la cédula del paciente, si es menor se inserta a la izquierda
     * si es mayor a la derecha
     * 
     * @param nodoActual nodo actual del árbol desde donde se realiza la insercion
     * @param paciente paciente que se desea insertar en el arbol
     * @return Nodo actualizado después de la insercion
     */
    private NodoArbol insertarRec(NodoArbol nodoActual, Paciente paciente) {

        // Caso 1• Si el árbol está vacío, el nuevo nodo se convierte en la raíz.
        if (nodoActual == null) {
            return new NodoArbol(paciente);
        } else {
            // Caso 2• Si el valor es menor que el nodo actual, va a la izquierda.
            if (paciente.getCedula().compareTo(nodoActual.getPaciente().getCedula()) < 0) {
                NodoArbol nodoAux = insertarRec(nodoActual.getNodoIzq(), paciente);
                nodoActual.setNodoIzq(nodoAux);
            } else if (paciente.getCedula().compareTo(nodoActual.getPaciente().getCedula()) > 0) {
                // Caso 3• Si es mayor, va a la derecha.
                NodoArbol nodoAux = insertarRec(nodoActual.getNodoDer(), paciente);
                nodoActual.setNodoDer(nodoAux);
            }
            return nodoActual;
        }

    }
    
    /**
     * llama al metodo que imprime Inorden
     */
    public void inOrden(){
        inOrdenRec(raiz);
    }
    
    /**
     * imprime el arbol  inOrden de manera recursiva
     * @param nodoActual nodoActual nodo actual del árbol desde donde se realiza la impresion
     */
    private void inOrdenRec(NodoArbol nodoActual){
        if (nodoActual != null){
            inOrdenRec(nodoActual.getNodoIzq()); // recursivamente mando a calcular inorden del hijo de la izquierda.
            
            System.out.print(nodoActual.getPaciente()+ " ");  // Imprimo el padre.
            inOrdenRec(nodoActual.getNodoDer()); // recursivamente mando a calcular inorden del hijo de la derecha.
        }    
    }
    
    /* 
    public void preOrden(){
        preOrdenRec(raiz);
    }
    
    private void preOrdenRec(NodoArbol nodoActual){
        if (nodoActual != null){
            System.out.print(nodoActual.getPaciente()+ " ");  // Imprimo el padre.
            preOrdenRec(nodoActual.getNodoIzq()); // recursivamente mando a calcular preorden del hijo de la izquierda.
            preOrdenRec(nodoActual.getNodoDer()); // recursivamente mando a calcular preorden del hijo de la derecha.
            
        }    
    }
    */
    
    /* 
    public void postOrden(){
        postOrdenRec(raiz);
    }
    
    private void postOrdenRec(NodoArbol nodoActual){
        if (nodoActual != null){
            postOrdenRec(nodoActual.getNodoIzq()); // recursivamente mando a calcular postorden del hijo de la izquierda.
            postOrdenRec(nodoActual.getNodoDer()); // recursivamente mando a calcular postorden del hijo de la derecha.
            System.out.print(nodoActual.getPaciente()+ " ");  // Imprimo el padre.
            
        }    
    }
    */

    /* 
    // Código para eliminar un nodo.
    // Método wrapper que invoca al método recursivo.
    public void eliminar(int valor){
        raiz = eliminarRec(raiz, valor);
    }
    */

    /* 
    // método recursivo que implementa los 3 casos de eliminación
    // del algoritmo.
    public NodoArbol eliminarRec(NodoArbol nodoActual, int valor){
        // Este IF me permite buscar el elemento y posicionarme 
        // en el elemento a eliminar.
        if (valor < nodoActual.getDato())
            nodoActual.setNodoIzq(eliminarRec(nodoActual.getNodoIzq(), valor));
        else if (valor > nodoActual.getDato())
            nodoActual.setNodoDer(eliminarRec(nodoActual.getNodoDer(), valor));
        else{ // Es igual. Es decir encontré el elemento a eliminar.
            // Lo encontró y por lo tanto vamos a eliminarlo.
            // Caso 1: Nodo sin Hijos (Una hoja).
            if (nodoActual.getNodoIzq() == null && nodoActual.getNodoDer() == null)
                    return null; // Al retornar NULL, lo estoy borrando.
            // Caso 2:  Nodo con un hijo
            if (nodoActual.getNodoIzq() == null) // 2.1 Cuando el hijo es el de la derecha.
                return nodoActual.getNodoDer();
            else if (nodoActual.getNodoDer() == null) // 2.2 Cuando el hijo es el de la izquierda.
                  return nodoActual.getNodoIzq();
            // Caso 3: Nodo con 2 hijos.
            NodoArbol sucesor  = buscarSucesorInOrden(nodoActual.getNodoDer());
            nodoActual.setDato(sucesor.getDato());
            nodoActual.setNodoDer(eliminarRec(nodoActual.getNodoDer(),sucesor.getDato())); // Elimino para que no este duplicado.
        }
        return nodoActual;
    }
    */
    /* 
    private NodoArbol buscarSucesorInOrden(NodoArbol nodoBase){
        while (nodoBase.getNodoIzq() != null){
            nodoBase = nodoBase.getNodoIzq();
        }
        return nodoBase;
    }
        */
    /* 
    //Inicia código de semana 11.
    public int obtenerNivel(int valor){
        return obtenerNivelRec(raiz, valor,0);
    }
    */
    
    /* 
    private int obtenerNivelRec(NodoArbol nodoActual, int valor, int nivel){
        if (nodoActual == null)  return -1;
        if (nodoActual.getDato() == valor) // Si lo encuentro
            return nivel;
        else{
            int nivelIzq = obtenerNivelRec(nodoActual.getNodoIzq(),valor, nivel +1);
            if (nivelIzq != -1)
                return nivelIzq;
            else // Si es igual a -1. No lo encontró yendose por la izq.
                return obtenerNivelRec(nodoActual.getNodoDer(),valor, nivel +1);
        }
    }
    */
    
    /* 
    // Métodos para calcular la altura de un arbol.
    // Método wrapper o envoltura que llama al recursivo.
    public int obtenerAltura(){
        return obtenerAturaRec(raiz);
    }
        */
    /* 
    public int obtenerAturaRec(Nodo nodoActual){
        if (nodoActual == null)
            return -1;
        else{
            int alturaIzq = obtenerAturaRec(nodoActual.getNodoIzq());
            int alturaDer = obtenerAturaRec(nodoActual.getNodoDer());
            return Math.max(alturaIzq, alturaDer) +1;
            //if (alturaIzq > alturaDer)
                //return alturaIzq + 1;
            //else 
                //return alturaDer + 1;
        }
    }
    */
    
    /**
     * llama al metodo para imprimir inOrden de manera iterativa desde la raiz
     */
    public void inOrdenIterativo(){
        inOrdenIterativo(raiz);
    }
    
    /**
     * recorre el arbol con ayuda de una pila para imprimir cada nodo inOrden
     * @param nodoActual nodo actual del árbol desde donde se realiza la impresion
     */
    public void inOrdenIterativo(NodoArbol nodoActual){
       
       Stack <NodoArbol> pila = new Stack(); // Clase precompilada de JAVA que sirve de Pila.
       NodoArbol actual = raiz;
       while (actual != null || !pila.isEmpty() ){
           while (actual!= null){
               pila.push(actual);
               actual = actual.getNodoIzq();
           }
           actual = pila.pop();
           System.out.println(actual.getPaciente()+ "");
           actual = actual.getNodoDer();
        }    
   
   } 
}