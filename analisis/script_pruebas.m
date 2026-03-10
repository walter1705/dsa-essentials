%% script_pruebas.m - Pruebas de todas las funciones del Laboratorio 1
% Ejecutar este script para validar cada metodo implementado.

clc; clear; close all;
addpath('funciones');

fprintf('========================================\n');
fprintf(' PRUEBAS - LABORATORIO 1\n');
fprintf('========================================\n\n');

%% 1. Funciones de error
fprintf('--- 1. Funciones de Error ---\n');
xReal = pi;
xAprox = 3.14;

fprintf('Valor real: %.10f\n', xReal);
fprintf('Valor aproximado: %.10f\n', xAprox);
fprintf('Error absoluto:    %.10f\n', errorAbsoluto(xReal, xAprox));
fprintf('Error relativo:    %.10e\n', errorRelativo(xReal, xAprox));
fprintf('Error porcentual:  %.6f %%\n\n', errorPorcentual(xReal, xAprox));

%% 2. Serie de Taylor
fprintf('--- 2. Serie de Taylor ---\n');
syms x;
fSym = exp(x);
aVal = 0;
xEval = 1;
nOrden = 10;

[pol, aprox, err] = serieTaylor(fSym, aVal, xEval, nOrden);
fprintf('f(x) = exp(x), a = %d, x = %d, orden = %d\n', aVal, xEval, nOrden);
fprintf('Polinomio: %s\n', char(pol));
fprintf('Aproximacion: %.10f\n', aprox);
fprintf('Valor real:   %.10f\n', exp(xEval));
fprintf('Error:        %.2e\n\n', err);

%% 3. Funcion del laboratorio: f(x) = x(L^3 - 2Lx^2 + x^3), L = 6
fprintf('--- 3. Funcion del Laboratorio ---\n');
L = 6;
f = @(x) x .* (L^3 - 2*L*x.^2 + x.^3);
df = @(x) L^3 - 6*L*x.^2 + 4*x.^3;
d2f = @(x) -12*L*x + 12*x.^2;

fprintf('f(x) = x(216 - 12x^2 + x^3)\n');
fprintf('f''(x) = 216 - 72x^2 + 4x^3\n');
fprintf('f''''(x) = -72x + 12x^2\n\n');

%% 3.1 Biseccion - raiz de f(x) en [4, 6]
fprintf('--- 3.1 Biseccion: f(x)=0 en [4, 6] ---\n');
[raiz, tabla, iter, conv] = biseccion(f, 4, 6, 1e-6, 100);
fprintf('Raiz: %.10f | Iteraciones: %d | Converge: %d\n', raiz, iter, conv);
fprintf('Tabla (ultimas 3 filas):\n');
if size(tabla, 1) >= 3
    disp(tabla(end-2:end, :));
else
    disp(tabla);
end
fprintf('\n');

%% 3.2 Regla Falsa - raiz de f(x) en [4, 6]
fprintf('--- 3.2 Regla Falsa: f(x)=0 en [4, 6] ---\n');
[raiz, tabla, iter, conv] = reglaFalsa(f, 4, 6, 1e-6, 100);
fprintf('Raiz: %.10f | Iteraciones: %d | Converge: %d\n', raiz, iter, conv);
fprintf('\n');

%% 3.3 Newton-Raphson - raiz de f'(x)=0
fprintf('--- 3.3 Newton-Raphson: f''(x)=0, x0=5 ---\n');
[raiz, tabla, iter, conv] = newton(df, d2f, 5, 1e-10, 100);
fprintf('Raiz (punto critico): %.10f | Iteraciones: %d | Converge: %d\n', raiz, iter, conv);
fprintf('\n');

%% 3.4 Secante - raiz de f(x) en cercania de [4, 5.5]
fprintf('--- 3.4 Secante: f(x)=0, x0=4, x1=5.5 ---\n');
[raiz, tabla, iter, conv] = secante(f, 4, 5.5, 1e-10, 100);
fprintf('Raiz: %.10f | Iteraciones: %d | Converge: %d\n', raiz, iter, conv);
fprintf('\n');

%% 4. Punto Fijo - caso convergente y divergente
fprintf('--- 4.1 Punto Fijo (Convergente) ---\n');
% Para f(x) = x^2 - 2 = 0, g(x) = sqrt(2 + x - x) = sqrt(2) ... mejor:
% f(x) = x - cos(x) = 0 => g(x) = cos(x)
gConv = @(x) cos(x);
[raiz, tabla, iter, conv] = puntoFijo(gConv, 0.5, 1e-8, 100);
fprintf('g(x) = cos(x), x0 = 0.5\n');
fprintf('Raiz: %.10f | Iteraciones: %d | Converge: %d\n', raiz, iter, conv);
fprintf('\n');

fprintf('--- 4.2 Punto Fijo (Divergente) ---\n');
gDiv = @(x) 2*x - x.^2 + 3;
[raiz, tabla, iter, conv] = puntoFijo(gDiv, 5, 1e-8, 50);
fprintf('g(x) = 2x - x^2 + 3, x0 = 5\n');
fprintf('Raiz: %.10f | Iteraciones: %d | Converge: %d\n', raiz, iter, conv);
fprintf('\n');

%% 5. Comparacion Newton clasico vs Newton modificado
fprintf('--- 5. Comparacion Newton clasico vs Modificado ---\n');
fprintf('h(x) = (x-2)^2*(x+1), raiz x=2 con multiplicidad 2\n\n');
h = @(x) (x - 2).^2 .* (x + 1);
dh = @(x) 2*(x - 2).*(x + 1) + (x - 2).^2;

x0Test = 3.5;
tol = 1e-10;
maxIt = 100;

fprintf('--- 5.1 Newton Clasico (m=1) ---\n');
[raiz1, tabla1, iter1, conv1] = newton(h, dh, x0Test, tol, maxIt);
fprintf('Raiz: %.10f | Iteraciones: %d | Converge: %d\n', raiz1, iter1, conv1);

fprintf('\n--- 5.2 Newton Modificado (m=2) ---\n');
[raiz2, tabla2, iter2, conv2] = newtonMultiple(h, dh, x0Test, 2, tol, maxIt);
fprintf('Raiz: %.10f | Iteraciones: %d | Converge: %d\n', raiz2, iter2, conv2);

fprintf('\nConclusion: Newton modificado (m=2) converge en %d iteraciones vs %d del clasico.\n', iter2, iter1);
fprintf('Esto demuestra el efecto de la multiplicidad en la convergencia.\n\n');

%% 6. Sensibilidad a semilla inicial
fprintf('--- 6. Sensibilidad a la semilla inicial ---\n');
fprintf('Newton-Raphson en h(x), semilla x0 = 3.5 y x0 = 0.5\n\n');

[raizA, ~, iterA, convA] = newton(h, dh, 3.5, tol, maxIt);
fprintf('x0 = 3.5 -> Raiz: %.10f | Iter: %d | Converge: %d\n', raizA, iterA, convA);

[raizB, ~, iterB, convB] = newton(h, dh, 0.5, tol, maxIt);
fprintf('x0 = 0.5 -> Raiz: %.10f | Iter: %d | Converge: %d\n', raizB, iterB, convB);

fprintf('\n========================================\n');
fprintf(' PRUEBAS COMPLETADAS\n');
fprintf('========================================\n');
