package DSA.segundoCohorte.listasEnlazadas.src.main.java.dsa.uniquindio.listaDobleCircular;

public class Main {
    public static void main(String[] args) {
        // Crear una instancia de la lista circular doblemente enlazada
        DoubleCircularList<Integer> list = new DoubleCircularList<>();
        
        System.out.println("===== PRUEBAS DE LA LISTA CIRCULAR DOBLEMENTE ENLAZADA =====");
        
        // 1. Prueba de método addFirst
        System.out.println("\n1. Prueba de addFirst:");
        System.out.println("Agregando elementos al inicio: 30, 20, 10");
        list.addFirst(30);
        list.addFirst(20);
        list.addFirst(10);
        System.out.println("Lista después de addFirst:");
        list.print();
        
        // 2. Prueba de método addLast
        System.out.println("\n2. Prueba de addLast:");
        System.out.println("Agregando elementos al final: 40, 50");
        list.addLast(40);
        list.addLast(50);
        System.out.println("Lista después de addLast:");
        list.print();
        
        // 3. Prueba de método get
        System.out.println("\n3. Prueba de get:");
        System.out.println("Elemento en la posición 0: " + list.get(0));
        System.out.println("Elemento en la posición 2: " + list.get(2));
        System.out.println("Elemento en la posición 4: " + list.get(4));
        
        // 4. Prueba de método add en posición intermedia
        System.out.println("\n4. Prueba de add en posición intermedia:");
        System.out.println("Agregando 25 en la posición 2");
        list.add(2, 25);
        System.out.println("Lista después de add(2, 25):");
        list.print();
        
        // 5. Prueba de método remove
        System.out.println("\n5. Prueba de remove:");
        System.out.println("Eliminando elemento en la posición 1");
        list.remove(1);
        System.out.println("Lista después de remove(1):");
        list.print();
        
        
        // 7. Prueba de método getIndexOfNode
        System.out.println("\n7. Prueba de getIndexOfNode:");
        System.out.println("Índice del valor 25: " + list.getIndexOfNode(25));
        System.out.println("Índice del valor 100: " + list.getIndexOfNode(100)); // No existe
        
        // 8. Prueba de método deleteValue
        System.out.println("\n8. Prueba de deleteValue:");
        System.out.println("Eliminando el valor 30");
        boolean deleted = list.deleteValue(30);
        System.out.println("¿Se eliminó el valor 30? " + deleted);
        System.out.println("Lista después de deleteValue(30):");
        list.print();
        
        // Prueba final: vaciar la lista
        System.out.println("\n9. Prueba de clear:");
        list.clear();
        System.out.println("¿Lista vacía? " + list.isEmpty());
        System.out.println("Tamaño de la lista: " + list.size());
    }
}
