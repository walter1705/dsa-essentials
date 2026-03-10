function guiLab1()
% guiLab1 - GUI principal del Laboratorio 1 de Algoritmos Numericos.
%   Contiene 4 pestanas:
%   P1: Errores y Serie de Taylor
%   P2: Metodo Grafico
%   P3: Metodos Cerrados (Biseccion, Regla Falsa)
%   P4: Metodos Abiertos (Newton, Secante, Punto Fijo, Newton Modificado)

    % Agregar carpeta de funciones al path
    addpath(fullfile(fileparts(mfilename('fullpath')), 'funciones'));

    % --- Ventana principal ---
    fig = figure('Name', 'Laboratorio 1 - Algoritmos Numericos', ...
        'NumberTitle', 'off', ...
        'Position', [100, 50, 1100, 700], ...
        'MenuBar', 'none', ...
        'Resize', 'on');

    tabGroup = uitabgroup(fig, 'Units', 'normalized', 'Position', [0 0 1 1]);

    % === PESTANA 1: Errores y Taylor ===
    crearPestana1(tabGroup);

    % === PESTANA 2: Metodo Grafico ===
    crearPestana2(tabGroup);

    % === PESTANA 3: Metodos Cerrados ===
    crearPestana3(tabGroup);

    % === PESTANA 4: Metodos Abiertos ===
    crearPestana4(tabGroup);

end

%% =========================================================================
%  PESTANA 1 - ERRORES Y SERIE DE TAYLOR
%  =========================================================================
function crearPestana1(tabGroup)
    tab1 = uitab(tabGroup, 'Title', 'P1: Errores y Taylor');

    % --- Panel de entrada ---
    panelIn = uipanel(tab1, 'Title', 'Parametros', ...
        'Units', 'normalized', 'Position', [0.01 0.65 0.48 0.33]);

    uicontrol(panelIn, 'Style', 'text', 'String', 'f(x) =', ...
        'Units', 'normalized', 'Position', [0.02 0.78 0.15 0.15], ...
        'HorizontalAlignment', 'right');
    edFuncion = uicontrol(panelIn, 'Style', 'edit', ...
        'String', 'exp(x)', ...
        'Units', 'normalized', 'Position', [0.18 0.78 0.78 0.18]);

    uicontrol(panelIn, 'Style', 'text', 'String', 'Punto a =', ...
        'Units', 'normalized', 'Position', [0.02 0.55 0.15 0.15], ...
        'HorizontalAlignment', 'right');
    edA = uicontrol(panelIn, 'Style', 'edit', 'String', '0', ...
        'Units', 'normalized', 'Position', [0.18 0.55 0.25 0.18]);

    uicontrol(panelIn, 'Style', 'text', 'String', 'Punto x =', ...
        'Units', 'normalized', 'Position', [0.50 0.55 0.15 0.15], ...
        'HorizontalAlignment', 'right');
    edX = uicontrol(panelIn, 'Style', 'edit', 'String', '1', ...
        'Units', 'normalized', 'Position', [0.66 0.55 0.25 0.18]);

    uicontrol(panelIn, 'Style', 'text', 'String', 'Orden n =', ...
        'Units', 'normalized', 'Position', [0.02 0.30 0.15 0.15], ...
        'HorizontalAlignment', 'right');
    edN = uicontrol(panelIn, 'Style', 'edit', 'String', '5', ...
        'Units', 'normalized', 'Position', [0.18 0.30 0.25 0.18]);

    uicontrol(panelIn, 'Style', 'pushbutton', 'String', 'Calcular', ...
        'Units', 'normalized', 'Position', [0.55 0.05 0.40 0.22], ...
        'FontWeight', 'bold', ...
        'Callback', @calcularTaylor);

    % --- Panel de resultados ---
    panelRes = uipanel(tab1, 'Title', 'Resultados', ...
        'Units', 'normalized', 'Position', [0.50 0.65 0.49 0.33]);

    txtResultado = uicontrol(panelRes, 'Style', 'text', ...
        'String', '', ...
        'Units', 'normalized', 'Position', [0.02 0.02 0.96 0.94], ...
        'HorizontalAlignment', 'left', 'FontSize', 10);

    % --- Tabla de errores por orden ---
    tablaErrores = uitable(tab1, 'Units', 'normalized', ...
        'Position', [0.01 0.35 0.48 0.28], ...
        'ColumnName', {'Orden', 'Aprox', 'Err Abs', 'Err Rel', 'Err %'}, ...
        'ColumnWidth', {50, 90, 90, 90, 90});

    % --- Grafica ---
    axTaylor = axes('Parent', tab1, 'Units', 'normalized', ...
        'Position', [0.55 0.08 0.42 0.53]);

    function calcularTaylor(~, ~)
        try
            syms x;
            fStr = get(edFuncion, 'String');
            fSym = str2sym(fStr);
            aVal = str2double(get(edA, 'String'));
            xVal = str2double(get(edX, 'String'));
            nVal = str2double(get(edN, 'String'));

            valorReal = double(subs(fSym, x, xVal));

            datosTabla = zeros(nVal + 1, 5);
            for orden = 0:nVal
                [~, aproxVal, errVal] = serieTaylor(fSym, aVal, xVal, orden);
                errRel = errorRelativo(valorReal, aproxVal);
                errPorc = errorPorcentual(valorReal, aproxVal);
                datosTabla(orden + 1, :) = [orden, aproxVal, errVal, errRel, errPorc];
            end
            set(tablaErrores, 'Data', datosTabla);

            % Resultado final
            [polinomio, aproxFinal, errFinal] = serieTaylor(fSym, aVal, xVal, nVal);
            msg = sprintf(['Valor real f(%.4f) = %.10f\n' ...
                           'Aproximacion (orden %d) = %.10f\n' ...
                           'Error absoluto = %.2e\n' ...
                           'Polinomio: %s'], ...
                           xVal, valorReal, nVal, aproxFinal, errFinal, char(polinomio));
            set(txtResultado, 'String', msg);

            % Grafica
            cla(axTaylor);
            hold(axTaylor, 'on');
            xPlot = linspace(aVal - 2, xVal + 2, 300);
            fNum = matlabFunction(fSym);
            plot(axTaylor, xPlot, fNum(xPlot), 'k-', 'LineWidth', 2, 'DisplayName', 'f(x)');

            colores = lines(nVal + 1);
            for orden = [1, floor(nVal/2), nVal]
                if orden < 1, continue; end
                [polK, ~, ~] = serieTaylor(fSym, aVal, xVal, orden);
                polNum = matlabFunction(polK);
                yPol = polNum(xPlot);
                plot(axTaylor, xPlot, yPol, '--', 'Color', colores(orden+1,:), ...
                    'LineWidth', 1.2, 'DisplayName', sprintf('Orden %d', orden));
            end

            plot(axTaylor, xVal, valorReal, 'ro', 'MarkerSize', 8, ...
                'MarkerFaceColor', 'r', 'DisplayName', 'Punto evaluado');
            legend(axTaylor, 'show', 'Location', 'best');
            xlabel(axTaylor, 'x');
            ylabel(axTaylor, 'y');
            title(axTaylor, 'Funcion original vs Aproximaciones de Taylor');
            grid(axTaylor, 'on');
            hold(axTaylor, 'off');

        catch ME
            errordlg(ME.message, 'Error en Taylor');
        end
    end
end

%% =========================================================================
%  PESTANA 2 - METODO GRAFICO
%  =========================================================================
function crearPestana2(tabGroup)
    tab2 = uitab(tabGroup, 'Title', 'P2: Metodo Grafico');

    % --- Panel de entrada ---
    panelIn = uipanel(tab2, 'Title', 'Parametros', ...
        'Units', 'normalized', 'Position', [0.01 0.82 0.98 0.17]);

    uicontrol(panelIn, 'Style', 'text', 'String', 'f(x) =', ...
        'Units', 'normalized', 'Position', [0.01 0.3 0.06 0.4], ...
        'HorizontalAlignment', 'right');
    edFuncion2 = uicontrol(panelIn, 'Style', 'edit', ...
        'String', 'x*(6^3 - 2*6*x.^2 + x.^3)', ...
        'Units', 'normalized', 'Position', [0.08 0.3 0.30 0.45]);

    uicontrol(panelIn, 'Style', 'text', 'String', 'a =', ...
        'Units', 'normalized', 'Position', [0.40 0.3 0.04 0.4], ...
        'HorizontalAlignment', 'right');
    edA2 = uicontrol(panelIn, 'Style', 'edit', 'String', '0', ...
        'Units', 'normalized', 'Position', [0.45 0.3 0.08 0.45]);

    uicontrol(panelIn, 'Style', 'text', 'String', 'b =', ...
        'Units', 'normalized', 'Position', [0.55 0.3 0.04 0.4], ...
        'HorizontalAlignment', 'right');
    edB2 = uicontrol(panelIn, 'Style', 'edit', 'String', '6', ...
        'Units', 'normalized', 'Position', [0.60 0.3 0.08 0.45]);

    uicontrol(panelIn, 'Style', 'pushbutton', 'String', 'Graficar', ...
        'Units', 'normalized', 'Position', [0.75 0.15 0.20 0.7], ...
        'FontWeight', 'bold', ...
        'Callback', @graficar);

    % --- Ejes de grafica ---
    axGraf = axes('Parent', tab2, 'Units', 'normalized', ...
        'Position', [0.08 0.08 0.88 0.70]);

    function graficar(~, ~)
        try
            syms x;
            fStr = get(edFuncion2, 'String');
            fSym = str2sym(fStr);
            aVal = str2double(get(edA2, 'String'));
            bVal = str2double(get(edB2, 'String'));

            dfSym = diff(fSym, x);
            d2fSym = diff(dfSym, x);

            fNum = matlabFunction(fSym, 'Vars', x);
            dfNum = matlabFunction(dfSym, 'Vars', x);
            d2fNum = matlabFunction(d2fSym, 'Vars', x);

            xPlot = linspace(aVal, bVal, 500);

            cla(axGraf);
            hold(axGraf, 'on');
            plot(axGraf, xPlot, fNum(xPlot), 'b-', 'LineWidth', 2, 'DisplayName', 'f(x)');
            plot(axGraf, xPlot, dfNum(xPlot), 'r--', 'LineWidth', 1.5, 'DisplayName', "f'(x)");
            plot(axGraf, xPlot, d2fNum(xPlot), 'g-.', 'LineWidth', 1.5, 'DisplayName', "f''(x)");
            yline(axGraf, 0, 'k-', 'LineWidth', 0.8, 'DisplayName', 'y = 0');
            legend(axGraf, 'show', 'Location', 'best');
            xlabel(axGraf, 'x');
            ylabel(axGraf, 'y');
            title(axGraf, 'Metodo Grafico: f(x), f''(x), f''''(x)');
            grid(axGraf, 'on');
            hold(axGraf, 'off');

        catch ME
            errordlg(ME.message, 'Error en Grafica');
        end
    end
end

%% =========================================================================
%  PESTANA 3 - METODOS CERRADOS
%  =========================================================================
function crearPestana3(tabGroup)
    tab3 = uitab(tabGroup, 'Title', 'P3: Metodos Cerrados');

    % --- Panel de entrada ---
    panelIn = uipanel(tab3, 'Title', 'Parametros', ...
        'Units', 'normalized', 'Position', [0.01 0.75 0.48 0.24]);

    uicontrol(panelIn, 'Style', 'text', 'String', 'f(x) =', ...
        'Units', 'normalized', 'Position', [0.02 0.75 0.12 0.18], ...
        'HorizontalAlignment', 'right');
    edF3 = uicontrol(panelIn, 'Style', 'edit', ...
        'String', 'x.^3 - 6*x.^2 + 11*x - 6', ...
        'Units', 'normalized', 'Position', [0.15 0.75 0.82 0.20]);

    uicontrol(panelIn, 'Style', 'text', 'String', 'a =', ...
        'Units', 'normalized', 'Position', [0.02 0.50 0.12 0.18], ...
        'HorizontalAlignment', 'right');
    edA3 = uicontrol(panelIn, 'Style', 'edit', 'String', '0', ...
        'Units', 'normalized', 'Position', [0.15 0.50 0.20 0.20]);

    uicontrol(panelIn, 'Style', 'text', 'String', 'b =', ...
        'Units', 'normalized', 'Position', [0.40 0.50 0.08 0.18], ...
        'HorizontalAlignment', 'right');
    edB3 = uicontrol(panelIn, 'Style', 'edit', 'String', '1.5', ...
        'Units', 'normalized', 'Position', [0.50 0.50 0.20 0.20]);

    uicontrol(panelIn, 'Style', 'text', 'String', 'Tol =', ...
        'Units', 'normalized', 'Position', [0.02 0.25 0.12 0.18], ...
        'HorizontalAlignment', 'right');
    edTol3 = uicontrol(panelIn, 'Style', 'edit', 'String', '1e-6', ...
        'Units', 'normalized', 'Position', [0.15 0.25 0.20 0.20]);

    uicontrol(panelIn, 'Style', 'text', 'String', 'MaxIter =', ...
        'Units', 'normalized', 'Position', [0.40 0.25 0.12 0.18], ...
        'HorizontalAlignment', 'right');
    edMaxIter3 = uicontrol(panelIn, 'Style', 'edit', 'String', '100', ...
        'Units', 'normalized', 'Position', [0.55 0.25 0.15 0.20]);

    uicontrol(panelIn, 'Style', 'text', 'String', 'Metodo:', ...
        'Units', 'normalized', 'Position', [0.02 0.02 0.12 0.18], ...
        'HorizontalAlignment', 'right');
    popMetodo3 = uicontrol(panelIn, 'Style', 'popupmenu', ...
        'String', {'Biseccion', 'Regla Falsa'}, ...
        'Units', 'normalized', 'Position', [0.15 0.02 0.30 0.20]);

    uicontrol(panelIn, 'Style', 'pushbutton', 'String', 'Calcular', ...
        'Units', 'normalized', 'Position', [0.55 0.02 0.40 0.22], ...
        'FontWeight', 'bold', ...
        'Callback', @calcularCerrado);

    % --- Panel resultado ---
    panelRes3 = uipanel(tab3, 'Title', 'Resultado', ...
        'Units', 'normalized', 'Position', [0.50 0.75 0.49 0.24]);
    txtRes3 = uicontrol(panelRes3, 'Style', 'text', 'String', '', ...
        'Units', 'normalized', 'Position', [0.02 0.02 0.96 0.94], ...
        'HorizontalAlignment', 'left', 'FontSize', 10);

    % --- Tabla iterativa ---
    tabla3 = uitable(tab3, 'Units', 'normalized', ...
        'Position', [0.01 0.02 0.48 0.71], ...
        'ColumnName', {'k', 'a', 'b', 'c', 'f(c)', 'Error'}, ...
        'ColumnWidth', {35, 80, 80, 80, 80, 80});

    % --- Grafica ---
    axCerrado = axes('Parent', tab3, 'Units', 'normalized', ...
        'Position', [0.57 0.08 0.40 0.62]);

    function calcularCerrado(~, ~)
        try
            fStr = get(edF3, 'String');
            f = str2func(['@(x) ' fStr]);
            aVal = str2double(get(edA3, 'String'));
            bVal = str2double(get(edB3, 'String'));
            tolVal = str2double(get(edTol3, 'String'));
            maxIterVal = str2double(get(edMaxIter3, 'String'));

            metodoIdx = get(popMetodo3, 'Value');

            if f(aVal) * f(bVal) > 0
                errordlg('f(a)*f(b) > 0: No hay cambio de signo en [a,b].', 'Error');
                return;
            end

            if metodoIdx == 1
                [raiz, tablaData, iter, conv] = biseccion(f, aVal, bVal, tolVal, maxIterVal);
                metodoNom = 'Biseccion';
            else
                [raiz, tablaData, iter, conv] = reglaFalsa(f, aVal, bVal, tolVal, maxIterVal);
                metodoNom = 'Regla Falsa';
            end

            set(tabla3, 'Data', tablaData);

            if conv
                convStr = 'SI';
            else
                convStr = 'NO';
            end
            msg = sprintf('Metodo: %s\nRaiz: %.10f\nIteraciones: %d\nConverge: %s', ...
                metodoNom, raiz, iter, convStr);
            set(txtRes3, 'String', msg);

            % Grafica
            cla(axCerrado);
            hold(axCerrado, 'on');
            xPlot = linspace(aVal, bVal, 300);
            plot(axCerrado, xPlot, f(xPlot), 'b-', 'LineWidth', 2);
            yline(axCerrado, 0, 'k-');
            if conv
                plot(axCerrado, raiz, f(raiz), 'ro', 'MarkerSize', 10, ...
                    'MarkerFaceColor', 'r', 'DisplayName', sprintf('Raiz = %.6f', raiz));
            end
            legend(axCerrado, 'show', 'Location', 'best');
            xlabel(axCerrado, 'x');
            ylabel(axCerrado, 'f(x)');
            title(axCerrado, sprintf('%s - Raiz encontrada', metodoNom));
            grid(axCerrado, 'on');
            hold(axCerrado, 'off');

        catch ME
            errordlg(ME.message, 'Error');
        end
    end
end

%% =========================================================================
%  PESTANA 4 - METODOS ABIERTOS
%  =========================================================================
function crearPestana4(tabGroup)
    tab4 = uitab(tabGroup, 'Title', 'P4: Metodos Abiertos');

    % --- Panel de entrada ---
    panelIn = uipanel(tab4, 'Title', 'Parametros', ...
        'Units', 'normalized', 'Position', [0.01 0.68 0.48 0.31]);

    uicontrol(panelIn, 'Style', 'text', 'String', 'f(x) =', ...
        'Units', 'normalized', 'Position', [0.02 0.85 0.12 0.10], ...
        'HorizontalAlignment', 'right');
    edF4 = uicontrol(panelIn, 'Style', 'edit', ...
        'String', '(x-2).^2.*(x+1)', ...
        'Units', 'normalized', 'Position', [0.15 0.85 0.82 0.12]);

    uicontrol(panelIn, 'Style', 'text', 'String', "f'(x) =", ...
        'Units', 'normalized', 'Position', [0.02 0.72 0.12 0.10], ...
        'HorizontalAlignment', 'right');
    edDf4 = uicontrol(panelIn, 'Style', 'edit', ...
        'String', '2*(x-2).*(x+1) + (x-2).^2', ...
        'Units', 'normalized', 'Position', [0.15 0.72 0.82 0.12]);

    uicontrol(panelIn, 'Style', 'text', 'String', 'g(x) =', ...
        'Units', 'normalized', 'Position', [0.02 0.59 0.12 0.10], ...
        'HorizontalAlignment', 'right');
    edG4 = uicontrol(panelIn, 'Style', 'edit', ...
        'String', '(2*x.^2 + 4)./(3*x.^2 - 1)', ...
        'Units', 'normalized', 'Position', [0.15 0.59 0.82 0.12]);

    uicontrol(panelIn, 'Style', 'text', 'String', 'x0 =', ...
        'Units', 'normalized', 'Position', [0.02 0.44 0.12 0.10], ...
        'HorizontalAlignment', 'right');
    edX0_4 = uicontrol(panelIn, 'Style', 'edit', 'String', '3', ...
        'Units', 'normalized', 'Position', [0.15 0.44 0.17 0.12]);

    uicontrol(panelIn, 'Style', 'text', 'String', 'x1 =', ...
        'Units', 'normalized', 'Position', [0.35 0.44 0.07 0.10], ...
        'HorizontalAlignment', 'right');
    edX1_4 = uicontrol(panelIn, 'Style', 'edit', 'String', '4', ...
        'Units', 'normalized', 'Position', [0.43 0.44 0.17 0.12]);

    uicontrol(panelIn, 'Style', 'text', 'String', 'm =', ...
        'Units', 'normalized', 'Position', [0.63 0.44 0.07 0.10], ...
        'HorizontalAlignment', 'right');
    edM4 = uicontrol(panelIn, 'Style', 'edit', 'String', '2', ...
        'Units', 'normalized', 'Position', [0.71 0.44 0.12 0.12]);

    uicontrol(panelIn, 'Style', 'text', 'String', 'Tol =', ...
        'Units', 'normalized', 'Position', [0.02 0.28 0.12 0.10], ...
        'HorizontalAlignment', 'right');
    edTol4 = uicontrol(panelIn, 'Style', 'edit', 'String', '1e-10', ...
        'Units', 'normalized', 'Position', [0.15 0.28 0.17 0.12]);

    uicontrol(panelIn, 'Style', 'text', 'String', 'MaxIter =', ...
        'Units', 'normalized', 'Position', [0.35 0.28 0.12 0.10], ...
        'HorizontalAlignment', 'right');
    edMaxIter4 = uicontrol(panelIn, 'Style', 'edit', 'String', '100', ...
        'Units', 'normalized', 'Position', [0.48 0.28 0.12 0.12]);

    uicontrol(panelIn, 'Style', 'text', 'String', 'Metodo:', ...
        'Units', 'normalized', 'Position', [0.02 0.08 0.12 0.12], ...
        'HorizontalAlignment', 'right');
    popMetodo4 = uicontrol(panelIn, 'Style', 'popupmenu', ...
        'String', {'Newton-Raphson', 'Secante', 'Punto Fijo', 'Newton Modificado'}, ...
        'Units', 'normalized', 'Position', [0.15 0.08 0.35 0.14]);

    uicontrol(panelIn, 'Style', 'pushbutton', 'String', 'Calcular', ...
        'Units', 'normalized', 'Position', [0.55 0.04 0.40 0.18], ...
        'FontWeight', 'bold', ...
        'Callback', @calcularAbierto);

    % --- Panel resultado ---
    panelRes4 = uipanel(tab4, 'Title', 'Resultado', ...
        'Units', 'normalized', 'Position', [0.50 0.68 0.49 0.31]);
    txtRes4 = uicontrol(panelRes4, 'Style', 'text', 'String', '', ...
        'Units', 'normalized', 'Position', [0.02 0.02 0.96 0.94], ...
        'HorizontalAlignment', 'left', 'FontSize', 10);

    % --- Tabla iterativa ---
    tabla4 = uitable(tab4, 'Units', 'normalized', ...
        'Position', [0.01 0.02 0.48 0.64], ...
        'ColumnName', {'k', 'x_k', 'f(x_k)/g(x_k)', 'Error x', 'Error f'}, ...
        'ColumnWidth', {35, 90, 90, 90, 90});

    % --- Grafica ---
    axAbierto = axes('Parent', tab4, 'Units', 'normalized', ...
        'Position', [0.57 0.08 0.40 0.55]);

    function calcularAbierto(~, ~)
        try
            metodoIdx = get(popMetodo4, 'Value');

            x0Val = str2double(get(edX0_4, 'String'));
            tolVal = str2double(get(edTol4, 'String'));
            maxIterVal = str2double(get(edMaxIter4, 'String'));

            switch metodoIdx
                case 1 % Newton-Raphson
                    fStr = get(edF4, 'String');
                    dfStr = get(edDf4, 'String');
                    f = str2func(['@(x) ' fStr]);
                    df = str2func(['@(x) ' dfStr]);

                    [raiz, tablaData, iter, conv] = newton(f, df, x0Val, tolVal, maxIterVal);
                    metodoNom = 'Newton-Raphson';
                    colNames = {'k', 'x_k', 'f(x_k)', 'Error x', 'Error f'};
                    fPlot = f;

                case 2 % Secante
                    fStr = get(edF4, 'String');
                    f = str2func(['@(x) ' fStr]);
                    x1Val = str2double(get(edX1_4, 'String'));

                    [raiz, tablaData, iter, conv] = secante(f, x0Val, x1Val, tolVal, maxIterVal);
                    metodoNom = 'Secante';
                    colNames = {'k', 'x_k', 'f(x_k)', 'Error x', 'Error f'};
                    fPlot = f;

                case 3 % Punto Fijo
                    gStr = get(edG4, 'String');
                    g = str2func(['@(x) ' gStr]);

                    [raiz, tablaData, iter, conv] = puntoFijo(g, x0Val, tolVal, maxIterVal);
                    metodoNom = 'Punto Fijo';
                    colNames = {'k', 'x_k', 'g(x_k)', 'Error'};
                    fPlot = [];

                case 4 % Newton Modificado
                    fStr = get(edF4, 'String');
                    dfStr = get(edDf4, 'String');
                    f = str2func(['@(x) ' fStr]);
                    df = str2func(['@(x) ' dfStr]);
                    mVal = str2double(get(edM4, 'String'));

                    [raiz, tablaData, iter, conv] = newtonMultiple(f, df, x0Val, mVal, tolVal, maxIterVal);
                    metodoNom = sprintf('Newton Modificado (m=%d)', mVal);
                    colNames = {'k', 'x_k', 'f(x_k)', 'Error x', 'Error f'};
                    fPlot = f;
            end

            set(tabla4, 'ColumnName', colNames);
            set(tabla4, 'Data', tablaData);

            if conv
                convStr = 'SI';
            else
                convStr = 'NO';
            end
            msg = sprintf('Metodo: %s\nRaiz: %.10f\nIteraciones: %d\nConverge: %s', ...
                metodoNom, raiz, iter, convStr);
            set(txtRes4, 'String', msg);

            % Grafica
            cla(axAbierto);
            hold(axAbierto, 'on');

            if metodoIdx == 3
                % Para Punto Fijo: graficar g(x) y y=x
                gStr = get(edG4, 'String');
                gFunc = str2func(['@(x) ' gStr]);
                xMin = min(tablaData(:, 2)) - 1;
                xMax = max(tablaData(:, 2)) + 1;
                xPlot = linspace(xMin, xMax, 300);
                plot(axAbierto, xPlot, gFunc(xPlot), 'b-', 'LineWidth', 2, 'DisplayName', 'g(x)');
                plot(axAbierto, xPlot, xPlot, 'k--', 'LineWidth', 1, 'DisplayName', 'y = x');
                if conv
                    plot(axAbierto, raiz, raiz, 'ro', 'MarkerSize', 10, ...
                        'MarkerFaceColor', 'r', 'DisplayName', sprintf('PF = %.6f', raiz));
                end
                title(axAbierto, 'Punto Fijo: g(x) vs y=x');
            else
                % Para los demas: graficar f(x)
                xArr = tablaData(:, 2);
                xMin = min(xArr) - 1;
                xMax = max(xArr) + 1;
                xPlot = linspace(xMin, xMax, 300);
                plot(axAbierto, xPlot, fPlot(xPlot), 'b-', 'LineWidth', 2, 'DisplayName', 'f(x)');
                yline(axAbierto, 0, 'k-');
                if conv
                    plot(axAbierto, raiz, fPlot(raiz), 'ro', 'MarkerSize', 10, ...
                        'MarkerFaceColor', 'r', 'DisplayName', sprintf('Raiz = %.6f', raiz));
                end
                title(axAbierto, sprintf('%s - Raiz encontrada', metodoNom));
            end

            legend(axAbierto, 'show', 'Location', 'best');
            xlabel(axAbierto, 'x');
            ylabel(axAbierto, 'y');
            grid(axAbierto, 'on');
            hold(axAbierto, 'off');

        catch ME
            errordlg(ME.message, 'Error');
        end
    end
end
