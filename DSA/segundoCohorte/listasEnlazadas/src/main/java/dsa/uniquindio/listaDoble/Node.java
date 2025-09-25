package DSA.segundoCohorte.listasEnlazadas.src.main.java.dsa.uniquindio.listaDoble;

public class Node<T> {
    T dato;
    Node<T> next;
    Node<T> prev;

    public Node(T dato) {
        this.dato = dato;
        this.next = null;
        this.prev = null;
    }
}
