package DSA.segundoCohorte.listasEnlazadas.src.main.java.dsa.uniquindio.listaDoble;
import java.util.NoSuchElementException;
import java.util.Iterator;

public class listaDoble<T extends Comparable<T>> implements Iterable<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public listaDoble() {
        head = null;
        tail = null;
        size = 0;
    }
    public boolean estaVacia() {
        return size == 0;
    }

    private boolean indiceValido(int pos) {
        return pos >= 0 && pos < size;
    }

    public int size() {
        return size;
    }

    public void addFirst(T valor) {
        Node<T> nuevo = new Node<>(valor);
        if (estaVacia()) {
            head = tail = nuevo;
        } else {
            nuevo.next = head;
            head.prev = nuevo;
            head = nuevo;
        }
        size++;
    }

    public void addLast(T valor) {
        Node<T> nuevo = new Node<>(valor);
        if (estaVacia()) {
            head = tail = nuevo;
        } else {
            tail.next = nuevo;
            nuevo.prev = tail;
            tail = nuevo;
        }
        size++;
    }

    public void agregar(T valor, int pos) {
        if (pos < 0 || pos > size) throw new IndexOutOfBoundsException("Posición inválida");

        if (pos == 0) {
            addFirst(valor);
        } else if (pos == size) {
            addLast(valor);
        } else {
            Node<T> actual = head;
            for (int i = 0; i < pos - 1; i++) {
                actual = actual.next;
            }
            Node<T> nuevo = new Node<>(valor);
            Node<T> siguiente = actual.next;

            actual.next = nuevo;
            nuevo.prev = actual;
            nuevo.next = siguiente;
            siguiente.prev = nuevo;
            size++;
        }
    }

    public T obtenerValorNodo(int pos) {
        return obtenerNodo(pos).dato;
    }

    public Node<T> obtenerNodo(int pos) {
        if (!indiceValido(pos)) throw new IndexOutOfBoundsException("Índice inválido");
        Node<T> actual = head;
        for (int i = 0; i < pos; i++) {
            actual = actual.next;
        }
        return actual;
    }

    public int obtenerPosicionNodo(T valor) {
        Node<T> actual = head;
        for (int i = 0; i < size; i++) {
            if (actual.dato.equals(valor)) return i;
            actual = actual.next;
        }
        return -1;
    }

    public void modificarNodo(int pos, T nuevoValor) {
        Node<T> nodo = obtenerNodo(pos);
        nodo.dato = nuevoValor;
    }

    public void eliminarPrimero() {
        if (estaVacia()) return;
        if (size == 1) {
            head = tail = null;
        } else {
            head = head.next;
            head.prev = null;
        }
        size--;
    }

    public void eliminarUltimo() {
        if (estaVacia()) return;
        if (size == 1) {
            head = tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
        size--;
    }

    public boolean eliminar(T valor) {
        Node<T> actual = head;
        while (actual != null) {
            if (actual.dato.equals(valor)) {
                Node<T> anterior = actual.prev;
                Node<T> siguiente = actual.next;

                if (anterior != null) anterior.next = siguiente;
                else head = siguiente;

                if (siguiente != null) siguiente.prev = anterior;
                else tail = anterior;

                size--;
                return true;
            }
            actual = actual.next;
        }
        return false;
    }

    public void ordenarLista() {
        if (size < 2) return;
        boolean cambiado;
        do {
            cambiado = false;
            Node<T> actual = head;
            while (actual != null && actual.next != null) {
                if (actual.dato.compareTo(actual.next.dato) > 0) {
                    T temp = actual.dato;
                    actual.dato = actual.next.dato;
                    actual.next.dato = temp;
                    cambiado = true;
                }
                actual = actual.next;
            }
        } while (cambiado);
    }

    public void borrarLista() {
        head = tail = null;
        size = 0;
    }

    public void imprimirLista() {
        Node<T> actual = head;
        while (actual != null) {
            System.out.print(actual.dato);
            actual = actual.next;
        }
        System.out.println("null");
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> actual = head;

            @Override
            public boolean hasNext() {
                return actual != null;
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                T valor = actual.dato;
                actual = actual.next;
                return valor;
            }
        };
    }
}


