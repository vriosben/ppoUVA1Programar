package src.avion;

import src.motorAvion.MotorAvion;
import src.sistemaControlVuelo.SistemaControlVuelo;
import src.alas.Ala;

public class Avion {

    private String marca;
    private String modelo;
    private MotorAvion motor;
    private SistemaControlVuelo sistemaControlVuelo;
    private Ala[] alas;
    private int numeroAsientos;
    private boolean entretenimiento; 

    
    public Avion(String marca, String modelo, MotorAvion motor, SistemaControlVuelo sistemaControlVuelo, Ala[] alas, int numeroAsientos, boolean entretenimiento) {
        this.marca = marca;
        this.modelo = modelo;
        this.motor = motor;
        this.sistemaControlVuelo = sistemaControlVuelo;
        this.alas = alas;
        this.numeroAsientos = numeroAsientos;
        this.entretenimiento = entretenimiento;
    }


    public void mostrarInformacion() {
        System.out.println("=== Información del Avión ===");
        System.out.println("Marca: " + marca);
        System.out.println("Modelo: " + modelo);
        System.out.println("Número de asientos: " + numeroAsientos);
        System.out.println("Sistema de entretenimiento: " + (entretenimiento ? "Sí" : "No"));


        motor.mostrarInformacion();
        sistemaControlVuelo.mostrarInformacion();

        for (int i = 0; i < alas.length; i++) {
            System.out.println("\nAla " + (i + 1) + ":");
            alas[i].mostrarInformacion();
        }
    }
    
}
