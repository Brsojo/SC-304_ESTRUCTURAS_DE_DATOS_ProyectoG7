/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ed_g7_proyectofinal;

/**
 * Clase principal del proyecto Hospital Su Salud.
 *
 * Esta clase contiene el método {@code main} y representa
 * el punto de entrada de la aplicación.
 *
 * Su función es inicializar el módulo principal del sistema
 * y dar inicio a la ejecución del menú interactivo que
 * permite gestionar pacientes, citas, medicamentos
 * y la consulta de expedientes únicos.
 *
 * Esta clase no contiene lógica de negocio, ya que dicha
 * responsabilidad se delega completamente a la clase
 * ModuloPrincipal.
 *
 * @author Rolando Salas
 * @version 2.0
 */
public class ED_G7_ProyectoFinal {

    /**
     * Método principal que inicia la ejecución del programa
     * 
     * @author: Alex Padilla Chinchilla
     * @param args: argumentos de línea de comandos
     */

    public static void main(String[] args) {
        
        ModuloPrincipal MP = new ModuloPrincipal();
        MP.MenuPrincipal();
         

        /* 
        Arbol arbol = new Arbol();

        // Pacientes
        Paciente p1 = new Paciente("A", "1", 10, "M");
        Paciente p2 = new Paciente("B", "2", 30, "F");
        Paciente p3 = new Paciente("C", "3", 70, "M");

        // Nodos dobles
        NodoDoble n1 = new NodoDoble(p1);
        NodoDoble n2 = new NodoDoble(p2);
        NodoDoble n3 = new NodoDoble(p3);

        // Construcción manual del árbol
        NodoArbol raiz = new NodoArbol(n2); // raíz = adulto
        NodoArbol izq = new NodoArbol(n1); // menor
        NodoArbol der = new NodoArbol(n3); // adulto mayor

        raiz.setNodoIzq(izq);
        raiz.setNodoDer(der);
        arbol.setRaiz(raiz);

        arbol.segmentacionPacientes();
        */
    }
}
