package UVA1.src.main;

import UVA1.src.avion.SistemaControlVuelo;

public class PruebaSistemaControlVuelo {
    
    public static void main(String[] args) {

        // Crear Sistemas de control de vuelo
        SistemaControlVuelo sistemaControlVuelo1 = new SistemaControlVuelo("Honeywell", 5, "manual");
        SistemaControlVuelo sistemaControlVuelo2 = new SistemaControlVuelo("Rockwell", 2, "piloto automático");
        
        // Mostrar la información
        System.out.println("Información del Sistema de Control de Vuelo 1:");
        System.out.println("Fabricante: " + sistemaControlVuelo1.getFabricante());
        System.out.println("Número de Modos: " + sistemaControlVuelo1.getNumeroModos());
        System.out.println("Tipo de Sistema: " + sistemaControlVuelo1.getTipoSistema());

        System.out.println("Cambiando a modo 2... ");
        sistemaControlVuelo1.cambiarModo(2);
        System.out.println("Modo actual: " + sistemaControlVuelo1.getModoActual());
        System.out.println();  

        System.out.println("Información del Sistema de Control de Vuelo 2:");
        System.out.println("Fabricante: " + sistemaControlVuelo2.getFabricante());
        System.out.println("Número de Modos: " + sistemaControlVuelo2.getNumeroModos());
        System.out.println("Tipo de Sistema: " + sistemaControlVuelo2.getTipoSistema());

        System.out.println("Cambiando a modo 5... ");
        sistemaControlVuelo2.cambiarModo(5);
        System.out.println("Modo actual: " + sistemaControlVuelo2.getModoActual());    
    }
}