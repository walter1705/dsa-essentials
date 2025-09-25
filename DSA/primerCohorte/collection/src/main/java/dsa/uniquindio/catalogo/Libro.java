package dsa.uniquindio.catalogo;

import java.util.Objects;

public class Libro implements Comparable<Libro> {
    String titulo, autor, ISBN;
    int anio;

    public Libro(String titulo, String autor, String ISBN, int anio) {
        this.titulo = titulo;
        this.autor = autor;
        this.ISBN = ISBN;
        this.anio = anio;
    }


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    @Override
    public int compareTo(Libro libro) {
        return getAnio() - libro.getAnio();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Libro other = (Libro) obj;
        return Objects.equals(anio, other.anio);
    }
}
