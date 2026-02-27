/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ed_g7_proyectofinal;

import java.time.LocalDateTime;
import javax.swing.JOptionPane;

/**
 *
 * @author Brandon
 */
public class Cola {
    private Nodo primero; // apunta al primer elemento de la cola
    private Nodo ultimo; // apunta al ultimo elemento de la cola
    
    public Cola() {
        this.primero=null;
        this.ultimo=null;
    }
    
    public Nodo getPrimero() {
        return primero;
    }

    public Nodo getUltimo() {
        return ultimo;
    }
    
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
    
    public boolean estaVacia(){
        if(primero==null){
            return true;
        }else{
            return false;
        }
        //return primero == null; 
    }
    
    // metodo front
    public String devuelvePrimero(){
        if(primero!=null){
            return this.getPrimero().getMiPaciente().getNombre();
        }else{
            System.out.println("No hay elementos");
            return null;
        }
    }
    
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
    
    public String devuelvefichas(){
        Nodo temp = primero; // crear una variable temp para recorrer la estructura de datos sin alterar        
        String fichas="";
//ciclo para recorrer pila
        while (temp != null){
           fichas= fichas+temp.getMiPaciente().getFicha()+" ";               
            temp=temp.getSiguiente();
        }        
        return fichas;
    }
    
     public Paciente buscarPaciente(String cedula){
        Nodo temp = primero; // crear una variable temp para recorrer la estructura de datos sin alterar      
        while (temp != null){
            if(temp.getMiPaciente().getCedula().equals(cedula)){
                return temp.getMiPaciente();
            }
            temp=temp.getSiguiente();
        }
        return null;
    }
     
    public void abandonarCola(Paciente nPaciente){
        
        // Caso 0: Cola vacía
        if (estaVacia()) {
            System.out.println("La cola está vacía.");
            return;
        }

        // Caso 1: El elemento es el primero
        if (primero.getMiPaciente().getCedula().equals(nPaciente.getCedula())) {
            JOptionPane.showMessageDialog(null,"Ficha #" + nPaciente.getFicha()+" con la cedula "+nPaciente.getCedula() + " ha abandonado la cola sin ser atendido(a).");
            nPaciente.setHoraSalida(LocalDateTime.now());
            
            return;
        }

        Nodo actual = primero;

        // Recorremos buscando el nodo ANTERIOR al que queremos eliminar
        while (actual.getSiguiente() != null && !actual.getSiguiente().getMiPaciente().getCedula().equals(nPaciente.getCedula())) {
            actual = actual.getSiguiente();
        }

        // Si salimos del ciclo y el siguiente es null, no se encontró
        if (actual.getSiguiente() == null) {
            System.out.println("El pacienta no se encuentra en la cola.");
        } else {
            // Encontramos el elemento. actual.getSiguiente() es el nodo a eliminar.

            // Caso 2: El elemento es el último
            if (actual.getSiguiente() == ultimo) {
                actual.setSiguiente(null);
                ultimo = actual; // Retrocedemos el puntero ultimo
                nPaciente.setHoraSalida(LocalDateTime.now());
            } 
            // Caso 3: El elemento está en el medio
            else {
                // Saltamos el nodo: el siguiente de actual ahora será el siguiente del siguiente
                actual.setSiguiente(actual.getSiguiente().getSiguiente());
                nPaciente.setHoraSalida(LocalDateTime.now());
            }
                JOptionPane.showMessageDialog(null,"Ficha #" + nPaciente.getFicha()+" con la cedula "+nPaciente.getCedula() + " ha abandonado la cola sin ser atendido(a).");
        }     
        
    }
    
    public void seleccionarFicha(Paciente nPaciente,String tipo){
        nPaciente.setHorallegada(LocalDateTime.now());
        if(tipo.equals("1")){
            if(this.estaVacia()){
                nPaciente.setFicha("P"+"1");
            }else{
                 int next = Integer.parseInt(this.getUltimo().getMiPaciente().getFicha().replaceAll("[^0-9]", ""))+1;
                 nPaciente.setFicha("P"+next);
                 
            }
        }else{
            if(this.estaVacia()){
                nPaciente.setFicha("R"+"1");
            }else{
                 int next = Integer.parseInt(this.getUltimo().getMiPaciente().getFicha().replaceAll("[^0-9]", ""))+1;
                 nPaciente.setFicha("R"+next);
                 
            }
        }
        this.encolar(nPaciente);
        JOptionPane.showMessageDialog(null,"Su número de ficha es la: "+nPaciente.getFicha());
        
    }
 
   
   
   public void mostrarFichas(Cola colaPref, Cola colaReg) {
        StringBuilder sb = new StringBuilder("<html>");

        sb.append("<h3 style='color:orange;'>Pacientes Preferenciales:</h3>");
        
        sb.append("<span style='color:orange;'>").append(colaPref.devuelvefichas()).append("</span>");

        sb.append("<br><h3 style='color:green;'>Pacientes Regulares:</h3>");
        sb.append("<span style='color:green;'>").append(colaReg.devuelvefichas()).append("</span>");

        sb.append("</html>");

        JOptionPane.showMessageDialog(null, sb.toString(), "Fichas Pendientes", JOptionPane.PLAIN_MESSAGE);
    }
    
}