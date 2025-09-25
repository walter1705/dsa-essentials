package dsa.uniquindio.cursos;

import java.util.LinkedHashSet;

public class Main {
    public static void main(String[] args) {
        LinkedHashSet<Curso> favoritos = new LinkedHashSet<>();

        // Agregar cursos
        favoritos.add(new Curso("C1", "Java Básico"));
        favoritos.add(new Curso("C2", "Estructuras de Datos"));
        favoritos.add(new Curso("C3", "Algoritmos"));

        // Intento duplicado
        favoritos.add(new Curso("C2", "Estructuras de Datos"));

        System.out.println("Favoritos iniciales: " + favoritos);

        // Eliminar un curso
        favoritos.remove(new Curso("C1", "Java Básico"));
        System.out.println("Tras eliminar Java Básico: " + favoritos);

        // Reinsertar un curso (se agrega al final)
        favoritos.add(new Curso("C1", "Java Básico"));
        System.out.println("Tras reinsertar Java Básico: " + favoritos);
    }
}
