package src.main;

import src.avion.Ala;

public class PruebaAla {
    public static void main(String[] args) {
        // Crear dos alas
        Ala ala1 = new Ala(15.5, "Blanco", "Aluminio");
        Ala ala2 = new Ala(23.0, "Gris", "Aleación");

        // Mostrar la información
        System.out.println("Información Ala 1:");
        System.out.println("Color: " + ala1.getColor());
        System.out.println("Material: " + ala1.getTipoMaterial());
        System.out.println("Envergadura: " + ala1.getEnvergadura() + " metros.");
        ala1.flap();
        System.out.println();

        System.out.println("Información Ala 2:");
        System.out.println("Color: " + ala2.getColor());
        System.out.println("Material: " + ala2.getTipoMaterial());
        System.out.println("Envergadura: " + ala2.getEnvergadura() + " metros.");
        ala2.flap();
    }
}   