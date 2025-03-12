package src.sistemaControlVuelo;

public class SistemaControlVuelo {
    
    // Atributos
    private String fabricante;
    private int numeroModos;
    private String tipoSistema;
    private int modoActual;

    // Constructor
    public SistemaControlVuelo(String fabricante, int numeroModos, String tipoSistema, int modoActual){
        this.fabricante = fabricante;
        this.numeroModos = numeroModos;
        this.tipoSistema = tipoSistema;
        this.modoActual = 1;
    }

    //Método para cambiar de modo
    public void cambiarModo(int nuevoModo) {
        if (nuevoModo > 0 && nuevoModo <= numeroModos) {
            modoActual = nuevoModo;
            System.out.println("Modo cambiado a: " + modoActual);
        } else {
            System.out.println("No se ha podido cambiar el modo");
        }

    }

    // Método para mostrar información
    public void mostrarInformacion() {
        System.out.println("Información del ala:");
        System.out.println("Fabricante: " + fabricante);
        System.out.println("El modo actual es: " + modoActual);
        System.out.println("El tipo de sistema es: " + tipoSistema);
    }

}
