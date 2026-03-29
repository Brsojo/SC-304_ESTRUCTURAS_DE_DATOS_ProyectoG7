package com.mycompany.ed_g7_proyectofinal;
import javax.swing.JOptionPane;
public class ExpedientePaciente extends ListaDobleCircular{

    public Boolean buscarPaciente(String cedula){
         if (getPrimero() == null) {
        System.out.println("El expediente de pacientes está vacío");
        return false;
    }

        NodoDoble aux = getPrimero();

         do { 
            if(aux.getPaciente().getNombre().equals(cedula)){
                return true;
            }

            aux = aux.getSiguiente();

         } while (aux != getPrimero());
        return false;
    }

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

