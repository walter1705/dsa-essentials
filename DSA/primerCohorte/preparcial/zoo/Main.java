package preparcial.zoo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
  public static void maid(String[] args) {
    // Create a zoo and add animals
    Zoo<Animal> zoo = new Zoo<>();
    zoo.addAnimal(new Animal("Leo", "Lion", 5));
    zoo.addAnimal(new Animal("Ellie", "Elephant", 12));
    zoo.addAnimal(new Animal("Zoe", "Zebra", 3));
    Collections.sort(zoo.especies);
    zoo.printAllAnimals();
  }

  public static void main(String[] args) {
    List<String> fruits = new ArrayList<>();
fruits.add("Apple");
fruits.add("Banana");
fruits.add("Cherry");

for (String fruit : fruits) {
    if (fruit.equals("Banana")) {
        fruits.add("Date");
    }
}
System.out.println(fruits.size());
}
}
