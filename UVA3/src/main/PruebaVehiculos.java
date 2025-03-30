package main;
import vehiculo.*;

public class PruebaVehiculos {
    public static void main(String[] args) throws Exception {
        
        // Vehiculo vehiculo = new Vehiculo("Toyota", 200, "negro");
        // System.out.println(vehiculo.desplazar());
        
        // Prueba Moto
        Moto moto = new Moto("Kawasaki", 250, "verde",400);    
        System.out.println(moto);
        System.out.println(moto.desplazar());
        System.out.println(moto.hacerWheelie());
        System.out.println(moto.llenarTanque());
        System.out.println();

        // Prueba Coche con sobrecarga
        Coche coche = new Coche("Toyota", 200, "negro",3);    
        System.out.println(coche);
        System.out.println(coche.desplazar('a'));
        System.out.println(coche.bajarVentanas());
        System.out.println(coche.llenarTanque());
        System.out.println();

        // Prueba Bicicleta con sobrescritura
        Bicicleta bicicleta = new Bicicleta("Venzo", 40, "rojo",false);    
        System.out.println(bicicleta);
        System.out.println(bicicleta.desplazar());
        System.out.println();
    }
}
