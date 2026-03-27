package com.mycompany.ed_g7_proyectofinal;

import java.util.Date;

public class ListaSimple {
    private Nodo cabeza;

    public ListaSimple() {
    }
    
    /**
     * Obtiene el nodo cabeza de la lista.
     * @author Alex Padilla Chinchilla
     * @return el nodo cabeza de la lista
     */

    public Nodo getCabeza() {
        return cabeza;
    }
    
    /**
     * Establece el nodo cabeza de la lista.
     * @author Alex Padilla Chinchilla
     * @param cabeza el nuevo nodo cabeza
     */

    public void setCabeza(Nodo cabeza) {
        this.cabeza = cabeza;
    }
    
    /**
     * Inserta un paciente en la lista de forma ordenada por cédula (de menor a mayor).
     * @author Alex Padilla Chinchilla
     * @param nPaciente el paciente a insertar en la lista
     */
    
    public void insertarOrdenado (Paciente nPaciente){
        
        Nodo nuevoNodo = new Nodo(nPaciente); // Creamos la cajita.
        
        // Caso 1: Lista está vacía.
        if (this.getCabeza() == null){
            cabeza = nuevoNodo;
        }else if (Integer.parseInt(cabeza.getMiPaciente().getCedula()) >= Integer.parseInt(nuevoNodo.getMiPaciente().getCedula())){ // Caso 2: Cuando el elemento a insertar es menor igual al primero.
            nuevoNodo.setSiguiente(cabeza); // Amarro la nueva caja al resto de la lista.
            cabeza = nuevoNodo;             // Moviendo la cabeza para que apunte al nuevo elemento menor.
        }
        else { // Caso 3: Cuando el elemento a insertar es mayor  al primero.
            // Recorrer la lista.
            Nodo aux = cabeza;   //Creo un elemento temporal y lo igual a la cabeza parra recorrer.
            while (aux.getSiguiente() != null && 
                   Integer.parseInt(aux.getSiguiente().getMiPaciente().getCedula()) < Integer.parseInt(nPaciente.getCedula())){
                aux = aux.getSiguiente(); // Avance o recorra
            }
            // Luego del ciclo el aux se queda una posición antes del lugar donde voy a insertar el elemento.
            nuevoNodo.setSiguiente(aux.getSiguiente()); // Enlaza el nuevo nodo a su lugar en la lista.
            aux.setSiguiente(nuevoNodo);  // Enlaza la lista al nuevo nodo.
        }
    }
    
    /**
     * Elimina un paciente de la lista por su nombre.
     * @author Alex Padilla Chinchilla
     * @param nombreEliminar el nombre del paciente a eliminar
     */
    
    public void eliminarNodo(String nombreEliminar){
        // Buscar el elemento a eliminar.
        Nodo actual = cabeza;
        Nodo anterior = null;
        while (actual != null && !actual.getMiPaciente().getNombre().equals(nombreEliminar)){
            anterior = actual;  // Me permite tener una referencia al elemento anterior al actual.
            actual = actual.getSiguiente(); // Actualiza la variable actual con el sgte de la lista.
        }
        //Caso 1: No encontré elemento a eliminar.
        if (actual == null)     // Significa que llegó al final de la lista y no encontró el elemento a eliminar.
            return;
        // Caso 2: Quiero eliminar el primer elemento de la lista.
        if (anterior == null) // Significa qu quiero eliminar el primero de la lista.
            cabeza = cabeza.getSiguiente(); // cabeza = actual.getSiguiente();
        else
        // Caso 3: Quiero eliminar otro elemento diferente del primero de la lista.
            anterior.setSiguiente(actual.getSiguiente());   //Saltarme la cajit que quiero eliminar
            //anterior.setSiguiente(anterior.getSiguiente().getSiguiente()); // Misma solución sin usar el actual (directametne)
    }
    
    /**
     * Elimina un paciente de la lista por su nombre y retorna el nodo eliminado.
     * @author Alex Padilla Chinchilla
     * @param nombreEliminar el nombre del paciente a eliminar
     * @return el nodo eliminado, o null si no se encontró
     */

    public Nodo eliminarNodoConRetorno(String nombreEliminar){
        // Buscar el elemento a eliminar.
        Nodo actual = cabeza;
        Nodo anterior = null;
        while (actual != null && !actual.getMiPaciente().getNombre().equals(nombreEliminar)){
            anterior = actual;  // Me permite tener una referencia al elemento anterior al actual.
            actual = actual.getSiguiente(); // Actualiza la variable actual con el sgte de la lista.
        }
        //Caso 1: No encontré elemento a eliminar.
        if (actual == null)     // Significa que llegó al final de la lista y no encontró el elemento a eliminar.
            return null;
        // Caso 2: Quiero eliminar el primer elemento de la lista.
        if (anterior == null) // Significa qu quiero eliminar el primero de la lista.
            cabeza = cabeza.getSiguiente(); // cabeza = actual.getSiguiente();
        else
        // Caso 3: Quiero eliminar otro elemento diferente del primero de la lista.
            anterior.setSiguiente(actual.getSiguiente());   //Saltarme la cajit que quiero eliminar
            //anterior.setSiguiente(anterior.getSiguiente().getSiguiente()); // Misma solución sin usar el actual (directametne)
        return actual;
    }
    
    /**
     * Busca un paciente en la lista por su nombre.
     * @author Alex Padilla Chinchilla
     * @param nombre el nombre del paciente a buscar
     * @return true si el paciente existe en la lista, false en caso contrario
     */    

    public boolean buscarpaciente (String nombre){
        Nodo aux = cabeza; // Creo una referencia auxiliar para recorrer la lista
        while (aux != null){
            if (aux.getMiPaciente().getNombre().equals(nombre))
                return true;
            else
                aux = aux.getSiguiente();
        }
        return false;
    }
    
    /**
     * Muestra en consola todos los pacientes almacenados en la bitácora.
     * @author Alex Padilla Chinchilla
     */

    public void mostrarbitacora(){
        Nodo aux = cabeza; // Creo una referencia auxiliar para recorrer la lista
        while (aux != null){
            System.out.println(aux.getMiPaciente().toStringBitacora());
            aux = aux.getSiguiente();
        }
    } 
} 
