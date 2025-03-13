package src.main;

import src.alas.Ala;
import src.avion.Avion;
import src.motorAvion.MotorAvion;
import src.sistemaControlVuelo.SistemaControlVuelo;

public class PruebaAvion {
    public static void main(String[] args) {

        Ala ala1 = new Ala(15.5, "Blanca", "Aluminio");
        Ala ala2 = new Ala(23.0, "Gris", "Aleación");

        Ala[] alasAvion1 = {ala1}; 
        Ala[] alasAvion2 = {ala2}; 

        SistemaControlVuelo sistemaControlVuelo1 = new SistemaControlVuelo("Honeywell", 5, "manual", 2);
        SistemaControlVuelo sistemaControlVuelo2 = new SistemaControlVuelo("Rockwell", 2, "piloto automático", 2);

        MotorAvion motorAvion1 = new MotorAvion("Hyundai", 23.9, 12.0,"Encendido");
        MotorAvion motorAvion2 = new MotorAvion("Audi", 14.0, 45.2, "Apagado");

        Avion avion1 = new Avion("AerolineasArgentinas", "Nacional", motorAvion1, sistemaControlVuelo1, alasAvion1, 34, true);
        Avion avion2 = new Avion("AerolineasArgentinas", "Nacional", motorAvion2, sistemaControlVuelo2, alasAvion2, 34, false);

        avion1.mostrarInformacion();
        avion2.mostrarInformacion();
    }
}