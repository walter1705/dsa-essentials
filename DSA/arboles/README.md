# Visualizador de Árbol Binario de Búsqueda

Aplicación JavaFX para visualizar y manipular árboles binarios de búsqueda con todas las operaciones básicas.

## Características

La aplicación implementa y visualiza todos los métodos de la clase `Tree`:

### Operaciones Básicas

1. **Insertar** - Agrega un nuevo elemento al árbol
2. **Buscar** - Busca un elemento específico en el árbol
3. **Eliminar** - Elimina un elemento del árbol (maneja los 3 casos: sin hijos, un hijo, dos hijos)

### Recorridos

4. **InOrder** - Recorrido en orden (Izquierda-Raíz-Derecha)
5. **PreOrder** - Recorrido en preorden (Raíz-Izquierda-Derecha)
6. **PostOrder** - Recorrido en postorden (Izquierda-Derecha-Raíz)

### Operaciones de Consulta

7. **Altura** - Muestra la altura del árbol
8. **Vacío** - Verifica si el árbol está vacío
9. **Tamaño** - Muestra el número de nodos en el árbol
10. **Encontrar Mínimo** - Encuentra el valor mínimo en el árbol
11. **Encontrar Máximo** - Encuentra el valor máximo en el árbol

### Operaciones Adicionales

12. **Limpiar** - Elimina todos los elementos del árbol
13. **Imprimir Árbol** - Muestra el árbol en formato texto con estructura visual

## Interfaz Gráfica

### Componentes Principales

1. **Panel Superior (Header)**

   - Título de la aplicación
   - Indicadores en tiempo real:
     - Tamaño del árbol
     - Altura del árbol
     - Estado de la última operación

2. **Panel Central**

   - Visualización gráfica del árbol
   - Los nodos se dibujan como círculos azules con su valor
   - Las líneas conectan padres e hijos
   - Se actualiza automáticamente después de cada operación

3. **Panel Derecho (Controles)**
   - **Entrada de Datos**: Campo de texto para ingresar números
   - **Operaciones Básicas**: Botones para insertar, buscar y eliminar
   - **Recorridos**: Botones para los tres tipos de recorrido
   - **Otras Operaciones**: Botones para encontrar mínimo/máximo, imprimir y limpiar
   - **Área de Salida**: Muestra los resultados de los recorridos y operaciones

## Cómo Usar

### Ejecutar la Aplicación

```bash
# Compilar y ejecutar con Maven
mvn clean javafx:run
```

### Operaciones Paso a Paso

#### 1. Insertar Elementos

- Ingresa un número en el campo de texto
- Haz clic en el botón **"Insertar"** (verde)
- El árbol se actualizará automáticamente mostrando el nuevo nodo

#### 2. Buscar un Elemento

- Ingresa el número a buscar
- Haz clic en **"Buscar"** (azul)
- El estado mostrará si el elemento fue encontrado o no

#### 3. Eliminar un Elemento

- Ingresa el número a eliminar
- Haz clic en **"Eliminar"** (rojo)
- El árbol se reorganizará automáticamente

#### 4. Ver Recorridos

- Haz clic en cualquier botón de recorrido (morado)
- El resultado aparecerá en el área de salida
- Muestra el orden de visita de los nodos

#### 5. Encontrar Mínimo/Máximo

- Haz clic en **"Mínimo"** o **"Máximo"** (verde azulado)
- El resultado se muestra en el área de salida

#### 6. Imprimir Estructura

- Haz clic en **"Imprimir Árbol"** (naranja)
- Muestra el árbol en formato texto con estructura jerárquica

#### 7. Limpiar el Árbol

- Haz clic en **"Limpiar Árbol"** (gris)
- Elimina todos los nodos del árbol

## Ejemplo de Uso

```
1. Insertar: 50, 30, 70, 20, 40, 60, 80
2. Ver estructura visual en el panel central
3. Hacer InOrder: verás "20 30 40 50 60 70 80"
4. Buscar 40: encontrado ✓
5. Eliminar 30: el árbol se reorganiza automáticamente
6. Encontrar Mínimo: 20
7. Encontrar Máximo: 80
```

## Estructura del Proyecto

```
src/
├── main/
│   ├── java/
│   │   ├── module-info.java
│   │   └── dsa/uniquindio/arboles/
│   │       ├── HelloApplication.java (Aplicación principal)
│   │       ├── arbolBinario/
│   │       │   ├── Node.java (Clase Nodo)
│   │       │   ├── Tree.java (Clase Árbol - Lógica)
│   │       │   └── Main.java
│   │       └── viewController/
│   │           └── TreeViewController.java (Controlador JavaFX)
│   └── resources/
│       └── dsa/uniquindio/arboles/
│           └── tree-view.fxml (Vista FXML)
```

## Tecnologías Utilizadas

- **Java 21**
- **JavaFX 21** - Para la interfaz gráfica
- **Maven** - Gestión de dependencias
- **FXML** - Diseño de interfaz declarativa

## Características Técnicas

### Clase Tree<T>

- Genérica con restricción `Comparable<T>`
- Implementa árbol binario de búsqueda balanceado por operaciones
- Manejo completo de casos de eliminación
- Métodos recursivos optimizados

### Visualización

- Dibuja nodos y conexiones dinámicamente
- Ajusta posiciones automáticamente según el árbol
- Actualización en tiempo real
- Colores diferenciados para mejor comprensión

### Interfaz de Usuario

- Diseño moderno y limpio
- Colores semánticos (verde=insertar, rojo=eliminar, etc.)
- Feedback visual inmediato
- Área de salida para resultados textuales

## Notas Importantes

- Solo acepta números enteros (Integer)
- No permite valores duplicados
- La altura de un árbol vacío es -1
- Las operaciones son case-sensitive en el código
- La visualización se adapta al tamaño del árbol

## Autor

Proyecto desarrollado como parte del curso de Estructuras de Datos - Universidad del Quindío

---

¡Disfruta explorando los árboles binarios de búsqueda! 🌳
