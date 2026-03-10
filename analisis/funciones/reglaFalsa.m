function [raiz, tabla, iter, converge] = reglaFalsa(f, a, b, tol, maxIter)
% reglaFalsa - Metodo de regla falsa (falsa posicion) para raices de f(x)=0.
%   [raiz, tabla, iter, converge] = reglaFalsa(f, a, b, tol, maxIter)
%
%   Entradas:
%       f       - function handle de la funcion
%       a, b    - extremos del intervalo (debe cumplirse f(a)*f(b) < 0)
%       tol     - tolerancia del error
%       maxIter - numero maximo de iteraciones
%
%   Salidas:
%       raiz     - aproximacion final de la raiz
%       tabla    - historial iterativo [k, a, b, c, f(c), error]
%       iter     - numero de iteraciones realizadas
%       converge - 1 si converge, 0 si no

    converge = 0;
    tabla = zeros(maxIter, 6);

    fa = f(a);
    fb = f(b);

    if fa * fb > 0
        warning('reglaFalsa:sinCambioSigno', ...
            'f(a) y f(b) tienen el mismo signo. No se garantiza raiz en [a,b].');
        raiz = NaN;
        tabla = [];
        iter = 0;
        return
    end

    cAnterior = a;

    for k = 1:maxIter
        c = b - fb * (b - a) / (fb - fa);
        fc = f(c);

        if k == 1
            errX = abs(c - a);
        else
            errX = abs(c - cAnterior);
        end

        tabla(k, :) = [k, a, b, c, fc, errX];

        if (errX < tol) || (abs(fc) < tol)
            raiz = c;
            iter = k;
            converge = 1;
            tabla = tabla(1:k, :);
            return
        end

        if fa * fc < 0
            b = c;
            fb = fc;
        else
            a = c;
            fa = fc;
        end

        cAnterior = c;
    end

    raiz = b - fb * (b - a) / (fb - fa);
    iter = maxIter;
    tabla = tabla(1:maxIter, :);
end
