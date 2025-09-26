package DSA.segundoCohorte.listasEnlazadas.src.main.java.dsa.uniquindio.cola;

public class Node<T> {
  T value;
  Node<T> next;
  
  public Node() {
    next = null;
  }

  public Node(Node<T> next, T value) {
    this.next = next;
    this.value = value;
  }

  public Node(Node<T> next) {
    this.next = next;
  }

  public Node(T value) {
    this.value = value;
  }

  public void setNext(Node<T> nextNode) {
    next = nextNode;
  }
}
