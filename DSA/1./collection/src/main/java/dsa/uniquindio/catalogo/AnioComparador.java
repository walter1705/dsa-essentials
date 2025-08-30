package dsa.uniquindio.catalogo;

import java.util.Comparator;

public class AnioComparador  implements Comparator<Libro> {
    @Override
    public int compare(Libro libro1, Libro libro2) {
        return libro1.getAnio() - libro2.getAnio();
    }
}
