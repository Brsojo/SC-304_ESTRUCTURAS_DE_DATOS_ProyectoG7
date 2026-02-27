/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ed_g7_proyectofinal;

import javax.swing.JOptionPane;
import java.time.LocalDateTime;
/**
 *
 * @author Rolando
 */
public class ModuloPrincipal {
    private String respuesta;
    private int indice;
    
     Cola ColaPreferencial = new Cola();
        
     Cola ColaRegular = new Cola();
     
     PilaQuejas miPila = new PilaQuejas();
     
     private int contadorPreferenciales = 0;
     
    public ModuloPrincipal(){
    
    }
    public void MenuPrincipal() {
        Paciente temp ;
        do {
            
            respuesta = JOptionPane.showInputDialog("***************** Hospital Su Salud *****************\n\n"
                    +"*****Bienvenido al Sistema de control de pacientes*******\n\n"
                    +"***************** MENU PRINCIPAL *****************\n"
                    + "\n1)Gestionar Llegada de Pacientes"
                    + "\n2)Ayuda"
                    + "\n0)Salir");
            if(respuesta==null){
                respuesta="0";
            }
            if(!respuesta.matches("[0-5]")){
                respuesta="9";
            }
            
            indice = Integer.parseInt(respuesta);
            
            switch (indice) {
                case 1:
                    
                    respuesta = JOptionPane.showInputDialog("***********Opcion*************\n"

                    + "\n1)Seleccionar Ficha"
                    + "\n2)Atender Paciente"
                    + "\n3)Abandonar Cola de Pacientes"
                    + "\n4)Mostrar Fichas Pendientes"
                    + "\n5)Mostrar Quejas Recibidas"
                    + "\n\n0)Regresar");
                    if(respuesta==null){
                        respuesta="0";
                    }
                    if(!respuesta.matches("[0-5]")){
                        respuesta="9";
                    }
                    indice = Integer.parseInt(respuesta);
                    switch (indice) {
                        case 1:
                           
                            String ced=JOptionPane.showInputDialog(null, "Ingrese la cedula del paciente: ");
                            String nom =JOptionPane.showInputDialog(null, "Ingrese el nombre del paciente: ");
                            String tipo =JOptionPane.showInputDialog(null, "Tipo de atencion: \n 1. Preferencial \n 2. Regular");
                            if(tipo.equals("1")){
                                ColaPreferencial.seleccionarFicha(temp =new Paciente(nom,ced),tipo);
                            }else{
                                ColaRegular.seleccionarFicha(temp =new Paciente(nom,ced),tipo);
                            }
                            break;

                        case 2:
                            this.atenderPaciente();
                            break;
                        case 3:
                            String ced2=JOptionPane.showInputDialog(null, "Ingrese la cedula del paciente que desea abandonar la cola: ");
                            String mensajeQueja=JOptionPane.showInputDialog(null, "Ingrese el motivo por el que abandona la cola:  ");
                            Paciente pAbandona = ColaPreferencial.buscarPaciente(ced2);
                            if (pAbandona == null) {
                               pAbandona = ColaRegular.buscarPaciente(ced2);
                            }
                            if (pAbandona != null) {
                                
                                if (pAbandona.getFicha().startsWith("P")) {
                                    ColaPreferencial.abandonarCola(pAbandona);
                                    } else {
                                    ColaRegular.abandonarCola(pAbandona);
                                }
                                miPila.apilar(pAbandona, mensajeQueja);
                            }
                            else {
                                    System.out.println("Paciente con cédula " + ced2 + " no encontrado.");
                            }       
                            break;
                        case 4:
                            //JOptionPane.showMessageDialog(null, "Mostrar Fichas Pendientes");
                            ColaPreferencial.mostrarFichas(ColaPreferencial, ColaRegular);
                            break;
                        case 5:
                            //JOptionPane.showMessageDialog(null, "Mostrar Quejas Recibidas");
                            miPila.quejasRecibidas();
                            break;
                        case 0:
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Valor incorrecto...");
                            break;
                    }
                    indice=1;
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null, "**************Version de la herramienta **************"
                    +"\n Avance 1 V 1.0.7"
                    +"\n\n Dessarrolado por:"
                    +"\n FI25049164  ILAMA PIEDRA MATTHEW"
                    +"\n FI24041046  PADILLA CHINCHILLA ALEX"
                    +"\n FI25047245  SALAS SANABRIA JOSE ROLANDO"
                    +"\n FI24038193  SOJO ACUÑA BRANDON YAHIR"
                    );
                    
                    break;
                case 0:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Error...");
                    break;
            }
                    
            
        
           } while (indice != 0);
    }
    
    public void atenderPaciente() {
    Paciente atendido = null;

    // Hay al menos un paciente preferencial y el contador es menor a dos, o no hay regulares
    if (!ColaPreferencial.estaVacia() && (contadorPreferenciales < 2 || ColaRegular.estaVacia())) {
        
        atendido = ColaPreferencial.desencolar();
        contadorPreferenciales++; 
        
        // Si la cola regular estaba vacía y pasamos un P, 
        // resetear el contador
        if (ColaRegular.estaVacia()) 
            contadorPreferenciales = 0;

    } 
    // Si no hay preferenciales, pero si hay regulares
    else if (!ColaRegular.estaVacia()) {
        
        atendido = ColaRegular.desencolar();
        contadorPreferenciales = 0; // IMPORTANTE: Al pasar un regular, se reinicia el ciclo
        
    }

    
    if (atendido != null) {
        JOptionPane.showMessageDialog(null,"Ficha # " + atendido.getFicha() + 
                           " con cédula " + atendido.getCedula() + 
                           " pasar a consulta médica.");
    } else {
        JOptionPane.showMessageDialog(null,"No hay pacientes pendientes en ninguna cola.");
    }
}
         
}
