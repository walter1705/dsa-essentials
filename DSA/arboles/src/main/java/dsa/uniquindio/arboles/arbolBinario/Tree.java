package dsa.uniquindio.arboles.arbolBinario;

public class Tree<T extends Comparable<T>> {
    private Node<T> root;
    private int size;

    public Tree() {
        this.root = null;
        this.size = 0;
    }

    // Getter para root (necesario para visualización)
    public Node<T> getRoot() {
        return root;
    }

    // 1. Insertar un elemento
    public void insert(T value) {
        root = insertRecursive(root, value);
    }

    private Node<T> insertRecursive(Node<T> current, T value) {
        if (current == null) {
            size++;
            return new Node<>(value);
        }

        int comparison = value.compareTo(current.getValue());
        if (comparison < 0) {
            current.setLeft(insertRecursive(current.getLeft(), value));
        } else if (comparison > 0) {
            current.setRight(insertRecursive(current.getRight(), value));
        }

        return current;
    }

    // 2. Buscar un elemento
    public boolean search(T value) {
        return searchRecursive(root, value);
    }

    private boolean searchRecursive(Node<T> current, T value) {
        if (current == null) {
            return false;
        }

        int comparison = value.compareTo(current.getValue());
        if (comparison == 0) {
            return true;
        } else if (comparison < 0) {
            return searchRecursive(current.getLeft(), value);
        } else {
            return searchRecursive(current.getRight(), value);
        }
    }

    // 3. Eliminar un elemento
    public void delete(T value) {
        root = deleteRecursive(root, value);
    }

    private Node<T> deleteRecursive(Node<T> current, T value) {
        if (current == null) {
            return null;
        }

        int comparison = value.compareTo(current.getValue());
        if (comparison < 0) {
            current.setLeft(deleteRecursive(current.getLeft(), value));
        } else if (comparison > 0) {
            current.setRight(deleteRecursive(current.getRight(), value));
        } else {
            size--;

            // Caso 1: Nodo sin hijos (hoja)
            if (current.isLeaf()) {
                return null;
            }

            // Caso 2: Nodo con un solo hijo
            if (!current.hasLeft()) {
                return current.getRight();
            }
            if (!current.hasRight()) {
                return current.getLeft();
            }

            // Caso 3: Nodo con dos hijos
            // Encontrar el sucesor inorden (el menor en el subárbol derecho)
            T successorValue = findMinValue(current.getRight());
            current.setValue(successorValue);
            current.setRight(deleteRecursive(current.getRight(), successorValue));
        }

        return current;
    }

    // Metodo auxiliar para encontrar el valor mínimo
    private T findMinValue(Node<T> node) {
        while (node.hasLeft()) {
            node = node.getLeft();
        }
        return node.getValue();
    }

    // 4. Recorrido InOrder (izquierda, raíz, derecha)
    public void inOrder() {
        System.out.print("InOrder: ");
        inOrderRecursive(root);
        System.out.println();
    }

    private void inOrderRecursive(Node<T> node) {
        if (node != null) {
            inOrderRecursive(node.getLeft());
            System.out.print(node.getValue() + " ");
            inOrderRecursive(node.getRight());
        }
    }

    // 5. Recorrido PreOrder (raíz, izquierda, derecha)
    public void preOrder() {
        System.out.print("PreOrder: ");
        preOrderRecursive(root);
        System.out.println();
    }

    private void preOrderRecursive(Node<T> node) {
        if (node != null) {
            System.out.print(node.getValue() + " ");
            preOrderRecursive(node.getLeft());
            preOrderRecursive(node.getRight());
        }
    }

    // 6. Recorrido PostOrder (izquierda, derecha, raíz)
    public void postOrder() {
        System.out.print("PostOrder: ");
        postOrderRecursive(root);
        System.out.println();
    }

    private void postOrderRecursive(Node<T> node) {
        if (node != null) {
            postOrderRecursive(node.getLeft());
            postOrderRecursive(node.getRight());
            System.out.print(node.getValue() + " ");
        }
    }

    // 7. Obtener la altura del árbol
    public int height() {
        return heightRecursive(root);
    }

    private int heightRecursive(Node<T> node) {
        if (node == null) {
            return -1; // Altura de árbol vacío es -1
        }
        return 1 + Math.max(heightRecursive(node.getLeft()), heightRecursive(node.getRight()));
    }

    // 8. Verificar si el árbol está vacío
    public boolean isEmpty() {
        return root == null;
    }

    // 9. Obtener el tamaño del árbol
    public int size() {
        return size;
    }

    // 10. Encontrar el valor mínimo en el árbol
    public T findMin() {
        if (isEmpty()) {
            throw new RuntimeException("El árbol está vacío");
        }
        return findMinValue(root);
    }

    // 11. Encontrar el valor máximo en el árbol
    public T findMax() {
        if (isEmpty()) {
            throw new RuntimeException("El árbol está vacío");
        }
        Node<T> current = root;
        while (current.hasRight()) {
            current = current.getRight();
        }
        return current.getValue();
    }

    // 12. Limpiar el árbol
    public void clear() {
        root = null;
        size = 0;
    }

    // 13. Método para imprimir el árbol de forma visual 
    public void printTree() {
        if (isEmpty()) {
            System.out.println("Árbol vacío");
            return;
        }
        printTreeRecursive(root, "", true);
    }

    private void printTreeRecursive(Node<T> node, String prefix, boolean isLast) {
        if (node != null) {
            System.out.println(prefix + (isLast ? "└── " : "├── ") + node.getValue());
            
            if (node.hasLeft() || node.hasRight()) {
                if (node.hasLeft()) {
                    printTreeRecursive(node.getLeft(), prefix + (isLast ? "    " : "│   "), !node.hasRight());
                }
                if (node.hasRight()) {
                    printTreeRecursive(node.getRight(), prefix + (isLast ? "    " : "│   "), true);
                }
            }
        }
    }
}
