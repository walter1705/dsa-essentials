function [polinomio, aprox, err] = serieTaylor(f, a, xEval, n)
% serieTaylor - Aproxima una funcion mediante su Serie de Taylor truncada.
%   [polinomio, aprox, err] = serieTaylor(f, a, xEval, n)
%
%   Entradas:
%       f     - funcion simbolica en terminos de la variable simbolica x
%       a     - punto de expansion
%       xEval - punto donde se evalua la aproximacion
%       n     - orden maximo de la serie
%
%   Salidas:
%       polinomio - expresion simbolica del polinomio de Taylor truncado
%       aprox     - valor numerico de la aproximacion en xEval
%       err       - error absoluto respecto al valor real f(xEval)

    syms x;

    % Construir el polinomio de Taylor
    polinomio = sym(0);
    derivada = f;

    for k = 0:n
        coef = subs(derivada, x, a) / factorial(k);
        polinomio = polinomio + coef * (x - a)^k;

        if k < n
            derivada = diff(derivada, x);
        end
    end

    % Simplificar el polinomio
    polinomio = simplify(polinomio);

    % Evaluar numericamente
    aprox = double(subs(polinomio, x, xEval));

    % Calcular error absoluto
    valorReal = double(subs(f, x, xEval));
    err = abs(valorReal - aprox);
end
