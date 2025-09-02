package dsa.uniquindio.imagenes;

import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Stack<Operacion> stack = new Stack<>();

        // Crear algunas operaciones
        Recortar recortar = new Recortar();
        Rotar rotar = new Rotar();
        Brillo brillo = new Brillo();

        // Añadir operaciones al stack para poder hacer ctrl+z
        stack.push(recortar);
        stack.push(rotar);
        stack.push(brillo);

        // Ejemplo de deshacer (ctrl+z)
        while (!stack.isEmpty()) {
            Operacion ultimaOperacion = stack.pop();
            System.out.println("Operación deshecha: " + ultimaOperacion);
        }
    }
}
