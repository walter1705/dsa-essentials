function eps_p = errorPorcentual(xReal, xAprox)
% errorPorcentual - Calcula el error porcentual entre un valor real y su aproximacion.
%   eps_p = errorPorcentual(xReal, xAprox)
%
%   Entradas:
%       xReal  - valor verdadero
%       xAprox - valor aproximado
%
%   Salida:
%       eps_p - error porcentual (100 * error relativo)

    eps_p = 100 * errorRelativo(xReal, xAprox);
end
