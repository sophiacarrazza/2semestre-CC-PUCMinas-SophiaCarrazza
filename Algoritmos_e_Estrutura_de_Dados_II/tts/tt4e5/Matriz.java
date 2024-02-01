import java.util.Scanner;

class Matriz{

	public static Scanner ler = new Scanner(System.in);
	public static void main (String args[]){
		System.out.println("Digite o n de linhas e colunas da matriz:");
		int lin, col;
		lin = ler.nextInt();
		col = ler.nextInt();
		int matriz[][] = new int[lin][col];

		for (int i=0; i<lin; i++){
			for(int j=0; j<col; j++){
			System.out.println("Digite o elemento da linha " + (i+1) + " e da coluna " + (j+1));
				matriz [i][j] = ler.nextInt();			
			}
		}
		System.out.println("A matriz digitada foi");
		for (int i=0; i<lin; i++){
			for(int j=0; j<col; j++){
			System.out.print(" " + matriz [i][j]);
						
			}
			System.out.print("\n");
		}}}