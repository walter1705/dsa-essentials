function [raiz, tabla, iter, converge] = newtonMultiple(f, df, x0, m, tol, maxIter)
% newtonMultiple - Metodo de Newton modificado para raices con multiplicidad.
%   [raiz, tabla, iter, converge] = newtonMultiple(f, df, x0, m, tol, maxIter)
%
%   Entradas:
%       f       - function handle de la funcion
%       df      - function handle de la derivada f'(x)
%       x0      - aproximacion inicial
%       m       - multiplicidad conocida de la raiz
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
    x = x0;

    for k = 1:maxIter
        fx = f(x);
        dfx = df(x);

        if abs(dfx) < 1e-14
            warning('newtonMultiple:derivadaCero', ...
                'La derivada es practicamente cero en x = %.10f. Se detiene.', x);
            raiz = x;
            iter = k;
            tabla = tabla(1:k-1, :);
            return
        end

        xNuevo = x - m * (fx / dfx);

        errX = abs(xNuevo - x);
        errF = abs(f(xNuevo));

        tabla(k, :) = [k, xNuevo, f(xNuevo), errX, errF];

        if (errX < tol) || (errF < tol)
            raiz = xNuevo;
            iter = k;
            converge = 1;
            tabla = tabla(1:k, :);
            return
        end

        x = xNuevo;
    end

    raiz = x;
    iter = maxIter;
    tabla = tabla(1:maxIter, :);
end
