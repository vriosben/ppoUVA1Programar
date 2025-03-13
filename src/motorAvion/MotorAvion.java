package src.motorAvion;

public class MotorAvion {
    private String marca;
    private double caballosFuerza;
    private double empuje;
    private String estadoActual;


    public MotorAvion(String marca, double caballosFuerza, double empuje, String estadoActual){
        this.marca = marca;
        this.caballosFuerza = caballosFuerza;
        this.empuje = empuje;
        this.estadoActual = "Apagado";
    }

    //  Método para encender motor
    public void encenderMotor() {
        if (estadoActual.equals("Apagado")){
            estadoActual = "Encendido";
            System.out.println("Motor arrancado. Estado actual: " + estadoActual);
        } else {
            System.out.println("El motor ya está encendido.");
        
        }
    }

    // Método para apagar motor
    public void apagarMotor() {
        if (estadoActual.equals("Encendido")){
            estadoActual = "Apagado";
            System.out.println("Motor detenido. Estado actual: " + estadoActual);
        } else {
            System.out.println("El motor ya está apagado.");
        
        }
    }


    // Método para mostrar información
    public void mostrarInformacion() {
        System.out.println("Información del ala:");
        System.out.println("Marca: " + marca);
        System.out.println("La fuerza del motor es: " + empuje + " lb");
        System.out.println("La fuerza del motor es: " + caballosFuerza + " hp");
        System.out.println("El estado actual es: " + estadoActual);
    
    }







}


