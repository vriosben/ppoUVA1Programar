import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;
        
        // Menú
        while (!salir) {
            System.out.println("¿Qué inciso querés probar? (Seleccioná 1 o 2 - 0 para salir)");
            System.out.println("\t1. Validación de entrada del usuario");
            System.out.println("\t2. Lectura/Escritura de Archivos con manejo de Excepciones");
            System.out.println("\t0. Salir");
            
            try {
                String entrada = scanner.nextLine().trim();
                // Verifica que la entrada no esté en blanco.
                if (entrada.isEmpty()) {
                    throw new EntradaVaciaException("Entrada vacía. Reintentar.");
                }
                // Verifica que sea un número válido.
                int opcion = Integer.parseInt(entrada);
                if (opcion < 0 || opcion > 2) {
                    throw new FueraRangoEdadException("El número ingresado debe ser 1, 2 o 0. Reintentar.");
                }  
                
                switch (opcion) {
                // Inciso 1
                    case 1: 
                        int edad = Metodos.pedirEdad(scanner);
                        System.out.println("Tu edad es: " + edad);
                        System.out.println();
                        break;
                    case 2:
                    // Inciso 
                        List<Integer> numeros = Metodos.leerDatos("src/data.txt");
                        if (numeros.size()>0) {
                            System.out.println("Multiplicando datos x 2.... ");
                            List<Integer> numerosProcesados = Metodos.multiplicarLista(numeros);
                            Metodos.escribirDatos(numerosProcesados);
                            System.out.println("Datos procesados correctamente! ");
                            Metodos.leerDatos("src/output.txt");
                            System.out.println(); 
                        }
                        break;
                    case 0:
                        salir = true;
                        System.out.println("Hasta Luego!");
                    default:
                        System.out.println(" Opción inválida.");;
                }
            } catch (FueraRangoEdadException e) {
                System.out.println(e.getMessage());
            } catch (EntradaVaciaException e) {
                System.out.println(e.getMessage());
            // Atrapa los valores no numéricos ingresados.
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar sólo valores númericos.");
            }    
        }
        scanner.close(); 
    }
}