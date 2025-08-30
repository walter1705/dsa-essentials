package dsa.uniquindio.catalogo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Biblioteca {
    ArrayList<Libro> libros;

    public Biblioteca() {
        this.libros = new ArrayList<>();
    }

    public void mostrarLibros() {
        libros.forEach(libro -> {
            System.out.println("Título: " + libro.getTitulo());
            System.out.println("Autor: " + libro.getAutor());
            System.out.println("Año: " + libro.getAnio());
            System.out.println("ISBN: " + libro.getISBN());
            System.out.println("------------------------");
        });
    }

    public void mostrarLibros(ArrayList<Libro> libros) {
        libros.forEach(libro -> {
            System.out.println("Título: " + libro.getTitulo());
            System.out.println("Autor: " + libro.getAutor());
            System.out.println("Año: " + libro.getAnio());
            System.out.println("ISBN: " + libro.getISBN());
            System.out.println("------------------------");
        });
    }

    public void agregar(Libro libro) {
        libros.add(libro);
    }

    public void eliminarPorISB(String isbn) {
        libros.removeIf(libro -> libro.getISBN().equals(isbn));
    }

    public ArrayList<Libro> buscarPorAutor(String autor) {
        return libros.stream()
                .filter(libro -> libro.getAutor().equals(autor))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Libro> masRecientes() {
        return libros.stream()
                .limit(5)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
