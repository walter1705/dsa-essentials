package collection.src.main.java.dsa.uniquindio.agenda;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.NavigableSet;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        TreeSet<Reunion> agenda = new TreeSet<>();

        // Agregar reuniones
        agenda.add(new Reunion(LocalDateTime.of(2025, Month.SEPTEMBER, 3, 10, 0), "Revisión proyecto"));
        agenda.add(new Reunion(LocalDateTime.of(2025, Month.SEPTEMBER, 2, 15, 30), "Llamada cliente"));
        agenda.add(new Reunion(LocalDateTime.of(2025, Month.SEPTEMBER, 10, 9, 0), "Demo producto"));
        agenda.add(new Reunion(LocalDateTime.of(2025, Month.SEPTEMBER, 5, 11, 0), "Plan estratégico"));

        // Mostrar todas en orden
        System.out.println("Agenda completa (orden cronológico):");
        agenda.forEach(System.out::println);

        // Primera y última reunión
        System.out.println("\nPrimera reunión: " + agenda.first());
        System.out.println("Última reunión: " + agenda.last());

        // Subrango: de hoy hasta fin de mes
        LocalDate hoy = LocalDate.of(2025, Month.SEPTEMBER, 2);
        LocalDateTime inicio = hoy.atStartOfDay();
        LocalDateTime finMes = hoy.withDayOfMonth(hoy.lengthOfMonth()).atTime(LocalTime.MAX);

        NavigableSet<Reunion> rango = agenda.subSet(
                new Reunion(inicio, ""),   // límite inferior
                true,                      // inclusive
                new Reunion(finMes, ""),   // límite superior
                true                       // inclusive
        );

        System.out.println("\nReuniones desde hoy hasta fin de mes:");
        rango.forEach(System.out::println);
    }
}
