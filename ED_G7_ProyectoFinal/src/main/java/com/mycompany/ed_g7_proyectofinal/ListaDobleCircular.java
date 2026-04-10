package com.mycompany.ed_g7_proyectofinal;


/**
 * Implementa una lista doblemente enlazada circular que almacena pacientes.
 *
 * Permite insertar pacientes de forma ordenada por cédula
 *
 * @author Brandon Sojo Acuña
 * @version 1.0
 */

public class ListaDobleCircular {
    /** Referencia al primer nodo de la lista. */
    private NodoDoble primero;
    /** Referencia al último nodo de la lista. */
    private NodoDoble ultimo;

    
    /**
     * Constructor por defecto.        
     */

    public ListaDobleCircular() {
    }

    
     /**
     * Retorna el primer nodo de la lista.
     *
     * @return el NodoDoble que actúa como primero
     */

    public NodoDoble getPrimero() {
        return primero;
    }

    
    /**
     * Retorna el último nodo de la lista.
     *
     * @return el NodoDoble que actúa como último
     */

    public NodoDoble getUltimo() {
        return ultimo;
    }

    
    /**
     * Asigna el primer nodo de la lista.
     *
     * @param primero nodo a establecer como primero.
     */
    public void setPrimero(NodoDoble primero) {
        this.primero = primero;
    }

    
    /**
     * Asigna el último nodo de la lista.
     *
    * @param ultimo nodo a establecer como último.
     */
    public void setUltimo(NodoDoble ultimo) {
        this.ultimo = ultimo;
    }
    
    
    /**
     * Inserta un {@code Paciente} en la lista de forma ordenada por cédula.
     *
    * @param paciente el Paciente a insertar en la lista.
     */
    public void insertaOrdenado (Paciente paciente){
        NodoDoble nuevoNodo = new NodoDoble (paciente);
        // Caso 1: Lista está vacía.
        if (this.getPrimero() == null){
            primero = nuevoNodo;    // Apunto el primero a la nueva caja.
            ultimo = primero;       // Apunto el último al primero.
            ultimo.setSiguiente(primero);   //Apunto el sgte al primero.
            primero.setAnterior(ultimo);    // Apunto el anterio al último.
            
        }else{
        // Caso 2: El elemento a insertar es menor igual al primero. Lo inserto a la izquierda.
           if (nuevoNodo.getPaciente().getCedula().compareTo(primero.getPaciente().getCedula()) <= 0){
                nuevoNodo.setSiguiente(primero);  // Amarrar la nueva cajita al primero.
                primero.setAnterior(nuevoNodo);  // Amarro el primero para atrás a la nueva cajita.
                nuevoNodo.setAnterior(ultimo);    // Amarro la cajita nueva para atrás al último de la lista.
                ultimo.setSiguiente(nuevoNodo);   // Amarro el sgte del ultimo a la nueva cajita para que continue circular.
                primero = nuevoNodo;              // muevo el primero al nuevo nodo.
            }else {  
        // Caso 3: El elemento a insertar es mayor igual al último.
                if (nuevoNodo.getPaciente().getCedula().compareTo(ultimo.getPaciente().getCedula()) >= 0){
                    
                    ultimo.setSiguiente(nuevoNodo);  // Ponel sgte del último apuntando a la nueva cajita.
                    nuevoNodo.setSiguiente(primero);  // Hacerla circular.
                    nuevoNodo.setAnterior(ultimo);    // Amarro la nueva cajita para atrás.
                    primero.setAnterior(nuevoNodo);   // hacerla circular en el otro sentido.
                    ultimo = nuevoNodo;               // Mover el último.
                }
                else{
                // Caso 4: El elemento a insertar va en una posición insterna de la lista,
                // Hay que iterar. (Ciclo).
                NodoDoble temp = primero;
                while (temp.getSiguiente().getPaciente().getCedula().compareTo(paciente.getCedula()) < 0){
                        temp = temp.getSiguiente();
                }
                nuevoNodo.setSiguiente(temp.getSiguiente()); // Amarro el sgte de la cajita nueva al que esta después de temp.
                nuevoNodo.setAnterior(temp);            // Amarro la nueva cajita para atrás
                nuevoNodo.getSiguiente().setAnterior(nuevoNodo); // Amarro el sgte de la nueva cajita para atrás a la nueva cajita.
                temp.setSiguiente (nuevoNodo); // Amarro el temporal a la nueva cajita.
                
                }
            }
        }
    }
}
