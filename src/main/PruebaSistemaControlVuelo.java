package src.main;

import src.sistemaControlVuelo.SistemaControlVuelo;

public class PruebaSistemaControlVuelo {
    
    public static void main(String[] args) {
        SistemaControlVuelo sistemaControlVuelo1 = new SistemaControlVuelo("Honeywell", 5, "manual", 2);
        SistemaControlVuelo sistemaControlVuelo2 = new SistemaControlVuelo("Rockwell", 2, "piloto automático", 2);
        
        sistemaControlVuelo1.mostrarInformacion();
    
    }


}
