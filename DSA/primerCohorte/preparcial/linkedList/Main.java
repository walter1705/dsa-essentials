package preparcial.linkedList;

import java.util.LinkedList;
import java.util.List;


public class Main {
  public static void main(String[] args) {
    // Create a LinkedList of Integers
    // Create two LinkedLists of Integers with some common elements
    LinkedList<Integer> list1 = new LinkedList<>();
    list1.add(10);
    list1.add(20);
    list1.add(30);
    list1.add(40);
    list1.add(50);

    LinkedList<Integer> list2 = new LinkedList<>();
    list2.add(30);
    list2.add(40);
    list2.add(50);
    list2.add(60);
    list2.add(70);
    
    // Print the original lists
    System.out.println("List 1: " + list1);
    System.out.println("List 2: " + list2);
    
    LinkedList<Integer> result = mezclar(list1, list2);
    System.out.println("Result: " + result);
  }

  public static <T> LinkedList<T> mezclar(List<T> l1, List<T> l2) {
    LinkedList<T> list = new LinkedList<>();

    for(int i = 0; i<l1.size();i++) {
      T el1 = l1.get(i);
      if(l2.contains(el1)) list.add(el1);
    }

    return list;
  }
}
