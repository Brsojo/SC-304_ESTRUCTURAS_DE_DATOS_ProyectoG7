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

    
}
