import java.util.Scanner;

class MaiorMenor{
public static Scanner ler = new Scanner(System.in);

	public static void main (String args[]){
	int tam;

	System.out.println("Digite tamanho do array desejado");	
	tam = ler.nextInt();

	int array[] = new int[tam]; //tem que inicializar um array assim pq em java um array é um objeto!
	int maior = array[0];
	int menor = Integer.MAX_VALUE;

	System.out.println("Digite o array desejado");
	
	for (int i=0; i<tam; i++){
		array[i] = ler.nextInt();
	}

	for (int i=0; i<tam; i++){
		if (array[i] >= maior){
			maior = array[i];

		}
		if (array[i] <= menor){
			menor = array[i];

		}
	}
	System.out.println("O maior numero do array e:" + maior);
	System.out.println("O menor numero do array e:" + menor);
	
	


}


}