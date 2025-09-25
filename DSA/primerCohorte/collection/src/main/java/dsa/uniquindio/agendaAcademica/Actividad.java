package dsa.uniquindio.agendaAcademica;


public class Actividad {
    private String descripcion;

    public Actividad(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return descripcion;
    }
}
