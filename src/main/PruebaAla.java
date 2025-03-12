package src.main;

import src.alas.Ala;

public class PruebaAla {
    public static void main(String[] args) {
        // Crear dos alas
        Ala ala1 = new Ala(15.5, "Blanca", "Aluminio");
        Ala ala2 = new Ala(23.0, "Gris", "Aleación");

        // Mostrar la información
        ala1.mostrarInformacion();
        ala1.flap();
        System.out.println();

        ala2.mostrarInformacion();
        ala2.flap();
    }
}
