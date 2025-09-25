package DSA.segundoCohorte.listasEnlazadas.src.main.java.dsa.uniquindio.listaCircular;
import java.util.Iterator;
import java.util.NoSuchElementException;

class ListaCircular<T extends Comparable<T>> implements Iterable<T> {
    private Node<T> aux;
    private int size;

    public ListaCircular() {
        aux = null;
        size = 0;
    }
    public void addFirst(T valor) {
        Node<T> nuevo = new Node<>(valor);
        if (aux == null) {
            aux = nuevo;
            aux.next = aux;
        } else {
            nuevo.next = aux.next;
            aux.next = nuevo;
        }
        size++;
    }

    public void addLast(T valor) {
        Node<T> nuevo = new Node<>(valor);
        if (aux == null) {
            aux = nuevo;
            aux.next = aux;
        } else {
            nuevo.next = aux.next;
            aux.next = nuevo;
            aux = nuevo;
        }
        size++;
    }

    public void agregar(T valor, int posicion) {
        if (posicion < 0 || posicion > size) {
            throw new IndexOutOfBoundsException("Posición inválida");
        }

        if (posicion == 0) {
            addFirst(valor);
        } else if (posicion == size) {
            addLast(valor);
        } else {
            Node<T> nuevo = new Node<>(valor);
            Node<T> actual = aux.next;
            for (int i = 0; i < posicion - 1; i++) {
                actual = actual.next;
            }
            nuevo.next = actual.next;
            actual.next = nuevo;
            size++;
        }
    }

    public T obtenerValorNodo(int posicion) {
        return obtenerNodo(posicion).dato;
    }

    public Node<T> obtenerNodo(int posicion) {
        if (!indiceValido(posicion)) {
            throw new IndexOutOfBoundsException("Índice inválido");
        }
        Node<T> actual = aux.next;
        for (int i = 0; i < posicion; i++) {
            actual = actual.next;
        }
        return actual;
    }

    public int obtenerPosicionNodo(T valor) {
        if (estaVacia()) return -1;
        Node<T> actual = aux.next;
        for (int i = 0; i < size; i++) {
            if (actual.dato.equals(valor)) return i;
            actual = actual.next;
        }
        return -1;
    }

    private boolean indiceValido(int pos) {
        return pos >= 0 && pos < size;
    }

    public boolean estaVacia() {
        return aux == null;
    }

    public void eliminarPrimero() {
        if (estaVacia()) return;
        if (size == 1) {
            aux = null;
        } else {
            aux.next = aux.next.next;
        }
        size--;
    }

    public void eliminarUltimo() {
        if (estaVacia()) return;
        if (size == 1) {
            aux = null;
        } else {
            Node<T> actual = aux.next;
            while (actual.next != aux) {
                actual = actual.next;
            }
            actual.next = aux.next;
            aux = actual;
        }
        size--;
    }

    public boolean eliminar(T valor) {
        if (estaVacia()) return false;

        Node<T> actual = aux.next;
        Node<T> anterior = aux;

        for (int i = 0; i < size; i++) {
            if (actual.dato.equals(valor)) {
                if (size == 1) {
                    aux = null;
                } else {
                    anterior.next = actual.next;
                    if (actual == aux) aux = anterior;
                }
                size--;
                return true;
            }
            anterior = actual;
            actual = actual.next;
        }
        return false;
    }

    public void ordenarLista() {
        if (estaVacia() || size == 1) return;

        boolean cambiado;
        do {
            cambiado = false;
            Node<T> actual = aux.next;
            for (int i = 0; i < size - 1; i++) {
                Node<T> siguiente = actual.next;
                if (actual.dato.compareTo(siguiente.dato) > 0) {
                    T temp = actual.dato;
                    actual.dato = siguiente.dato;
                    siguiente.dato = temp;
                    cambiado = true;
                }
                actual = actual.next;
            }
        } while (cambiado);
    }


    public void borrarLista() {
        aux = null;
        size = 0;
    }

    public void modificarNodo(int pos, T nuevoValor) {
    if (pos < 0 || pos >= size) {
        throw new IndexOutOfBoundsException("Posición inválida");
    }

    Node<T> actual = aux.next; // asumiendo que aux apunta al último nodo
    for (int i = 0; i < pos; i++) {
        actual = actual.next;
    }
    actual.dato = nuevoValor;
}

    public void imprimirLista() {
        if (estaVacia()) {
            System.out.println("Lista vacía");
            return;
        }
        Node<T> actual = aux.next;
        do {
            System.out.print(actual.dato);
            actual = actual.next;
        } while (actual != aux.next);
        System.out.println(aux.next.dato);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> actual = (aux != null) ? aux.next : null;
            private int contador = 0;

            @Override
            public boolean hasNext() {
                return contador < size;
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                T valor = actual.dato;
                actual = actual.next;
                contador++;
                return valor;
            }
        };
    }
}

