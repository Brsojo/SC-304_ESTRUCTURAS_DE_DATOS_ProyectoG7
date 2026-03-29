/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ed_g7_proyectofinal;

import java.time.LocalDateTime;

import javax.swing.JOptionPane;
/**
 * Clase principal que controla el flujo general del sistema hospitalario.
 *
 * Esta clase se encarga de:
 * - Mostrar y gestionar los menús del sistema
 * - Coordinar la atención de pacientes
 * - Manejar colas preferenciales y regulares
 * - Registrar citas y medicamentos
 * - Consultar el expediente único de los pacientes
 *
 * Actúa como punto central de interacción entre el usuario
 * y las distintas estructuras de datos del sistema.
 *
 * @author Rolando Salas
 * @version 2.0
 */
public class ModuloPrincipal {
    /** Opción ingresada por el usuario en los menús */
    private String respuesta;
    /** Índice numérico de la opción seleccionada */
    private int indice;
    
    /** Cola de pacientes preferenciales */ 
    ColaPacientes ColaPreferencial = new ColaPacientes();
    
    /** Cola de pacientes regulares */    
    ColaPacientes ColaRegular = new ColaPacientes();
    
    /** Pila utilizada para registrar quejas de pacientes */
    PilaQuejas miPila = new PilaQuejas();
    
    /** Contador de pacientes preferenciales atendidos consecutivamente */
    private int contadorPreferenciales = 0;
     
    /** Lista simple utilizada como bitácora de citas del día */
    BitacoraCitasDia bitacoraDelDia = new BitacoraCitasDia();

    /** Lista doble circular que almacena los expedientes únicos de los pacientes */
    ExpedientePaciente expedientes = new ExpedientePaciente();
    
    /**
     * Constructor de la clase ModuloPrincipal.
     * Inicializa las estructuras de datos del sistema.
     */
    public ModuloPrincipal(){
    }
    
     /**
     * Muestra el menú principal del sistema y gestiona la navegación
     * @author: Alex Padilla Chinchilla
     */
    
    public void MenuPrincipal() {
        Paciente temp ;
        do {
            
            respuesta = JOptionPane.showInputDialog("***************** Hospital Su Salud *****************\n\n"
                    +"*****Bienvenido al Sistema de control de pacientes*******\n\n"
                    +"***************** MENU PRINCIPAL *****************\n"
                    + "\n1)Gestionar Llegada de Pacientes"
                    + "\n2)Consultar Bitácora de Citas del Día"
                    + "\n3)Consultar expediente de pacientes"                    
                    + "\n4)Ayuda"
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
                    if(!respuesta.matches("[0-7]")){
                        respuesta="9";
                    }
                    indice = Integer.parseInt(respuesta);
                    switch (indice) {
                        case 1:
                           
                            String ced=JOptionPane.showInputDialog(null, "Ingrese la cedula del paciente: ");
                            String nom =JOptionPane.showInputDialog(null, "Ingrese el nombre del paciente: ");
                            int edad = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingresese la edad del paciente"));
                            String genero = JOptionPane.showInputDialog(null, "Ingrese el genero del paciente: ");
                            String tipo =JOptionPane.showInputDialog(null, "Tipo de atencion: \n 1. Preferencial \n 2. Regular");
                            if(tipo.equals("1")){
                                ColaPreferencial.seleccionarFicha(temp =new Paciente(nom,ced,edad,genero),tipo);
                            }else{
                                ColaRegular.seleccionarFicha(temp =new Paciente(nom,ced,edad,genero),tipo);
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
                    bitacoraDelDia.consultarBitacoraDia();
                    break;
                case 3:
                    expedientes.mostrarExpediente();
                    break;
                case 4:
                    JOptionPane.showMessageDialog(null, "**************Version de la herramienta **************"
                    +"\n Avance 2 V 2.0.7"
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
    
    /**
     * Atiende al siguiente paciente aplicando el algoritmo 2:1
     * (2 preferenciales por cada 1 regular)
     * 
     *  * Durante la atención:
     * - Se registra la cita médica
     * - Se registran los medicamentos prescritos
     * - Se inserta el paciente en el expediente único si no existe
     * - Se almacena el paciente en la bitácora de citas del día
     * @author: Matthew Ilama Piedra
     */
    
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
// Se obtiene la fecha actual sin nanosegundos para mantener consistencia en el sistema
        LocalDateTime ahora = LocalDateTime.now().withNano(0); 
        
        atendido.setHoraAtencion(ahora);
        bitacoraDelDia.insertarOrdenado(atendido);
        JOptionPane.showMessageDialog(null,"Ficha # " + atendido.getFicha() + 
                           " con cédula " + atendido.getCedula() + 
                           " pasar a consulta médica.");
         
        NodoDoble paciente = expedientes.retornaPaciente(atendido.getCedula()); // busca paciente en expedientes
         if (paciente == null) {
            JOptionPane.showMessageDialog(null,
                "Paciente " + atendido.getNombre() + " asiste a consulta por primera vez");
            expedientes.insertaOrdenado(atendido);
            paciente = expedientes.retornaPaciente(atendido.getCedula()); // ya existe
        }
         
        String doctor = JOptionPane.showInputDialog("Ingrese el nombre del doctor: ") ;
        String diagnostico = JOptionPane.showInputDialog("Ingrese el diagnostico: ") ;
        String medicamento = JOptionPane.showInputDialog("Ingrese el medicamento recetado: ") ;
       
        paciente.getHistoricoMedicamentosPrescritos().insertarMedicamento(ahora, medicamento);
        paciente.getHistoricoCitas().insertarCita(ahora, doctor, diagnostico);

 
    } else {
        JOptionPane.showMessageDialog(null,"No hay pacientes pendientes en ninguna cola.");
    }
}
    
/**
 * Convierte un objeto LocalDateTime a un formato de texto legible sin nanosegundos.
 * El formato generado es: yyyy-MM-dd HH:mm:ss
 * 
 * Este método evita el formato por defecto de LocalDateTime (que incluye la 'T')
 * y permite mostrar fechas de forma más clara en el sistema.
 * 
 * @param fecha objeto LocalDateTime que se desea formatear
 * @return String con la fecha formateada o "Sin fecha" si es null
 */    
    public static String formatearFecha(LocalDateTime fecha) {
        if (fecha == null) return "Sin fecha";

        return fecha.getYear() + "-" +
            String.format("%02d", fecha.getMonthValue()) + "-" +
            String.format("%02d", fecha.getDayOfMonth()) + " " +
            String.format("%02d", fecha.getHour()) + ":" +
            String.format("%02d", fecha.getMinute()) + ":" +
            String.format("%02d", fecha.getSecond());         
    }
}