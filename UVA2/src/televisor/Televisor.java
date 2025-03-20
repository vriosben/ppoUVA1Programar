package UVA2.src.televisor;
public class Televisor {

    // Atributos
    private static int contadorID = 0;

    private int id;
    private String marca;
    private String modelo;
    private int anio;
    private Pantalla pantalla;
    private boolean smart;
    private boolean encendido;

    // Constructor
    public Televisor(String marca, String modelo, int anio, Pantalla pantalla, boolean smart) {
        this.id = ++contadorID;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.pantalla = pantalla;
        this.smart = smart;
        this.encendido = false;
    }
    
    // Getters
    public int getId() {
        return id;
    }

    public boolean isEncendido() {
        return encendido;
    }
   
    public Pantalla getPantalla() {
        return pantalla;
    }

    // Encender televisor
    public void encender(){
        if (!encendido)
            encendido = true;
    }

    // Apagar televisor
    public void apagar(){
        if (encendido)
        encendido = false;
    }
        
    // Mostrar Información
    @Override
    public String toString() {
        return "Televisor " + marca + " " + modelo + " --- " + pantalla + " --- " + (smart ? "Smart" : "Estándar") + "(" + anio + ")";
    }  

    // Mostrar Información y Estado
    public String obtenerEstado() {
        return "Información: " + this.toString() + "\n" +
               "Estado: Televisor " + (encendido ? "encendido" : "apagado") + " - Pantalla " + (pantalla.estaEncendida(encendido)? "encendida" : "apagada") ;
    }
}