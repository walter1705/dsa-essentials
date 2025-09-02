package dsa.uniquindio.agendaAcademica;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;

public class Main {
    public static void main(String[] args) {
        AgendaAcademica agenda = new AgendaAcademica();

        agenda.agregarActividad(LocalDate.of(2025, 9, 3), new Actividad("Examen Matemáticas"));
        agenda.agregarActividad(LocalDate.of(2025, 9, 5), new Actividad("Entrega Proyecto"));
        agenda.agregarActividad(LocalDate.of(2025, 9, 5), new Actividad("Charla de Orientación"));
        agenda.agregarActividad(LocalDate.of(2025, 9, 10), new Actividad("Exposición Programación"));

        // Mostrar próxima actividad
        System.out.println(" Próxima actividad:");
        Map.Entry<LocalDate, List<Actividad>> proxima = agenda.obtenerProximaActividad();
        if (proxima != null) {
            System.out.println(proxima.getKey() + " -> " + proxima.getValue());
        } else {
            System.out.println("No hay actividades próximas.");
        }

        // Reporte entre dos fechas
        System.out.println("\n Reporte del 3 al 7 de septiembre:");
        NavigableMap<LocalDate, List<Actividad>> reporte = agenda.generarReporte(
                LocalDate.of(2025, 9, 3),
                LocalDate.of(2025, 9, 7)
        );
        for (Map.Entry<LocalDate, List<Actividad>> entry : reporte.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}
