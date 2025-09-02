package dsa.uniquindio.soporteTecnico;

import java.time.LocalDateTime;
import java.util.PriorityQueue;



public class Ticket implements Comparable<Ticket> {
    private String descripcion;
    private Severidad severidad;
    private LocalDateTime fechaCreacion;

    public Ticket(String descripcion, Severidad severidad, LocalDateTime fechaCreacion) {
        this.descripcion = descripcion;
        this.severidad = severidad;
        this.fechaCreacion = fechaCreacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Severidad getSeveridad() {
        return severidad;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    @Override
    public int compareTo(Ticket otro) {
        int cmp = Integer.compare(otro.severidad.ordinal(), this.severidad.ordinal());
        if (cmp != 0) return cmp;

        return this.fechaCreacion.compareTo(otro.fechaCreacion);
    }

    @Override
    public String toString() {
        return "[" + severidad + "] " + descripcion + " (" + fechaCreacion + ")";
    }
}


