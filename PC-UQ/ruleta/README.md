# Ruleta

Se necesita simular una ruleta por medio de matrices. En este caso se recibe una matriz la cual se va a girar hasta encontrar su forma final después de lograr una cantidad de giros N. Donde N será igual a la cantidad de giros de 90 grados que debe hacer la matriz. Si el valor de N es positivo, indica que los giros deben hacerse en sentido de las manecillas del reloj. Si el valor es negativo, el giro deberá hacerse en sentido contrario a las manecillas del reloj. Al final se retorna la matriz en su estado final donde se debe de imprimir dicha matriz(recursivo) para validar dicho estado.

## Ejemplos

### Ejemplo 1: Matriz 3x3 con 2 giros en sentido horario

**Matriz inicial:**

```
N J K
I Z Y
U E R
```

**Función:** `girarRuleta(matriz1, 2)`

**Resultado esperado después de 2 giros de 90° en sentido horario:**

```
U I N
E Z J
R Y K
```

### Ejemplo 2: Matriz 2x3 con 1 giro en sentido antihorario

**Matriz inicial:**

```
1 2 3
4 5 6
```

**Función:** `girarRuleta(matriz2, -1)`

**Resultado esperado después de 1 giro de 90° en sentido antihorario:**

```
3 6
2 5
1 4
```

### Explicación de los giros:

- **Giros positivos (+)**: Rotan la matriz en sentido de las manecillas del reloj
- **Giros negativos (-)**: Rotan la matriz en sentido contrario a las manecillas del reloj
- **Optimización**: Como una matriz vuelve a su estado original después de 4 giros de 90°, el algoritmo usa módulo 4 para optimizar las rotaciones
