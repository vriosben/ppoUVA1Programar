## EJERCICIO UVA 4
Desarrollar un sistema básico para gestionar los libros de una biblioteca utilizando colecciones en Java. Este sistema debe permitir agregar, eliminar y buscar libros, así como compararlos por distintos criterios.

Requisitos:  

Clase Libro: 
- Atributos: título, autor, año de publicación e ISBN. 
- Implementar el método toString() para mostrar la información del libro. 
- Implementar interfaces de comparación (Comparable y Comparator) para poder comparar libros por título, autor y año de publicación. 

Gestión de Colecciones: 
- Utilizar una colección adecuada (como ArrayList o HashSet) para almacenar los libros. 
- Implementar métodos para agregar y eliminar libros de la colección. 
- Implementar la búsqueda de libros dentro de la colección utilizando un iterador. 

Comparación de Libros:  
- Implementar al menos dos comparadores: 
    - Por título del libro. 
    - Por año de publicación. 

Interfaz de Usuario:  
- Crear una interfaz simple en la consola para interactuar con el usuario. 
- Permitir al usuario agregar, eliminar, buscar libros y mostrar todos los libros.   

Instrucciones: 
- Utilizar colecciones de Java para manejar los datos.   
- Aplicar el patrón de diseño Iterator para recorrer la colección de libros.    
- Implementar la interfaz Comparable en la clase Libro para una comparación por defecto (por ejemplo, por ISBN) y crear clases separadas que implementen Comparator para otros criterios de comparación.  
- Proveer feedback al usuario tras cada operación (por ejemplo, confirmación de que un libro fue agregado o un mensaje de error si el libro no se encuentra).  