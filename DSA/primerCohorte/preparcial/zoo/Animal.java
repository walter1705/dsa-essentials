package preparcial.zoo;

public class Animal implements Comparable<Animal> {
    String nombre, especie;
    Integer edad;

    public Animal(String nombre, String especie, Integer edad) {
      this.edad = edad;
      this.especie = especie;
      this.nombre = nombre;
    }

    @Override
    public int compareTo(Animal animal) {
      return animal.edad - edad;
    }
}
