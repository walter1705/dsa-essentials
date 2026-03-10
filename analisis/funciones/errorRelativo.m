function eps_r = errorRelativo(xReal, xAprox)
% errorRelativo - Calcula el error relativo entre un valor real y su aproximacion.
%   eps_r = errorRelativo(xReal, xAprox)
%
%   Entradas:
%       xReal  - valor verdadero
%       xAprox - valor aproximado
%
%   Salida:
%       eps_r - error relativo |xReal - xAprox| / |xReal|
%
%   Nota: Si xReal == 0, se usa |xAprox| como denominador para evitar
%         division por cero. En ese caso se emite un warning.

    if xReal == 0
        if xAprox == 0
            eps_r = 0;
        else
            warning('errorRelativo:divCero', ...
                'xReal es cero. Se usa |xAprox| como denominador.');
            eps_r = abs(xReal - xAprox) / abs(xAprox);
        end
    else
        eps_r = abs(xReal - xAprox) / abs(xReal);
    end
end
