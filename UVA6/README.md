## Contexto:
Se requiere desarrollar una aplicación de escritorio para la gestión de una biblioteca. El sistema permitirá a los usuarios buscar libros, prestar y devolver ejemplares. Además, debe incluir un módulo de administración para que los bibliotecarios puedan añadir, modificar o eliminar libros del sistema. La aplicación debe ser intuitiva y visualmente atractiva, facilitando su uso tanto para los usuarios como para los empleados de la biblioteca. 


## Objetivos del ejercicio: 

### Interfaz Gráfica de Usuario (GUI):
- Desarrollar una interfaz gráfica usando Java Swing que incluya: 
- Pantalla principal con botones para acceder a las diferentes funcionalidades (buscar libro, prestar libro, devolver libro, administración de libros). 
- Ventanas separadas para cada funcionalidad con formularios adecuados y tablas para mostrar los datos de los libros. 

### Flujos de E/S: 
- Implementar la funcionalidad de carga y guardado de la información de los libros utilizando flujos de E/S. Los datos de los libros deben persistirse en un archivo local (por ejemplo, un archivo .txt o .dat). 
- Asegurar que cada vez que la aplicación se inicie, se carguen los datos desde el archivo y al cerrar, se guarden las modificaciones realizadas durante la sesión. 

### Implementación de funcionalidades: 
- Búsqueda de libros: permitir a los usuarios buscar libros por título, autor o categoría. 
- Prestar y devolver libros: gestionar el préstamo de libros, verificando la disponibilidad y actualizar el estado del libro cuando sea devuelto. 
- Administración de libros: permitir a los bibliotecarios añadir nuevos libros, editar información existente o eliminar libros del sistema. 
- Administración de usuarios

### Requerimientos técnicos: 
- Utilizar clases y objetos para modelar los datos y la lógica de la aplicación (libros, usuarios, transacciones de préstamo/devolución). 
- Implementar excepciones para manejar errores en la entrada de datos y operaciones de archivo. 
- Aplicar principios de POO como encapsulación, herencia y polimorfismo donde sea pertinente. 
