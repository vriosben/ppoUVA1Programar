package UVA1.src.avion;

    // Atributos
public class MotorAvion {
    private String marca;
    private double caballosFuerza;
    private double empuje;
    private String estadoActual;

    // Constructor
    public MotorAvion(String marca, double caballosFuerza, double empuje, String estadoActual){
        this.marca = marca;
        this.caballosFuerza = caballosFuerza;
        this.empuje = empuje;
        this.estadoActual = estadoActual;
    }

    // Getters
    public String getMarca() {
        return marca;
    }

    public double getCaballosFuerza() {
        return caballosFuerza;
    }

    public double getEmpuje() {
        return empuje;
    }

    public String getEstadoActual() {
        return estadoActual;
    }

    //  Método para encender motor
    public void encenderMotor() {
        if (estadoActual.equals("Apagado")){
            estadoActual = "Encendido";
            System.out.println("Motor arrancado.");
        } else {
            System.out.println("El motor ya está encendido.");
        }
    }

    // Método para apagar motor
    public void apagarMotor() {
        if (estadoActual.equals("Encendido")){
            estadoActual = "Apagado";
            System.out.println("Motor detenido.");
        } else {
            System.out.println("El motor ya está apagado.");
        }
    }  
}