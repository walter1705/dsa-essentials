package dsa.uniquindio;

import java.util.NoSuchElementException;

public class SimpleLinkedList {
  int size = 0;
  Node firstNode;

  public SimpleLinkedList() {
    firstNode = null;
  }

  public void addFirst(int value) {
    Node newNode = new Node(value);
    newNode.setNext(firstNode);;
    firstNode = newNode;
    size++;
  }

  public void addLast(int value) {
    Node newNode = new Node(value);

    Node auxNode =  firstNode;  
    while (auxNode.next != null) {
      auxNode = auxNode.next;
    }

    auxNode.setNext(newNode);;
    size++; 
  }  
  
  public void print() {
    Node auxNode = firstNode;

    while(auxNode.next != null) {
      System.out.println(auxNode.value);
      auxNode = auxNode.next;
    }

    System.out.println(auxNode.value);

  }
  
  public void clear() {
    size = 0;
    firstNode = null;
  }

  public void add(int index, int value) {
    if(size() < index) throw new IndexOutOfBoundsException("Error accesing no existent index in a list.");
    if (index == 0) {
      addFirst(value);
    }else if(index == size()) {
      addLast(value);
    } else {
      addBetween(index, value);
    }
  }

  private void addBetween(int index, int value) {
    validateIndex(index);
    int counter = 0;
    Node newNode = new Node(value);
    Node auxNode = firstNode;

    while(auxNode.next != null) {
      if (index == counter) {
        newNode.setNext(auxNode.next);
        auxNode.setNext(newNode);
        break;
      }
      ++counter;
    }
  }

  private void validateIndex(int index) {
    if(index > size-1) {
      throw new IndexOutOfBoundsException("Index out of bounds.");
    } else if (index < 0) {
      throw new IllegalArgumentException("Not valid index.");
    }
  }

  public int size() {
    return size;
  }

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

  private void removeLast() {
    Node auxNode = firstNode;
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

  private void removeFirst() {
    firstNode = firstNode.next;
  }

  public void removeBetween(int index) {
    Node auxNode = firstNode;
    int counter = 0;
    while(counter != index-1) {
      auxNode = auxNode.next;
      ++counter;
    }

    auxNode.setNext(auxNode.next.next);
  }

  public Node getNode(int index) {
    validateIndex(index);
    Node auxNode = firstNode;
    int counter =0;

    while(index != counter) {
      auxNode = auxNode.next;
      ++counter;
    }

    return auxNode;
  }

  public Node getNodeRecursive(int index) {
    Node auxNode = firstNode;
    return getNodeRecursive(index, auxNode, 0);
  }

  public Node getNodeRecursive(int index, Node auxNode, int counter) {
    if (counter==index) return auxNode;
    return getNodeRecursive(index, auxNode.next, ++counter);
  }

  public int get(int index) {
    return getNode(index).value;
  }


}
