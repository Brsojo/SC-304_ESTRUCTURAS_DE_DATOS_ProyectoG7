package com.mycompany.ed_g7_proyectofinal;


/**
 * Implementa una lista enlazada circular simple de pacientes.
 *
 *
 * La lista permite insertar pacientes de forma ordenada por cédula,
 * insertar nodos al final y recorrer todos los elementos de la lista.
 *
 * @author Matthew
 * @version 1.0
 */

public class ListaCircular {
    /** Referencia al primer nodo de la lista circular */
    private Nodo primero;
    /** Referencia al último nodo de la lista circular */
    private Nodo ultimo;

    
    /**
     * Constructor por defecto.
     * Inicializa la lista como vacía.
     */

    public ListaCircular() {
        this.primero = null;
        this.ultimo = null;
    }

    
    /**
     * Retorna el primer nodo de la lista.
     *
     * @return el primer {@code Nodo} o {@code null} si la lista está vacía.
     */

    public Nodo getPrimero() {
        return primero;
    }

    
    /**
     * Retorna el último nodo de la lista.
     *
     * @return el último {@code Nodo} o {@code null} si la lista está vacía.
     */
    public Nodo getUltimo() {
        return ultimo;
    }

    
    /**
     * Establece el primer nodo de la lista.
     *
     * @param primero nodo que será asignado como primero.
     */
    public void setPrimero(Nodo primero) {
        this.primero = primero;
    }

    
    /**
     * Establece el último nodo de la lista.
     *
     * @param ultimo nodo que será asignado como último.
     */
    public void setUltimo(Nodo ultimo) {
        this.ultimo = ultimo;
    }
    
    
    /**
     * Inserta un paciente en la lista de forma ordenada según su cédula.
     * @param paciente objeto {@code Paciente} que se desea insertar.
     */

    public void insertaOrdenado (Paciente paciente){
        Nodo nuevoNodo = new Nodo (paciente);
        // Caso 1: Lista está vacía.
        if (this.getPrimero() == null){
            primero = nuevoNodo;  // Pongo a apuntar el primero a la nueva caja
            ultimo = primero;     // Igualo el último con el primero.
            ultimo.setSiguiente(primero);  // la hago circular.
        
        }else{
        // Caso 2: El elemento a insertar es menor igual al primero. Lo inserto a la izquierda.
            if (paciente.getCedula().compareTo(primero.getMiPaciente().getCedula()) <= 0){
                
                nuevoNodo.setSiguiente(primero);    // Amara la nueva cajita al primero.
                primero = nuevoNodo;                // Muevo el primero al nuevo primero.
                ultimo.setSiguiente(primero);       // Ligo el último al nuevo primero, para hacerla circular.
                
            }else {  
        // Caso 3: El elemento a insertar es mayor igual al último.
                if (ultimo.getMiPaciente().getCedula().compareTo(paciente.getCedula()) <= 0){
                    ultimo.setSiguiente(nuevoNodo);  // Amarramos el último a la nueva cajita.
                    ultimo = nuevoNodo;             // Mover el último
                    ultimo.setSiguiente(primero);   // ligar el último al primero para hacerlo circular.
                }
                else{
                // Caso 4: El elemento a insertar va en una posición insterna de la lista,
                // Hay que iterar. (Ciclo).
                    Nodo temp = primero;
                    while (temp.getSiguiente().getMiPaciente().getCedula()
                            .compareTo(paciente.getCedula())< 0){
                        temp = temp.getSiguiente();
                    }
                    nuevoNodo.setSiguiente(temp.getSiguiente()); // Amarra la nueva caja al resto de la lista.
                    temp.setSiguiente(nuevoNodo);           // Amarro el temporal al nuevo siguiente.
                }
            }
        }
        
    }

    
    /**
     * Inserta un nodo al final de la lista circular.
     *
     * Este método no valida orden y se utiliza como operación básica
     * de inserción.
     *
     * @param nuevoNodo nodo que se desea insertar en la lista.
     */

    protected void insertarNodo(Nodo nuevoNodo) { 
    
        if (primero == null) { 
            primero = nuevoNodo; 
            ultimo = nuevoNodo; 
            ultimo.setSiguiente(primero); 
    
        } else {
            ultimo.setSiguiente(nuevoNodo); 
            nuevoNodo.setSiguiente(primero); 
            ultimo = nuevoNodo;
        } 
    }
    
    
    /**
     * Recorre la lista circular y retorna una representación en texto
     * de todos los pacientes almacenados.
     *  
     * @return una cadena con el nombre y la cédula de cada paciente         
     */

    public String recorrer(){

        if (primero == null) {
            return "Lista vacía";
        }

        String resultado = "";
        Nodo actual = primero;

        do {
            resultado += actual.getMiPaciente().getNombre() + " - " +
                        actual.getMiPaciente().getCedula() + "\n";

            actual = actual.getSiguiente();

        } while (actual != primero);

        return resultado;
    } 
    
}