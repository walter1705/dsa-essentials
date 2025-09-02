package dsa.uniquindio.laboratorio;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class Laboratorio {
    public static void main(String[] args) {
        LinkedList<Estudiante> cola = new LinkedList<>();

        // Comparator que define prioridad
        Comparator<Estudiante> comparador = (e1, e2) -> {
            if (e1.isTieneReserva() && !e2.isTieneReserva()) return -1;
            if (!e1.isTieneReserva() && e2.isTieneReserva()) return 1;
            return Integer.compare(e1.getOrdenLlegada(), e2.getOrdenLlegada());
        };

        // Simulamos llegadas
        agregarOrdenado(cola, new Estudiante("A", false, 1), comparador);
        agregarOrdenado(cola, new Estudiante("B", false, 2), comparador);
        agregarOrdenado(cola, new Estudiante("C", true, 3), comparador);
        agregarOrdenado(cola, new Estudiante("D", false, 4), comparador);
        agregarOrdenado(cola, new Estudiante("E", true, 5), comparador);

        // Mostrar cola
        System.out.println("Cola de turnos: " + cola);

        // Atender
        while (!cola.isEmpty()) {
            Estudiante atendido = cola.removeFirst();
            System.out.println("Atendiendo a: " + atendido);
        }
    }

    private static void agregarOrdenado(LinkedList<Estudiante> cola, Estudiante e, Comparator<Estudiante> comp) {
        cola.add(e);
        Collections.sort(cola, comp);
    }
}
