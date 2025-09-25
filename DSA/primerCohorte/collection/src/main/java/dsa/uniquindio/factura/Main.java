package dsa.uniquindio.factura;

public class Main {
    public static void main(String[] args) {
        Factura factura = new Factura();

        factura.escanearProducto("001", "Arroz", 2500, 2);
        factura.escanearProducto("002", "Leche", 3200, 1);
        factura.escanearProducto("003", "Huevos", 15000, 1);
        factura.escanearProducto("001", "Arroz", 2500, 1); // se repite arroz, se suma cantidad

        factura.imprimirFactura();
    }
}
