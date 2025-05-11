## Inciso 1: Validación de entrada del usuario 

Supongamos que estás desarrollando una aplicación que solicita al usuario su edad y esta información es crucial para el procesamiento posterior. La aplicación debe garantizar que el dato ingresado es un número entero válido y que está dentro de un rango aceptable (por ejemplo, 0 a 120 años). Si la entrada no es válida, el programa debe informar al usuario y solicitarle que reintente hasta que proporcione una entrada adecuada. 

Para resolver este problema, implementaremos un método que lea la entrada del usuario, valide si es un entero y esté dentro del rango permitido utilizando manejo de excepciones. Utilizaremos un bucle para repetir la solicitud de entrada hasta que sea correcta, gestionando las excepciones que puedan surgir si la entrada no es un número entero.

## Inciso 2: Lectura/Escritura de Archivos con manejo de Excepciones

Imaginar que se está desarrollando una aplicación en Java que necesita leer datos de un archivo de texto llamado "data.txt" y luego escribir cierta información procesada en otro archivo llamado "output.txt". El programa debe asegurarse de manejar cualquier error relacionado con la ausencia de archivos, problemas de acceso, y garantizar el cierre correcto de los recursos utilizados. 

Solución con manejo de excepciones en Java 

Para resolver este problema, implementaremos una solución que use manejo de excepciones para abordar problemas potenciales de entrada/salida (E/S). Utilizaremos try-with-resources para asegurar que todos los recursos se cierren automáticamente, independientemente de si se produce una excepción.  

## Adicional:
Armar un menú para permitir al usuario seleccionar el inciso 1 o el 2.



