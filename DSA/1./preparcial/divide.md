# Pre-parcial 1

Voy a simular estilo parcial ya que sera escrito, entonces el codigo que hare sera
en un edito no en un IDE sino en texto puro y duro. ;)

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
