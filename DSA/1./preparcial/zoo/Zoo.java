package preparcial.zoo;

import java.util.Iterator;
import java.util.LinkedList;

public class Zoo<T extends Animal>  {

  LinkedList<T> especies = new LinkedList<>();

  public void addAnimal(T animal) {
    especies.add(animal);
  }

  public void especieAnimal(String especie) {
    Iterator<T> iterator = especies.iterator();

    while(iterator.hasNext()) {
      T actual = iterator.next();
      if (actual.especie == especie) {
        System.out.println("Name: " + actual.nombre + ", Age: " + actual.edad + ", Species: " + actual.especie);
      }
    }
  }

  public void printAllAnimals() {
    System.out.println("Zoo Animals:");
    for (T animal : especies) {
      System.out.println("Name: " + animal.nombre + ", Age: " + animal.edad + ", Species: " + animal.especie);
    }
  }
}
