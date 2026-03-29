package com.mycompany.ed_g7_proyectofinal;

import java.time.LocalDateTime;

import javax.swing.JOptionPane;

/**
 * Clase que hereda de la clase Cola y agrega metodos adicionales
 * @author Brandon Sojo Acuña
 */

public class ColaPacientes extends Cola{

    
    public ColaPacientes() {
        super();
    }

    /**
     * Devuelve una cadena con todas las fichas de la cola
     * @author: Brandon Sojo Acuña
     * @return: cadena con las fichas separadas por espacios
     */
    
    public String devuelvefichas(){
        Nodo temp = getPrimero(); // crear una variable temp para recorrer la estructura de datos sin alterar        
        String fichas="";
        //ciclo para recorrer pila
        while (temp != null){
           fichas= fichas+temp.getMiPaciente().getFicha()+" ";               
            temp=temp.getSiguiente();
        }        
        return fichas;
    }
    
    /**
     * Busca un paciente por su número de cédula
     * @author: Brandon Sojo Acuña
     * @param cedula: cédula del paciente a buscar
     * @return: paciente encontrado o null si no existe
     */
    
     public Paciente buscarPaciente(String cedula){
        Nodo temp = getPrimero(); // crear una variable temp para recorrer la estructura de datos sin alterar      
        while (temp != null){
            if(temp.getMiPaciente().getCedula().equals(cedula)){
                return temp.getMiPaciente();
            }
            temp=temp.getSiguiente();
        }
        return null;
    }
     
     /**
     * Elimina un paciente de la cola por abandono
     * @author: Jose Rolando Salas Sanabria
     * @param nPaciente: paciente que abandona la cola
     * @see EDG7-11
     */
     
    public void abandonarCola(Paciente nPaciente){
        
        // Caso 0: Cola vacía
        if (estaVacia()) {
            System.out.println("La cola está vacía.");
            return;
        }

        // Caso 1: El elemento es el primero
        if (getPrimero().getMiPaciente().getCedula().equals(nPaciente.getCedula())) {
            JOptionPane.showMessageDialog(null,"Ficha #" + nPaciente.getFicha()+" con la cedula "+nPaciente.getCedula() + " ha abandonado la cola sin ser atendido(a).");
            nPaciente.setHoraSalida(LocalDateTime.now());
            
            return;
        }

        Nodo actual = getPrimero();

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
            if (actual.getSiguiente() == getUltimo()) {
                actual.setSiguiente(null);
                setUltimo(actual);; // Retrocedemos el puntero ultimo
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
    
    /**
     * Asigna una ficha a un nuevo paciente y lo encola
     * @author: Brandon Sojo Acuña
     * @param nPaciente: paciente a registrar
     * @param tipo: tipo de atención (1: Preferencial, 2: Regular)
     * @see EDG7-9
     */
    
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
 
   /**
    * Muestra las fichas pendientes de ambas colas en un cuadro de diálogo
    * @author: Alex Padilla Chinchilla
    * @param colaPref: cola de pacientes preferenciales
    * @param colaReg: cola de pacientes regulares
    * @see EDG7-12
    */
   
   public void mostrarFichas(ColaPacientes colaPref, ColaPacientes colaReg) {
        StringBuilder sb = new StringBuilder("<html>");

        sb.append("<h3 style='color:orange;'>Pacientes Preferenciales:</h3>");
        
        sb.append("<span style='color:orange;'>").append(colaPref.devuelvefichas()).append("</span>");

        sb.append("<br><h3 style='color:green;'>Pacientes Regulares:</h3>");
        sb.append("<span style='color:green;'>").append(colaReg.devuelvefichas()).append("</span>");

        sb.append("</html>");

        JOptionPane.showMessageDialog(null, sb.toString(), "Fichas Pendientes", JOptionPane.PLAIN_MESSAGE);
    }
}
