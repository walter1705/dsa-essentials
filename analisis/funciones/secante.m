function [raiz, tabla, iter, converge] = secante(f, x0, x1, tol, maxIter)
% secante - Metodo de la secante para encontrar raices de f(x)=0.
%   [raiz, tabla, iter, converge] = secante(f, x0, x1, tol, maxIter)
%
%   Entradas:
%       f       - function handle de la funcion
%       x0, x1  - dos aproximaciones iniciales
%       tol     - tolerancia del error
%       maxIter - numero maximo de iteraciones
%
%   Salidas:
%       raiz     - aproximacion final de la raiz
%       tabla    - historial iterativo [k, x_k, f(x_k), errX, errF]
%       iter     - numero de iteraciones realizadas
%       converge - 1 si converge, 0 si no

    converge = 0;
    tabla = zeros(maxIter, 5);
    xPrev = x0;
    xCurr = x1;

    for k = 1:maxIter
        fPrev = f(xPrev);
        fCurr = f(xCurr);

        denom = fCurr - fPrev;
        if abs(denom) < 1e-14
            warning('secante:denominadorCero', ...
                'Denominador practicamente cero en iteracion %d. Se detiene.', k);
            raiz = xCurr;
            iter = k;
            tabla = tabla(1:k-1, :);
            return
        end

        xNuevo = xCurr - fCurr * (xCurr - xPrev) / denom;

        errX = abs(xNuevo - xCurr);
        errF = abs(f(xNuevo));

        tabla(k, :) = [k, xNuevo, f(xNuevo), errX, errF];

        if (errX < tol) || (errF < tol)
            raiz = xNuevo;
            iter = k;
            converge = 1;
            tabla = tabla(1:k, :);
            return
        end

        xPrev = xCurr;
        xCurr = xNuevo;
    end

    raiz = xCurr;
    iter = maxIter;
    tabla = tabla(1:maxIter, :);
end
