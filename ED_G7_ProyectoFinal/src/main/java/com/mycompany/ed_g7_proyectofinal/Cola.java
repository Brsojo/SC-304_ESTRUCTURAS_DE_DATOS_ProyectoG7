/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ed_g7_proyectofinal;

import java.time.LocalDateTime;
import javax.swing.JOptionPane;

/**Implementa una estructura de datos tipo Cola (FIFO)
 * para la gestión de pacientes en el sistema hospitalario.
 *
 * Esta cola es utilizada para manejar pacientes preferenciales
 * y regulares, permitiendo:
 * - Encolar pacientes
 * - Desencolar pacientes
 * - Buscar pacientes por cédula
 * - Manejar abandono de cola
 * - Asignar fichas de atención
 *
 * La clase forma parte del flujo principal de atención
 * del sistema Hospital Su Salud.
 *
 * @author Brandon
 */
public class Cola {
    /** Referencia al primer nodo de la cola */
    private Nodo primero; 
    /** Referencia al último nodo de la cola */
    private Nodo ultimo; // apunta al ultimo elemento de la cola
    
    
    /**
     * Constructor de la cola.
     * Inicializa la cola como vacía.
     */
    public Cola() {
        this.primero=null;
        this.ultimo=null;
    }
    
     /**
     * Obtiene el primer nodo de la cola
     * @author: Brandon Sojo Acuña
     * @return: primer nodo de la cola
     */
    
    public Nodo getPrimero() {
        return primero;
    }
    
     /**
     * Obtiene el último nodo de la cola
     * @author: Brandon Sojo Acuña
     * @return: último nodo de la cola
     */

    public Nodo getUltimo() {
        return ultimo;
    }
    
    /***
     * Define el ultimo
     * @param ultimo nodo a registrar
     */
    public void setUltimo(Nodo ultimo) {
        this.ultimo = ultimo;
    }
    /**
     * Agrega un nuevo paciente al final de la cola
     * @author: Brandon Sojo Acuña
     * @param nPaciente: paciente a encolar
     */
    
     public void encolar(Paciente nPaciente){
        //1.Crar el nodo
        Nodo nuevo = new Nodo();
        nuevo.setMiPaciente(nPaciente);
        if(ultimo!=null){//me garantizo que la cola no esta vacia
            ultimo.setSiguiente(nuevo);
            
        }
        ultimo = nuevo; //actualizo la referencia al ultimo para que sea la nueva cajita
        if(primero==null){ // si la cola esta vacia
            primero = nuevo;
        }
    }
     
     /**
     * Elimina y retorna el primer paciente de la cola
     * @author: Brandon Sojo Acuña
     * @return: paciente desencolado o null si la cola está vacía
     */
    
    
    public Paciente desencolar(){
        // Caso 1: La cola esta vacia
        if(primero==null){
            System.out.println("No hay elementos");
            return null;
        }else{ // caso 2: la cola no esta vacia
            Paciente temp =primero.getMiPaciente(); // guardar el primero en variable temporal para retornarlo
            primero = primero.getSiguiente(); // muevo la referencia de primero al segundo elemento
            // si la cola solo tiene un elemento, debo restablecer el ultimo
            if(primero==null){
                ultimo=null; // actualizo el ultimo con valor null
            }
            return temp;
        }
    }
    
    /**
     * Verifica si la cola está vacía
     * @author: Brandon Sojo Acuña
     * @return: true si está vacía, false en caso contrario
     */
    
    public boolean estaVacia(){
        if(primero==null){
            return true;
        }else{
            return false;
        }
        //return primero == null; 
    }
    
    /**
     * Devuelve el nombre del primer paciente sin eliminarlo
     * @author: Brandon Sojo Acuña
     * @return: nombre del primer paciente o null si la cola está vacía
     */
    
    public String devuelvePrimero(){
        if(primero!=null){
            return this.getPrimero().getMiPaciente().getNombre();
        }else{
            System.out.println("No hay elementos");
            return null;
        }
    }
    
     /**
     * Imprime en consola todos los pacientes de la cola
     * @author: Brandon Sojo Acuña
     */
    
    public void imprimeCola(){
        Nodo temp = primero; // crear una variable temp para recorrer la estructura de datos sin alterar        
        //ciclo para recorrer pila
        while (temp != null){
            System.out.println("Ficha: "+temp.getMiPaciente().getFicha()+
                    "\n Nombre: "+temp.getMiPaciente().getNombre()+
                    "\n Cedula: "+temp.getMiPaciente().getCedula()+
                    "\n Hora llegada: "+temp.getMiPaciente().getHorallegada()
                    );
            temp=temp.getSiguiente();
        }        
    }
}