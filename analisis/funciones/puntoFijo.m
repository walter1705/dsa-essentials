function [raiz, tabla, iter, converge] = puntoFijo(g, x0, tol, maxIter)
% puntoFijo - Metodo de punto fijo para resolver x = g(x).
%   [raiz, tabla, iter, converge] = puntoFijo(g, x0, tol, maxIter)
%
%   Entradas:
%       g       - function handle de la funcion de iteracion g(x)
%       x0      - aproximacion inicial
%       tol     - tolerancia del error
%       maxIter - numero maximo de iteraciones
%
%   Salidas:
%       raiz     - aproximacion final de la raiz
%       tabla    - historial iterativo [k, x_k, g(x_k), error]
%       iter     - numero de iteraciones realizadas
%       converge - 1 si converge, 0 si no

    converge = 0;
    tabla = zeros(maxIter, 4);
    x = x0;

    for k = 1:maxIter
        xNuevo = g(x);
        errX = abs(xNuevo - x);

        tabla(k, :) = [k, x, xNuevo, errX];

        if errX < tol
            raiz = xNuevo;
            iter = k;
            converge = 1;
            tabla = tabla(1:k, :);
            return
        end

        % Detectar divergencia (valor muy grande)
        if abs(xNuevo) > 1e15 || isnan(xNuevo) || isinf(xNuevo)
            raiz = xNuevo;
            iter = k;
            converge = 0;
            tabla = tabla(1:k, :);
            return
        end

        x = xNuevo;
    end

    raiz = x;
    iter = maxIter;
    tabla = tabla(1:maxIter, :);
end
