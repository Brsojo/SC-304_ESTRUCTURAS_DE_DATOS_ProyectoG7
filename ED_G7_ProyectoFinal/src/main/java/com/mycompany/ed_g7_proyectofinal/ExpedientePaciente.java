package com.mycompany.ed_g7_proyectofinal;
import javax.swing.JOptionPane;


/**
 * Gestiona el expediente de pacientes usando una estructura de datos basada en una
 * {@code ListaDobleCircular}. Cada nodo ({@code NodoDoble}) representa un paciente y
 * mantiene referencias a su historial de citas y medicamentos prescritos.
 *
 * Esta clase ofrece operaciones para:
 * 
 *   Buscar un paciente dentro del expediente.
 *   Retornar el nodo del paciente (si existe) a partir de su cédula.
 *   Mostrar de forma interactiva (con {@code JOptionPane}) los expedientes,
 *       permitiendo avanzar/retroceder en la lista.
 *
 *
 * @author Brandon Sojo Acuña
 *
 */

public class ExpedientePaciente extends ListaDobleCircular{

    
 /**
     * Determina si existe un paciente en el expediente según el valor recibido.
     *
     * @param cedula valor a buscar 
     * @return true si encuentra una coincidencia en algún nodo; code false si no existe
     *         o si la lista está vacía.
     */

    public Boolean buscarPaciente(String cedula){
         if (getPrimero() == null) {
        System.out.println("El expediente de pacientes está vacío");
        return false;
    }

        NodoDoble aux = getPrimero();

         do { 
            if(aux.getPaciente().getCedula().equals(cedula)){
                return true;
            }

            aux = aux.getSiguiente();

         } while (aux != getPrimero());
        return false;
    }

    
    /**
     * Retorna el nodo  asociado a un paciente cuya cédula coincida
     * con la cédula indicada.
     *
     * Este método recorre la lista circular desde  getPrimero() hasta volver al inicio.
     *
     * @param cedula cédula del paciente a localizar.
     * @return el NodoDoble del paciente si se encuentra;  null si no existe
     *         o si el expediente está vacío.
     */

    public NodoDoble retornaPaciente(String cedula){

        if (getPrimero() == null) {
            return null;
        }

        NodoDoble aux = getPrimero();

        do { 
            if(aux.getPaciente().getCedula().equals(cedula)){
                return aux; // si lo encuentra
            }

            aux = aux.getSiguiente();

        } while (aux != getPrimero());

        return null; // si no lo encuentra
    }

    
    /**
     * Muestra de forma interactiva el expediente de pacientes mediante cuadros de diálogo, 
     * incluyendo datos del paciente y sus historiales asociados.
     *
     * ,Si el expediente está vacío, informa por consola y finaliza.,
     *
     * Se muestra:
     *   Nombre, cédula, edad y género del paciente.
     *   Historial de citas
     *   Historial de medicamentos prescrito
     * 
     * Permite navegar por el historico de expedientes moviendose hacia
     * atras o hacia adelante    
     */

    public void mostrarExpediente(){

         if (getPrimero() == null) {
            System.out.println("El expediente de pacientes está vacío");
            return;
        }

        NodoDoble aux = getPrimero();

        do {
            int opcion = Integer.parseInt(JOptionPane.showInputDialog(null, "EXPEDIENTE \n"+ "Paciente: " + aux.getPaciente().getNombre()+"\n"
            +"cedula: " + aux.getPaciente().getCedula()+"\n"
            +"Edad: " + aux.getPaciente().getEdad()+"\n"
            +"Genero: " + aux.getPaciente().getGenero()+"\n"+"-----------------\n"+"HISTORIAL DE CITAS\n"+
            aux.getHistoricoCitas().mostrarHistorial()+"\n"+ "-----------------\n"+"HISTORIAL DE MEDICAMENTOS PRESCRITOS\n" + aux.getHistoricoMedicamentosPrescritos().mostrarMedicamentos()+"\n"+"\n"+"1. Siguiente expediente"+"\n"
            + "2. Expediente anterior"+"\n"+"3. Volver al menu"));

            if (opcion == 1){
                aux = aux.getSiguiente();
            }else if(opcion == 2){
                aux = aux.getAnterior();
            }else if(opcion == 3){
                return;
            }else{
                JOptionPane.showMessageDialog(null, "Numero invalido para recorrer la lista, volviendo al menu...");
                return;
            }
        } while (true);
    }

}

