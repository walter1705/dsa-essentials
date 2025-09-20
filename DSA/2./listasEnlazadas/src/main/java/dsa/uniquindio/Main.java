package dsa.uniquindio;

import java.util.LinkedList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        LinkedList a = new LinkedList<>();
    
        SimpleLinkedList list = new SimpleLinkedList();
        list.addFirst(2);
        list.addFirst(1);
        list.addLast(3);
        list.remove(2);
        list.print();
        System.out.println("NODO "+ list.getNodeRecursive(1).value);

    }


}
