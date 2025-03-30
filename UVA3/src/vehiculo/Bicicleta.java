package vehiculo;

public class Bicicleta extends Vehiculo{
    // Atributos
    private boolean tieneCanasto;

    // Constructor
    public Bicicleta(String marca, int velocidad, String color, boolean tieneCanasto) {
        super(marca, velocidad, color);
        this.tieneCanasto = tieneCanasto;   
    }
    // Métodos
    public String desplazar(){
        return "Se empezó a pedalear la bicicleta.";
    }

    @Override
    public String toString() {
        return "Bicicleta " + marca + " de color " + color + (tieneCanasto? " con canasto." : " sin canasto.") + " Velocidad máxima: " + velocidad + " km/h.";
    }
}