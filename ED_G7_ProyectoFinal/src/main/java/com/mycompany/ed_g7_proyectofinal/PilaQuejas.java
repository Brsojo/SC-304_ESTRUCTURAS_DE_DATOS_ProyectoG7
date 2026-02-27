/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ed_g7_proyectofinal;

import javax.swing.JOptionPane;

/**
 *
 * @author Alex
 */
public class PilaQuejas {
    private Nodo cima;
    
    public PilaQuejas() {
        cima= null; // inicia la pila en null para indicar que la pila esta vacia
    }
    
    public Nodo getCima() {
        return cima;
    }
    
    public void setCima(Nodo cima) {
        this.cima = cima;
    }
    
    public void apilar(Paciente nPaciente, String abandono){
      Nodo miNodo = new Nodo(nPaciente); // crear nodo
       
       if (cima == null){ //La pila esta vacia
           miNodo.setAbandono(abandono);
           cima = miNodo;
       }else{ // la pila no esta vacia
           miNodo.setAbandono(abandono);
           miNodo.setSiguiente(cima); // amarrar el nodo a la pila
           cima=miNodo; //mover la cima al nuevo nodo
       }
           
   }
    
    /**
     * Retira y retorna el elemento que se encuentra en la cima (operación Pop).
     * @return El objeto Dato retirado, o null si la pila está vacía.
     */
   public Paciente desapilar(){
       if(cima==null){
           System.out.println("Pila esta vacia");
           return null;
       }else{ // pila tiene elementos
           //1. guardar lo que esta en la cima
           Paciente cimaTemp = cima.getMiPaciente();
            //2. mover la cima al de abajo
           cima=cima.getSiguiente();
           return cimaTemp;
       }
   }
   
    /**
     * Consulta el elemento en la cima sin retirarlo (operación Peek).
     * @return El objeto  en la cima, o null si está vacía.
     */
   public Paciente devuelveCima(){ 
       //if(cima==null){
       if(esVacia()){
           System.out.println("Pila esta vacia");
           return null;
       }else{ // pila tiene elementos
           return cima.getMiPaciente();
       }
   }
   
   /**
     * Verifica si la pila carece de elementos.
     * @return true si la cima es nula;  false en caso contrario.
     */
   public boolean esVacia(){
       if (cima==null)
           return true;
       else
           return false;
   }
   
     /**
     * Recorre la pila para contar el número total de elementos.
     * @return El número de nodos presentes en la estructura.
     */
   public int retornaTamano(){
       Nodo temp = cima; // crear una variable temp para recorrer la estructura de datos sin alterar 
       
       int tamano = 0;
       //ciclo para recorrer pila
       while (temp != null){
           tamano++;
           temp=temp.getSiguiente();
       }
       return tamano;
   }
   
   /**
     * Imprime en la consola los valores almacenados en la pila, 
     * desde la cima hasta el fondo.
     */
    public void imprimePila(){
        Nodo temp = cima; // crear una variable temp para recorrer la estructura de datos sin alterar        
        //ciclo para recorrer pila
        while (temp != null){
            System.out.println("Ficha: "+temp.getMiPaciente().getFicha());
            temp=temp.getSiguiente();
        }        
    }
    
    public void quejasRecibidas(){
        Nodo temp = cima; // crear una variable temp para recorrer la estructura de datos sin alterar        
        //ciclo para recorrer pila
        while (temp != null){
           JOptionPane.showMessageDialog(null,"Ficha #: "+temp.getMiPaciente().getFicha()+" con cedula "+temp.getMiPaciente().getCedula()
           +"abandona la cola sin ser atendido (a) a la fecha y hora "+temp.getMiPaciente().getHoraSalida()+" por el siguiente motivo: "+temp.getAbandono());
           
            temp=temp.getSiguiente();
        }        
    }
   
}
