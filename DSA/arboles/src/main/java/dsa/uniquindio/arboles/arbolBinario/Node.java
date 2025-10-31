package dsa.uniquindio.arboles.arbolBinario;

public class Node<T> {
  T value;
  Node<T> left;
  Node<T> right;
  
  // Constructor por defecto
  public Node() {
    this.value = null;
    this.left = null;
    this.right = null;
  }

  // Constructor solo con valor
  public Node(T value) {
    this.value = value;
    this.left = null;
    this.right = null;
  }

  // Constructor completo
  public Node(T value, Node<T> left, Node<T> right) {
    this.value = value;
    this.left = left;
    this.right = right;
  }

  // Getters y Setters para value
  public T getValue() {
    return value;
  }

  public void setValue(T value) {
    this.value = value;
  }

  // Getters y Setters para left
  public Node<T> getLeft() {
    return left;
  }

  public void setLeft(Node<T> left) {
    this.left = left;
  }

  // Getters y Setters para right
  public Node<T> getRight() {
    return right;
  }

  public void setRight(Node<T> right) {
    this.right = right;
  }

  // Método para verificar si es una hoja
  public boolean isLeaf() {
    return left == null && right == null;
  }

  // Método para verificar si tiene hijo izquierdo
  public boolean hasLeft() {
    return left != null;
  }

  // Método para verificar si tiene hijo derecho
  public boolean hasRight() {
    return right != null;
  }

  @Override
  public String toString() {
    return value != null ? value.toString() : "null";
  }
}
