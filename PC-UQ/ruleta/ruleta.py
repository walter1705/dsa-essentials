#Matrices de los ejemplos

matriz1=[ ["N","J","K"],
        ["I", "Z", "Y"],
        ["U", "E", "R"]
]

matriz2=[[1, 2, 3], 
         [4, 5, 6]] 

def girarRuleta(matriz,numgiros):
    if(numgiros==0): return matriz
    else:  numgiros -= 1
    
    matriz = rotarMatriz(matriz,[],len(matriz),len(matriz[0]),0,0)

    return girarRuleta(matriz, numgiros % 4)


def rotarMatriz(matriz, nuevaMatriz, numfilas, numcolumnas, i, j):
    if j >= len(matriz[0]): return nuevaMatriz

    nuevaFila = []

    for k in range(len(matriz) - 1, -1, -1):
        nuevaFila.append(matriz[k][j])

    nuevaMatriz.append(nuevaFila)

    return rotarMatriz(matriz, nuevaMatriz, numfilas, numcolumnas, i, j + 1)

def imprimirMatriz(matriz,i,j):
    if(i==len(matriz)): return
    imprimirFila(matriz,i,j)
    imprimirMatriz(matriz, i+1, j)

def imprimirFila(matriz,i,j):
    if(j==len(matriz[i])): 
        return
    for k in matriz[i]:
        print(k, end="")
    print()

#Casos de prueba

imprimirMatriz(girarRuleta(matriz1, 2), 0 ,0) 

imprimirMatriz(girarRuleta(matriz2, -1), 0 ,0)

