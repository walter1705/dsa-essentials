package dsa.uniquindio.factura;

import java.util.LinkedHashMap;
import java.util.Map;

public class Factura {
    private LinkedHashMap<String, Detalle> items = new LinkedHashMap<>();
    private static final double IVA = 0.19; // 19% IVA

    public void escanearProducto(String codigo, String nombre, double precioUnitario, int cantidad) {
        if (items.containsKey(codigo)) {
            items.get(codigo).sumarCantidad(cantidad);
        } else {
            items.put(codigo, new Detalle(nombre, cantidad, precioUnitario));
        }
    }

    public void imprimirFactura() {
        double subtotal = 0;

        System.out.println("=== FACTURA SUPERMERCADO ===");
        for (Map.Entry<String, Detalle> entry : items.entrySet()) {
            Detalle d = entry.getValue();
            double subtotalItem = d.getSubtotal();
            subtotal += subtotalItem;

            System.out.printf("%-10s %-15s x%d  $%.2f  Subtotal: $%.2f%n",
                    entry.getKey(),
                    d.getNombre(),
                    d.getCantidad(),
                    d.getPrecioUnitario(),
                    subtotalItem);
        }

        double iva = subtotal * IVA;
        double total = subtotal + iva;

        System.out.println("\n----------------------------");
        System.out.printf("Subtotal: $%.2f%n", subtotal);
        System.out.printf("IVA (19%%): $%.2f%n", iva);
        System.out.printf("TOTAL: $%.2f%n", total);
    }
}
