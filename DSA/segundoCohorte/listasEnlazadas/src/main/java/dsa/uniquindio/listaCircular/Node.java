package DSA.segundoCohorte.listasEnlazadas.src.main.java.dsa.uniquindio.listaCircular;

public class Node<T> {
    T dato;
    Node<T> next;

    public Node(T dato) {
        this.dato = dato;
        this.next = null;
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }
}
