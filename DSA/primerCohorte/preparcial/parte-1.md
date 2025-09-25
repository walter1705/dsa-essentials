# Pre-parcial 1

Voy a simular estilo parcial ya que sera escrito, entonces el codigo que hare sera
en un edito no en un IDE sino en texto puro y duro. ;)

Explicacion a detalle del preparcial: [click.](https://youtu.be/dQw4w9WgXcQ?si=Vl6gkqviJSHEsBa-)

---

```java
//n es el numero a contar, en este caso 0

public int divide(ArrayList<int> lista, int n) {
	if(lista.length == 0) return 0;
	if(lista.length == 1) return (lista.get(0) == n) ? 1 : 0;
	int mid = lista.length / 2;
	ArrayList<int> left = lista.subList(0, mid);
	ArrayList<int> right = lista.subList(mid, lista.length-1);

	return divide(left)+divide(right);
}
```

### Preguntas sobre colecciones:

A -> R/ C

B. -> R/ C

C. -> R/ Explicar y hacer dos ejemplos con los métodos de la clase Collections

- Organizar una lista de numeros sin orden:

```java
List<Integer> lista = List.of(3, 2, 1, 4);
Collections.sort(lista);
```

- Reversar una lista de nombres:

```java
List<String> lista = List.of("Juan", "Pedro", "Walter");
Collections.reverse(lista);
```

### Hallar recursivamente el promedio de los elementos de la diagonal de una matriz cuadrada.

```java
public class Matriz {
	public static void main(String[] args) {
		int[][] matriz = {
			{1, 2, 3},
			{4, 5, 6},
			{7, 8, 9}
		};
		System.out.println(promedioMatriz(matriz, 0, 0, 0));
	}

	public static double promedioMatriz(int[][] matriz, int promedio, int i, int j) {
		if(i >= matriz.length) return (double) promedio / matriz.length;
		promedio+= matriz[i][j];
		return promedioMatriz(matriz, promedio, i+1, j+1);
	}
}
```

### Ventajas y desventajas de la recursividad

**Ventajas**

- Algunos problemas son jerarquicos y son naturalmente mas eficientes con recursividad: (arboles, busqueda binaria, divide y venceras).
- Menos codigo utilizado en resolver el mismo problema que uno lineal.

**Desventajas**

- Mas complejo, por lo tanto mas dificil de debugear.
- Consume mas memoria al poner mas llamadas al stack, por lo que, si hay muchas llamadas, puede haber un error de desbordamiento (stack overflow).

### Preguntas sueltas

- A partir de las colecciones ArrayList , HashSet, TreeSet, HaspMap indicar de que interfaces están implementando respectivamente las colecciones.

  R-/ ArrayList implementa: List
  HashSet implementa: Set
  TreeSet implementa: NavigableSet
  HashMap implementa: Map

- ¿cuál es la principal diferencia entre las colecciones (ArrayList , HashSet, TreeSet, HashMap)?

  Que son colecciones para casos de uso distintos:
  R-/ ArrayList -> Array dinamico, pero con operaciones costosas.
  HashSet -> No permite duplicados, no asegura un orden.
  TreeSet -> No permite duplicados, pero asegura un order con respecto a la inserccion.
  HashMap -> No asegura un orden de insercion, no permite claves duplicados, coleccion clave: valor.

- Que es un Iterador (iterator) y de un ejemplo de cómo obtener dicho iterador para las siguientes colecciones (declarar la colección y obtener el iterador):

  `ArrayList,  HashMap`

  R-/Es una clase que tiene la logica de iteracion de cada uno de las estructuras de datos de Java, tambien podemos nuestra propia clase hija de iterador para implementar logica de iteracion en nuestras clases.

ArrayList

```java
ArrayList<Integer> coleccion = new ArrayList<>();
Iterator<Integer> iterador = coleccion.iterator();
```

HashMap

```java
HashMap<Integer, String> map = new HashMap<>();
Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
```

### Enunciados

1. Diseñar una clase genérica Zoo<T> que contenga una LinkedList de Animales. Cada Animal tiene un nombre, especie y edad. Implementar un método que use un iterador para encontrar todos los animales de una especie específica. Además, implementar una clase interna que permita ordenar los animales por edad usando comparadores.

Solucion [en.](./zoo/Main.java)

2. Diseñar un método genérico que tome dos
   listas de cualquier tipo List<T> y devuelva una nueva LinkedList que contenga solo los elementos que están presentes en ambas listas, es decir, la intersección de las dos listas.

Solucion [en.](./linkedList/Main.java)

3. Implementar un método genérico que reciba una lista de elementos comparables y devuelva el segundo máximo elemento en dicha lista. Usa la interfaz Comparable<T> para asegurar que los elementos puedan ser comparados (sin usar ciclos ni lambdas).

Solucion [en.](./comparables/Main.java)

- Resultado del codigo

---

[Continuar a Parte 2](parte-2.md).
