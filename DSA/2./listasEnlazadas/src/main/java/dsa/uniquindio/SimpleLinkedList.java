package dsa.uniquindio;



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

  public int size() {
    return size;
  }
}
