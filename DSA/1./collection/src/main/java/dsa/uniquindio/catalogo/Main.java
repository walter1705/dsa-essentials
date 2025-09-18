package dsa.uniquindio.catalogo;



public class Main {
    public static void main(String[] args) {
        // Crear instancia de Biblioteca
        Biblioteca biblioteca = new Biblioteca();

        // Crear 8 libros
        Libro libro1 = new Libro("Cien años de soledad", "Gabriel García Márquez", "9780307474728", 1967);
        Libro libro2 = new Libro("El amor en los tiempos del cólera", "Gabriel García Márquez", "9780307387264", 1985);
        Libro libro3 = new Libro("Rayuela", "Julio Cortázar", "9788437601722", 1963);
        Libro libro4 = new Libro("Pedro Páramo", "Juan Rulfo", "9788437604183", 1955);
        Libro libro5 = new Libro("La ciudad y los perros", "Mario Vargas Llosa", "9788420471839", 1963);
        Libro libro6 = new Libro("Ficciones", "Jorge Luis Borges", "9780802130303", 1944);
        Libro libro7 = new Libro("La casa de los espíritus", "Isabel Allende", "9780525433477", 1982);
        Libro libro8 = new Libro("El laberinto de la soledad", "Octavio Paz", "9788437507040", 1950);

        // Agregar libros a la biblioteca
        biblioteca.agregar(libro1);
        biblioteca.agregar(libro2);
        biblioteca.agregar(libro3);
        biblioteca.agregar(libro4);
        biblioteca.agregar(libro5);
        biblioteca.agregar(libro6);
        biblioteca.agregar(libro7);
        biblioteca.agregar(libro8);

        biblioteca.libros.sort(new AnioComparador());
        // Mostrar todos los libros
        System.out.println("--- Listado de todos los libros ---");
        biblioteca.mostrarLibros();

        // Buscar libros por autor
        System.out.println("\n--- Libros de Gabriel García Márquez ---");
        biblioteca.mostrarLibros(biblioteca.buscarPorAutor("Gabriel García Márquez"));


    }
}
