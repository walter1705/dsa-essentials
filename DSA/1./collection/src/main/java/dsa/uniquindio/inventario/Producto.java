package dsa.uniquindio.inventario;

public class Producto {
    int stock;
    int precio;

    public Producto(int stock, int precio) {
        this.stock = stock;
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
}
