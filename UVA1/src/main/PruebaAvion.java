package UVA1.src.main;

import UVA1.src.avion.Ala;
import UVA1.src.avion.Avion;
import UVA1.src.avion.MotorAvion;
import UVA1.src.avion.SistemaControlVuelo;

public class PruebaAvion {
    public static void main(String[] args) {

        // Crear dos aviones
        Ala[] alasAvion1 = {new Ala(15.5, "Blanca", "Aluminio"),
        new Ala(15.5, "Blanca", "Aluminio")}; 
        Ala[] alasAvion2 = {new Ala(23.0, "Gris", "Aleación"),
        new Ala(23.0, "Gris", "Aleación")}; 

        SistemaControlVuelo sistemaControlVuelo1 = new SistemaControlVuelo("Honeywell", 5, "manual");
        SistemaControlVuelo sistemaControlVuelo2 = new SistemaControlVuelo("Rockwell", 2, "piloto automático");

        MotorAvion motorAvion1 = new MotorAvion("Hyundai", 23.9, 12.0,"Encendido");
        MotorAvion motorAvion2 = new MotorAvion("Audi", 14.0, 45.2, "Apagado");

        Avion avion1 = new Avion("AerolineasArgentinas", "Nacional", motorAvion1, sistemaControlVuelo1, alasAvion1, 34, true);
        Avion avion2 = new Avion("AerolineasArgentinas", "Nacional", motorAvion2, sistemaControlVuelo2, alasAvion2, 34, false);

        // Mostrar la información del Avión 1
        System.out.println("Información Avion 1");
        System.out.println("Marca: " + avion1.getMarca() + ". Modelo: " + avion1.getModelo());
        System.out.println("Marca del Motor: " + avion1.getMotor().getMarca());
        System.out.println("Fabricante del Sistema de Vuelo: " + avion1.getSistemaControlVuelo().getFabricante());
        System.out.print("Colores de las alas: ");
        for (Ala ala : avion1.getAlas()) {
            System.out.print(ala.getColor() + " "); 
        }
        System.out.println();
        System.out.println("Asientos: " + avion1.getNumeroAsientos());
        System.out.println("Entretenimiento: " + (avion1.isEntretenimiento() ? "Sí" : "No"));
        
        // Encender y apagar el motor
        System.out.print("Encendiendo avión... ");
        avion1.encenderMotor();
        System.out.print("Apagando avión... ");
        avion1.apagarMotor();

        // Cambiar el modo del sistema de vuelo
        System.out.print("Cambiando modo del sistema de vuelo... ");
        avion1.cambiarModo(3);
        System.out.println("El modo actual es: " + avion1.getSistemaControlVuelo().getModoActual());

        // Flaps
        avion1.flap();
        System.out.println();

        // Mostrar la información del Avión 2
        System.out.println("Información Avion 2");
        System.out.println("Marca: " + avion2.getMarca() + ". Modelo: " + avion2.getModelo());
        System.out.println("Marca del Motor: " + avion2.getMotor().getMarca());
        System.out.println("Fabricante del Sistema de Vuelo: " + avion2.getSistemaControlVuelo().getFabricante());
        System.out.print("Colores de las alas: ");
        for (Ala ala : avion2.getAlas()) {
            System.out.print(ala.getColor() + " "); 
        }
        System.out.println();
        System.out.println("Asientos: " + avion2.getNumeroAsientos());
        System.out.println("Entretenimiento: " + (avion2.isEntretenimiento() ? "Sí" : "No"));
        
        // Encender y apagar el motor
        System.out.print("Encendiendo avión... ");
        avion2.encenderMotor();
        System.out.print("Apagando avión... ");
        avion2.apagarMotor();

        // Cambiar el modo del sistema de vuelo
        System.out.print("Cambiando modo del sistema de vuelo... ");
        avion2.cambiarModo(2);
        System.out.println("El modo actual es: " + avion2.getSistemaControlVuelo().getModoActual());

        // Flaps
        avion2.flap();
    }
}