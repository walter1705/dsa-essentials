function E_T = errorAbsoluto(xReal, xAprox)
% errorAbsoluto - Calcula el error absoluto entre un valor real y su aproximacion.
%   E_T = errorAbsoluto(xReal, xAprox)
%
%   Entradas:
%       xReal  - valor verdadero
%       xAprox - valor aproximado
%
%   Salida:
%       E_T - error absoluto |xReal - xAprox|

    E_T = abs(xReal - xAprox);
end
