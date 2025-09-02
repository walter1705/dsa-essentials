package dsa.uniquindio.agenda;

import java.time.LocalDateTime;
import java.util.Objects;

class Reunion implements Comparable<Reunion> {
    private LocalDateTime fechaHora;
    private String asunto;

    public Reunion(LocalDateTime fechaHora, String asunto) {
        this.fechaHora = fechaHora;
        this.asunto = asunto;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public String getAsunto() {
        return asunto;
    }

    @Override
    public int compareTo(Reunion otra) {
        return this.fechaHora.compareTo(otra.fechaHora);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reunion)) return false;
        Reunion reunion = (Reunion) o;
        return Objects.equals(fechaHora, reunion.fechaHora);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fechaHora);
    }

    @Override
    public String toString() {
        return fechaHora + " - " + asunto;
    }
}
