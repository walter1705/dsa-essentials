//Quiz de 1.1: Ruleta


public class Ruleta {
  public static void main(String[] args) {
    int[][] matriz = {
        { 1, 2, 3 },
        { 4, 0, 6 },
        { 7, 8, 9 }
    };
    System.out.println("Ruleta original:");
    imprimirMatriz(matriz);
  

    String[][] matriz1 = {
        {"N", "J", "K"},
        {"I", "Z", "Y"},
        {"U", "E", "R"}
    };
 
    int[][] matriz2 = {
        {1, 2, 3},
        {4, 5, 6}
    };

    System.out.println("matriz1 girada 2 veces:");
    imprimirMatriz(girarRuleta(matriz1, 2));
    System.out.println("matriz2 girada -1 vez:");
    imprimirMatriz(girarRuleta(matriz2, -1));
  }

  public static String[][] girarRuleta(String[][] matriz, int numGiros) {
    if (numGiros == 0) return matriz;
    numGiros -= 1;
    matriz = rotarMatriz(matriz);
    return girarRuleta(matriz, numGiros % 4);
  }

  public static String[][] rotarMatriz(String[][] matriz) {
    int numFilas = matriz.length;
    int numColumnas = matriz[0].length;
    String[][] nuevaMatriz = new String[numColumnas][numFilas];
    for (int j = 0; j < numColumnas; j++) {
      for (int i = numFilas - 1, k = 0; i >= 0; i--, k++) {
        nuevaMatriz[j][k] = matriz[i][j];
      }
    }
    
    return nuevaMatriz;
  }

  

  public static void imprimirMatriz(String[][] matriz) {
    for (int i = 0; i < matriz.length; i++) {
      for (int j = 0; j < matriz[i].length; j++) {
        System.out.print(matriz[i][j]);
      }
      System.out.println();
    }
  }

  public static int[][] girarRuleta(int[][] matriz, int numGiros) {
    if (numGiros == 0) return matriz;
    numGiros -= 1;
    matriz = rotarMatriz(matriz);
    return girarRuleta(matriz, numGiros % 4);
  }

  public static int[][] rotarMatriz(int[][] matriz) {
    int numFilas = matriz.length;
    int numColumnas = matriz[0].length;
    int[][] nuevaMatriz = new int[numColumnas][numFilas];
    for (int j = 0; j < numColumnas; j++) {
      for (int i = numFilas - 1, k = 0; i >= 0; i--, k++) {
        nuevaMatriz[j][k] = matriz[i][j];
      }
    }
    return nuevaMatriz;
  }

  public static void imprimirMatriz(int[][] matriz) {
    for (int i = 0; i < matriz.length; i++) {
      for (int j = 0; j < matriz[i].length; j++) {
        System.out.print(matriz[i][j]);
      }
      System.out.println();
    }
  }

}
