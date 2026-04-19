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
import javax.swing.JOptionPane;



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
     * Cuenta coincidencias recorriendo el árbol completo 
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
     */
    private boolean historicoContieneDiagnostico(HistoricoCitas historico, String diagBuscado) {
        if (historico == null) return false;

        diagBuscado = limpiarTexto(diagBuscado);

       
        NodoCita primero = historico.getPrimeroCita();   
        if (primero == null) return false;

        NodoCita actual = primero;
        boolean primeraVuelta = true;

       
        while (actual != null && (primeraVuelta || actual != primero)) {
            primeraVuelta = false;

            String diag = limpiarTexto(actual.getDiagnostico()); 
            if (diag.equals(diagBuscado)) {
                return true;
            }
            actual = actual.getSiguiente(); 
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
     *
     */
    private String limpiarTexto(String s) {
        if (s == null) return "";
        s = s.trim();
        if (s.isEmpty()) return "";
        
        s = Normalizer.normalize(s, Normalizer.Form.NFD);
        s = s.replaceAll("\\p{M}", "");

      
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
    
    /**
     * Analiza las enfermedades más frecuentes en el historial de citas de todos los pacientes.
     * Recorre el árbol binario, extrae todos los diagnósticos del historial de citas de cada paciente,
     * cuenta la frecuencia de cada diagnóstico y los ordena de mayor a menor.
     * @return String con el listado de diagnósticos y su frecuencia, ordenados de mayor a menor.
     *         Si no hay pacientes o diagnósticos, retorna un mensaje indicando la situación.
     * @author Alex Padilla Chinchilla
     */
    
    public String analisisEnfermedadesMasFrecuentes() {
        if (getRaiz() == null) {
            return "ANÁLISIS DE ENFERMEDADES MÁS FRECUENTES\n\n"
                    + "No hay pacientes registrados.";
        }

        // Recolectar todos los diagnósticos
        StringBuilder todosDiagnosticos = new StringBuilder();
        recolectarDiagnosticos(getRaiz(), todosDiagnosticos);
        
        if (todosDiagnosticos.length() == 0) {
            return "ANÁLISIS DE ENFERMEDADES MÁS FRECUENTES\n\n"
                    + "No hay diagnósticos registrados.";
        }
        
        // Contar cuántos diagnósticos hay en total
        int totalRegistros = 1;
        for (int i = 0; i < todosDiagnosticos.length(); i++) {
            if (todosDiagnosticos.charAt(i) == '|') {
                totalRegistros++;
            }
        }
        
        // Arreglos para almacenar diagnósticos y frecuencias
        String[] diagnosticos = new String[totalRegistros];
        int[] frecuencias = new int[totalRegistros];
        int totalDistintos = 0;
        
        // Extraer y contar cada diagnóstico
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < todosDiagnosticos.length(); i++) {
            char c = todosDiagnosticos.charAt(i);
            if (c == '|') {
                String diag = temp.toString();
                if (diag != null && diag.length() > 0) {
                    // Buscar si ya existe
                    int pos = -1;
                    for (int j = 0; j < totalDistintos; j++) {
                        if (diagnosticos[j].equals(diag)) {
                            pos = j;
                            break;
                        }
                    }
                    if (pos != -1) {
                        frecuencias[pos]++;
                    } else {
                        diagnosticos[totalDistintos] = diag;
                        frecuencias[totalDistintos] = 1;
                        totalDistintos++;
                    }
                }
                temp = new StringBuilder();
            } else {
                temp.append(c);
            }
        }
        
        // Procesar el último diagnóstico
        String ultimoDiag = temp.toString();
        if (ultimoDiag != null && ultimoDiag.length() > 0) {
            int pos = -1;
            for (int j = 0; j < totalDistintos; j++) {
                if (diagnosticos[j].equals(ultimoDiag)) {
                    pos = j;
                    break;
                }
            }
            if (pos != -1) {
                frecuencias[pos]++;
            } else {
                diagnosticos[totalDistintos] = ultimoDiag;
                frecuencias[totalDistintos] = 1;
                totalDistintos++;
            }
        }
        
        // Ordenar por frecuencia (mayor a menor)
        for (int i = 0; i < totalDistintos - 1; i++) {
            for (int j = 0; j < totalDistintos - i - 1; j++) {
                if (frecuencias[j] < frecuencias[j + 1]) {
                    // Intercambiar frecuencias
                    int tempFrec = frecuencias[j];
                    frecuencias[j] = frecuencias[j + 1];
                    frecuencias[j + 1] = tempFrec;
                    // Intercambiar diagnósticos
                    String tempDiag = diagnosticos[j];
                    diagnosticos[j] = diagnosticos[j + 1];
                    diagnosticos[j + 1] = tempDiag;
                }
            }
        }
        
        // Construir resultado
        StringBuilder sb = new StringBuilder();
        sb.append("ANÁLISIS DE ENFERMEDADES MÁS FRECUENTES\n\n");
        
        for (int i = 0; i < totalDistintos; i++) {
            sb.append(diagnosticos[i]);
            sb.append(": ");
            sb.append(frecuencias[i]);
            sb.append(" casos\n");
        }
        
        return sb.toString();
    }

    /**
     * Busca un diagnóstico en un arreglo de diagnósticos.
     * Recorre el arreglo de diagnósticos desde la posición 0 hasta el total indicado
     * y compara cada elemento con el diagnóstico buscado.
     * @param diagnosticos Arreglo de cadenas que contiene los diagnósticos almacenados.
     * @param total Cantidad total de elementos válidos en el arreglo.
     * @param busqueda Diagnóstico que se desea buscar en el arreglo.
     * @return int El índice donde se encuentra el diagnóstico si existe, -1 si no se encuentra.
     * @author Alex Padilla Chinchilla
     */
    
    private int buscarDiagnostico(String[] diagnosticos, int total, String busqueda) {
        for (int i = 0; i < total; i++) {
            if (diagnosticos[i] != null && diagnosticos[i].equals(busqueda)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Recolecta todos los diagnósticos del árbol en un StringBuilder.
     * Realiza un recorrido InOrden del árbol, extrae cada diagnóstico del historial de citas
     * de cada paciente y los concatena en un StringBuilder.
     * @param nodo Nodo actual del árbol que se está recorriendo.
     * @param acumulador StringBuilder donde se acumulan los diagnósticos.
     * @author Alex Padilla Chinchilla
     */
    
    private void recolectarDiagnosticos(NodoArbol nodo, StringBuilder acumulador) {
        if (nodo == null) return;
        
        recolectarDiagnosticos(nodo.getNodoIzq(), acumulador);
        
        HistoricoCitas citas = nodo.getHistoricoCitas();
        if (citas != null && citas.getPrimeroCita() != null) {
            NodoCita actual = citas.getPrimeroCita();
            do {
                String diagnostico = actual.getDiagnostico();
                if (diagnostico != null && diagnostico.trim().length() > 0) {
                    diagnostico = limpiarTexto(diagnostico);
                    if (acumulador.length() > 0) {
                        acumulador.append("|");
                    }
                    acumulador.append(diagnostico);
                }
                actual = actual.getSiguiente();
            } while (actual != citas.getPrimeroCita());
        }
        
        recolectarDiagnosticos(nodo.getNodoDer(), acumulador);
    }

    /**
     * Propuesta de valor con análisis de correlaciones y alertas tempranas.
     * Ofrece tres análisis:
     * 1. Correlación entre género y diagnóstico más frecuente
     * 2. Identificación de pacientes con comorbilidad (2 o más diagnósticos distintos)
     * 3. Alertas sobre medicamentos de alto uso
     * @return String con el análisis completo de propuesta de valor, incluyendo tablas
     *         con correlaciones, estadísticas de comorbilidad y alertas de medicamentos.
     * @author Alex Padilla Chinchilla
     */
    
    public String propuestaValor() {
        if (getRaiz() == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("PROPUESTA DE VALOR - HOSPITAL SU SALUD\n");
            sb.append("═══════════════════════════════════════════════════════════════════\n\n");
            sb.append("No hay pacientes registrados. Cargue el expediente primero.\n");
            return sb.toString();
        }

        StringBuilder reporte = new StringBuilder();
        reporte.append("PROPUESTA DE VALOR - HOSPITAL SU SALUD\n");
        reporte.append("Análisis de Correlaciones y Alertas Tempranas\n");
        reporte.append("══════════════════════════════════════════════════════════════════\n\n");

        reporte.append("1. CORRELACIÓN GÉNERO - DIAGNÓSTICO                             \n");
        reporte.append("────────────────────────────────────────────────────────────────\n");

        String correlaciones = analizarCorrelacionGeneroDiagnostico();
        reporte.append(correlaciones);
        reporte.append("────────────────────────────────────────────────────────────────\n\n");

        reporte.append("2. PACIENTES CON COMORBILIDAD\n");
        reporte.append("────────────────────────────────────────────────────────────────\n");

        String comorbilidad = analizarComorbilidad();
        reporte.append(comorbilidad);
        reporte.append("────────────────────────────────────────────────────────────────\n\n");

        reporte.append("3. ALERTAS DE MEDICAMENTOS\n");
        reporte.append("────────────────────────────────────────────────────────────────\n");

        String alertasMeds = analizarAlertasMedicamentos();
        reporte.append(alertasMeds);
        reporte.append("────────────────────────────────────────────────────────────────\n\n");

        return reporte.toString();
    }

    /**
     * Analiza la correlación entre el género del paciente y los diagnósticos más frecuentes.
     * Recorre el árbol y separa los diagnósticos por género (masculino y femenino),
     * luego identifica cuál es el diagnóstico más común en cada grupo.
     * @return String con el diagnóstico más frecuente en hombres y el diagnóstico
     *         más frecuente en mujeres, junto con el número de casos.
     * @author Alex Padilla Chinchilla
     */
    
    private String analizarCorrelacionGeneroDiagnostico() {
        // Arreglos para almacenar (máximo 50 diagnósticos distintos)
        String[] diagMasculino = new String[50];
        int[] frecMasculino = new int[50];
        int totalMasculino = 0;

        String[] diagFemenino = new String[50];
        int[] frecFemenino = new int[50];
        int totalFemenino = 0;

        // Arreglos auxiliares para pasar por referencia
        int[] totalM = new int[]{totalMasculino};
        int[] totalF = new int[]{totalFemenino};

        // Recorrer el árbol
        recolectarDiagnosticosPorGenero(getRaiz(), diagMasculino, frecMasculino, totalM, diagFemenino, frecFemenino, totalF);

        StringBuilder sb = new StringBuilder();

        // Top diagnóstico en hombres
        if (totalM[0] > 0) {
            sb.append("MASCULINO:\n");
            sb.append(obenerTopDeArreglo(diagMasculino, frecMasculino, totalM[0]));
        } else {
            sb.append("MASCULINO: Sin datos suficientes \n");
        }

        sb.append("────────────────────────────────────────────────────────────────\n");

        // Top diagnóstico en mujeres
        if (totalF[0] > 0) {
            sb.append("FEMENINO:\n");
            sb.append(obenerTopDeArreglo(diagFemenino, frecFemenino, totalF[0]));
        } else {
            sb.append("FEMENINO: Sin datos suficientes\n");
        }

        return sb.toString();
    }

    /**
     * Recolecta y clasifica los diagnósticos por género durante el recorrido del árbol.
     * Recorre el árbol InOrden y, para cada paciente, extrae sus diagnósticos y los agrega
     * a la colección correspondiente según su género (masculino o femenino).
     * @param nodo Nodo actual del árbol que se está recorriendo.
     * @param diagM Arreglo para almacenar los diagnósticos de pacientes masculinos.
     * @param freqM Arreglo para almacenar la frecuencia de cada diagnóstico masculino.
     * @param totalM Arreglo con un entero que indica cuántos diagnósticos distintos hay en hombres.
     * @param diagF Arreglo para almacenar los diagnósticos de pacientes femeninos.
     * @param freqF Arreglo para almacenar la frecuencia de cada diagnóstico femenino.
     * @param totalF Arreglo con un entero que indica cuántos diagnósticos distintos hay en mujeres.
     * @author Alex Padilla Chinchilla
     */
    
    private void recolectarDiagnosticosPorGenero(NodoArbol nodo, String[] diagM, int[] freqM, int[] totalM, String[] diagF, int[] freqF, int[] totalF) {
        if (nodo == null) {
            return;
        }

        recolectarDiagnosticosPorGenero(nodo.getNodoIzq(), diagM, freqM, totalM, diagF, freqF, totalF);

        Paciente p = nodo.getPaciente();
        String genero = limpiarTexto(p.getGenero());

        HistoricoCitas citas = nodo.getHistoricoCitas();
        if (citas != null && citas.getPrimeroCita() != null) {
            NodoCita actual = citas.getPrimeroCita();
            do {
                String diagnostico = limpiarTexto(actual.getDiagnostico());
                if (diagnostico != null && diagnostico.length() > 0) {
                    if (genero.equals("MASCULINO")) {
                        agregarAColeccion(diagM, freqM, totalM, diagnostico);
                    } else if (genero.equals("FEMENINO")) {
                        agregarAColeccion(diagF, freqF, totalF, diagnostico);
                    }
                }
                actual = actual.getSiguiente();
            } while (actual != citas.getPrimeroCita());
        }

        recolectarDiagnosticosPorGenero(nodo.getNodoDer(), diagM, freqM, totalM, diagF, freqF, totalF);
    }

    /**
     * Agrega un diagnóstico a una colección de arreglos paralelos.
     * Si el diagnóstico ya existe en la colección, incrementa su frecuencia.
     * Si no existe, lo agrega como un nuevo elemento.
     * @param items Arreglo que almacena los valores (diagnósticos o medicamentos).
     * @param frecuencias Arreglo que almacena la frecuencia de cada valor.
     * @param total Arreglo con un entero que indica cuántos elementos distintos hay actualmente.
     * @param valor Valor a agregar a la colección.
     * @author Alex Padilla Chinchilla
     */
    
    private void agregarAColeccion(String[] items, int[] frecuencias, int[] total, String valor) {
        // Buscar si ya existe
        for (int i = 0; i < total[0]; i++) {
            if (items[i] != null && items[i].equals(valor)) {
                frecuencias[i]++;
                return;
            }
        }

        // Agregar nuevo
        if (total[0] < items.length) {
            items[total[0]] = valor;
            frecuencias[total[0]] = 1;
            total[0]++;
        }
    }

    /**
     * Obtiene el elemento más frecuente de un arreglo de valores.
     * Recorre el arreglo de frecuencias para encontrar el índice con mayor valor,
     * luego retorna una cadena formateada con el elemento y su frecuencia.
     * @param items Arreglo que contiene los valores (diagnósticos).
     * @param frecuencias Arreglo que contiene la frecuencia de cada valor.
     * @param total Cantidad total de elementos distintos en el arreglo.
     * @return String formateado con el diagnóstico más común y su número de casos.
     * @author Alex Padilla Chinchilla
     */
    
    private String obenerTopDeArreglo(String[] items, int[] frecuencias, int total) {
        if (total == 0) {
            return "";
        }

        // Encontrar el de mayor frecuencia
        int maxIndex = 0;
        for (int i = 1; i < total; i++) {
            if (frecuencias[i] > frecuencias[maxIndex]) {
                maxIndex = i;
            }
        }

        StringBuilder sb = new StringBuilder();
        String nombre = items[maxIndex];
        if (nombre.length() > 40) {
            nombre = nombre.substring(0, 37) + "...";
        }

        sb.append("    • Diagnóstico más común: ");
        sb.append(nombre);

        // Ajustar espacios
        int espacios = 31 - nombre.length();
        for (int i = 0; i < espacios; i++) {
            sb.append(" ");
        }
        sb.append("\n");

        sb.append("    • ");
        sb.append(frecuencias[maxIndex]);
        sb.append(" casos registrados");

        // Ajustar espacios
        espacios = 42 - String.valueOf(frecuencias[maxIndex]).length();
        for (int i = 0; i < espacios; i++) {
            sb.append(" ");
        }
        sb.append("\n");

        return sb.toString();
    }

    /**
     * Analiza cuántos pacientes presentan comorbilidad (2 o más diagnósticos distintos).
     * Recorre el árbol y para cada paciente cuenta cuántos diagnósticos diferentes tiene.
     * Un paciente se considera con comorbilidad si tiene 2 o más diagnósticos distintos.
     * @return String con la cantidad de pacientes con comorbilidad y el porcentaje
     *         que representan sobre el total de pacientes.
     * @author Alex Padilla Chinchilla
     */
    
    private String analizarComorbilidad() {
        int[] pacientesConComorbilidad = new int[1];
        int[] totalPacientes = new int[1];

        contarComorbilidad(getRaiz(), pacientesConComorbilidad, totalPacientes);

        StringBuilder sb = new StringBuilder();

        if (totalPacientes[0] == 0) {
            sb.append("No hay pacientes registrados.\n");
            return sb.toString();
        }

        int cantidad = pacientesConComorbilidad[0];

        sb.append("Pacientes con 2 o más diagnósticos diferentes: ");
        sb.append(cantidad);

        // Ajustar espacios
        int espacios = 35 - String.valueOf(cantidad).length();
        for (int i = 0; i < espacios; i++) {
            sb.append(" ");
        }
        sb.append("\n");

        // Calcular porcentaje manualmente
        int porcentajeEntero = (cantidad * 100) / totalPacientes[0];
        int porcentajeDecimal = ((cantidad * 100) % totalPacientes[0]) * 10 / totalPacientes[0];

        sb.append("Representa el ");
        sb.append(porcentajeEntero);
        sb.append(".");
        sb.append(porcentajeDecimal);
        sb.append("% del total de pacientes");

        espacios = 28 - (String.valueOf(porcentajeEntero).length() + 2);
        for (int i = 0; i < espacios; i++) {
            sb.append(" ");
        }
        sb.append("\n");

        if (cantidad > 0) {
            sb.append("IMPORTANTE:\n");
            sb.append("Estos pacientes requieren atención multidisciplinaria\n");
            sb.append("y seguimiento más frecuente para evitar complicaciones.\n");
        }

        return sb.toString();
    }

    /**
     * Cuenta recursivamente los pacientes que tienen comorbilidad en el árbol.
     * Para cada paciente, recolecta sus diagnósticos distintos y si tiene 2 o más
     * incrementa el contador de comorbilidad.
     * @param nodo Nodo actual del árbol que se está recorriendo.
     * @param contador Arreglo con un entero que acumula la cantidad de pacientes con comorbilidad.
     * @param totalPacientes Arreglo con un entero que acumula el total de pacientes recorridos.
     * @author Alex Padilla Chinchilla
     */
    
    private void contarComorbilidad(NodoArbol nodo, int[] contador, int[] totalPacientes) {
        if (nodo == null) {
            return;
        }

        contarComorbilidad(nodo.getNodoIzq(), contador, totalPacientes);

        totalPacientes[0]++;

        // Recolectar diagnósticos distintos del paciente
        String[] diagnosticos = new String[20];
        int totalDiag = 0;

        HistoricoCitas citas = nodo.getHistoricoCitas();
        if (citas != null && citas.getPrimeroCita() != null) {
            NodoCita actual = citas.getPrimeroCita();
            do {
                String diag = limpiarTexto(actual.getDiagnostico());
                if (diag != null && diag.length() > 0) {
                    // Verificar si ya existe
                    boolean existe = false;
                    for (int i = 0; i < totalDiag; i++) {
                        if (diagnosticos[i] != null && diagnosticos[i].equals(diag)) {
                            existe = true;
                            break;
                        }
                    }
                    if (!existe && totalDiag < diagnosticos.length) {
                        diagnosticos[totalDiag] = diag;
                        totalDiag++;
                    }
                }
                actual = actual.getSiguiente();
            } while (actual != citas.getPrimeroCita());
        }

        // Si tiene 2 o más diagnósticos distintos, tiene comorbilidad
        if (totalDiag >= 2) {
            contador[0]++;
        }

        contarComorbilidad(nodo.getNodoDer(), contador, totalPacientes);
    }

    /**
     * Recolecta todos los medicamentos del árbol en un StringBuilder.
     * Realiza un recorrido InOrden del árbol, extrae cada medicamento del historial
     * de medicamentos de cada paciente y los concatena en un StringBuilder
     * @param nodo Nodo actual del árbol que se está recorriendo.
     * @param acumulador StringBuilder donde se acumulan los medicamentos.
     * @author Alex Padilla Chinchilla
     */
    
    private void recolectarMedicamentos(NodoArbol nodo, StringBuilder acumulador) {
        if (nodo == null) {
            return;
        }

        // Recorrer izquierda
        recolectarMedicamentos(nodo.getNodoIzq(), acumulador);

        // Procesar nodo actual
        HistoricoMedicamentosPrescritos meds = nodo.getHistoricoMedicamentos();
        if (meds != null && meds.getPrimeroMedicamento() != null) {
            NodoMedicamento actual = meds.getPrimeroMedicamento();
            do {
                String medicamento = actual.getMedicamento();
                if (medicamento != null && medicamento.trim().length() > 0) {
                    medicamento = limpiarTexto(medicamento);
                    if (acumulador.length() > 0) {
                        acumulador.append("|");
                    }
                    acumulador.append(medicamento);
                }
                actual = actual.getSiguiente();
            } while (actual != meds.getPrimeroMedicamento());
        }

        // Recorrer derecha
        recolectarMedicamentos(nodo.getNodoDer(), acumulador);
    }

    /**
     * Analiza los medicamentos prescritos para identificar el más recetado.
     * Recolecta todos los medicamentos del árbol, cuenta sus frecuencias
     * y determina cuál ha sido recetado con mayor frecuencia.
     * @return String con el nombre del medicamento más recetado y la cantidad de recetas
     *         junto con una recomendación para la gestión de inventario.
     * @author Alex Padilla Chinchilla
     */
    
    private String analizarAlertasMedicamentos() {
        // Recolectar todos los medicamentos
        StringBuilder todosMeds = new StringBuilder();
        recolectarMedicamentos(getRaiz(), todosMeds);

        StringBuilder sb = new StringBuilder();

        if (todosMeds.length() == 0) {
            sb.append("No hay medicamentos registrados.\n");
            return sb.toString();
        }

        // Contar cuántos medicamentos hay
        int cantidadMeds = 1;
        for (int i = 0; i < todosMeds.length(); i++) {
            if (todosMeds.charAt(i) == '|') {
                cantidadMeds++;
            }
        }

        // Crear arreglos del tamaño exacto
        String[] valores = new String[cantidadMeds];
        int[] frecuencias = new int[cantidadMeds];
        int totalDistintos = 0;

        // Extraer cada medicamento
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < todosMeds.length(); i++) {
            char c = todosMeds.charAt(i);
            if (c == '|') {
                String med = temp.toString();
                if (med != null && med.length() > 0) {
                    // Buscar si ya existe
                    int pos = -1;
                    for (int j = 0; j < totalDistintos; j++) {
                        if (valores[j] != null && valores[j].equals(med)) {
                            pos = j;
                            break;
                        }
                    }
                    if (pos != -1) {
                        frecuencias[pos]++;
                    } else {
                        valores[totalDistintos] = med;
                        frecuencias[totalDistintos] = 1;
                        totalDistintos++;
                    }
                }
                temp = new StringBuilder();
            } else {
                temp.append(c);
            }
        }
        // Procesar el último
        String ultimoMed = temp.toString();
        if (ultimoMed != null && ultimoMed.length() > 0) {
            int pos = -1;
            for (int j = 0; j < totalDistintos; j++) {
                if (valores[j] != null && valores[j].equals(ultimoMed)) {
                    pos = j;
                    break;
                }
            }
            if (pos != -1) {
                frecuencias[pos]++;
            } else {
                valores[totalDistintos] = ultimoMed;
                frecuencias[totalDistintos] = 1;
                totalDistintos++;
            }
        }

        // Encontrar el más recetado
        if (totalDistintos > 0) {
            int maxIndex = 0;
            for (int i = 1; i < totalDistintos; i++) {
                if (frecuencias[i] > frecuencias[maxIndex]) {
                    maxIndex = i;
                }
            }

            String nombre = valores[maxIndex];
            if (nombre.length() > 40) {
                nombre = nombre.substring(0, 37) + "...";
            }

            sb.append("Medicamento más recetado: ");
            sb.append(nombre);

            int espacios = 31 - nombre.length();
            for (int i = 0; i < espacios; i++) {
                sb.append(" ");
            }
            sb.append("\n");

            sb.append("    ");
            sb.append(frecuencias[maxIndex]);
            sb.append(" recetas en total");

            espacios = 44 - String.valueOf(frecuencias[maxIndex]).length();
            for (int i = 0; i < espacios; i++) {
                sb.append(" ");
            }
            sb.append("\n");

            sb.append("RECOMENDACIÓN:\n");
            sb.append("    • Asegurar stock suficiente de este medicamento\n");
            sb.append("    • Negociar precio por volumen con proveedores\n");
        }

        return sb.toString();
    }
    /**
    * Realiza una segmentación de pacientes recorriendo el Árbol Binario de Búsqueda (ABB)
    * en grupos de edades:
    * 
    *   Menores de edad: 0 a 17 años
    *   Adultos: 18 a 64 años
    *   Adultos mayores: 65 años o más
    * 
    *  Si el árbol está vacío (raíz nula), se informa al usuario mediante un mensaje
    * y no se realiza el cálculo.
    *
    * La salida se muestra en una ventana de diálogo con el resumen de la segmentación.
    */
    
    public void segmentacionPacientes() {

        if (raiz == null) {
            JOptionPane.showMessageDialog(null, "No hay pacientes registrados.");
            return;
        }

        int[] contadores = new int[3];

        segmentacionRec(raiz, contadores);

        JOptionPane.showMessageDialog(null,
                "***** SEGMENTACIÓN DE PACIENTES ******\n\n" +
                        "Menores de Edad: " + contadores[0] +
                        "\nAdultos: " + contadores[1] +
                        "\nAdultos Mayores: " + contadores[2]);
    }

    
    
/**
 * Método recursivo que recorre el ABB en orden (InOrden) para contabilizar
 * pacientes según su edad y acumular los resultados en un arreglo de contadores.
 *

 * @param nodoActual Nodo actual del árbol durante el recorrido
 * @param contadores Arreglo entero de tamaño 3 donde se acumulan los conteos 
 */

    private void segmentacionRec(NodoArbol nodoActual, int[] contadores) {

        if (nodoActual != null) {

            segmentacionRec(nodoActual.getNodoIzq(), contadores);

            Paciente paciente = nodoActual.getPaciente();
            int edad = paciente.getEdad();

            if (edad >= 0 && edad < 18) {
                contadores[0]++;
            } else if (edad < 65) {
                contadores[1]++;
            } else {
                contadores[2]++;
            }

            segmentacionRec(nodoActual.getNodoDer(), contadores);
        }
    }
    
    
}

    
    
