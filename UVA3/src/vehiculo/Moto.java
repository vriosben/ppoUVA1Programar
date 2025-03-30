package vehiculo;

public class Moto extends Vehiculo implements Combustible{
    // Atributos
    private int cilindrada;

    // Constructor
    public Moto(String marca, int velocidad, String color, int cilindrada){
        super(marca, velocidad, color);
        this.cilindrada = cilindrada;
    }

    // Metodos
    public String hacerWheelie(){
        return "La moto ha hecho un wheelie.";
    }

    @Override
    public String llenarTanque() {
        return "Se ha llenado el tanque de nafta de la moto.";
    }

    @Override
    public String toString() {
        return "Moto " + marca + " de color " + color + " de " + cilindrada + " cc. " + "Velocidad máxima: " + velocidad + " km/h.";
    }  
}
