package src.main;

import src.motorAvion.MotorAvion;

public class PruebaMotorAvion {
    
    public static void main(String[] args) {

        // Crear 2 motores
        MotorAvion motorAvion1 = new MotorAvion("Hyundai", 23.9, 12.0, "Apagado");
        MotorAvion motorAvion2 = new MotorAvion("Audi", 14.0, 45.2, "Encendido");
          
        // Mostrar la información
        System.out.println("Información Motor 1:");
        System.out.println("Marca: " + motorAvion1.getMarca());
        System.out.println("Caballos de fuerza: " + motorAvion1.getCaballosFuerza() + " hp.");
        System.out.println("Empuje: " + motorAvion1.getEmpuje() + " lbs.");
        System.out.println("Estado actual del motor: " + motorAvion1.getEstadoActual());

        System.out.println("Encendiendo motor de avión 1... ");
        motorAvion1.encenderMotor();
        System.out.println("Apagando motor de avión 1... ");
        motorAvion1.apagarMotor();
        System.out.println();   

        System.out.println("Información Motor 2:");
        System.out.println("Marca: " + motorAvion2.getMarca());
        System.out.println("Caballos de fuerza: " + motorAvion2.getCaballosFuerza() + " hp.");
        System.out.println("Empuje: " + motorAvion2.getEmpuje() + " lbs.");
        System.out.println("Estado actual del motor: " + motorAvion2.getEstadoActual());

        System.out.println("Encendiendo motor de avión 2... ");
        motorAvion2.encenderMotor();
        System.out.println("Apagando motor de avión 2... ");
        motorAvion2.apagarMotor();
        System.out.println();   
    }
}