package dsa.uniquindio.inventario;

import java.util.HashMap;
import java.util.Map;

public class Ferreteria {
    HashMap<String,Producto> inventario = new HashMap<>();
    public static void main(String[] args) {
        Ferreteria ferreteria = new Ferreteria();

        // Agregar productos
        ferreteria.agregarProducto("P001", new Producto(10, 25000));
        ferreteria.agregarProducto("P002", new Producto(0, 8000));
        ferreteria.agregarProducto("P003", new Producto(5, 120000));

        // Consultar precio
        System.out.println("Precio del taladro: $" + ferreteria.consultarPrecio("P003"));

        // Actualizar stock
        ferreteria.actualizarStock("P001", 7);
        System.out.println("Stock actualizado de Martillo: " + ferreteria.inventario.get("P001").getStock());

        // Listar faltantes
        System.out.println("\nProductos faltantes:");
        ferreteria.listarFaltantes();
    }
    public void agregarProducto(String cod, Producto producto) {
        inventario.put(cod, producto);
    }

    public void actualizarStock(String cod, int n)
    {
        inventario.get(cod).setStock(n);
    }

    public int consultarPrecio(String cod) {
        return inventario.get(cod).getPrecio();
    }

    public void listarFaltantes() {
        for (Map.Entry<String, Producto> entry : inventario.entrySet()) {
            if (entry.getValue().getStock() == 0) {
                System.out.println(entry.getKey() + " -> " + entry.getValue());
            }
        }
    }
}
