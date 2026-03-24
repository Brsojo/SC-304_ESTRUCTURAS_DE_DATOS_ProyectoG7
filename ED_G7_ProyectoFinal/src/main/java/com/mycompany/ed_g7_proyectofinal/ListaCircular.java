package com.mycompany.ed_g7_proyectofinal;

public class ListaCircular {
    private Nodo primero;
    private Nodo ultimo;

    public ListaCircular() {
        this.primero = null;
        this.ultimo = null;
    }

    public Nodo getPrimero() {
        return primero;
    }

    public Nodo getUltimo() {
        return ultimo;
    }

    public void setPrimero(Nodo primero) {
        this.primero = primero;
    }

    public void setUltimo(Nodo ultimo) {
        this.ultimo = ultimo;
    }
    
    public void insertaOrdenado (int valor){
        Nodo nuevoNodo = new Nodo (valor);
        // Caso 1: Lista está vacía.
        if (this.getPrimero() == null){
            primero = nuevoNodo;  // Pongo a apuntar el primero a la nueva caja-
            ultimo = primero;     // Igualo el último con el primero.
            ultimo.setSiguiente(primero);  // la hago circular.
        
        }else{
        // Caso 2: El elemento a insertar es menor igual al primero. Lo inserto a la izquierda.
            if (valor <= primero.getDato()){
                
                nuevoNodo.setSiguiente(primero);    // Amara la nueva cajita al primero.
                primero = nuevoNodo;                // Muevo el primero al nuevo primero.
                ultimo.setSiguiente(primero);       // Ligo el último al nuevo primero, para hacerla circular.
                
            }else {  
        // Caso 3: El elemento a insertar es mayor igual al último.
                if (ultimo.getDato() <= valor){
                    ultimo.setSiguiente(nuevoNodo);  // Amarramos el último a la nueva cajita.
                    ultimo = nuevoNodo;             // Mover el último
                    ultimo.setSiguiente(primero);   // ligar el último al primero para hacerlo circular.
                }
                else{
                // Caso 4: El elemento a insertar va en una posición insterna de la lista,
                // Hay que iterar. (Ciclo).
                    Nodo temp = primero;
                    while (temp.getSiguiente().getDato() < valor){
                        temp = temp.getSiguiente();
                    }
                    nuevoNodo.setSiguiente(temp.getSiguiente()); // Amarra la nueva caja al resto de la lista.
                    temp.setSiguiente(nuevoNodo);           // Amarro el temporal al nuevo siguiente.
                }
            }
        }
        
    }
    
        public void recorrer(){
            // Tarea completar este método con el código de filmina 5
        
        }
}
