package vehiculo;

public interface Combustible {
    String llenarTanque();
}

/* Ejercicio 2

Pensamos dos maneras de abstraer datos y comportamientos comunes. Una opción es crear una jerarquía de clases, añadiendo una clase intermedia entre Vehiculo y las subclases Moto y Coche (por ejemplo VehiculoConCombusible), que contenga los atributos y métodos comunes. La otra opción (la que usamos) es crear una interfaz, como hicimos con Combustible, para definir métodos comunes y añadir otro nivel de abstracción. 

*/