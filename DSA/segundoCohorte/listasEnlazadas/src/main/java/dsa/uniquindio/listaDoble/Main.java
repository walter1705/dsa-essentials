package DSA.segundoCohorte.listasEnlazadas.src.main.java.dsa.uniquindio.listaDoble;

public class Main {
    public static void main(String[] args) {
        listaDoble<String> lista = new listaDoble<>();

        lista.addFirst("2");
        lista.addFirst("3");
        lista.addLast("1");
        lista.agregar("5", 1);

        System.out.println("Lista original:");
        lista.imprimirLista();

        lista.ordenarLista();
        System.out.println("Lista ordenada:");
        lista.imprimirLista();

        lista.modificarNodo(2, "xd");
        lista.imprimirLista();

        lista.eliminarPrimero();
        lista.imprimirLista();

        lista.borrarLista();
        lista.imprimirLista();
    }
}

