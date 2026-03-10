# Documentacion del Laboratorio 1 — Algoritmos Numericos

## Descripcion general

Este proyecto implementa en MATLAB el Laboratorio 1 del curso de Algoritmos Numericos para Ingenieria Civil. Incluye:

- **10 funciones modulares** (cada una en su propio archivo `.m`)
- **1 GUI con 4 pestanas** para interactuar graficamente con todos los metodos
- **1 script de pruebas** que valida todas las funciones con los casos del laboratorio

---

## Estructura de archivos

```
Ejercicios/a/
├── funciones/
│   ├── errorAbsoluto.m      Funcion de error absoluto
│   ├── errorRelativo.m      Funcion de error relativo
│   ├── errorPorcentual.m    Funcion de error porcentual
│   ├── serieTaylor.m        Aproximacion por Serie de Taylor
│   ├── biseccion.m          Metodo de biseccion
│   ├── reglaFalsa.m         Metodo de regla falsa (falsa posicion)
│   ├── puntoFijo.m          Metodo de punto fijo
│   ├── newton.m             Metodo de Newton-Raphson
│   ├── secante.m            Metodo de la secante
│   └── newtonMultiple.m     Newton modificado para raices multiples
├── guiLab1.m                GUI principal con 4 pestanas
├── script_pruebas.m         Script de validacion de todas las funciones
├── lab.md                   Guia del laboratorio (transcripcion)
└── Laboratorio_1_20261.pdf  PDF original del laboratorio
```

---

## Funciones implementadas

### 1. Funciones de error

Estas tres funciones calculan las metricas de error definidas en la guia.

#### `errorAbsoluto(xReal, xAprox)`

**Archivo:** `funciones/errorAbsoluto.m`

**Formula:**

```
E_T = |xReal - xAprox|
```

**Entradas:**

- `xReal` — valor verdadero
- `xAprox` — valor aproximado

**Salida:**

- `E_T` — error absoluto

---

#### `errorRelativo(xReal, xAprox)`

**Archivo:** `funciones/errorRelativo.m`

**Formula:**

```
eps_r = |xReal - xAprox| / |xReal|
```

**Entradas:**

- `xReal` — valor verdadero
- `xAprox` — valor aproximado

**Salida:**

- `eps_r` — error relativo

**Manejo de division por cero:** Si `xReal == 0` y `xAprox ~= 0`, se usa `|xAprox|` como denominador y se emite un `warning`. Si ambos son cero, retorna 0.

---

#### `errorPorcentual(xReal, xAprox)`

**Archivo:** `funciones/errorPorcentual.m`

**Formula:**

```
eps_p = 100 * errorRelativo(xReal, xAprox)
```

Reutiliza directamente la funcion `errorRelativo`.

---

### 2. Serie de Taylor

#### `serieTaylor(f, a, xEval, n)`

**Archivo:** `funciones/serieTaylor.m`

**Formula:**

```
f_n(x) = sum_{k=0}^{n} f^(k)(a) / k! * (x - a)^k
```

**Entradas:**

- `f` — funcion simbolica (variable `x` de tipo `sym`)
- `a` — punto de expansion
- `xEval` — punto donde se evalua la aproximacion
- `n` — orden maximo del polinomio

**Salidas:**

- `polinomio` — expresion simbolica del polinomio de Taylor truncado
- `aprox` — valor numerico de la aproximacion evaluada en `xEval`
- `err` — error absoluto respecto a `f(xEval)` real

**Implementacion:**

- Usa el Symbolic Math Toolbox de MATLAB (`sym`, `diff`, `subs`, `double`)
- Calcula las derivadas sucesivas de `f` con `diff`
- Evalua cada derivada en el punto `a` y construye la sumatoria
- Simplifica el polinomio con `simplify`

---

### 3. Metodos cerrados (requieren intervalo con cambio de signo)

Ambos metodos requieren que `f(a) * f(b) < 0`. Si no se cumple, emiten un `warning` y retornan `NaN`.

#### `biseccion(f, a, b, tol, maxIter)`

**Archivo:** `funciones/biseccion.m`

**Formula del punto medio:**

```
c = (a + b) / 2
```

**Regla de actualizacion:**

- Si `f(a) * f(c) < 0` entonces `b = c`
- Si no, `a = c`

**Entradas:**

- `f` — function handle `@(x) ...`
- `a, b` — extremos del intervalo
- `tol` — tolerancia del error
- `maxIter` — numero maximo de iteraciones

**Salidas:**

- `raiz` — aproximacion final
- `tabla` — matriz con columnas `[k, a, b, c, f(c), error]`
- `iter` — numero de iteraciones realizadas
- `converge` — `1` si convergio, `0` si no

**Criterios de parada:**

1. `error < tol`
2. `|f(c)| < tol`
3. `k >= maxIter`

---

#### `reglaFalsa(f, a, b, tol, maxIter)`

**Archivo:** `funciones/reglaFalsa.m`

**Formula de interpolacion lineal:**

```
c = b - f(b) * (b - a) / (f(b) - f(a))
```

Misma interfaz que biseccion. La diferencia es que el punto `c` se calcula por interpolacion lineal entre `(a, f(a))` y `(b, f(b))` en lugar del punto medio.

**Salidas:** identicas a biseccion — `[raiz, tabla, iter, converge]`, tabla con columnas `[k, a, b, c, f(c), error]`.

---

### 4. Metodos abiertos (requieren aproximaciones iniciales)

Cada metodo retorna `[raiz, tabla, iter, converge]`.

#### `puntoFijo(g, x0, tol, maxIter)`

**Archivo:** `funciones/puntoFijo.m`

**Formula iterativa:**

```
x_{k+1} = g(x_k)
```

**Entradas:**

- `g` — function handle de la funcion de iteracion `g(x)` (reescritura de `f(x)=0` como `x=g(x)`)
- `x0` — aproximacion inicial
- `tol, maxIter` — criterios de parada

**Tabla:** columnas `[k, x_k, g(x_k), error]`

**Deteccion de divergencia:** Si `|x_{k+1}| > 1e15` o el valor es `NaN`/`Inf`, se detiene inmediatamente con `converge = 0`.

**Condicion teorica de convergencia:** `|g'(x)| < 1` en la vecindad de la raiz. La funcion no verifica esto automaticamente — es responsabilidad del usuario elegir un `g(x)` adecuado.

---

#### `newton(f, df, x0, tol, maxIter)`

**Archivo:** `funciones/newton.m`

**Formula de Newton-Raphson:**

```
x_{k+1} = x_k - f(x_k) / f'(x_k)
```

**Entradas:**

- `f` — function handle de la funcion
- `df` — function handle de la derivada `f'(x)`
- `x0` — aproximacion inicial
- `tol, maxIter` — criterios de parada

**Tabla:** columnas `[k, x_k, f(x_k), errX, errF]`

**Validacion:** Si `|f'(x_k)| < 1e-14`, emite un warning y se detiene para evitar division por cero.

---

#### `secante(f, x0, x1, tol, maxIter)`

**Archivo:** `funciones/secante.m`

**Formula de la secante:**

```
x_{k+1} = x_k - f(x_k) * (x_k - x_{k-1}) / (f(x_k) - f(x_{k-1}))
```

**Entradas:**

- `f` — function handle
- `x0, x1` — dos aproximaciones iniciales
- `tol, maxIter` — criterios de parada

**Tabla:** columnas `[k, x_k, f(x_k), errX, errF]`

**Validacion:** Si el denominador `f(x_k) - f(x_{k-1})` es practicamente cero, emite warning y se detiene.

---

#### `newtonMultiple(f, df, x0, m, tol, maxIter)`

**Archivo:** `funciones/newtonMultiple.m`

**Formula de Newton modificado:**

```
x_{k+1} = x_k - m * f(x_k) / f'(x_k)
```

Donde `m` es la multiplicidad conocida de la raiz. Cuando `m = 1`, se reduce al Newton clasico. Cuando la raiz tiene multiplicidad `m > 1`, este metodo restaura la convergencia cuadratica que Newton clasico pierde.

**Entradas:**

- `f` — function handle
- `df` — function handle de la derivada
- `x0` — aproximacion inicial
- `m` — multiplicidad de la raiz
- `tol, maxIter` — criterios de parada

**Tabla:** columnas `[k, x_k, f(x_k), errX, errF]`

---

## GUI — `guiLab1.m`

La interfaz grafica se construye con `figure`, `uitabgroup` y `uitab` de MATLAB. Al ejecutar `guiLab1` se abre una ventana con 4 pestanas.

La GUI agrega automaticamente la carpeta `funciones/` al path de MATLAB con `addpath`.

### Pestana 1 — Errores y Serie de Taylor

**Campos de entrada:**

- `f(x)`: funcion en notacion simbolica (ej: `exp(x)`, `sin(x)`, `x^3 - 2*x`)
- `Punto a`: centro de la expansion de Taylor
- `Punto x`: punto donde se evalua la aproximacion
- `Orden n`: grado maximo del polinomio

**Al presionar "Calcular":**

1. Convierte la funcion a simbolica con `str2sym`
2. Calcula la serie de Taylor para cada orden de 0 a n
3. Llena una tabla con columnas: `[Orden, Aprox, Err Abs, Err Rel, Err %]`
4. Muestra el polinomio, el valor real, la aproximacion y el error
5. Grafica la funcion original superpuesta con las aproximaciones de Taylor de ordenes seleccionados (orden 1, orden medio, orden n)

---

### Pestana 2 — Metodo Grafico

**Campos de entrada:**

- `f(x)`: funcion (usa notacion con punto para vectorizacion, ej: `x.^2`)
- `a, b`: extremos del intervalo a graficar

**Al presionar "Graficar":**

1. Calcula simbolicamente `f'(x)` y `f''(x)` con `diff`
2. Convierte a function handles con `matlabFunction`
3. Grafica las tres curvas `f(x)`, `f'(x)`, `f''(x)` superpuestas
4. Dibuja la linea `y = 0` para identificar visualmente las raices
5. Incluye leyenda identificando cada curva

Esto permite al usuario identificar visualmente:

- Raices de f(x) (donde cruza y=0)
- Puntos criticos (donde f'(x) cruza y=0)
- Puntos de inflexion (donde f''(x) cruza y=0)

---

### Pestana 3 — Metodos Cerrados

**Campos de entrada:**

- `f(x)`: funcion (notacion con punto)
- `a, b`: extremos del intervalo
- `Tol`: tolerancia (ej: `1e-6`)
- `MaxIter`: numero maximo de iteraciones (ej: `100`)
- Selector: `Biseccion` o `Regla Falsa`

**Validacion:** Antes de calcular, verifica que `f(a) * f(b) < 0`. Si no se cumple, muestra un dialogo de error.

**Al presionar "Calcular":**

1. Ejecuta el metodo seleccionado
2. Muestra la tabla iterativa completa con `uitable`
3. Muestra el resultado: raiz, iteraciones, convergencia
4. Grafica `f(x)` en el intervalo con la raiz marcada como punto rojo

---

### Pestana 4 — Metodos Abiertos

**Campos de entrada:**

- `f(x)`: funcion
- `f'(x)`: derivada (requerida para Newton y Newton modificado)
- `g(x)`: funcion de iteracion (requerida para Punto Fijo)
- `x0`: aproximacion inicial
- `x1`: segunda aproximacion (solo para Secante)
- `m`: multiplicidad (solo para Newton Modificado)
- `Tol, MaxIter`: criterios de parada
- Selector: `Newton-Raphson`, `Secante`, `Punto Fijo`, `Newton Modificado`

**Al presionar "Calcular":**

1. Segun el metodo seleccionado, toma los campos relevantes
2. Ejecuta la funcion correspondiente
3. Ajusta los nombres de columna de la tabla al metodo usado
4. Muestra resultado y tabla iterativa
5. Grafica:
   - Para Punto Fijo: `g(x)` y la linea `y = x` (la raiz es la interseccion)
   - Para los demas: `f(x)` con la raiz marcada

---

## Script de pruebas — `script_pruebas.m`

Este script valida todas las funciones con los casos especificos del laboratorio. Se ejecuta con `run('script_pruebas.m')` o directamente desde el editor de MATLAB.

### Pruebas incluidas:

#### 1. Funciones de error

- Compara `pi` vs `3.14`
- Muestra error absoluto, relativo y porcentual

#### 2. Serie de Taylor

- `f(x) = exp(x)`, expansion en `a = 0`, evaluacion en `x = 1`, orden 10
- Compara la aproximacion con `e^1 = 2.71828...`

#### 3. Funcion del laboratorio

```
f(x) = x(L^3 - 2Lx^2 + x^3),  L = 6
     = x(216 - 12x^2 + x^3)

f'(x) = 216 - 72x^2 + 4x^3

f''(x) = -72x + 12x^2
```

Pruebas realizadas:

- **Biseccion** en `f(x) = 0` en `[4, 6]`
- **Regla Falsa** en `f(x) = 0` en `[4, 6]`
- **Newton-Raphson** en `f'(x) = 0` con `x0 = 5` (busca punto critico)
- **Secante** en `f(x) = 0` con `x0 = 4, x1 = 5.5`

#### 4. Punto Fijo — convergencia y divergencia

- **Convergente:** `g(x) = cos(x)`, `x0 = 0.5` — converge a la raiz de `x = cos(x)` (~0.7390851332)
- **Divergente:** `g(x) = 2x - x^2 + 3`, `x0 = 5` — diverge (|g'(x)| > 1)

#### 5. Comparacion Newton clasico vs Newton modificado

Funcion de prueba:

```
h(x) = (x - 2)^2 * (x + 1)
```

La raiz `x = 2` tiene multiplicidad 2.

- **Newton clasico (m=1):** convergencia lineal (muchas iteraciones)
- **Newton modificado (m=2):** convergencia cuadratica restaurada (pocas iteraciones)

Esto demuestra el efecto de la multiplicidad algebraica en la velocidad de convergencia.

#### 6. Sensibilidad a la semilla inicial

- Newton-Raphson sobre `h(x)` con `x0 = 3.5` — converge a `x = 2`
- Newton-Raphson sobre `h(x)` con `x0 = 0.5` — puede converger a `x = -1` (raiz simple diferente)

Muestra como la eleccion de la semilla afecta a cual raiz converge el metodo.

---

## Como ejecutar

1. Abrir MATLAB
2. Navegar a la carpeta del proyecto (`Ejercicios/a/`)
3. **Validar funciones:** ejecutar `script_pruebas` en la consola
4. **Abrir la GUI:** ejecutar `guiLab1` en la consola

---

## Requisitos

- MATLAB (probado con R2020a o superior)
- Symbolic Math Toolbox (para `serieTaylor.m` y la pestana de Taylor/Metodo Grafico en la GUI)

---

## Decisiones de diseno

1. **Modularidad:** Cada metodo en su propio archivo `.m` segun lo exige la guia. Pueden usarse independientemente fuera de la GUI.

2. **Retorno uniforme:** Todas las funciones de raices retornan `[raiz, tabla, iter, converge]` con el mismo significado.

3. **Doble criterio de parada:** Todas las funciones usan tanto `error < tol` como `|f(x)| < tol` para determinar convergencia, mas el limite de `maxIter`.

4. **Proteccion contra errores:**
   - Division por cero en derivadas (Newton, Secante)
   - Division por cero en error relativo
   - Falta de cambio de signo (Biseccion, Regla Falsa)
   - Divergencia en Punto Fijo (valores > 1e15, NaN, Inf)

5. **GUI con `uitabgroup`:** Permite navegar entre metodos con pestanas. Cada pestana es independiente y contiene sus propios controles, tabla y grafica.

6. **Notacion vectorizada:** Las funciones se ingresan en la GUI con notacion de punto (`.^`, `.*`) para compatibilidad con vectores de MATLAB.
