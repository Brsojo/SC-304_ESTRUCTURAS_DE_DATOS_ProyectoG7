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