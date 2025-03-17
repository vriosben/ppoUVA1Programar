package src.avion;

import src.motorAvion.MotorAvion;
import src.sistemaControlVuelo.SistemaControlVuelo;
import src.alas.Ala;

public class Avion {

    // Atributos
    private String marca;
    private String modelo;
    private MotorAvion motor;
    private SistemaControlVuelo sistemaControlVuelo;
    private Ala[] alas;
    private int numeroAsientos;
    private boolean entretenimiento; 

    // Constructor
    public Avion(String marca, String modelo, MotorAvion motor, SistemaControlVuelo sistemaControlVuelo, Ala[] alas, int numeroAsientos, boolean entretenimiento) {
        this.marca = marca;
        this.modelo = modelo;
        this.motor = motor;
        this.sistemaControlVuelo = sistemaControlVuelo;
        this.alas = alas;
        this.numeroAsientos = numeroAsientos;
        this.entretenimiento = entretenimiento;
    }

    // Getters
    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public MotorAvion getMotor() {
        return motor;
    }

    public SistemaControlVuelo getSistemaControlVuelo() {
        return sistemaControlVuelo;
    }

    public Ala[] getAlas() {
        return alas;
    }

    public int getNumeroAsientos() {
        return numeroAsientos;
    }

    public boolean isEntretenimiento() {
        return entretenimiento;
    }

    // Metodos
    public void encenderMotor() {
        motor.encenderMotor();
    }

    public void apagarMotor() {
        motor.apagarMotor();
    }

    public void cambiarModo(int nuevoModo) {
        sistemaControlVuelo.cambiarModo(nuevoModo);
    }
    
    public void flap() {
        for (Ala ala : alas) {
            ala.flap();
        }
    }
}