package preparcial.cola;

import java.util.LinkedList;

public class Cola<T> {
  int tamano;
  LinkedList<T> cola = new LinkedList<>();

  public Cola(int tamano) {
    this.tamano = tamano;
  }

  public void add(T el) {
    if (cola.size()>= tamano) {
      throw new IllegalStateException("El tamano excedio el permitido.");
    } else {
      cola.add(el);
    } 
  }
}
