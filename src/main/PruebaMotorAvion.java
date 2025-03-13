package src.main;

import src.motorAvion.MotorAvion;

public class PruebaMotorAvion {
    
    public static void main(String[] args) {
        MotorAvion motorAvion1 = new MotorAvion("Hyundai", 23.9, 12.0, "Apagado");
        MotorAvion motorAvion2 = new MotorAvion("Audi", 14.0, 45.2, "Encendido");
        
        motorAvion1.mostrarInformacion();
        motorAvion2.mostrarInformacion();
    }


}