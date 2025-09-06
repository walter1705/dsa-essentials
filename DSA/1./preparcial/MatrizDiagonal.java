package preparcial;

public class MatrizDiagonal {

	public static void main(String[] args) {
		int[][] matriz = {
			{1, 2, 3},
			{4, 5, 6},
			{7, 8, 9}
		};

		int[] diagonal = diagonalInversa(matriz, new int[matriz.length], 0);
		for (int val : diagonal) {
			System.out.print(val + " ");
		}
	}

	public static int promedioMatriz(int[][] matriz, int promedio, int i, int j) {
		if(i >= matriz.length) return promedio / matriz.length;
		promedio+= matriz[i][j];
		return promedioMatriz(matriz, promedio, i+1, j+1);
	}

	public static int[] diagonalInversa(int[][] matriz, int[] nuevaLista, int i) {
		if (i >= matriz.length ) return nuevaLista;
		int now = matriz.length - 1 - i;
		nuevaLista[i] = matriz[i][now];
		return diagonalInversa(matriz, nuevaLista, i+1);
	} 

	//TODO transpuesta
}
