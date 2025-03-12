package src.alas;

public class Ala {

    //Atributos
    private double envergadura;
    private String color;
    private String tipoMaterial;

    //Constructor
    public Ala(double envergadura, String color, String tipoMaterial){
        this.envergadura = envergadura;
        this.color = color;
        this.tipoMaterial = tipoMaterial;
    }

    // Método flap()
    public void flap() {
        System.out.println("El ala está realizando un flap.");
    }

    // Método para mostrar información 
    public void mostrarInformacion() {
        System.out.println("Información del ala:");
        System.out.println("Envergadura: " + envergadura + " metros");
        System.out.println("Color: " + color);
        System.out.println("Tipo de material: " + tipoMaterial);
    }


}
