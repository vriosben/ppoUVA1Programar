package vehiculo;

public class Vehiculo {
    // Atributos
    protected String marca;
    protected int velocidad;
    protected String color;

    // Constructor
    public Vehiculo(String marca, int velocidad, String color) {
        this.marca = marca;
        this.velocidad = velocidad;
        this.color = color;
    }

    // Métodos
    public String desplazar(){
        return "Vehículo desplazándose";
    }
}