package UVA1.src.avion;

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

    //Getters
    public double getEnvergadura() {
        return envergadura;
    }

    public String getColor() {
        return color;
    }

    public String getTipoMaterial() {
        return tipoMaterial;
    }

    // Método flap()
    public void flap() {
        System.out.println("El ala está realizando un flap.");
    }

}