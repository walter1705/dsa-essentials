
//Quiz de 1.1: Ruleta
import java.util.Scanner;

public class Ruleta {
  public static void main(String[] args) {
    int[][] matriz = {
        { 1, 2, 3 },
        { 4, 0, 6 },
        { 7, 8, 9 }
    };
    System.out.println("Ruleta original:");
    imprimirMatriz(matriz);

    Scanner sc = new Scanner(System.in);
    System.out.print("¿Cuántas veces desea girar la ruleta?: ");
    int giros = sc.nextInt();

    imprimirMatriz(girar(matriz, giros));
  }

  public static int[][] girar(int[][] mtr, int n) {

    for (int i = 0; i < n; i++) {
      mtr = trans(mtr);
    }
    return mtr;

  }

  private static int[][] trans(int[][] mtr) {
    int n = mtr.length;
    if (n < 2)
      return mtr;

    int temp = mtr[0][0];

    for (int j = 0; j < n - 1; j++)
      mtr[0][j] = mtr[0][j + 1];

    for (int i = 0; i < n - 1; i++)
      mtr[i][n - 1] = mtr[i + 1][n - 1];

    for (int j = n - 1; j > 0; j--)
      mtr[n - 1][j] = mtr[n - 1][j - 1];

    for (int i = n - 1; i > 1; i--)
      mtr[i][0] = mtr[i - 1][0];

    mtr[1][0] = temp;

    return mtr;
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
