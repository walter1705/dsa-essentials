package dsa.uniquindio.navegacion;

import java.util.ArrayDeque;

public class Navegador {
    private ArrayDeque<String> pilaAtras = new ArrayDeque<>();
    private ArrayDeque<String> pilaAdelante = new ArrayDeque<>();
    private String paginaActual;

    public void visitar(String pagina) {
        if (paginaActual != null) {
            pilaAtras.push(paginaActual);
        }
        paginaActual = pagina;
        pilaAdelante.clear();
        System.out.println("Visitando: " + paginaActual);
    }

    public void atras() {
        if (!pilaAtras.isEmpty()) {
            pilaAdelante.push(paginaActual);
            paginaActual = pilaAtras.pop();
            System.out.println("Atrás -> " + paginaActual);
        } else {
            System.out.println("No hay páginas atrás.");
        }
    }

    public void adelante() {
        if (!pilaAdelante.isEmpty()) {
            pilaAtras.push(paginaActual);
            paginaActual = pilaAdelante.pop();
            System.out.println("Adelante -> " + paginaActual);
        } else {
            System.out.println("No hay páginas adelante.");
        }
    }

    public String getPaginaActual() {
        return paginaActual;
    }
}