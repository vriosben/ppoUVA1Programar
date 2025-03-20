package UVA1.src.avion;

public class SistemaControlVuelo {
    
    // Atributos
    private String fabricante;
    private int numeroModos;
    private String tipoSistema;
    private int modoActual;

    // Constructor
    public SistemaControlVuelo(String fabricante, int numeroModos, String tipoSistema){
        this.fabricante = fabricante;
        this.numeroModos = numeroModos;
        this.tipoSistema = tipoSistema;
        this.modoActual = 1;
    }
 
    // Getters
    public String getFabricante() {
        return fabricante;
    }

    public int getNumeroModos() {
        return numeroModos;
    }

    public String getTipoSistema() {
        return tipoSistema;
    }

    public int getModoActual() {
        return modoActual;
    }

    //Método para cambiar de modo
    public void cambiarModo(int nuevoModo) {
        if (nuevoModo > 0 && nuevoModo <= numeroModos) {
            modoActual = nuevoModo;
            System.out.println("Modo cambiado.");
        } else {
            System.out.println("No se ha podido cambiar el modo.");
        }
    }
}