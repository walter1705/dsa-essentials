package dsa.uniquindio.agendaAcademica;

import java.time.LocalDate;
import java.util.*;

public class AgendaAcademica {

    private TreeMap<LocalDate, List<Actividad>> agenda = new TreeMap<>();

    // Agregar actividad a una fecha
    public void agregarActividad(LocalDate fecha, Actividad actividad) {
        agenda.computeIfAbsent(fecha, f -> new ArrayList<>()).add(actividad);
    }

    // Obtener la actividad más próxima (>= hoy)
    public Map.Entry<LocalDate, List<Actividad>> obtenerProximaActividad() {
        LocalDate hoy = LocalDate.now();
        return agenda.ceilingEntry(hoy); // primera clave >= hoy
    }

    // Reporte entre dos fechas
    public NavigableMap<LocalDate, List<Actividad>> generarReporte(LocalDate inicio, LocalDate fin) {
        return agenda.subMap(inicio, true, fin, true);
    }



}