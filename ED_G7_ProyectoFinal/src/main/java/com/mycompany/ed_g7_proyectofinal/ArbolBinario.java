/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ed_g7_proyectofinal;


/**
 * Implementa un Árbol Binario de Búsqueda (ABB) para la gestión
 * del Expediente Único de Pacientes.
 *
 * El árbol utiliza la cédula del paciente como clave de ordenamiento,
 * permitiendo operaciones eficientes de inserción, recorrido,
 * eliminación y análisis estructural del árbol.
 *
 * Cada nodo del árbol está representado por la clase {@link NodoArbol},
 * la cual encapsula: 
 *   Datos generales del paciente
 *   Histórico de citas médicas
 *   Histórico de medicamentos prescritos
 * 
 * Esta clase corresponde al Avance 3 del proyecto, donde se sustituye
 * el uso de estructuras lineales por un ABB para mejorar la eficiencia
 * en la administración de expedientes médicos.
 *
 * @author Rolando Salas
 * @version 3.0
 */

public class ArbolBinario {
    /** Referencia a la raíz del árbol binario */
    protected NodoArbol raiz;

    
    /**
     * Constructor del árbol binario.
     * Inicializa el árbol como vacío.
     */
    public ArbolBinario() {
        raiz=null;
    }

    
    /**
     * Retorna la raíz del árbol.
     *
     * @return Nodo raíz del árbol
     *
    */
    public NodoArbol getRaiz() {
        return raiz;
    }

    
    /**
     * Asigna un nuevo nodo como raíz del árbol.
     *
     * @param raiz Nodo que será la nueva raíz
     */
    public void setRaiz(NodoArbol raiz) {
        this.raiz = raiz;
    }
    

    /**
     * Inserta un nodo de paciente en el árbol binario de búsqueda.
     * Este método actúa como envoltorio del método recursivo.
     *
     * @param nodoPaciente Nodo que contiene el expediente del paciente
     */
    public void insertar(NodoArbol nodoPaciente){
        raiz= insertarRec(raiz,nodoPaciente);
        
    }


    /**
     * Método recursivo para insertar un nodo en el árbol binario.
     *
     * @param nodoActual Nodo actual en el recorrido
     * @param nodoPaciente Nodo del paciente que se desea insertar
     * @return Nodo actualizado del árbol
     */
    private NodoArbol insertarRec(NodoArbol nodoActual, NodoArbol nodoPaciente){
        //caso 1, arbol vacio
        if(nodoActual==null){
            return nodoPaciente;// new NodoArbol(nodoPaciente.getPaciente());
        }
        //caso 2, valor es menor que el nodo actual, va a la izq
        if(Integer.parseInt(nodoPaciente.getPaciente().getCedula())<Integer.parseInt(nodoActual.getPaciente().getCedula())){
            NodoArbol nodoAux = insertarRec(nodoActual.getNodoIzq(),nodoPaciente);
            nodoActual.setNodoIzq(nodoAux);
        }
        else if(Integer.parseInt(nodoPaciente.getPaciente().getCedula())>Integer.parseInt(nodoActual.getPaciente().getCedula())){
            //caso 3 valor es mayor que nodo actual, va a la der
            NodoArbol nodoAux = insertarRec(nodoActual.getNodoDer(),nodoPaciente);
            nodoActual.setNodoDer(nodoAux);
        }
        return nodoActual;
    }
   

    /**
     * Recorre el árbol en orden (InOrden).
     * Muestra las cédulas de los pacientes en orden ascendente.
     */
    public void inOrden(){
        inOrdenRec(raiz);
    }
    

    /**
     * Método recursivo para el recorrido InOrden.
     *
     * @param nodoActual Nodo actual del recorrido
     */
    private void inOrdenRec(NodoArbol nodoActual){
        if (nodoActual!= null){
            inOrdenRec(nodoActual.getNodoIzq());
            System.out.println(nodoActual.getPaciente().getCedula());
            inOrdenRec(nodoActual.getNodoDer());
        }
    }
    

    /**
     * Recorre el árbol en preorden (PreOrden).
     */
    public void preOrden(){
        preOrdenRec(raiz);
    }
    

    /**
     * Método recursivo para el recorrido PreOrden.
     *
     * @param nodoActual Nodo actual del recorrido
     */
    private void preOrdenRec(NodoArbol nodoActual){
        if (nodoActual!= null){
            System.out.println(nodoActual.getPaciente().getCedula());
            preOrdenRec(nodoActual.getNodoIzq());
            preOrdenRec(nodoActual.getNodoDer());
        }
    }
    

    /**
     * Recorre el árbol en postorden (PostOrden).
     */
    public void postOrden(){
        postOrdenRec(raiz);
    }
    

    /**
     * Método recursivo para el recorrido PostOrden.
     *
     * @param nodoActual Nodo actual del recorrido
     */
    private void postOrdenRec(NodoArbol nodoActual){
        if (nodoActual!= null){            
            postOrdenRec(nodoActual.getNodoIzq());
            postOrdenRec(nodoActual.getNodoDer());
            System.out.println(nodoActual.getPaciente().getCedula());
        }
    }
    

    /**
     * Elimina un nodo del árbol según la cédula del paciente.
     *
     * @param cedula Cédula del paciente a eliminar
     */
    public void eliminar(int cedula){
        eliminarRec(raiz,cedula);                
    }
    

    /**
     * Método recursivo para eliminar un nodo del árbol.
     *
     * @param nodoActual Nodo actual del recorrido
     * @param cedula Cédula del paciente a eliminar
     * @return Nodo actualizado tras la eliminación
     */
    public NodoArbol eliminarRec (NodoArbol nodoActual, int cedula){
        
        // buscar el elemento y posicionarme en el elemento a eliminar
        if (cedula<Integer.parseInt(nodoActual.getPaciente().getCedula())){
            nodoActual.setNodoIzq(eliminarRec(nodoActual.getNodoIzq(),cedula));
        } else if(cedula>Integer.parseInt(nodoActual.getPaciente().getCedula())){
            nodoActual.setNodoDer(eliminarRec(nodoActual.getNodoDer(),cedula));
        } else {
            //cuando es igual, encontre el elemento a eliminar
            // caso 1 : sin hijos
            if(nodoActual.getNodoIzq()==null && nodoActual.getNodoDer()==null){
                return null; // al retornar null lo estoy borrando
            }
            // caso 2: un hijo
            if(nodoActual.getNodoIzq()==null){ //el hijo esta a la derecha
                return nodoActual.getNodoDer();
            }else if (nodoActual.getNodoDer()==null){ //el hijo esta a la izq 
                return nodoActual.getNodoIzq();
            }
            //caso 3 : dos hijos
            NodoArbol sucesor = buscarSucesorInOrden(nodoActual.getNodoDer());
            nodoActual.setPaciente(sucesor.getPaciente()); 
            nodoActual.setNodoDer(eliminarRec(nodoActual.getNodoDer(),Integer.parseInt(sucesor.getPaciente().getCedula())));
        }
        
        return nodoActual;
    }
    
    
    /**
     * Busca el sucesor InOrden de un nodo dado.
     *
     * @param nodoBase Nodo desde el cual se busca el sucesor
     * @return Nodo sucesor InOrden
     */
    private NodoArbol buscarSucesorInOrden(NodoArbol nodoBase){
        while (nodoBase.getNodoIzq()!=null){
            nodoBase = nodoBase.getNodoIzq();
        }
        return nodoBase;        
    }  
    
    //Inicia codigo semana 11 23 Mar 2026
    
    //calcular el Nivel del arbol
    /*
    public int obtenerNivel(int valor){
        return obtenerNivelRec(raiz,valor,0);
    }
    
    private int obtenerNivelRec(NodoArbol nodoActual, int valor, int nivel){
        if(nodoActual==null){
            return -1;
        }
        if(nodoActual.getDato()==valor){ //si lo encuentro
            return nivel;
        }else {
            int nivelIzq = obtenerNivelRec(nodoActual.getNodoIzq(),valor,nivel+1);
            if(nivelIzq !=-1){
                return nivelIzq;
            } else {// si es igual a -1, no lo encontro por la izq 
                return obtenerNivelRec(nodoActual.getNodoDer(),valor,nivel+1);
            }
        }        
    }
    
    //metodos para calcular altura del arbol
    
    public int obtenerAltura(){
        return obtenerAlturaRec(raiz);
    }
    
    private int obtenerAlturaRec(NodoArbol nodoActual){
        if(nodoActual==null){
            return -1;
        }else{
            int alturaIzq= obtenerAlturaRec(nodoActual.getNodoIzq());
            int alturaDer= obtenerAlturaRec(nodoActual.getNodoDer());
            return Math.max(alturaIzq, alturaDer)+1;                                  
        }                
    }  */
    
    /*
    public void inOrdenIterativo(){
        inOrdenIterativo(raiz);
    }
    
    public void inOrdenIterativo(NodoArbol nodoActual){
       Stack <NodoArbol> pila = new Stack(); // Clase precompilada de JAVA que sirve de Pila.
       NodoArbol actual = raiz;
       while (actual != null || !pila.isEmpty() ){
           while (actual!= null){
               pila.push(actual);
               actual = actual.getNodoIzq();
           }
           actual = pila.pop();
           System.out.println(actual.getDato()+ "");
           actual = actual.getNodoDer();
        }    
   } */
    
}