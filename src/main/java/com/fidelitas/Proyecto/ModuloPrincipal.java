/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fidelitas.Proyecto;

import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class ModuloPrincipal {
   private String respuesta;
    private int indice;

    public ModuloPrincipal() {
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
                            JOptionPane.showMessageDialog(null, "Seleccionar Ficha");
                            break;

                        case 2:
                            JOptionPane.showMessageDialog(null, "Atender Paciente");
                            break;
                        case 3:
                            JOptionPane.showMessageDialog(null, "Abandonar Cola de Pacientes");
                            break;
                        case 4:
                            JOptionPane.showMessageDialog(null, "Mostrar Fichas Pendientes");
                            break;
                        case 5:
                            JOptionPane.showMessageDialog(null, "Mostrar Quejas Recibidas");
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
                    +"\n Avance 1 V 1.0.1"
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
    
    
         
}
