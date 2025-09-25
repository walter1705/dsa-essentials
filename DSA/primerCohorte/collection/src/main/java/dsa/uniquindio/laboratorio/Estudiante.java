package dsa.uniquindio.laboratorio;


class Estudiante {
    private String nombre;
    private boolean tieneReserva;
    private int ordenLlegada;

    public Estudiante(String nombre, boolean tieneReserva, int ordenLlegada) {
        this.nombre = nombre;
        this.tieneReserva = tieneReserva;
        this.ordenLlegada = ordenLlegada;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isTieneReserva() {
        return tieneReserva;
    }

    public int getOrdenLlegada() {
        return ordenLlegada;
    }

    @Override
    public String toString() {
        return nombre + (tieneReserva ? " (Reserva)" : " (Normal)");
    }
}