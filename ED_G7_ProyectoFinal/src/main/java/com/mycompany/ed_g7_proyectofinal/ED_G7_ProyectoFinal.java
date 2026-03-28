/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ed_g7_proyectofinal;

/**
 *
 * @author Rolando
 */
public class ED_G7_ProyectoFinal {
    
     /**
     * Método principal que inicia la ejecución del programa
     * @author: Alex Padilla Chinchilla
     * @param args: argumentos de línea de comandos
     */

    public static void main(String[] args) {
        ModuloPrincipal MP = new ModuloPrincipal();
        MP.MenuPrincipal();
        
        /*
        ListaCircular lista = new ListaCircular();

        Paciente p1 = new Paciente("Juan", "3");
        Paciente p2 = new Paciente("Maria", "1");
        Paciente p3 = new Paciente("Carlos", "2");
        
        lista.insertaOrdenado(p1);
        lista.insertaOrdenado(p2);
        lista.insertaOrdenado(p3);
        
        p1.getHistoricoCitas().insertarCita(
        LocalDateTime.now(), "Dr. Ana", "Dolor de cabeza"
    );
    p1.getHistoricoMedicamentos().insertarMedicamento(
        LocalDateTime.now(), "Ibuprofeno"
    );

    p2.getHistoricoCitas().insertarCita(
        LocalDateTime.now(), "Dr. Luis", "Fiebre"
    );
    p2.getHistoricoMedicamentos().insertarMedicamento(
        LocalDateTime.now(), "Paracetamol"
    );

    p3.getHistoricoCitas().insertarCita(
        LocalDateTime.now(), "Dr. Carlos", "Dolor estomacal"
    );
    p3.getHistoricoMedicamentos().insertarMedicamento(
        LocalDateTime.now(), "Omeprazol"
    );

    // Recorrer lista y mostrar con JOptionPane
    if (lista.getPrimero() == null) {
        JOptionPane.showMessageDialog(null, "Lista vacía");
        return;
    }

    Nodo actual = lista.getPrimero();

    do {
        Paciente p = actual.getMiPaciente();

        String info = "============================\n";
        info += "Paciente: " + p.getNombre() + "\n";
        info += "Cédula: " + p.getCedula() + "\n\n";

        info += "--- HISTORIAL DE CITAS ---\n";
        info += p.getHistoricoCitas().mostrarHistorial() + "\n\n";

        info += "--- HISTORIAL DE MEDICAMENTOS ---\n";
        info += p.getHistoricoMedicamentos().mostrarMedicamentos();

        JOptionPane.showMessageDialog(null, info);

        actual = actual.getSiguiente();

    } while (actual != lista.getPrimero());
    }
*/
    }
}
