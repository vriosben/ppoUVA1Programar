import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Metodos {

    public static int pedirEdad(Scanner scanner){
        while (true) {
            try {
                System.out.println("Ingresá tu edad (0-120): ");
                String entrada = scanner.nextLine().trim();
                // Verifica que la entrada no esté en blanco.
                if (entrada.isEmpty()) {
                    throw new EntradaVaciaException("Entrada vacía. Reintentar.");
                }

                int edad = Integer.parseInt(entrada);
                // Verifica que sea una edad dentro del rango aceptado.
                if (edad>120 || edad<0 ) {
                    throw new FueraRangoEdadException("Tu edad debe ser entre 0 y 120. Reintentar.");    
                }

                return edad;
                
            } catch (FueraRangoEdadException e) {
                System.out.println(e.getMessage());
            } catch (EntradaVaciaException e) {
                System.out.println(e.getMessage());
            // Atrapa los valores no numéricos ingresados.
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar sólo valores númericos enteros entre 0 y 120.");
            }
        } 
    }

    public static List<Integer> leerDatos(String recurso){
        List<Integer> numeros = new ArrayList<>();
       
        try (BufferedReader reader = new BufferedReader(new FileReader(recurso)) ) {
            System.out.println("Datos en " + recurso + ": ");
            String linea = reader.readLine();
            
            // Verifica que el archivo no está en blanco
            if (linea == null){
                throw new IllegalArgumentException("El archivo esta vacio");
            }

            while (linea != null) {
                try {
                    int numero = Integer.parseInt(linea.trim());
                    numeros.add(numero);
                    linea = reader.readLine();
                // Atrapa la excepción si el archivo no tiene sólo números enteros. 
                } catch (NumberFormatException e) {
                    System.out.println("Archivo inválido: Linea \"" + linea + "\" no es un número válido. Revise " + recurso + "\n");
                    return new ArrayList<>(); 
                }
            }
        
        // Maneja la excepción cuando no existe el archivo
        } catch(FileNotFoundException e) {
            System.out.println("Archivo no encontrado");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        // Maneja la excepción cuando no se puede leer o cerrar el archivo correctamente
        } catch (IOException e) {
            System.out.println("Error al leer o cerrar el archivo");
        }
        
        for (Integer numero : numeros) { System.out.print(numero + " "); }
        System.out.println();
        
        return numeros;
    }

    public static void escribirDatos(List<Integer> lista){ 
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/output.txt")) ) {
            for (Integer linea : lista) {
                writer.append(String.valueOf(linea));
                writer.newLine();
            }
        // Maneja la excepción cuando no se puede leer o cerrar el archivo correctamente
        } catch (IOException e) {
            System.out.println("Error al leer o cerrar el archivo");
        }
    }

    public static List<Integer> multiplicarLista(List<Integer> lista){
        List<Integer> listaProcesada = new ArrayList<>();
        for (Integer numero : lista) { listaProcesada.add(numero * 2); }
        return listaProcesada;
    }
}