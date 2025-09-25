package DSA.segundoCohorte.listasEnlazadas.src.main.java.dsa.uniquindio.listaCircular;

public class Main {
    public static void main(String[] args) {
        ListaCircular<String> lista = new ListaCircular<>();

        lista.addFirst("Banana");
        lista.addFirst("Manzana");
        lista.addLast("Cereza");
        lista.agregar("Aguacate", 1);

        System.out.println("Lista original:");
        lista.imprimirLista();

        lista.ordenarLista();
        System.out.println("Lista ordenada:");
        lista.imprimirLista();

        System.out.println("Recorriendo con Iterator:");
        for (String fruta : lista) {
            System.out.print(fruta + " ");
        }
        System.out.println();

        lista.modificarNodo(2, "Durazno");
        lista.imprimirLista();

        lista.eliminarPrimero();
        lista.imprimirLista();

        lista.borrarLista();
        lista.imprimirLista();
    }
}



