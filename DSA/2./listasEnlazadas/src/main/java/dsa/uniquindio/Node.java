package dsa.uniquindio;

public class Node {
  int value;
  Node next;

  public Node() {
    next = null;
  }

  public Node(Node next, int value) {
    this.next = next;
    this.value = value;
  }

  public Node(Node next) {
    this.next = next;
  }

  public Node(int value) {
    this.value = value;
  }

  public void setNext(Node nextNode) {
    next = nextNode;
  }
}
