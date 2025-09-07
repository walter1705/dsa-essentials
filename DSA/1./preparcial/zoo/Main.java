package preparcial.zoo;

public class Main {
  public static void main(String[] args) {
    // Create a zoo and add animals
    Zoo<Animal> zoo = new Zoo<>();
    zoo.addAnimal(new Animal("Leo", "Lion", 5));
    zoo.addAnimal(new Animal("Ellie", "Elephant", 12));
    zoo.addAnimal(new Animal("Zoe", "Zebra", 3));
    zoo.especieAnimal("Lion");;
  }
}
