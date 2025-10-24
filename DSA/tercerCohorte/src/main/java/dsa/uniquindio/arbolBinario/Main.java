package dsa.uniquindio.arbolBinario;

public class Main {
  public static void main(String[] args) {
    System.out.println("===== PRUEBAS DEL ÁRBOL BINARIO DE BÚSQUEDA =====\n");
    
    // Crear un árbol de enteros
    Tree<Integer> tree = new Tree<>();
    
    // 1. Verificar si está vacío inicialmente
    System.out.println("1. Estado inicial del árbol:");
    System.out.println("¿Está vacío? " + tree.isEmpty());
    System.out.println("Tamaño: " + tree.size());
    System.out.println();
    
    // 2. Insertar elementos
    System.out.println("2. Insertando elementos: 50, 30, 70, 20, 40, 60, 80, 10, 25, 35, 45");
    tree.insert(50);
    tree.insert(30);
    tree.insert(70);
    tree.insert(20);
    tree.insert(40);
    tree.insert(60);
    tree.insert(80);
    tree.insert(10);
    tree.insert(25);
    tree.insert(35);
    tree.insert(45);
    
    System.out.println("Tamaño después de insertar: " + tree.size());
    System.out.println();
    
    // 3. Visualizar el árbol
    System.out.println("3. Estructura del árbol:");
    tree.printTree();
    System.out.println();
    
    // 4. Probar los recorridos
    System.out.println("4. Recorridos del árbol:");
    tree.inOrder();
    tree.preOrder();
    tree.postOrder();
    System.out.println();
    
    // 5. Buscar elementos
    System.out.println("5. Búsquedas en el árbol:");
    System.out.println("¿Existe 40? " + tree.search(40));
    System.out.println("¿Existe 25? " + tree.search(25));
    System.out.println("¿Existe 100? " + tree.search(100));
    System.out.println("¿Existe 15? " + tree.search(15));
    System.out.println();
    
    // 6. Encontrar mínimo y máximo
    System.out.println("6. Valores extremos:");
    System.out.println("Valor mínimo: " + tree.findMin());
    System.out.println("Valor máximo: " + tree.findMax());
    System.out.println("Altura del árbol: " + tree.height());
    System.out.println();
    
    // 7. Eliminar elementos (diferentes casos)
    System.out.println("7. Eliminaciones:");
    
    // Eliminar una hoja
    System.out.println("Eliminando 10 (hoja):");
    tree.delete(10);
    System.out.println("Tamaño después: " + tree.size());
    tree.inOrder();
    System.out.println();
    
    // Eliminar nodo con un hijo
    System.out.println("Eliminando 25 (un hijo):");
    tree.delete(25);
    System.out.println("Tamaño después: " + tree.size());
    tree.inOrder();
    System.out.println();
    
    // Eliminar nodo con dos hijos
    System.out.println("Eliminando 30 (dos hijos):");
    tree.delete(30);
    System.out.println("Tamaño después: " + tree.size());
    tree.inOrder();
    System.out.println();
    
    // 8. Estado del árbol después de eliminaciones
    System.out.println("8. Árbol después de eliminaciones:");
    tree.printTree();
    System.out.println();
    
    // 9. Intentar eliminar un elemento que no existe
    System.out.println("9. Intentando eliminar 100 (no existe):");
    int sizeBefore = tree.size();
    tree.delete(100);
    System.out.println("Tamaño antes: " + sizeBefore + ", después: " + tree.size());
    System.out.println();
    
    // 10. Prueba con otro tipo de dato (String)
    System.out.println("10. Prueba con árbol de Strings:");
    Tree<String> stringTree = new Tree<>();
    
    stringTree.insert("mango");
    stringTree.insert("banana");
    stringTree.insert("uva");
    stringTree.insert("apple");
    stringTree.insert("naranja");
    stringTree.insert("pera");
    
    System.out.println("Árbol de frutas:");
    stringTree.printTree();
    System.out.println("Recorrido InOrder (orden alfabético):");
    stringTree.inOrder();
    System.out.println();
    
    // 11. Limpiar el árbol original
    System.out.println("11. Limpiando el árbol de enteros:");
    tree.clear();
    System.out.println("¿Está vacío después de clear? " + tree.isEmpty());
    System.out.println("Tamaño después de clear: " + tree.size());
    
    System.out.println("\n===== FIN DE LAS PRUEBAS =====");
  }
}
