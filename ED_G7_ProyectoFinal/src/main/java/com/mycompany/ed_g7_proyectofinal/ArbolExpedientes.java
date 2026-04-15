/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ed_g7_proyectofinal;

/**
 *
 * @author sanabrir
 */
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.text.Normalizer;
import java.util.Locale;



/**
 * Extiende la funcionalidad del {@link ArbolBinario} para permitir
 * la carga masiva del Expediente Único de Pacientes desde un archivo
 * en formato JSON.
 * 
 * @author Rolando Salas
 * @version 3.0
 */
public class ArbolExpedientes extends ArbolBinario{
    
    /** Ruta del archivo JSON que contiene la carga masiva de expedientes */
    String ruta = "src/main/resources/CargaMasivaExpediente.json";
        
   /** Formato utilizado para convertir las fechas del archivo JSON */
    private static final DateTimeFormatter FORMATO_FECHA =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    
    
 
    /**
     * Carga el expediente médico de pacientes desde un archivo JSON
     * y construye el Árbol Binario de Búsqueda de expedientes.
     *
     * Por cada objeto del archivo:
     * <ul>
     *   <li>Se leen los datos generales del paciente</li>
     *   <li>Se crea un {@link NodoArbol} con su expediente</li>
     *   <li>Se insertan las citas médicas en su histórico</li>
     *   <li>Se insertan los medicamentos prescritos en su histórico</li>
     * </ul>
     *
     * @return Cantidad de pacientes cargados en el árbol
     * @throws IOException Si ocurre un error al leer el archivo JSON
     */
    public int cargarExpediente() throws IOException {
        int count = 0;

        try (JsonReader reader = new JsonReader(new BufferedReader(new FileReader(ruta)))) {

           
            reader.beginArray();

            while (reader.hasNext()) {
                
                String cedula = null;
                String nombre = null;
                int edad = 0;
                String genero = null;

                // Vamos a guardar temporalmente los históricos mientras leemos
                // y los insertamos directo al nodo una vez creado
                reader.beginObject();
                // Creamos el paciente cuando tengamos lo básico
                // pero podemos crear al final también

                // Variables para insertar al histórico en el momento
                //NodoArbol nodoPaciente = null;
                NodoArbol nodoPaciente=null;
                while (reader.hasNext()) {
                    String key = reader.nextName();

                    switch (key) {
                        case "CEDULA":
                            cedula = reader.nextString();
                            break;
                        case "NOMBRE":
                            nombre = reader.nextString();
                            break;
                        case "EDAD":
                            edad = reader.nextInt();
                            break;
                        case "GENERO":
                            genero = reader.nextString();
                            Paciente p = new Paciente(nombre, cedula, edad, genero);
                            nodoPaciente = new NodoArbol(p);
                            this.insertar(nodoPaciente);
                            break;
                        case "CITAS":
                           
                            leerCitasArray(reader, nodoPaciente);
                            break;

                        case "MEDICAMENTOS":
                            leerMedicamentosArray(reader, nodoPaciente);
                            break;

                        default:
                            // Campo inesperado: lo saltamos
                            reader.skipValue();
                            break;
                    }
                }

                reader.endObject();

                // Si el JSON venía sin CITAS/MEDICAMENTOS, puede que no se haya creado nodo aún
                if (nodoPaciente == null) {
                    Paciente p = new Paciente(nombre, cedula, edad, genero);
                    //nodoPaciente = insertarODevolverNodo(p);
                }

                count++;
            }

            reader.endArray();
        }

        return count;
    }

    
    /**
     * Lee el arreglo de citas médicas desde el archivo JSON
     * y las inserta en el histórico de citas del nodo del paciente.
     *
     * @param reader Lector JSON en modo streaming
     * @param nodoPaciente Nodo del árbol asociado al paciente
     * @throws IOException Si ocurre un error al leer el JSON
     */
    private void leerCitasArray(JsonReader reader, NodoArbol nodoPaciente) throws IOException {
        if (reader.peek() == JsonToken.NULL) { reader.nextNull(); return; }

        reader.beginArray();
        while (reader.hasNext()) {
            String fechaStr = null;
            String medico = null;
            String diagnostico = null;

            reader.beginObject();
            while (reader.hasNext()) {
                String k = reader.nextName();
                switch (k) {
                    case "FECHA":
                        fechaStr = reader.nextString();                        
                        break;
                    case "MEDICO":
                        medico = reader.nextString();
                        break;
                    case "DIAGNOSTICO":
                        diagnostico = reader.nextString();                   
                        
                        break;
                    default:
                        reader.skipValue();
                }
            }
            reader.endObject();

            // Insertar directamente en el histórico del nodo
            LocalDateTime fecha = LocalDateTime.parse(fechaStr, FORMATO_FECHA);
            nodoPaciente.getHistoricoCitas().insertarCita(fecha, medico, diagnostico);
        }
        reader.endArray();
    }

    
    /**
     * Lee el arreglo de medicamentos prescritos desde el archivo JSON
     * y los inserta en el histórico de medicamentos del nodo del paciente.
     *
     * @param reader Lector JSON en modo streaming
     * @param nodoPaciente Nodo del árbol asociado al paciente
     * @throws IOException Si ocurre un error al leer el JSON
     */
    private void leerMedicamentosArray(JsonReader reader, NodoArbol nodoPaciente) throws IOException {
        if (reader.peek() == JsonToken.NULL) { reader.nextNull(); return; }

        reader.beginArray();
        while (reader.hasNext()) {
            String fechaStr = null;
            String medicamento = null;

            reader.beginObject();
            while (reader.hasNext()) {
                String k = reader.nextName();
                switch (k) {
                    case "FECHA":
                        fechaStr = reader.nextString();
                        break;
                    case "MEDICAMENTO":
                        medicamento = reader.nextString();
                        break;
                    default:
                        reader.skipValue();
                }
            }
            reader.endObject();

            LocalDateTime fecha = LocalDateTime.parse(fechaStr, FORMATO_FECHA);
            nodoPaciente.getHistoricoMedicamentos().insertarMedicamento(fecha, medicamento);
            
        }
        reader.endArray();
    }
    
    

public void inOrdenRecTest(NodoArbol nodoActual){
    if (nodoActual != null){
        inOrdenRecTest(nodoActual.getNodoIzq());

        System.out.println(
            nodoActual.getPaciente().getCedula() +
            " | citas? " + (nodoActual.getHistoricoCitas().getPrimeroCita() != null) +
            " | meds? " + (nodoActual.getHistoricoMedicamentos().getPrimeroMedicamento() != null)
        );

        inOrdenRecTest(nodoActual.getNodoDer());
    }
}



    
    // =========================================================
    // ============  CONSULTA AVANZADA / PATRONES  =============
    // =========================================================

    /**
     * Genera un reporte (ficha) de consulta avanzada.
     * El usuario puede indicar de 1 a 4 parámetros (los demás pueden ir vacíos),
     * pero NO puede dejar todos vacíos.
     *
     * @param edadInicio edad mínima (puede ser null)
     * @param edadFin edad máxima (puede ser null)
     * @param diagnostico diagnóstico a buscar (puede ser null o vacío)
     * @param genero género a buscar (puede ser null o vacío)
     * @param medicamento medicamento a buscar (puede ser null o vacío)
     * @return String con la ficha: parámetros + cantidad de pacientes que cumplen
     */
    
    public String reporteConsultaAvanzada(Integer edadInicio, Integer edadFin,
                                         String diagnostico, String genero,
                                         String medicamento) {

        // Normalización de strings (null -> "")
        diagnostico = limpiarTexto(diagnostico);
        genero = limpiarTexto(genero);
        medicamento = limpiarTexto(medicamento);

        // Validación: al menos 1 parámetro activo
        boolean filtroEdad = (edadInicio != null || edadFin != null);
        boolean filtroDiag = !diagnostico.isEmpty();
        boolean filtroGenero = !genero.isEmpty();
        boolean filtroMed = !medicamento.isEmpty();

        if (!filtroEdad && !filtroDiag && !filtroGenero && !filtroMed) {
            return  "---- REPORTE CONSULTA AVANZADA ----\n\n" +
                    "Parámetros de entrada: (VACÍOS)\n" +
                    "⚠ Debe indicar al menos 1 parámetro.\n\n" +
                    "Resultado: 0";
        }

        // Conteo recorriendo el ABB
        int encontrados = contarCoincidenciasRec(getRaiz(), edadInicio, edadFin, diagnostico, genero, medicamento);

        // Construcción de ficha
        return construirFicha(edadInicio, edadFin, diagnostico, genero, medicamento, encontrados);
    }

    /**
     * Cuenta coincidencias recorriendo el árbol completo (InOrden lógico).
     */
    private int contarCoincidenciasRec(NodoArbol nodo,
                                       Integer edadInicio, Integer edadFin,
                                       String diagnostico, String genero,
                                       String medicamento) {
        if (nodo == null) return 0;

        int count = 0;
        // izquierda
        count += contarCoincidenciasRec(nodo.getNodoIzq(), edadInicio, edadFin, diagnostico, genero, medicamento);

        // actual
        if (cumpleFiltros(nodo, edadInicio, edadFin, diagnostico, genero, medicamento)) {
            count++;
        }

        // derecha
        count += contarCoincidenciasRec(nodo.getNodoDer(), edadInicio, edadFin, diagnostico, genero, medicamento);

        return count;
    }

    /**
     * Verifica si un expediente (NodoArbol) cumple los filtros activos.
     */
    private boolean cumpleFiltros(NodoArbol nodo,
                                 Integer edadInicio, Integer edadFin,
                                 String diagnostico, String genero,
                                 String medicamento) {

        Paciente p = nodo.getPaciente();
        if (p == null) return false;

        // 1) Edad (si aplica)
        if (edadInicio != null || edadFin != null) {
            int edad = p.getEdad();
            if (edadInicio != null && edad < edadInicio) return false;
            if (edadFin != null && edad > edadFin) return false;
        }

        // 2) Género (si aplica)
        if (genero != null && !genero.isEmpty()) {
            String genPaciente = limpiarTexto(p.getGenero());
            // comparación flexible: exacta por texto normalizado
            if (!genPaciente.equals(genero)) return false;
        }

        // 3) Diagnóstico (si aplica) -> en el histórico de citas
        if (diagnostico != null && !diagnostico.isEmpty()) {
            if (!historicoContieneDiagnostico(nodo.getHistoricoCitas(), diagnostico)) return false;
        }

        // 4) Medicamento (si aplica) -> en el histórico de medicamentos
        if (medicamento != null && !medicamento.isEmpty()) {
            if (!historicoContieneMedicamento(nodo.getHistoricoMedicamentos(), medicamento)) return false;
        }

        return true;
    }

    // =========================================================
    // ======  BÚSQUEDA EN HISTÓRICOS (CITAS / MEDS)  ===========
    // =========================================================

    /**
     * Retorna true si el histórico de citas contiene el diagnóstico buscado.
     *
     * NOTA: Ajusta los nombres de getters si tu implementación varía.
     */
    private boolean historicoContieneDiagnostico(HistoricoCitas historico, String diagBuscado) {
        if (historico == null) return false;

        diagBuscado = limpiarTexto(diagBuscado);

        // ---- Ajusta estos tipos/nombres si es necesario ----
        NodoCita primero = historico.getPrimeroCita();   // <-- si tu método se llama distinto, cámbialo aquí
        if (primero == null) return false;

        NodoCita actual = primero;
        boolean primeraVuelta = true;

        // Soporta listas circulares o lineales:
        while (actual != null && (primeraVuelta || actual != primero)) {
            primeraVuelta = false;

            String diag = limpiarTexto(actual.getDiagnostico()); // <-- ajusta getter si aplica
            if (diag.equals(diagBuscado)) {
                return true;
            }
            actual = actual.getSiguiente(); // <-- ajusta getter si aplica
        }

        return false;
    }

    /**
     * Retorna true si el histórico de medicamentos contiene el medicamento buscado.
     * 
     */
    private boolean historicoContieneMedicamento(HistoricoMedicamentosPrescritos historico, String medBuscado) {
        if (historico == null) return false;

        medBuscado = limpiarTexto(medBuscado);

        
        NodoMedicamento primero = historico.getPrimeroMedicamento(); 
        if (primero == null) return false;

        NodoMedicamento  actual = primero;
        boolean primeraVuelta = true;

        while (actual != null && (primeraVuelta || actual != primero)) {
            primeraVuelta = false;

            String med = limpiarTexto(actual.getMedicamento()); 
            if (med.equals(medBuscado)) {
                return true;
            }
            actual = actual.getSiguiente(); 
        }

        return false;
    }

    // =========================================================
    // ===================  UTILIDADES  ========================
    // =========================================================

    /**
     * Normaliza texto para comparar sin tildes, sin dobles espacios, y en MAYÚSCULA.
     * Esto ayuda a que "Migraña" = "MIGRANA" y evita problemas de encoding.
     */
    private String limpiarTexto(String s) {
        if (s == null) return "";
        s = s.trim();
        if (s.isEmpty()) return "";

        // Normaliza unicode (quita tildes)
        s = Normalizer.normalize(s, Normalizer.Form.NFD);
        s = s.replaceAll("\\p{M}", "");

        // Unifica espacios y mayúsculas
        s = s.replaceAll("\\s+", " ").toUpperCase(Locale.ROOT);

        return s;
    }

    /**
     * Construye la "ficha" del reporte con parámetros usados y resultado.
     */
    private String construirFicha(Integer edadInicio, Integer edadFin,
                                  String diagnostico, String genero,
                                  String medicamento, int encontrados) {

        StringBuilder sb = new StringBuilder();
        sb.append("---- REPORTE CONSULTA AVANZADA ----\n\n");
        sb.append("Parámetros de entrada:\n");

        if (edadInicio != null || edadFin != null) {
            sb.append("- Rango de edad: ")
              .append(edadInicio != null ? edadInicio : "SIN LÍMITE")
              .append(" a ")
              .append(edadFin != null ? edadFin : "SIN LÍMITE")
              .append("\n");
        }
        if (diagnostico != null && !diagnostico.isEmpty()) {
            sb.append("- Diagnóstico: ").append(diagnostico).append("\n");
        }
        if (genero != null && !genero.isEmpty()) {
            sb.append("- Género: ").append(genero).append("\n");
        }
        if (medicamento != null && !medicamento.isEmpty()) {
            sb.append("- Medicamento: ").append(medicamento).append("\n");
        }

        sb.append("\nResultado de la búsqueda:\n");
        sb.append("Cantidad de pacientes encontrados: ").append(encontrados).append("\n");

        return sb.toString();
    }
}

    
    
