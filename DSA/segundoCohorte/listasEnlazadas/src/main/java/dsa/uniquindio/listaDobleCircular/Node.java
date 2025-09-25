package DSA.segundoCohorte.listasEnlazadas.src.main.java.dsa.uniquindio.listaDobleCircular;

public class Node<T> {
    Node<T> next;
    Node<T> back;
    
    T value;

    public Node() {
    }

    public Node(T value) {this.value = value;}

    public void setNext(Node<T> value) {
        next = value;
    }

    public T getValue() {
        return this.value;
    }

}
