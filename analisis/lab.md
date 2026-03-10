A continuación transcribo **todo el contenido solicitado del documento a formato Markdown (.md)**.
No es una conversión automática; está **reestructurado manualmente respetando secciones, fórmulas y jerarquía** del documento original.

---

# Guía de Laboratorio N° 1

**Algoritmos Numéricos - Ingeniería Civil**

Mg. Mario Hernán López Arbeláez
3 de marzo de 2026

**Tema:**
Teoría del Error, Serie de Taylor y Raíces de Funciones

---

# 1. Introducción

En este laboratorio se desarrollará una aplicación con interfaz gráfica (GUI) que permita estudiar el comportamiento numérico de distintos métodos para la resolución de ecuaciones no lineales, así como el análisis de error y aproximaciones mediante Series de Taylor.

Como primera etapa, cada equipo de estudiantes (máximo tres) deberá construir en MATLAB un conjunto de funciones reutilizables para cada método expuesto en clase.

La intención es separar con claridad:

- La formulación matemática del método.
- La implementación computacional.
- El análisis de resultados (convergencia, divergencia y sensibilidad).

El objetivo **no es únicamente obtener una raíz**, sino analizar el comportamiento frente a distintos escenarios:

- convergencia
- divergencia
- sensibilidad a la semilla inicial (condiciones iniciales)
- efecto de la multiplicidad algebraica

---

# 2. Objetivos

1. Aplicar correctamente las definiciones de error absoluto, relativo y porcentual.
2. Diseñar funciones en MATLAB con entradas y salidas claramente definidas para cada procedimiento numérico.
3. Implementar la aproximación de una función mediante Serie de Taylor y analizar el error de truncamiento.
4. Implementar métodos cerrados y abiertos para el cálculo de raíces.
5. Analizar el comportamiento del método de Punto Fijo (casos de convergencia y divergencia).
6. Estudiar el efecto de raíces con multiplicidad algebraica mayor que uno.
7. Comparar convergencia entre Newton clásico y Newton modificado.

---

# 3. Recordatorio Conceptual

## Conceptos clave

### Errores

Sea:

- (x\_{real}) el valor verdadero
- (x\_{aprox}) una aproximación

**Error absoluto**

[
E_T = |x_{real} - x_{aprox}|
]

**Error relativo**

[
\epsilon_r =
\frac{|x_{real} - x_{aprox}|}{|x_{real}|}
]

(si (x\_{real} \neq 0))

**Error porcentual**

[
\epsilon_p = 100 \epsilon_r
]

---

### Serie de Taylor

Aproximación alrededor de (a) hasta orden (n):

[
f_n(x) =
\sum_{k=0}^{n}
\frac{f^{(k)}(a)}{k!}(x-a)^k
]

---

### Métodos cerrados

Requieren un intervalo ([a,b]) con cambio de signo:

[
f(a)f(b) < 0
]

---

### Métodos abiertos

Requieren una o dos aproximaciones iniciales.

---

### Punto fijo

Transformar

[
f(x)=0
]

en

[
x = g(x)
]

y verificar localmente

[
|g'(x)| < 1
]

para convergencia.

---

### Raíz múltiple

**Newton modificado (multiplicidad conocida)**

[
x_{k+1} = x_k - m \frac{f(x_k)}{f'(x_k)}
]

---

### Método para raíces múltiples de Ralston–Rabinowitz

Se sustituye:

[
u(x) = \frac{f(x)}{f'(x)}
]

en el método de Newton clásico.

---

# 4. Primera Parte: Construcción de Funciones en MATLAB

Antes de desarrollar la GUI, el laboratorio inicia con la implementación modular de funciones para cada método visto en clase.

Cada algoritmo debe quedar encapsulado en un archivo independiente de MATLAB.

---

## Requisito común para todas las funciones de raíces

Cada método debe retornar como mínimo:

- aproximación final **raiz**
- número de iteraciones **iter**
- una **tabla de historial iterativo**
- indicador **converge**

```
converge = 1  si converge
converge = 0  si no converge
```

---

## Criterios de parada obligatorios

Usar ambos:

1. Número máximo de iteraciones

[
i \leq maxIter
]

2. Tolerancia de error

[
\epsilon_x < tol
]

---

# 4.1 Funciones para análisis de error

Implementar funciones para:

- error absoluto
- error relativo
- error porcentual

**Entradas**

- valor real
- valor aproximado

Si el valor real es cero, **justificar el tratamiento del error relativo para evitar división por cero.**

---

# 4.2 Función para Serie de Taylor

La función debe recibir:

- función
- punto de expansión (a)
- punto de evaluación (x)
- orden (n)

Debe retornar:

- polinomio truncado
- aproximación numérica
- error (si aplica)

---

# 4.3 Funciones para métodos de raíces

Implementar una función por cada método:

- Bisección
- Regla falsa
- Punto fijo
- Newton–Raphson
- Secante
- Newton modificado (raíces múltiples)

---

# 4.4 Claridad sobre las formulaciones

Las firmas de función deben ser:

```
Bisección y Regla Falsa
metodo(f, a, b, tol, maxIter)
```

```
Punto Fijo
puntoFijo(g, x0, tol, maxIter)
```

```
Newton-Raphson
newton(f, df, x0, tol, maxIter)
```

```
Secante
secante(f, x0, x1, tol, maxIter)
```

```
Newton modificado
newtonMultiple(f, df, x0, m, tol, maxIter)
```

---

# 5. Planteamiento del Problema

## Contexto de Ingeniería Civil

En ingeniería civil es común que una magnitud física se describa mediante una función continua

[
y(x)
]

en un dominio

[
x \in [a,b]
]

Muchos problemas se reducen a resolver:

[
f(x) = 0
]

[
f'(x) = 0
]

[
f''(x) = 0
]

donde (x) representa una variable de interés del problema.

---

# Contexto sugerido

La función (F(x)) puede representar:

- condición de equilibrio
- condición de servicio
- condición de óptimo estructural
- cambio de concavidad

La formulación debe ser validada con un docente.

---

# Interpretación física

## Caso A — Condición de equilibrio

Resolver

[
F(x)=0
]

Interpretación: posición donde una magnitud se anula.

Condición para métodos cerrados:

```
F continua en [a,b]
F(a)F(b) < 0
```

---

## Caso B — Condición de extremo

Resolver

[
F'(x)=0
]

Interpretación: máximo o mínimo.

Clasificación:

```
F''(x*) > 0  → mínimo
F''(x*) < 0  → máximo
```

---

## Caso C — Punto de inflexión

Resolver

[
F''(x)=0
]

Condición suficiente:

```
F'' cambia de signo alrededor del punto
```

---

# Planificación numérica

El estudiante debe:

1. Definir el dominio ([a,b])
2. Verificar condiciones previas
3. Seleccionar método numérico
4. Definir tolerancia
5. Definir máximo de iteraciones

---

# Modelo de referencia

[
f(x) = x(L^3 - 2Lx^2 + x^3)
]

con

[
L = 6
]

Analizar en el intervalo

```
[0,L]
```

los casos:

```
f(x)=0
f'(x)=0
f''(x)=0
```

---

## Análisis de multiplicidad

En ([0,L]):

- identificar raíces simples
- identificar raíces múltiples
- discutir impacto en convergencia

---

# Función adicional

[
h(x) = (x-2)^2(x+1)
]

Se usa para comparar:

- Newton clásico
- Newton modificado

---

# 6. Requerimientos de la Aplicación

La GUI debe tener pestañas.

---

## Etapa previa obligatoria

- Construcción de funciones
- Pruebas individuales
- Verificación teórica

---

## P1 — Errores y Taylor

Debe permitir:

- ingresar función
- punto (a)
- orden (n)
- punto de evaluación

Comparar:

- valor real
- aproximación

---

## P2 — Método gráfico

Visualización de:

```
f(x)
f'(x)
f''(x)
```

Selección interactiva de intervalo.

---

## P3 — Métodos cerrados

Implementar:

- Bisección
- Regla falsa

Mostrar:

- tabla iterativa
- criterio de parada

Validar:

```
f(a)f(b) < 0
```

---

## P4 — Métodos abiertos

Implementar:

- Newton
- Secante
- Punto fijo
- Newton modificado

Validaciones:

Newton:

```
f'(x) ≠ 0
```

Secante:

```
denominador ≠ 0
```

Newton modificado:

- permitir ingresar multiplicidad

---

# 7. Análisis requerido

El informe debe incluir:

1. Comparación de número de iteraciones entre métodos cerrados y abiertos.
2. Un caso donde Punto Fijo converge.
3. Un caso donde Punto Fijo diverge.
4. Comparación Newton clásico vs Newton modificado usando (h(x)).
5. Discusión del efecto de la multiplicidad.
6. Sensibilidad a la semilla inicial (mínimo dos semillas).

---

# 8. Entregables

1. Informe en **PDF**
2. Carpeta con funciones **MATLAB**
3. Código fuente de la **GUI**
4. Video demostrativo (2–3 minutos)

---

# 9. Criterios de Evaluación

## Dominio conceptual (25%)

- comprensión de (f(x)=0)
- identificación de raíces
- justificación del método

---

## Implementación (25%)

- modularidad
- criterios de parada
- manejo de errores

---

## Análisis numérico (25%)

- uso de errores
- comparación de métodos
- análisis de multiplicidad

---

## Defensa (15%)

- explicación del método
- interpretación física

---

## Coherencia (10%)

- consistencia teoría / código / análisis

---

## Resumen

| Criterio           | Peso |
| ------------------ | ---- |
| Dominio conceptual | 25%  |
| Implementación     | 25%  |
| Análisis del error | 25%  |
| Defensa oral       | 15%  |
| Coherencia         | 10%  |

---

# 10. Ejemplo de código

### Script de ejecución

```matlab
% Definición de la función
f = @(x) (x - 10) * (x - pi)^2;
df = @(x) (x - pi)^2 + 2 * (x - 10) * (x - pi);

x0 = 3.5;
m = 2;
tol = 1e-10;
maxIter = 100;

[raiz, tabla, iter, converge] = newtonMultiple(f, df, x0, m, tol, maxIter);

fprintf('Raíz hallada: %.10f\n', raiz);
disp(tabla);
```

---

### Función Newton modificado

```matlab
function [raiz, tabla, iter, converge] = newtonMultiple(f, df, x0, m, tol, maxIter)

x = x0;
tabla = zeros(maxIter,5);
converge = 0;

for k = 1:maxIter

fx = f(x);
dfx = df(x);

if abs(dfx) < 1e-14
    iter = k;
    raiz = x;
    tabla = tabla(1:k-1,:);
    return
end

xNuevo = x - m*(fx/dfx);

errX = abs(xNuevo-x);
errF = abs(f(xNuevo));

tabla(k,:) = [k,xNuevo,f(xNuevo),errX,errF];

if (errX < tol) || (errF < tol)
    raiz = xNuevo;
    iter = k;
    converge = 1;
    tabla = tabla(1:k,:);
    return
end

x = xNuevo;

end

raiz = x;
iter = maxIter;
tabla = tabla(1:maxIter,:);

end
```

---

# 11. Bibliografía

1. Burden, Faires – _Numerical Analysis_
2. Kincaid & Cheney – _Numerical Analysis_
3. McKenna et al. – OpenSees
4. Mazzoni et al. – OpenSees manual
5. MATLAB documentation `fzero`
6. MATLAB documentation `fsolve`
7. Tuhaise et al. – Digital Twin in Construction
8. Deng et al. – BIM to Digital Twins

---

✅ Si quieres, también puedo darte:

- **la estructura completa del repositorio del laboratorio**
- **la estructura del informe PDF**
- **las firmas exactas de todas las funciones MATLAB**
- **el análisis matemático de la función del laboratorio (raíces, multiplicidad, etc.)** que probablemente **sí te lo van a preguntar en la sustentación**.

---

**Nivel de confianza:** 98%
**Riesgos de error:** posibles pequeños cambios de formato en ecuaciones respecto al PDF original.
**Qué mejoraría precisión:** el PDF fuente editable o confirmar si el docente exige exactamente la misma redacción o solo el contenido.
