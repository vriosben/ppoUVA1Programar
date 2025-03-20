package UVA2.src.main;

import UVA2.src.televisor.Televisor;
import UVA2.src.televisor.Pantalla;

public class PruebaTelevisor {
    public static void main(String[] args) throws Exception {
        
        System.out.println("Ejercicio 1");

        // Crea 2 Pantallas
        Pantalla pantalla1 = new Pantalla(50, "4K",'O');
        Pantalla pantalla2 = new Pantalla(55, "HD",'P');

        // Crea 2 TVs
        Televisor televisor1 = new Televisor("LG", "ThinkQ", 2024, pantalla1, true);
        Televisor televisor2 = new Televisor("Noblex", "Inova", 2018, pantalla2, false);

        // Muestra la información de los 2 TVs
        System.out.println(televisor1);
        System.out.println(televisor2);

        System.out.println();
        System.out.println("Ejercicio 2");

        // Crea otro TV con el componente pantalla compartido con televisor 1
        Televisor televisor3 = new Televisor("Samsung", "Crystal Series", 2025, pantalla1, true);

        // Muestra la información de los 2 TVs
        System.out.println(televisor1);
        System.out.println(televisor3);

        // Cambia los atributos del componente pantalla compartido
        System.out.println("Modificando el componente pantalla...");
        pantalla1.setPulgadas(60);
        pantalla1.setResolucion("8K");
        pantalla1.setTipo('L');

        // Muestra la información de los 2 TVs luego de los cambios en pantalla
        System.out.println(televisor1);
        System.out.println(televisor3);

        System.out.println();
        System.out.println("Ejercicio 3");

        // Enciende el televisor 1. 
        System.out.println("Encendiendo Televisor " + televisor1.getId() + "...");
        televisor1.encender();
        System.out.println(televisor1.obtenerEstado() + "\n");
    
        // Enciende el televisor 3.
        System.out.println("Encendiendo Televisor " + televisor3.getId() + "...");
        televisor3.encender();
        System.out.println(televisor3.obtenerEstado() + "\n");


        // Apaga el televisor 1
        System.out.println("Apagando Televisor " + televisor1.getId() + "...");
        televisor1.apagar();
        System.out.println(televisor1.obtenerEstado() + "\n");
      }
  }