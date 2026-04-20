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
         


    }
}
