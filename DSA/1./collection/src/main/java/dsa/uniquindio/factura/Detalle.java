package dsa.uniquindio.factura;

import java.util.LinkedHashMap;
import java.util.Map;

class Detalle {
    private String nombre;
    private int cantidad;
    private double precioUnitario;

    public Detalle(String nombre, int cantidad, double precioUnitario) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public void sumarCantidad(int n) {
        this.cantidad += n;
    }

    public double getSubtotal() {
        return cantidad * precioUnitario;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }
}
