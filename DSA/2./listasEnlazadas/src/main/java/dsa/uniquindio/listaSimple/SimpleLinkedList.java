package dsa.uniquindio.listaSimple;

import java.util.NoSuchElementException;

public class SimpleLinkedList<T> {
  int size = 0;
  Node<T> firstNode;

  public SimpleLinkedList() {
    firstNode = null;
  }

  // 1
  public void addFirst(T value) {
    Node<T> newNode = new Node<T>(value);
    newNode.setNext(firstNode);;
    firstNode = newNode;
    size++;
  }

  //2
  public void addLast(T value) {
    Node<T> newNode = new Node<T>(value);

    Node<T> auxNode =  firstNode;  
    while (auxNode.next != null) {
      auxNode = auxNode.next;
    }

    auxNode.setNext(newNode);;
    size++; 
  }  

  //3
  public void add(int index, T value) {
    if(size() < index) throw new IndexOutOfBoundsException("Error accesing no existent index in a list.");
    if (index == 0) {
      addFirst(value);
    }else if(index == size()) {
      addLast(value);
    } else {
      addBetween(index, value);
    }
  }
  
  //4
  public T get(int index) {
    return getNode(index).value;
  }

  //5
  public Node<T> getNodeRecursive(int index) {
    Node<T> auxNode = firstNode;
    return getNodeRecursive(index, auxNode, 0);
  }

  //5
  public Node<T> getNodeRecursive(int index, Node<T> auxNode, int counter) {
    if (counter==index) return auxNode;
    return getNodeRecursive(index, auxNode.next, ++counter);
  }

  //6

  public int getIndexOfNode(T val) {
    Node<T> auxNode = firstNode;
    int counter = 0;

    while(auxNode != null ) {
      if(auxNode.value == val) break;
      auxNode = auxNode.next;
      ++counter;
    }

    return counter;
  }

  //7
  private void validateIndex(int index) {
    if(index > size-1) {
      throw new IndexOutOfBoundsException("Index out of bounds.");
    } else if (index < 0) {
      throw new IllegalArgumentException("Not valid index.");
    }
  }

  //8
  public boolean isEmpty() {
    return firstNode == null;
  }

  private void addBetween(int index, T value) {
    validateIndex(index);
    int counter = 0;
    Node<T> newNode = new Node<T>(value);
    Node<T> auxNode = firstNode;

    while(auxNode.next != null) {
      if (index == counter) {
        newNode.setNext(auxNode.next);
        auxNode.setNext(newNode);
        break;
      }
      ++counter;
    }
  }

  

  public int size() {
    return size;
  }

  //9 
  private void removeFirst() {
    firstNode = firstNode.next;
  }

  //10
  private void removeLast() {
    Node<T> auxNode = firstNode;
    int counter = 0;

    if(size()==0) {
      throw new NoSuchElementException("List is empty.");
    }

    while (counter != size()-2) {
      auxNode = auxNode.next;
      ++counter;
    }

    auxNode.next = null;
  }

  //11

  public void removeBetween(int index) {
    Node<T> auxNode = firstNode;
    int counter = 0;
    while(counter != index-1) {
      auxNode = auxNode.next;
      ++counter;
    }

    auxNode.setNext(auxNode.next.next);
  }

  //11
  public void remove(int index) {
    validateIndex(index);

    if(index == size()-1) {
      removeLast();
    } else if (index==0) {
      removeFirst();
    } else {
      removeBetween(index);
    }
  }
  //11 elimar un valor

  public boolean deleteValue(T value) {
    Node<T> auxNode = firstNode;
    
    while(auxNode != null) {
      if(auxNode.value == value) {
        return true;
      }
      auxNode = auxNode.next;
    }
    return false;
  }
  //12
  public Node<T> getNode(int index) {
    validateIndex(index);
    Node<T> auxNode = firstNode;
    int counter =0;

    while(index != counter) {
      auxNode = auxNode.next;
      ++counter;
    }

    return auxNode;
  }

  //12
  public void modifyNode(int index, T value) {
    getNode(index).value = value;
  }

  //13

  //14
  public void print() {
    Node<T> auxNode = firstNode;

    while(auxNode.next != null) {
      System.out.println(auxNode.value);
      auxNode = auxNode.next;
    }

    System.out.println(auxNode.value);

  }
  //15

  //16
  public void clear() {
    size = 0;
    firstNode = null;
  }

  //17
  public void reverse() {
    Node<T> pastNode = firstNode;
    reverse(pastNode.next, pastNode);
    pastNode.next = null;
  }

  public void reverse(Node<T> actualNode,Node<T> pastNode) {
    if (actualNode == null) {
      firstNode = pastNode;
      return;
    }
    reverse(actualNode.next, actualNode);
    actualNode.next = pastNode;

  }
}
