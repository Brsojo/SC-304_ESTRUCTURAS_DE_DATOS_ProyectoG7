package com.mycompany.ed_g7_proyectofinal;

public class ListaDobleCircular {
    private NodoDoble primero;
    private NodoDoble ultimo;

    public ListaDobleCircular() {
    }

    public NodoDoble getPrimero() {
        return primero;
    }

    public NodoDoble getUltimo() {
        return ultimo;
    }

    public void setPrimero(NodoDoble primero) {
        this.primero = primero;
    }

    public void setUltimo(NodoDoble ultimo) {
        this.ultimo = ultimo;
    }
    
    
    public void insertaOrdenado (int valor){
        NodoDoble nuevoNodo = new NodoDoble (valor);
        // Caso 1: Lista está vacía.
        if (this.getPrimero() == null){
            primero = nuevoNodo;    // Apunto el primero a la nueva caja.
            ultimo = primero;       // Apunto el último al primero.
            ultimo.setSiguiente(primero);   //Apunto el sgte al primero.
            primero.setAnterior(ultimo);    // Apunto el anterio al último.
            
        }else{
        // Caso 2: El elemento a insertar es menor igual al primero. Lo inserto a la izquierda.
            if (valor <= primero.getDato()){
                nuevoNodo.setSiguiente(primero);  // Amarrar la nueva cajita al primero.
                primero.setAnterior(nuevoNodo);  // Amarro el primero para atrás a la nueva cajita.
                nuevoNodo.setAnterior(ultimo);    // Amarro la cajita nueva para atrás al último de la lista.
                ultimo.setSiguiente(nuevoNodo);   // Amarro el sgte del ultimo a la nueva cajita para que continue circular.
                primero = nuevoNodo;              // muevo el primero al nuevo nodo.
            }else {  
        // Caso 3: El elemento a insertar es mayor igual al último.
                if (ultimo.getDato() <= valor){
                    
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
                while (temp.getSiguiente().getDato() < valor){
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
