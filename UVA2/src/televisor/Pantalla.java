package UVA2.src.televisor;
public class Pantalla {

    // Atributos
    private int pulgadas;
    private String resolucion;
    private char tipo; // P plasma, L LCD, O OLED

    // Constructor
    public Pantalla(int pulgadas, String resolucion, char tipo) {
        this.pulgadas = pulgadas;
        this.resolucion = resolucion;
        this.tipo = tipo;
    }

    // Setters
    public void setPulgadas(int pulgadas) {
        this.pulgadas = pulgadas;
    }
    
    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }
    
    public void setTipo(char tipo) {
        this.tipo = tipo;
    }
    
    // Mostrar si la pantalla está encendida o no (La pantalla está encendida cuando la tv está encendida y está apagada cuando la tv también lo está).
    public boolean estaEncendida(boolean televisorEncendido) {
        return televisorEncendido;
    }

    // Mostrar Información
    @Override
    public String toString() {
        String tipoNombre;
        if (tipo == 'P') {
            tipoNombre = "Plasma";
        } else if (tipo == 'L') {
            tipoNombre = "LCD";
        } else if (tipo == 'O') {
            tipoNombre = "OLED";
        } else {
            tipoNombre = "Desconocido"; 
        }

        return pulgadas + "\" " + resolucion + " " + tipoNombre;
    }
}