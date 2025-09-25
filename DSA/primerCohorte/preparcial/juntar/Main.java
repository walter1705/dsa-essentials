package preparcial.juntar;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    Main main = new Main();

    // Create two lists with some elements
    List<Integer> list1 = new LinkedList<>();
    list1.add(1);
    list1.add(2);
    list1.add(3);
    list1.add(4);

    List<Integer> list2 = new LinkedList<>();
    list2.add(3);
    list2.add(4);
    list2.add(5);
    list2.add(6);

    // Call the interseccion method
    LinkedList<Integer> result = main.interseccion(list1, list2);

    // Print the lists and result
    System.out.println("List 1: " + list1);
    System.out.println("List 2: " + list2);
    System.out.println("Result: " + result);

    // Test with strings
    List<String> strList1 = new LinkedList<>();
    strList1.add("apple");
    strList1.add("banana");
    strList1.add("cherry");

    List<String> strList2 = new LinkedList<>();
    strList2.add("banana");
    strList2.add("cherry");
    strList2.add("date");

    LinkedList<String> strResult = main.interseccion(strList1, strList2);
    System.out.println("\nString List 1: " + strList1);
    System.out.println("String List 2: " + strList2);
    System.out.println("Result: " + strResult);
  }

  public <T> LinkedList<T> interseccion(List<T> l1, List<T> l2) {
    LinkedHashSet<T> NL = new LinkedHashSet<>(l1);
    NL.addAll(l2);
    return new LinkedList<>(NL);
  }
}
