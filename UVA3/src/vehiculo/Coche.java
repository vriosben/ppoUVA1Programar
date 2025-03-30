package vehiculo;

public class Coche extends Vehiculo implements Combustible {
    // Atributos
    private int cantidadPuertas;

    // Constructor
    public Coche(String marca, int velocidad, String color, int cantidadPuertas) {
        super(marca, velocidad, color);
        this.cantidadPuertas = cantidadPuertas;
    }

    // Métodos
    public String desplazar(char modo){
        if (modo =='a'){
            return "El coche se desplaza en modo automático.";}
        else if (modo =='n'){
            return "El coche se desplaza en modo normal.";}
        else {
            return "Modo de desplazamiento inválido.";}
        }

    public String bajarVentanas(){
        return "Se han bajado las ventanas del coche.";
    }
    
    @Override
    public String llenarTanque() {
        return "Se ha llenado el tanque de nafta del coche.";
    }

    @Override
    public String toString() {
        return "Coche " + marca + " de color " + color + " de " + cantidadPuertas + " puertas. " + "Velocidad máxima: " + velocidad + " km/h.";
    }  
}