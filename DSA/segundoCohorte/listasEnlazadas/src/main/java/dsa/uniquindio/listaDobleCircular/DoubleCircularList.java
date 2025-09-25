package DSA.segundoCohorte.listasEnlazadas.src.main.java.dsa.uniquindio.listaDobleCircular;

import java.util.NoSuchElementException;

public class DoubleCircularList<T> {
    Node<T> firstNode;
    Node<T> lastNode;
    int size = 0;

    public DoubleCircularList() {
        firstNode = null;
        lastNode = null;
    }

    // 1
    public void addFirst(T value) {
        Node<T> newNode = new Node<T>(value);
        
        if (isEmpty()) {
            firstNode = newNode;
            lastNode = newNode;
            newNode.setNext(newNode);
            newNode.back = newNode;
        } else {
            newNode.setNext(firstNode);
            newNode.back = lastNode;
            firstNode.back = newNode;
            lastNode.setNext(newNode);
            firstNode = newNode;
        }
        size++;
    }

    // 2
    public void addLast(T value) {
        if (isEmpty()) {
            addFirst(value);
            return;
        }
        
        Node<T> newNode = new Node<T>(value);
        
        newNode.setNext(firstNode);
        newNode.back = lastNode;
        lastNode.setNext(newNode);
        firstNode.back = newNode;
        lastNode = newNode;
        
        size++;
    }

    // 3
    public void add(int index, T value) {
        if (size() < index) throw new IndexOutOfBoundsException("Error accessing non-existent index in a list.");
        if (index == 0) {
            addFirst(value);
        } else if (index == size()) {
            addLast(value);
        } else {
            addBetween(index, value);
        }
    }

    // 4
    public T get(int index) {
        return getNode(index).value;
    }

    // 5
    public Node<T> getNodeRecursive(int index) {
        Node<T> auxNode = firstNode;
        return getNodeRecursive(index, auxNode, 0);
    }

    // 5
    public Node<T> getNodeRecursive(int index, Node<T> auxNode, int counter) {
        if (counter == index) return auxNode;
        return getNodeRecursive(index, auxNode.next, ++counter);
    }

    // 6
    public int getIndexOfNode(T val) {
        if (isEmpty()) return -1;
        
        Node<T> auxNode = firstNode;
        int counter = 0;
        
        do {
            if (auxNode.value == val || (auxNode.value != null && auxNode.value.equals(val))) {
                return counter;
            }
            auxNode = auxNode.next;
            ++counter;
        } while (auxNode != firstNode && counter < size);
        
        return -1; // Value not found
    }

    // 7
    private void validateIndex(int index) {
        if (index > size - 1) {
            throw new IndexOutOfBoundsException("Index out of bounds.");
        } else if (index < 0) {
            throw new IllegalArgumentException("Not valid index.");
        }
    }

    // 8
    public boolean isEmpty() {
        return firstNode == null;
    }

    private void addBetween(int index, T value) {
        validateIndex(index);
        
        Node<T> newNode = new Node<T>(value);
        Node<T> current = firstNode;
        
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        
        Node<T> previous = current.back;
        
        newNode.setNext(current);
        newNode.back = previous;
        previous.setNext(newNode);
        current.back = newNode;
        
        size++;
    }

    public int size() {
        return size;
    }

    // 9
    private void removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty.");
        }
        
        if (size == 1) {
            firstNode = null;
            lastNode = null;
        } else {
            firstNode = firstNode.next;
            firstNode.back = lastNode;
            lastNode.setNext(firstNode);
        }
        
        size--;
    }

    // 10
    private void removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty.");
        }
        
        if (size == 1) {
            firstNode = null;
            lastNode = null;
        } else {
            lastNode = lastNode.back;
            lastNode.setNext(firstNode);
            firstNode.back = lastNode;
        }
        
        size--;
    }

    // 11
    public void removeBetween(int index) {
        validateIndex(index);
        
        Node<T> nodeToRemove = getNode(index);
        Node<T> prevNode = nodeToRemove.back;
        Node<T> nextNode = nodeToRemove.next;
        
        prevNode.setNext(nextNode);
        nextNode.back = prevNode;
        
        size--;
    }

    // 11
    public void remove(int index) {
        validateIndex(index);

        if (index == 0) {
            removeFirst();
        } else if (index == size - 1) {
            removeLast();
        } else {
            removeBetween(index);
        }
    }

    // 11 eliminar un valor
    public boolean deleteValue(T value) {
        if (isEmpty()) return false;
        
        Node<T> auxNode = firstNode;
        int index = 0;
        
        do {
            if (auxNode.value == value || (auxNode.value != null && auxNode.value.equals(value))) {
                remove(index);
                return true;
            }
            auxNode = auxNode.next;
            index++;
        } while (auxNode != firstNode && index < size);
        
        return false;
    }

    // 12
    public Node<T> getNode(int index) {
        validateIndex(index);
        
        if (index == 0) return firstNode;
        if (index == size - 1) return lastNode;
        
        Node<T> auxNode;
        
        // Decide whether to traverse forward or backward based on which would be faster
        if (index <= size / 2) {
            auxNode = firstNode;
            for (int i = 0; i < index; i++) {
                auxNode = auxNode.next;
            }
        } else {
            auxNode = lastNode;
            for (int i = size - 1; i > index; i--) {
                auxNode = auxNode.back;
            }
        }
        
        return auxNode;
    }

    // 12
    public void modifyNode(int index, T value) {
        getNode(index).value = value;
    }

    // 14
    public void print() {
        if (isEmpty()) {
            System.out.println("List is empty");
            return;
        }
        
        Node<T> auxNode = firstNode;
        
        do {
            System.out.println(auxNode.value);
            auxNode = auxNode.next;
        } while (auxNode != firstNode);
    }

    // 16
    public void clear() {
        size = 0;
        firstNode = null;
        lastNode = null;
    }

}
