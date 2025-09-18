package preparcial.comparablesII;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    // Create a list of Integers
    List<Integer> numbers = new ArrayList<>();
    numbers.add(5);
    numbers.add(10);
    numbers.add(3);
    numbers.add(8);
    numbers.add(1);

    // Create an instance of Main
    Main main = new Main();

    // Call the max method and print the result
    Integer maxNumber = main.max(numbers);
    System.out.println("Maximum number: " + maxNumber);

    // Example with Strings
    List<String> names = new ArrayList<>();
    names.add("Alice");
    names.add("Bob");
    names.add("Charlie");
    names.add("David");
    names.add("Eve");

    String maxName = main.max(names);
    System.out.println("Alphabetically last name: " + maxName);
  }

  public <T extends Comparable<? super T>> T max(List<T> l1) {
    Collections.sort(l1);
    return l1.get(l1.size() - 1);
  }
}
